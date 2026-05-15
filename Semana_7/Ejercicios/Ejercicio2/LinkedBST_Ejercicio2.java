
package Ejercicio2;
import Actividades.*;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°2   ------------------  *******************
 ***********************************************************************************************
 */


public class LinkedBST_Ejercicio2<T extends Comparable<T>> {
    
    // Eliminar todos los nodos
    public void destroyNodes() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty();
        root = null;
    }
    
    // Metodo que cuenta el total de Nodos del Arbol
    public int countAllNodes() {
        return countAllNodes(root);
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

    // Altura iterativa de un subárbol
    public int height(E x) {
        // Localizar el nodo utilizando la lógica de búsqueda del BST
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

        // Calcular altura por niveles usando la cola personalizada
        MyQueue<Node<E>> queue = new MyQueue<>();
        queue.enqueue(startNode);

        int h = -1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            h++; // Aumenta la altura por cada nivel encontrado

            for (int i = 0; i < levelSize; i++) {
                Node<E> node = queue.poll();
                if (node.left != null) queue.enqueue(node.left);
                if (node.right != null) queue.enqueue(node.right);
            }
        }
        return h;
    }
    
    // Método para hallar la anchura máxima (amplitud)
    public int amplitude() {
        if (root == null) return 0;

        MyQueue<Node<E>> queue = new MyQueue<>();
        queue.enqueue(root);

        int maxNodes = 0;

        while (!queue.isEmpty()) {
            // La cantidad de elementos en la cola representa el ancho del nivel actual
            int nodesInCurrentLevel = queue.size();

            if (nodesInCurrentLevel > maxNodes) {
                maxNodes = nodesInCurrentLevel;
            }

            // Vaciamos el nivel actual y cargamos el siguiente
            for (int i = 0; i < nodesInCurrentLevel; i++) {
                Node<E> current = queue.poll();
                if (current.left != null) queue.enqueue(current.left);
                if (current.right != null) queue.enqueue(current.right);
            }
        }
        return maxNodes;
    }
}
