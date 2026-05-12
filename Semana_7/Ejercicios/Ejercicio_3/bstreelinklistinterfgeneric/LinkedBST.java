
package bstreelinklistinterfgeneric;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°3   ------------------  *******************
 ***********************************************************************************************
 */

//Arbol con operaciones genericas
public class LinkedBST<T extends Comparable<T>> {
    private Node<T> root;   // Nodo raiz del arbol

    public LinkedBST() {
        this.root = null;
    }
    
    // Metodo interativo para calcular el area del arbol segun la cantidad de hojas del arbol y la altura total de este
    public int areaBST() {
        if (root == null) return 0;     // Cuando el arbol no existe

        MyQueue<T> queue = new MyQueue<>();     // Instanciacion de una Cola para recorrer el arbol almacenando la cantidad de hojas 
        queue.enqueue(root);      //   Metodo de la Cola
        
        // Se asigna el tamaño y la cantidad de hojas
        int height = -1;
        int leaves = 0;
        
        // Mientras la cola no esta vacia (Iteratividad)
        while (!queue.isEmpty()) {
            int levelSize = queue.size();   // Instancia del tamaño de la cola
            height++;
            
            // Recorre el tamaño de la cola
            for (int i = 0; i < levelSize; i++) {   
                Node<T> current = queue.poll();     // Instancia el primer Nodo de la Cola
                
                // Recorre el arbol rama por rama hasta llegar a las Hojas
                if (current.left == null && current.right == null) {
                    leaves++;
                }
                
                // Reingreso de Nodos
                if (current.left != null) {
                    queue.enqueue(current.left);
                }

                if (current.right != null) {
                    queue.enqueue(current.right);
                }
            }
        }
        
        // Retorna el area
        return leaves * height;
    }
    
    // Sobrecarga del metodo para una mejor impresion de datos en consola
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, 0, sb);
        return sb.toString();
    }
    
    // Se formula el arbol a imprimir
    private void buildString(Node<T> node, int level, StringBuilder sb) {
        if (node != null) {
            buildString(node.right, level + 1, sb);
            for (int i = 0; i < level; i++) {
                sb.append("    ");
            }
            sb.append(node.data).append("\n");
            buildString(node.left, level + 1, sb);
        }
    }

    // Metodo para agregar Nodos al Arbol
    public void insert(T data) {
        root = insertRecursive(root, data);
    }
    
    // Metodo recursivo que recorre rama por rama hasta llegar a las hojas
    private Node<T> insertRecursive(Node<T> root, T data) {
        if (root == null) return new Node<>(data);
        if (data.compareTo(root.data) < 0) root.left = insertRecursive(root.left, data);
        else if (data.compareTo(root.data) > 0) root.right = insertRecursive(root.right, data);
        return root;
    }
    
    // Metodo que imprime el toString()
    public void drawBST() {
        System.out.print(this.toString());
    }
    
    /* 
       ----------------------------------------------------------------
       Se utiliza una Cola para el recorrido en anchura, debido a que
       permite gestionar la secuencia de nodos pendientes por visitar
       
       - Se inicia visitando la raíz y se agregan sus hijos a la cola.
       - Se extrae el primer elemento de la cola, se procesa y se 
         agregan sus hijos al final.
       - Esto asegura que los nodos se procesen nivel por nivel, 
         manteniendo el orden correcto de generación
       ----------------------------------------------------------------
    */

    // Clase Nodo de Cola interna de la clase LinkedBST
    private static class QNode<T> {
        Node<T> treeNode;   // Nodo hoja del arbol
        QNode<T> next;     // Nodo siguiente para la cola

        QNode(Node<T> treeNode) {
            this.treeNode = treeNode;
            this.next = null;
        }
    }
    
    // Clase Cola interna de la clase LinkedBST
    private static class MyQueue<T> {
        private QNode<T> first, last;      // Nodo primero y ultimo de la cola
        private int size = 0;       // Tamaño de la cola
        
        // Metodo que almacena los nodos de la cola en la cola
        public void enqueue(Node<T> node) {
            QNode<T> newNode = new QNode<>(node);      // Se asigna la hoja del arbol a un Nodo de la cola
            
            // Agrega el Nodo a la Cola cuando esta vacia o cuando esta llena (FIFO)
            if (last == null) {
                first = last = newNode;
            } else {
                last.next = newNode;
                last = newNode;
            }
            size++;     // Se almacena cada ingreso de nodo para calcular el tamaño de este
        }
        
        // Metodo que retorna el primer el elemento de la cola
        public Node<T> poll() {
            if (first == null) return null;
            Node<T> res = first.treeNode;
            first = first.next;
            if (first == null) last = null;
            size--;
            return res;
        }
        
        // Metodo para verificar la existencia de un elemento
        public boolean isEmpty() {
            return size == 0;
        }
        
        // Metodo que retorna el tamaño de la cola
        public int size() {
            return size;
        }
    }
    // -------------------------------------------------------------------
    
    
    // ==============================  Main  ==============================================
    public static void main(String[] args) {
        
        LinkedBST<Integer> tree1 = new LinkedBST<>();
        LinkedBST<Integer> tree2 = new LinkedBST<>();
        LinkedBST<Integer> tree3 = new LinkedBST<>();

        int[] datos1 = {50, 30, 70, 20, 40, 60, 90};
        for (int n : datos1) tree1.insert(n);

        int[] datos2 = {10, 20, 30, 40, 50};
        for (int n : datos2) tree2.insert(n);

        int[] datos3 = {100, 50, 150, 25, 75, 125, 175, 10, 60};
        for (int n : datos3) tree3.insert(n);

        System.out.println("========================================");
        System.out.println("REPORTE DE ESTRUCTURAS Y AREAS");
        System.out.println("========================================\n");

        Prueba.printTreeStats("ARBOL 1 (Balanceado)", tree1);
        Prueba.printTreeStats("ARBOL 2 (Linea recta)", tree2);
        Prueba.printTreeStats("ARBOL 3 (Complejo)", tree3);

        System.out.println("----------------------------------------");
        System.out.println("Arbol 1 y Arbol 2 tienen la misma area?: " + (tree1.areaBST() == tree2.areaBST() ? "Si" : "NO"));
    }
}
