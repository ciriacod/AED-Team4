package bstreelinklistinterfgeneric2;

public class LinkedBST<T extends Comparable<T>> {
    private Node<T> root;

    public LinkedBST() {
        this.root = null;
    }

    public void destroyNodes() throws ExceptionIsEmpty {
        if (root == null) {
            throw new ExceptionIsEmpty("No hay arbol");
        }
        root = null;
    }

    public int countAllNodes() {
        return countAllNodes(root);
    }

    private int countAllNodes(Node<T> node) {
        if (node == null) return 0;
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int countNodes(Node<T> node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    private Node<T> searchNode(T x) {
        Node<T> current = root;
        while (current != null) {
            int cmp = x.compareTo(current.data);
            if (cmp == 0) return current;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // Método que usaba Queue y ahora usa MyQueue
    public int height(T x) {
        Node<T> start = searchNode(x);
        if (start == null) return -1;

        MyQueue<T> queue = new MyQueue<>();
        queue.offer(start);

        int height = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;

            for (int i = 0; i < size; i++) {
                Node<T> current = queue.poll();
                if (current.left != null) queue.offer(current.left);
                if (current.right != null) queue.offer(current.right);
            }
        }
        return height;
    }

    private int nodesInLevel(Node<T> node, int level) {
        if (node == null) return 0;
        if (level == 0) return 1;
        return nodesInLevel(node.left, level - 1) + nodesInLevel(node.right, level - 1);
    }

    public int amplitude() {
        if (root == null) return 0;
        int h = height(root.data);
        int max = 0;
        for (int level = 0; level <= h; level++) {
            int nodes = nodesInLevel(root, level);
            if (nodes > max) max = nodes;
        }
        return max;
    }

    // Métodos de apoyo para el Main
    public void insert(T data) {
        root = insertRecursive(root, data);
    }

    private Node<T> insertRecursive(Node<T> current, T data) {
        if (current == null) return new Node<>(data);
        if (data.compareTo(current.data) < 0) current.left = insertRecursive(current.left, data);
        else if (data.compareTo(current.data) > 0) current.right = insertRecursive(current.right, data);
        return current;
    }

    public void drawBST() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, 0, sb);
        return sb.toString();
    }

    private void buildString(Node<T> node, int level, StringBuilder sb) {
        if (node != null) {
            buildString(node.right, level + 1, sb);
            for (int i = 0; i < level; i++) sb.append("    ");
            sb.append(node.data).append("\n");
            buildString(node.left, level + 1, sb);
        }
    }
    
    // --- LÓGICA DE COLA PROPIA (Sin java.util) ---
    private static class QNode<T> {
        Node<T> treeNode;
        QNode<T> next;

        QNode(Node<T> treeNode) {
            this.treeNode = treeNode;
            this.next = null;
        }
    }

    private static class MyQueue<T> {
        private QNode<T> front, rear;
        private int size = 0;

        public void offer(Node<T> node) {
            QNode<T> newNode = new QNode<>(node);
            if (rear == null) {
                front = rear = newNode;
            } else {
                rear.next = newNode;
                rear = newNode;
            }
            size++;
        }

        public Node<T> poll() {
            if (front == null) return null;
            Node<T> res = front.treeNode;
            front = front.next;
            if (front == null) rear = null;
            size--;
            return res;
        }

        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }
    }
    // --------------------------------------------
    
    public static void main(String[] args) {
        LinkedBST<Integer> tree = new LinkedBST<>();

        // 1. Inserción de datos
        // Construiremos un árbol balanceado:
        //          50
        //        /    \
        //      30      70
        //     /  \    /  \
        //    20  40  60  90
        int[] valores = {50, 30, 70, 20, 40, 60, 90};
        for (int v : valores) {
            tree.insert(v);
        }

        // 2. Visualización
        System.out.println("========================================");
        System.out.println("ESTRUCTURA DEL ÁRBOL:");
        System.out.println("========================================");
        tree.drawBST(); 
        System.out.println("========================================\n");

        // 3. Pruebas de Conteo
        System.out.println("--- Estadísticas de Nodos ---");
        System.out.println("Total de nodos (AllNodes): " + tree.countAllNodes()); // Esperado: 7
        System.out.println("Nodos internos (No-hojas): " + tree.countNodes());    // Esperado: 3 (50, 30, 70)

        // 4. Pruebas de Altura (BFS con cola propia)
        System.out.println("\n--- Pruebas de Altura (BFS) ---");
        System.out.println("Altura desde la raíz (50): " + tree.height(50));      // Esperado: 2
        System.out.println("Altura del subárbol 70: " + tree.height(70));        // Esperado: 1
        System.out.println("Altura de una hoja (20): " + tree.height(20));        // Esperado: 0
        System.out.println("Altura de nodo inexistente (99): " + tree.height(99)); // Esperado: -1

        // 5. Prueba de Amplitud
        System.out.println("\n--- Prueba de Amplitud ---");
        System.out.println("Máximo de nodos en un nivel: " + tree.amplitude());    // Esperado: 4 (nivel de las hojas)

        // 6. Prueba de Destrucción
        System.out.println("\n--- Prueba de Destrucción ---");
        try {
            System.out.println("Vaciando el árbol...");
            tree.destroyNodes();
            System.out.println("¿Árbol vacío? Total nodos: " + tree.countAllNodes());

            // Esto debería disparar la excepción
            tree.destroyNodes(); 
        } catch (ExceptionIsEmpty e) {
            System.out.println("Captura de excepción: " + e.getMessage());
        }
    }
}