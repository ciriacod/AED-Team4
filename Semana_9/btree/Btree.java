package Semana_9.btree;
     package Ejercicio04;

public class BTree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;

    private int size;

    public BTree(int orden) {
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

        // Recorre los elementos desde la ultima posicion hasta k
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

    public void remove(E key) {
        if (delete(key)) {
            size--;
            System.out.println("Elemento eliminado.");
        } else {
            System.out.println("Elemento no encontrado.");
        }
    }
    // ================================== Ejercicio 3 =========================================================
    public boolean delete(E key) {
        if (isEmpty()) {
            return false;
        }

        boolean result = delete(this.root, key);

        // Si la raiz queda vacia, el arbol reduce su altura
        if (this.root != null && this.root.count == 0) {
            if (this.root.childs.get(0) == null) {
                this.root = null;
            } else {
                this.root = this.root.childs.get(0);
            }
        }

        return result;
    }

    private boolean delete(BNode<E> node, E key) {
        int pos[] = new int[1];
        boolean found = node.searchNode(key, pos);

        if (found) {
            // Caso 1: el nodo es hoja
            if (node.childs.get(pos[0]) == null) {
                removeKey(node, pos[0]);
                return true;
            } else {
                // Caso 2: la clave esta en un nodo interno
                E pred = getPredecessor(node, pos[0]);
                node.keys.set(pos[0], pred);

                boolean isDelete = delete(node.childs.get(pos[0]), pred);

                if (node.childs.get(pos[0]).count < (orden - 1) / 2) {
                    fix(node, pos[0]);
                }

                return isDelete;
            }
        } else {
            // Caso 3: la clave no esta en este nodo
            if (node.childs.get(pos[0]) == null) {
                return false;
            } else {
                boolean isDelete = delete(node.childs.get(pos[0]), key);

                if (node.childs.get(pos[0]).count < (orden - 1) / 2) {
                    fix(node, pos[0]);
                }

                return isDelete;
            }
        }
    }

    private void removeKey(BNode<E> node, int index) {
        // Desplaza claves e hijos hacia la izquierda
        for (int i = index; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
            node.childs.set(i + 1, node.childs.get(i + 2));
        }

        node.keys.set(node.count - 1, null);
        node.childs.set(node.count, null);
        node.count--;
    }

    private E getPredecessor(BNode<E> node, int index) {
        // El predecesor es la clave mas a la derecha del subarbol izquierdo
        BNode<E> current = node.childs.get(index);

        while (current.childs.get(current.count) != null) {
            current = current.childs.get(current.count);
        }

        return current.keys.get(current.count - 1);
    }

    private void fix(BNode<E> parent, int index) {
        // Intenta pedir prestado del hermano izquierdo
        if (index > 0 && parent.childs.get(index - 1).count > (orden - 1) / 2) {
            borrowFromLeft(parent, index);
        }
        // Intenta pedir prestado del hermano derecho
        else if (index < parent.count && parent.childs.get(index + 1).count > (orden - 1) / 2) {
            borrowFromRight(parent, index);
        }
        // Si no se puede pedir prestado, se fusiona
        else {
            if (index > 0) {
                merge(parent, index - 1);
            } else {
                merge(parent, index);
            }
        }
    }

    private void borrowFromLeft(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index - 1);
        BNode<E> current = parent.childs.get(index);

        // Desplaza elementos en current para hacer espacio al inicio
        for (int i = current.count - 1; i >= 0; i--) {
            current.keys.set(i + 1, current.keys.get(i));
        }

        for (int i = current.count; i >= 0; i--) {
            current.childs.set(i + 1, current.childs.get(i));
        }

        // Baja la clave del padre al hijo actual
        current.keys.set(0, parent.keys.get(index - 1));
        current.childs.set(0, left.childs.get(left.count));

        // Sube la ultima clave del hermano izquierdo al padre
        parent.keys.set(index - 1, left.keys.get(left.count - 1));

        left.keys.set(left.count - 1, null);
        left.childs.set(left.count, null);

        current.count++;
        left.count--;
    }

    private void borrowFromRight(BNode<E> parent, int index) {
        BNode<E> current = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);

        // Baja la clave del padre al final del nodo actual
        current.keys.set(current.count, parent.keys.get(index));
        current.childs.set(current.count + 1, right.childs.get(0));

        // Sube la primera clave del hermano derecho al padre
        parent.keys.set(index, right.keys.get(0));

        // Desplaza los elementos del hermano derecho a la izquierda
        for (int i = 0; i < right.count - 1; i++) {
            right.keys.set(i, right.keys.get(i + 1));
        }

        for (int i = 0; i < right.count; i++) {
            right.childs.set(i, right.childs.get(i + 1));
        }

        right.keys.set(right.count - 1, null);
        right.childs.set(right.count, null);

        current.count++;
        right.count--;
    }

    private void merge(BNode<E> parent, int index) {
        BNode<E> left = parent.childs.get(index);
        BNode<E> right = parent.childs.get(index + 1);

        // Baja la clave del padre al nodo izquierdo
        left.keys.set(left.count, parent.keys.get(index));
        left.count++;

        // Copia claves e hijos del nodo derecho al izquierdo
        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count + i, right.keys.get(i));
        }

        for (int i = 0; i <= right.count; i++) {
            left.childs.set(left.count + i, right.childs.get(i));
        }

        left.count += right.count;

        // Desplaza claves e hijos del padre
        for (int i = index; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }

        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "BTree is empty...";
        }

        return writeTree(this.root);
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
