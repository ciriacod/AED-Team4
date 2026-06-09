package Semana_9.btree;

public class Btree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    private int size;

    public Btree(int orden) {
        this.orden = orden;
        this.root = null;
        this.size = 0;
    }
    // ================================== Actividad 3 ,4=========================================================
    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E c1) {
    	
        up = false;
        E mediana;
        BNode<E> pnew;

        mediana = push(this.root, c1);

        // Solo se ejecuta cuando se hace un split en la raiz
        if (up) {
            pnew = new BNode<>(this.orden);
            pnew.count = 1;

            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }

        if (mediana != null) {
            size++;
        }
    }

    // Retorna la mediana
    private E push(BNode<E> current, E c1) {
        int pos[] = new int[1];
        E mediana;

        // Cuando se llega a una hoja
        if (current == null) {
            up = true;
            nDes = null;
            return c1;
        } else {
            boolean f1;
            f1 = current.searchNode(c1, pos);

            // Verifica si el elemento ya se encuentra en el nodo
            if (f1) {
                System.out.println("ITEM DUPLICADO\n");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), c1);

            // Si la bandera se levanto se realiza la insercion
            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }

            return mediana;
        }
    }

    // Metodo para poner una clave dentro de un nodo
    private void putNode(BNode<E> current, E c1, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }

        // Asigna los elementos al espacio libre
        current.keys.set(k, c1);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    // Metodo que divide un nodo lleno
    private E dividedNode(BNode<E> current, E c1, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;

        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;

        nDes = new BNode<E>(this.orden);

        // Copia las claves desde la mediana hacia adelante al nuevo nodo
        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= this.orden / 2) {
            putNode(current, c1, rd, k);
        } else {
            putNode(nDes, c1, rd, k - posMdna);
        }

        E median = current.keys.get(current.count - 1);

        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }

    public int size() {
        return size;
    }
    // ================================== Ejercicio 1 =========================================================
    public boolean search(E key) {
        return search(this.root, key);
    }

    private boolean search(BNode<E> current, E key) {
        if (current == null) {
            return false;
        }

        int pos[] = new int[1];

        if (current.searchNode(key, pos)) {
            System.out.println(key + " se encuentra en el nodo " + current.idNode + " en la posicion " + pos[0]);
            return true;
        }

        return search(current.childs.get(pos[0]), key);
    }
    //========================================Ejercicio04==================================================
    /*Buscar un libro por ISBN mostrando el camino recorrido en el Árbol B*/
    public void searchPath(E key) {
        searchPath(this.root, key);
    }

    private void searchPath(BNode<E> current, E key) {
        if (current == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }

        System.out.println("Visitando nodo: " + current);

        int pos[] = new int[1];

        if (current.searchNode(key, pos)) {
            System.out.println("Encontrado: " + current.keys.get(pos[0]));
        } else {
            searchPath(current.childs.get(pos[0]), key);
        }
    }

    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(BNode<E> current) {
        if (current == null) {
            return;
        }

        int i;

        for (i = 0; i < current.count; i++) {
            inOrder(current.childs.get(i));
            System.out.println(current.keys.get(i));
        }

        inOrder(current.childs.get(i));
    }

    public int height() {
        int altura = 0;
        BNode<E> aux = this.root;

        while (aux != null) {
            altura++;
            aux = aux.childs.get(0);
        }

        return altura;
    }
    // ================================== Ejercicio 2 =========================================================
    public void searchRange(E min, E max) {
        if (min == null || max == null || min.compareTo(max) > 0) {
            System.out.println("Rango invalido: El valor minimo no puede ser mayor que el maximo.");
            return;
        }

        System.out.print("Claves en el rango [" + min + ", " + max + "]: ");

        boolean[] foundAny = new boolean[1];// Bandera por referencia para saber si hubo resultados

        searchRange(this.root, min, max, foundAny);

        if (!foundAny[0]) {
            System.out.print("Rango inexistente en el arbol.");
        }

        System.out.println();// Salto de línea final
    }

    private void searchRange(BNode<E> current, E min, E max, boolean[] foundAny) {
        if (current == null) {
            return;
        }

        int i = 0;

        // Se saltan claves menores que min descartamos los hijos correspondientes que sabemos que contienen valores menores
        while (i < current.count && current.keys.get(i).compareTo(min) < 0) {
            i++;
        }

        // Recorrido de claves dentro del rango
        while (i < current.count) {
            E currentKey = current.keys.get(i);

            // Si la clave supera el maximo, se corta la busqueda por la derecha significa que ni esta clave 
            // ni ninguna de las claves o hijos que están a su derecha están en el rango
            if (currentKey.compareTo(max) > 0) {
                searchRange(current.childs.get(i), min, max, foundAny);
                return;
            }
            // Visitamos el hijo izquierdo de la clave actual
            searchRange(current.childs.get(i), min, max, foundAny);

            // Procesamos (imprimimos) la clave actual ya que está en el rango [min, max]
            System.out.print(currentKey + " ");
            foundAny[0] = true;

            i++;
        }// 3. No olvidar visitar el último hijo (el que está a la derecha de la última clave procesada)

        searchRange(current.childs.get(i), min, max, foundAny);
    }

    // ================================== Ejercicio 3 =========================================================
    
    public void remove(E key) {
        if (delete(key)) {
            size--;
            System.out.println("Elemento eliminado.");
        } else {
            System.out.println("Elemento no encontrado.");
        }
    }

    public boolean delete(E key) {
        if (isEmpty()) {
            return false;
        }

        boolean result = delete(this.root, key);

        // Si la raíz quedó completamente vacía tras una fusión, el árbol reduce su altura
        if (this.root != null && this.root.count == 0) {
            if (this.root.childs.get(0) == null) {
                this.root = null; // El árbol se quedó vacío
            } else {
                this.root = this.root.childs.get(0); // El primer hijo pasa a ser la nueva raíz
            }
        }

        return result;
    }

    // Método recursivo interno para buscar y eliminar la llave
    private boolean delete(BNode<E> current, E key) {
        int pos[] = new int[1];
        boolean found = current.searchNode(key, pos);

        if (found) {
            // CASO 1: La llave está en un nodo HOJA
            if (current.childs.get(0) == null) {
                removeFromLeaf(current, pos[0]);
            } 
            // CASO 2: La llave está en un nodo INTERNO (Tiene hijos)
            else {
                removeFromNonLeaf(current, pos[0]);
            }
            return true;
        } else {
            // Si es una hoja y no se encontró, la llave no existe en el árbol
            if (current.childs.get(0) == null) {
                return false;
            }

            // Descendemos recursivamente por el hijo adecuado
            boolean deleted = delete(current.childs.get(pos[0]), key);

            // AL REGRESAR DE LA RECURSIÓN: Verificamos si el hijo entró en subocupación (Underflow)
            int minKeys = (this.orden - 1) / 2;
            if (deleted && current.childs.get(pos[0]).count < minKeys) {
                fixUnderflow(current, pos[0]);
            }

            return deleted;
        }
    }

    // Elimina desplazando los elementos a la izquierda en una hoja
    private void removeFromLeaf(BNode<E> current, int idx) {
        for (int i = idx; i < current.count - 1; i++) {
            current.keys.set(i, current.keys.get(i + 1));
        }
        current.keys.set(current.count - 1, null);
        current.count--;
    }

    // Elimina de un nodo interno buscando su sucesor in-order
    private void removeFromNonLeaf(BNode<E> current, int idx) {
        BNode<E> successorNode = current.childs.get(idx + 1);
        
        // Viajamos al extremo izquierdo del subárbol derecho (el más pequeño de los mayores)
        while (successorNode.childs.get(0) != null) {
            successorNode = successorNode.childs.get(0);
        }

        E successorKey = successorNode.keys.get(0);
        current.keys.set(idx, successorKey); // Reemplazamos la llave por su sucesor

        // Borramos el sucesor de la hoja donde se encontraba originalmente
        delete(current.childs.get(idx + 1), successorKey);

        // Verificamos si el hijo derecho quedó en subocupación tras quitar el sucesor
        int minKeys = (this.orden - 1) / 2;
        if (current.childs.get(idx + 1).count < minKeys) {
            fixUnderflow(current, idx + 1);
        }
    }

    // Resuelve la subocupación mediante préstamo o fusión
    private void fixUnderflow(BNode<E> parent, int idx) {
        int minKeys = (this.orden - 1) / 2;

        // Opción A: Intentar prestar del hermano izquierdo
        if (idx > 0 && parent.childs.get(idx - 1).count > minKeys) {
            borrowFromLeft(parent, idx);
        }
        // Opción B: Intentar prestar del hermano derecho
        else if (idx < parent.count && parent.childs.get(idx + 1).count > minKeys) {
            borrowFromRight(parent, idx);
        }
        // Opción C: No se puede prestar, toca fusionar (Merge)
        else {
            if (idx > 0) {
                mergeNodes(parent, idx - 1); // Fusionar con el hermano izquierdo
            } else {
                mergeNodes(parent, idx);     // Fusionar con el hermano derecho
            }
        }
    }

    // Redistribución: Trae una llave del hermano izquierdo a través del padre
    private void borrowFromLeft(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx - 1);

        // Desplazamos todo en el hijo para hacer espacio en la posición 0
        for (int i = child.count - 1; i >= 0; i--) {
            child.keys.set(i + 1, child.keys.get(i));
            child.childs.set(i + 1, child.childs.get(i));
        }
        child.childs.set(child.count + 1, child.childs.get(child.count));

        // Bajamos la llave del padre al hijo en la posición 0
        child.keys.set(0, parent.keys.get(idx - 1));
        child.childs.set(0, sibling.childs.get(sibling.count));

        // Subimos la última llave del hermano al padre
        parent.keys.set(idx - 1, sibling.keys.get(sibling.count - 1));

        sibling.keys.set(sibling.count - 1, null);
        sibling.childs.set(sibling.count, null);

        child.count++;
        sibling.count--;
    }

    // Redistribución: Trae una llave del hermano derecho a través del padre
    private void borrowFromRight(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx + 1);

        // Bajamos la llave del padre al final del hijo
        child.keys.set(child.count, parent.keys.get(idx));
        child.childs.set(child.count + 1, sibling.childs.get(0));

        // Subimos la primera llave del hermano derecho al padre
        parent.keys.set(idx, sibling.keys.get(0));

        // Desplazamos los elementos del hermano derecho a la izquierda
        for (int i = 0; i < sibling.count - 1; i++) {
            sibling.keys.set(i, sibling.keys.get(i + 1));
            sibling.childs.set(i, sibling.childs.get(i + 1));
        }
        sibling.childs.set(sibling.count - 1, sibling.childs.get(sibling.count));
        
        sibling.keys.set(sibling.count - 1, null);
        sibling.childs.set(sibling.count, null);

        child.count++;
        sibling.count--;
    }

    // Fusión (Merge): Une el hijo 'idx' con el hijo 'idx+1' y la llave intermedia del padre
    private void mergeNodes(BNode<E> parent, int idx) {
        BNode<E> left = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        // Bajamos la llave intermedia del padre al nodo izquierdo
        left.keys.set(left.count, parent.keys.get(idx));
        left.count++;

        // Copiamos todas las llaves e hijos del nodo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count, right.childs.get(i));
            left.count++;
        }
        left.childs.set(left.count, right.childs.get(right.count));

        // Desplazamos las llaves e hijos del padre para rellenar el vacío que dejó la llave bajada
        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }
//¨********************************************************************************************FIN ELIMINACION******************

    @Override
    public String toString() {
        String s = "";

        if (isEmpty())
            return "BTree is empty...";
        else
            s = writeTree(this.root);
        
        return s;
    }

    private String writeTree(BNode<E> current) {
        if (current == null) {
            return "";
        }

        String s = current.toString() + "\n";

        for (int i = 0; i <= current.count; i++) {
            s += writeTree(current.childs.get(i));
        }

        return s;
    }
}
