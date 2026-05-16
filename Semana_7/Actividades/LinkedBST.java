package Semana_7.Actividades;

import Importar.Estructuras.LinkedQueue;
import Importar.Exceptions.ExceptionIsEmpty;
import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNotFound;
import Importar.IEstructuras.*;

public class LinkedBST <E extends Comparable<E>> implements BinarySearchTree<E> {
    protected class Node<E> {
        public E data;
        public Node<E> left, right;

        public Node(E data) { this(data, null, null); }
        public Node(E data, Node<E> left, Node<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    protected Node<E> root;

    public LinkedBST() { this.root = null; }

    @Override
    public boolean isEmpty() { return this.root == null; }

    // ACTIVIDAD 6: Inserción
    public void insert(E x) throws ItemDuplicated {
        this.root = insertRec(x, this.root);
    }

    protected Node<E> insertRec(E x, Node<E> actual) throws ItemDuplicated {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(x); // Caso base: crea el nodo si es null
        } else {
            int resC = actual.data.compareTo(x); // Comparación de datos
            if (resC == 0) {
                throw new ItemDuplicated("x " + x + " esta duplicado"); // Error si ya existe
            }
            if (resC < 0) {
                res.right = insertRec(x, actual.right); // Menor que x: subárbol derecho
            } else {
                res.left = insertRec(x, actual.left); // Mayor que x: subárbol izquierdo
            }
        }
        return res;
    }

    // ACTIVIDAD 6: Búsqueda 
    @Override
    public E search(E x) throws ItemNotFound {
    Node<E> res = searchRec(x, this.root); // Inicia búsqueda desde la raíz
    if (res == null)
        throw new ItemNotFound(x + " no se encuentra");
    return res.data;
    }

    protected Node<E> searchRec(E x, Node<E> actual) {
        if (actual == null) return null;
        int cmp = actual.data.compareTo(x);
        if (cmp == 0) return actual; // Elemento encontrado 
        return (cmp < 0) ? searchRec(x, actual.right) : searchRec(x, actual.left);
    }

    // ACTIVIDAD 6: Eliminación
    @Override
    public void delete(E x) throws ExceptionIsEmpty {
    this.root = removeRec(x, this.root); // Actualiza la raíz tras la eliminación
}

    protected Node<E> removeRec(E x, Node<E> actual) throws ExceptionIsEmpty {
        if (actual == null) throw new ExceptionIsEmpty(x + " no esta"); // Caso no encontrado

        Node<E> res = actual;
        int cmp = actual.data.compareTo(x);

        if (cmp < 0) {
            res.right = removeRec(x, actual.right); // Busca en la derecha
        } else if (cmp > 0) {
            res.left = removeRec(x, actual.left); // Busca en la izquierda 
        } else { // actual.data == x [cite: 72, 79]
            if (actual.left != null && actual.right != null) { // Caso: dos hijos
                // Reemplaza con el mínimo del subárbol derecho (sucesor inorden)
                res.data = findMin(actual.right).data;
                actual.right = findMin(actual.right); // Elimina el nodo duplicado
            } else { // Caso: uno o ningún hijo
                res = (actual.left != null) ? actual.left : actual.right;
            }
        }
        return res;
    }
    
    // Sobrecarga del metodo toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, 0, sb);
        return sb.toString();
    }
    
    // Metodo que facilida el modelado grafico del arbol
    private void buildString(Node<E> node, int level, StringBuilder sb) {
        if (node != null) {
            buildString(node.right, level + 1, sb);
            for (int i = 0; i < level; i++) sb.append("    ");
            sb.append(node.data).append("\n");
            buildString(node.left, level + 1, sb);
        }
    }

    // ********************************************************************************************************** //
    // RECORRIDOS (Actividades 7, 8, 9)
    // Método público para iniciar el recorrido
    public void inOrder() {
        inOrder(this.root);
        System.out.println();
    }

    // Implementación privada recursiva
    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);        // Recorrer subárbol izquierdo 
            System.out.print(node.data + " "); // Visitar la raíz
            inOrder(node.right);       // Recorrer subárbol derecho 
        }
    }
    
    // Método público para iniciar el recorrido
    public void preOrder() {
        preOrder(this.root);
        System.out.println();
    }

    // Implementación privada recursiva [cite: 94]
    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " "); // Visitar la raíz primero
            preOrder(node.left);               // Luego subárbol izquierdo
            preOrder(node.right);              // Finalmente subárbol derecho
        }
    }
    
    // Método público para iniciar el recorrido
    public void postOrder() {
        postOrder(this.root);
        System.out.println();
    }

    // Implementación privada recursiva [cite: 98]
    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);              // Recorrer subárbol izquierdo
            postOrder(node.right);             // Recorrer subárbol derecho
            System.out.print(node.data + " "); // Visitar la raíz al final 
        }
    }

