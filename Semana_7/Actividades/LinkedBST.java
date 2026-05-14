package Semana_7.Actividades;
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

    // ACTIVIDAD 6: Inserción [cite: 79, 80]
    @Override
    public void insert(E x) throws ItemDuplicated {
    this.root = insertRec(x, this.root);
}

    protected Node<E> insertRec(E x, Node<E> actual) throws ItemDuplicated {
        Node<E> res = actual;
        if (actual == null) {
            res = new Node<E>(x); // Caso base: crea el nodo si es null [cite: 52]
        } else {
            int resC = actual.data.compareTo(x); // Comparación de datos [cite: 53]
            if (resC == 0) {
                throw new ItemDuplicated("x " + x + " esta duplicado"); // Error si ya existe [cite: 54]
            }
            if (resC < 0) {
                res.right = insertRec(x, actual.right); // Menor que x: subárbol derecho [cite: 55]
            } else {
                res.left = insertRec(x, actual.left); // Mayor que x: subárbol izquierdo [cite: 56]
            }
        }
        return res;
    }

    // ACTIVIDAD 6: Búsqueda [cite: 81]
    @Override
    public E search(E x) throws ItemNotFound {
    Node<E> res = searchRec(x, this.root); // Inicia búsqueda desde la raíz [cite: 48]
    if (res == null)
        throw new ItemNotFound(x + " no se encuentra");
    return res.data;
}

    protected Node<E> searchRec(E x, Node<E> actual) {
        if (actual == null) return null;
        int cmp = actual.data.compareTo(x);
        if (cmp == 0) return actual; // Elemento encontrado [cite: 61]
        return (cmp < 0) ? searchRec(x, actual.right) : searchRec(x, actual.left);
    }

    // ACTIVIDAD 6: Eliminación [cite: 58, 82]
    @Override
    public void delete(E x) throws ExceptionIsEmpty {
    this.root = removeRec(x, this.root); // Actualiza la raíz tras la eliminación [cite: 64, 65]
}

    protected Node<E> removeRec(E x, Node<E> actual) throws ExceptionIsEmpty {
        if (actual == null) throw new ExceptionIsEmpty(x + " no esta"); // Caso no encontrado [cite: 68]

        Node<E> res = actual;
        int cmp = actual.data.compareTo(x);

        if (cmp < 0) {
            res.right = removeRec(x, actual.right); // Busca en la derecha [cite: 70, 77]
        } else if (cmp > 0) {
            res.left = removeRec(x, actual.left); // Busca en la izquierda [cite: 71, 78]
        } else { // actual.data == x [cite: 72, 79]
            if (actual.left != null && actual.right != null) { // Caso: dos hijos [cite: 73, 80]
                // Reemplaza con el mínimo del subárbol derecho (sucesor inorden) [cite: 81]
                res.data = findMin(actual.right).data;
                actual.right = findMin(actual.right); // Elimina el nodo duplicado [cite: 82]
            } else { // Caso: uno o ningún hijo [cite: 75, 83]
                res = (actual.left != null) ? actual.left : actual.right;
            }
        }
        return res;
    }

    // Encuentra el valor mínimo en el subárbol [cite: 101]

    private Node<E> findMin(Node<E> node) {
        Node<E> current = node;
        // El menor valor siempre es el nodo más a la izquierda [cite: 101]
        while (current.left != null) {
            current = current.left;
        }
        return current;
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
}
