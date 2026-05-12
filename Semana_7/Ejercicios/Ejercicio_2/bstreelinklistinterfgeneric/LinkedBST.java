
package bstreelinklistinterfgeneric;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°2   ------------------  *******************
 ***********************************************************************************************
 */

// Clase Arbol enlazado generico
public class LinkedBST<T extends Comparable<T>> {
    private Node<T> root;      // Nodo raiz del arbol

    public LinkedBST() {
        this.root = null;
    }
    
    // Metodo que elimina todos los Nodos
    public void destroyNodes() throws ExceptionIsEmpty {        // Uso de una clase extendida de Exception
        
        // Verifica si exite un arbol
        if (root == null) {
            throw new ExceptionIsEmpty("No hay arbol");
        }
        root = null;
    }
    
    // Metodo que cuenta el total de Nodos del Arbol
    public int countAllNodes() {
        return countAllNodes(root);     // LLamada al metodo recursivo
    }
    
    // Sobrecarga del metodo recursivo que cuenta la cantidad de Nodos total del Arbol
    private int countAllNodes(Node<T> node) {
        if (node == null) return 0;     // Caso base
        return 1 + countAllNodes(node.left) + countAllNodes(node.right);    // Suma de los Nodos derechos e izquierdos desde la raiz (+1) hasta las hojas
    }
    
    // Metodo que cuenta el total de Nodos total del Arbol sin contar las Hojas
    public int countNodes() {
        return countNodes(root);        // Llamada al metodo recursivo
    }
    
    // Sobrecarga del metodo recursivo que cuenta la cantidad de Nodos total del Arbol sin contar las Hojas
    private int countNodes(Node<T> node) {
        if (node == null) return 0;     // Caso Base
        if (node.left == null && node.right == null) return 0;      // Caso alterno cuando se llega a la base del Arbol
        return 1 + countNodes(node.left) + countNodes(node.right);      // Suma de los Nodos derechos e izquierdos y la raiz(+1)
    }
    
    // Metodo que busca un elemento de todo el Arbol
    private Node<T> searchNode(T x) {
        Node<T> current = root;     // Se instancia el Arbol a uno temporal
        
        // Mientras el Arbol no sea nulo
        while (current != null) {
            
            // Manejo manual de los retornos del metodo compareTo() (Metodo de busqueda InOrden)
            int cmp = x.compareTo(current.data);
            if (cmp == 0) return current;
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null;
    }

    // Metodo Iterativo que calcula la altura del Arbol (Desde un Nodo hasta llegar a la Hoja)
    public int height(T x) {
        Node<T> start = searchNode(x);      // Busque del Nodo desde donde se desea contar
        if (start == null) return -1;      // Si no hay arbol retorna -1

        MyQueue<T> queue = new MyQueue<>();     // Instancia de una Cola para almacenar los Nodos del Arbol
        queue.enqueue(start);   // Metodo de la Cola

        int height = -1;    // Se inicia la altura del Arbol
        
        // Mientras la cola exista (Iteratividad)
        while (!queue.isEmpty()) {
            int size = queue.size();       // Se instancia el tamaño de la Cola
            height++;
            
            // Recorre la cola quitando un elemento y volviendolo a colocar
            for (int i = 0; i < size; i++) {
                Node<T> current = queue.poll();
                if (current.left != null) queue.enqueue(current.left);
                if (current.right != null) queue.enqueue(current.right);
            }
        }
        return height;
    }
    
    // Metodo Recursivo que calcula la cantidad de Nodos en un respectivo nivel
    private int nodesInLevel(Node<T> node, int level) {
        if (node == null) return 0;     // Caso Base
        if (level == 0) return 1;      // Caso cuando no hay niveles
        return nodesInLevel(node.left, level - 1) + nodesInLevel(node.right, level - 1);    // Suma la cantidad de nodos en un nivel
    }
    
    // Metodo que halla la anchura del arbol
    public int amplitude() {
        if (root == null) return 0;   // Cuando no hay arbol  
        int h = height(root.data);     // Se utiliza el metodo height() para tener el tamaño del Arbol y conseguir el nivel total
        int max = 0;
        
        // Recorre nivel por nivel
        for (int level = 0; level <= h; level++) {
            int nodes = nodesInLevel(root, level);  // Calcula el total de nodos del nivel actual
            if (nodes > max) max = nodes;   // Verifica si el Nodo anterior es menor a la cantidad de Nodos actual
        }
        return max;
    }

    // Metodo de agregacion de Nodos al Arbol
    public void insert(T data) {
        root = insertRecursive(root, data);
    }
    
    // Metodo recursivo que agrega Nodos a las Hojas del Arbol
    private Node<T> insertRecursive(Node<T> current, T data) {
        if (current == null) return new Node<>(data);
        if (data.compareTo(current.data) < 0) current.left = insertRecursive(current.left, data);
        else if (data.compareTo(current.data) > 0) current.right = insertRecursive(current.right, data);
        return current;
    }
     
    // Metodo que dibuja el arbol usando toString()
    public void drawBST() {
        System.out.println(this.toString());
    }
    
    // Sobrecarga del metodo toString()
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        buildString(root, 0, sb);
        return sb.toString();
    }
    
    // Metodo que facilida el modelado grafico del arbol
    private void buildString(Node<T> node, int level, StringBuilder sb) {
        if (node != null) {
            buildString(node.right, level + 1, sb);
            for (int i = 0; i < level; i++) sb.append("    ");
            sb.append(node.data).append("\n");
            buildString(node.left, level + 1, sb);
        }
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
    
    public static void main(String[] args) {
        LinkedBST<Integer> tree = new LinkedBST<>();

        int[] valores = {50, 30, 70, 20, 40, 60, 90};
        for (int v : valores) {
            tree.insert(v);
        }

        // 2. Visualización
        System.out.println("========================================");
        System.out.println("ESTRUCTURA DEL ARBOL:");
        System.out.println("========================================");
        tree.drawBST(); 
        System.out.println("========================================\n");

        // 3. Pruebas de Conteo
        System.out.println("--- Estadisticas de Nodos ---");
        System.out.println("Total de nodos (AllNodes): " + tree.countAllNodes()); // Esperado: 7
        System.out.println("Nodos internos (No-hojas): " + tree.countNodes());    // Esperado: 3 (50, 30, 70)

        // 4. Pruebas de Altura (BFS con cola propia)
        System.out.println("\n--- Pruebas de Altura (BFS) ---");
        System.out.println("Altura desde la raiz (50): " + tree.height(50));      // Esperado: 2
        System.out.println("Altura del subarbol 70: " + tree.height(70));        // Esperado: 1
        System.out.println("Altura de una hoja (20): " + tree.height(20));        // Esperado: 0
        System.out.println("Altura de nodo inexistente (99): " + tree.height(99)); // Esperado: -1

        // 5. Prueba de Amplitud
        System.out.println("\n--- Prueba de Amplitud ---");
        System.out.println("Maximo de nodos en un nivel: " + tree.amplitude());    // Esperado: 4 (nivel de las hojas)

        // 6. Prueba de Destrucción
        System.out.println("\n--- Prueba de Destruccion ---");
        try {
            System.out.println("Vaciando el arbol...");
            tree.destroyNodes();
            System.out.println("Arbol vacio? Total nodos: " + tree.countAllNodes());

            // Esto debería disparar la excepción
            tree.destroyNodes(); 
        } catch (ExceptionIsEmpty e) {
            System.out.println("Captura de excepcion: " + e.getMessage());
        }
    }
}