// ********************************************************************************************************** //
    // Actividad 10
    private Node<E> findMin(Node<E> node) {
        Node<E> current = node;
        // El menor valor siempre es el nodo más a la izquierda
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Encuentra el valor máximo en el subárbol [cite: 103]
    @SuppressWarnings("unused")
    private Node<E> findMax(Node<E> node) {
        Node<E> current = node;
        // El mayor valor siempre es el nodo más a la derecha 
        while (current.right != null) {
            current = current.right;
        }
        return current;
    } 

// ********************************************************************************************************** //
    // EJERCICIOS ADICIONALES
    
    // 02a. Eliminar todos los nodos
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty();
        root = null;
    }

    // 02c. Contar nodos no-hoja
    // Metodo que cuenta el total de Nodos del Arbol
    public int countAllNodes() {
        return countAllNodes(root);     // LLamada al metodo recursivo
    }
    
    // Sobrecarga del metodo recursivo que cuenta la cantidad de Nodos total del Arbol
    private int countAllNodes(Node<E> node) {
        if (node == null) return 0;     // Caso base
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);    // Suma de los Nodos derechos e izquierdos desde la raiz (+1) hasta las hojas
    }
    
    // Metodo que cuenta el total de Nodos total del Arbol sin contar las Hojas
    public int countNodes() {
        return countNodes(root);        // Llamada al metodo recursivo
    }
    
    // Sobrecarga del metodo recursivo que cuenta la cantidad de Nodos total del Arbol sin contar las Hojas
    private int countNodes(Node<E> node) {
        if (node == null) return 0;     // Caso Base
        if (node.left == null && node.right == null) return 0;      // Caso alterno cuando se llega a la base del Arbol
        return 1 + countNodes(node.left) + countNodes(node.right);      // Suma de los Nodos derechos e izquierdos y la raiz(+1)
    }

    // 02d. Altura iterativa de un subárbol
    // Ejercicio 2: Altura de un nodo específico
    public int height(E x) throws ExceptionIsEmpty {
        // 1. Localizar el nodo utilizando la lógica de búsqueda del BST
        Node<E> current = root;
        Node<E> startNode = null;

        while (current != null) {
            int cmp = x.compareTo(current.data);
            if (cmp == 0) {
                startNode = current;
                break;
            }
            current = (cmp < 0) ? current.left : current.right;
        }

        if (startNode == null) return -1; // El nodo no existe en el árbol

        // 2. Calcular altura por niveles usando la cola personalizada
        LinkedQueue<Node<E>> cola = new LinkedQueue<Node<E>>();
        cola.enqueue(startNode);

        int h = -1;
        while (!cola.isEmpty()) {
            int levelSize = cola.size();
            h++; // Aumenta la altura por cada nivel encontrado

            for (int i = 0; i < levelSize; i++) {
                Node<E> node = cola.dequeue();
                if (node.left != null) cola.enqueue(node.left);
                if (node.right != null) cola.enqueue(node.right);
            }
        }
        return h;
    }
    
    // Metodo que halla la anchura del arbol de forma iterativa y eficiente
    // Método para hallar la anchura máxima (amplitud)
    public int amplitude() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Sin Elementos");

        LinkedQueue<Node<E>> cola = new LinkedQueue<>();
        cola.enqueue(root);

        int maxNodes = 0;

        while (!cola.isEmpty()) {
            // La cantidad de elementos en la cola representa el ancho del nivel actual
            int nodesInCurrentLevel = cola.size();

            if (nodesInCurrentLevel > maxNodes) {
                maxNodes = nodesInCurrentLevel;
            }

            // Vaciamos el nivel actual y cargamos el siguiente
            for (int i = 0; i < nodesInCurrentLevel; i++) {
                Node<E> current = cola.dequeue();
                if (current.left != null) cola.enqueue(current.left);
                if (current.right != null) cola.enqueue(current.right);
            }
        }
        return maxNodes;
    }
// ********************************************************************************************************** //
    // 03a. Área del BST (Hojas * Altura) [cite: 117, 118]
    // Ejercicio 3: Área (Hojas * Altura Total)
    public int areaBST() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Arbol Vacio");

        // Calculamos la altura total llamando al método height desde la raíz
        int totalHeight = height(root.data);

        // Contamos las hojas de forma iterativa con la cola
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.enqueue(root);
        int leavesCount = 0;

        while (!queue.isEmpty()) {
            Node<E> current = queue.dequeue();

            // Un nodo es hoja si ambos hijos son nulos
            if (current.left == null && current.right == null) {
                leavesCount++;
            }

            if (current.left != null) queue.enqueue(current.left);
            if (current.right != null) queue.enqueue(current.right);
        }

        return leavesCount * totalHeight;
    }
    
    public void drawBST() {
        System.out.println(this.toString());
    }
    
}