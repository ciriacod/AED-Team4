package Importar.Estructuras;

import Importar.IEstructuras.BinarySearchTree;
import Importar.Exceptions.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    protected class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this(data, null, null);
        }

        public Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    protected Node<E> root;

    public LinkedBST() {
        this.root = null;
    }

    // =========================================================
    // OPERACIONES BÁSICAS
    // =========================================================

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void insert(E x) {
        this.root = insertRec(x, this.root);
    }

    private Node<E> insertRec(E x, Node<E> node) {
        if (node == null) {
            return new Node<>(x);
        }

        int cmp = node.data.compareTo(x);

        if (cmp == 0) {
            throw new ItemDuplicated("Elemento duplicado: " + x);
        }

        if (cmp < 0) {
            node.right = insertRec(x, node.right);
        } else {
            node.left = insertRec(x, node.left);
        }

        return node;
    }

    @Override
    public E search(E x) {
        Node<E> result = searchRec(x, root);

        if (result == null) {
            throw new ItemNotFound("Elemento no encontrado: " + x);
        }

        return result.data;
    }

    private Node<E> searchRec(E x, Node<E> node) {
        if (node == null) {
            return null;
        }

        int cmp = node.data.compareTo(x);

        if (cmp == 0) {
            return node;
        }

        return (cmp < 0)
                ? searchRec(x, node.right)
                : searchRec(x, node.left);
    }

    @Override
    public void delete(E x) {
        this.root = removeRec(x, this.root);
    }

    private Node<E> removeRec(E x, Node<E> node) {
        if (node == null) {
            throw new ItemNotFound("Elemento no encontrado: " + x);
        }

        int cmp = node.data.compareTo(x);

        if (cmp < 0) {
            node.right = removeRec(x, node.right);
        } else if (cmp > 0) {
            node.left = removeRec(x, node.left);
        } else {

            if (node.left != null && node.right != null) {

                Node<E> min = findMin(node.right);

                node.data = min.data;
                node.right = removeRec(min.data, node.right);

            } else {

                node = (node.left != null)
                        ? node.left
                        : node.right;
            }
        }

        return node;
    }

    // =========================================================
    // RECORRIDOS
    // =========================================================

    @Override
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(Node<E> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    @Override
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(Node<E> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    @Override
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(Node<E> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    // =========================================================
    // INFORMACIÓN DEL ÁRBOL
    // =========================================================

    @Override
    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return 1
                + countAllNodes(node.left)
                + countAllNodes(node.right);
    }

    @Override
    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<E> node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 0;
        }

        return 1
                + countNodes(node.left)
                + countNodes(node.right);
    }

    @Override
    public int countLeaves() {
        return countLeaves(root);
    }

    private int countLeaves(Node<E> node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    @Override
    public int height(E x) {

        Node<E> current = root;
        Node<E> startNode = null;

        while (current != null) {

            int cmp = x.compareTo(current.data);

            if (cmp == 0) {
                startNode = current;
                break;
            }

            current = (cmp < 0)
                    ? current.left
                    : current.right;
        }

        if (startNode == null) {
            return -1;
        }

        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.enqueue(startNode);

        int height = -1;

        while (!queue.isEmpty()) {

            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {

                Node<E> node = queue.dequeue();

                if (node.left != null) {
                    queue.enqueue(node.left);
                }

                if (node.right != null) {
                    queue.enqueue(node.right);
                }
            }
        }

        return height;
    }

    @Override
    public int amplitude() {

        if (isEmpty()) {
            throw new ExceptionIsEmpty("Árbol vacío");
        }

        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.enqueue(root);

        int maxNodes = 0;

        while (!queue.isEmpty()) {

            int currentLevel = queue.size();

            maxNodes = Math.max(maxNodes, currentLevel);

            for (int i = 0; i < currentLevel; i++) {

                Node<E> node = queue.dequeue();

                if (node.left != null) {
                    queue.enqueue(node.left);
                }

                if (node.right != null) {
                    queue.enqueue(node.right);
                }
            }
        }

        return maxNodes;
    }

    @Override
    public int areaBST() {

        if (isEmpty()) {
            throw new ExceptionIsEmpty("Árbol vacío");
        }

        int totalHeight = height(root.data);

        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        queue.enqueue(root);

        int leaves = 0;

        while (!queue.isEmpty()) {

            Node<E> current = queue.dequeue();

            if (current.left == null && current.right == null) {
                leaves++;
            }

            if (current.left != null) {
                queue.enqueue(current.left);
            }

            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        return leaves * totalHeight;
    }

    // =========================================================
    // VALIDACIONES
    // =========================================================

    @Override
    public boolean isValidBST() {

        if (isEmpty()) {
            throw new ExceptionIsEmpty("Árbol vacío");
        }

        StackLink<Node<E>> stack = new StackLink<>();

        Node<E> current = root;
        E prevData = null;

        while (current != null || !stack.isEmpty()) {

            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            if (prevData != null
                    && current.data.compareTo(prevData) <= 0) {
                return false;
            }

            prevData = current.data;
            current = current.right;
        }

        return true;
    }

    // =========================================================
    // CONSULTAS
    // =========================================================

    @Override
    public void searchRange(E min, E max) {
        searchRange(root, min, max);
        System.out.println();
    }

    private void searchRange(Node<E> node, E min, E max) {

        if (node == null) {
            return;
        }

        if (node.data.compareTo(min) > 0) {
            searchRange(node.left, min, max);
        }

        if (node.data.compareTo(min) >= 0
                && node.data.compareTo(max) <= 0) {

            System.out.print(node.data + " ");
        }

        if (node.data.compareTo(max) < 0) {
            searchRange(node.right, min, max);
        }
    }

    @Override
    public void printDescending() {
        printDescending(root);
        System.out.println();
    }

    private void printDescending(Node<E> node) {

        if (node != null) {

            printDescending(node.right);

            System.out.print(node.data + " ");

            printDescending(node.left);
        }
    }

    // =========================================================
    // VISUALIZACIÓN
    // =========================================================

    @Override
    public void drawBST() {
        System.out.println(this);
    }

    @Override
    public void parenthesize() {

        if (isEmpty()) {
            System.out.println("()");
            return;
        }

        parenthesizeRec(root, 0);
        System.out.println();
    }

    private void parenthesizeRec(Node<E> node, int depth) {

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }

        if (node == null) {
            System.out.println("()");
            return;
        }

        System.out.print(node.data);

        if (node.left == null && node.right == null) {
            System.out.println();
            return;
        }

        System.out.println(" (");

        parenthesizeRec(node.left, depth + 1);
        parenthesizeRec(node.right, depth + 1);

        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }

        System.out.println(")");
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        buildString(root, 0, sb);

        return sb.toString();
    }

    private void buildString(Node<E> node,
                             int level,
                             StringBuilder sb) {

        if (node != null) {

            buildString(node.right, level + 1, sb);

            for (int i = 0; i < level; i++) {
                sb.append("    ");
            }

            sb.append(node.data).append("\n");

            buildString(node.left, level + 1, sb);
        }
    }

    // =========================================================
    // UTILIDADES
    // =========================================================

    @Override
    public void destroyNodes() {
        root = null;
    }

    private Node<E> findMin(Node<E> node) {

        Node<E> current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    @SuppressWarnings("unused")
    private Node<E> findMax(Node<E> node) {

        Node<E> current = node;

        while (current.right != null) {
            current = current.right;
        }

        return current;
    }
}