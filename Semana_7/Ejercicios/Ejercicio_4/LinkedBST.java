package Arbol;


public class LinkedBST<E extends Comparable<E>> {

    class Node {
        E data;
        Node left;
        Node right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public LinkedBST() {
        root = null;
    }

    public void insertRoot(E data) {
        if (root == null) {
            root = new Node(data);
        }
    }

   //aqui validamos si es BST
    public boolean isValidBST() {
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(Node node, E min, E max) {
//si el arbol esta vacio 
        if (node == null) {
            return true;
        }

 //verificamos el nodo inferir minimo
        if (min != null && node.data.compareTo(min) <= 0) {
            return false;
        }

        // verificar limite superior
        if (max != null && node.data.compareTo(max) >= 0) {
            return false;
        }

        // validar subarbol izquierdo y derecho
        return isValidBST(node.left, min, node.data)
            && isValidBST(node.right, node.data, max);
    }
}
