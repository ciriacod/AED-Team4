package Arbol;

public class LinkedQuaternaryT<E extends Comparable<E>> {

    // Clase interna Node: representa un nodo del arbol
    class Node {
        public E data;              // dato almacenado
        public ChildNode children;  // lista enlazada de hijos

        public Node(E data) {
            this.data = data;
            this.children = null;   // inicialmente sin hijos
        }
    }

    // Clase interna ChildNode: lista enlazada para manejar los hijos
    class ChildNode {
        public Node child;          // referencia al hijo
        public ChildNode next;      // siguiente hijo en la lista

        public ChildNode(Node child) {
            this.child = child;
            this.next = null;
        }
    }

    private Node root;              // raiz del arbol

    public LinkedQuaternaryT() {
        this.root = null;           // arbol vacio al inicio
    }

    // Inserta la raiz del arbol
    public void insertRoot(E data) {
        if (root == null) {
            root = new Node(data);
        } else {
            System.out.println("La raiz ya existe");
        }
    }

    // Busca un nodo por su dato
    public Node search(E data) {
        return searchRec(root, data);
    }

    // Metodo recursivo para buscar en todo el arbol
    private Node searchRec(Node node, E data) {
        if (node == null) return null;

        if (node.data.compareTo(data) == 0) {
            return node; // encontrado
        }

        ChildNode current = node.children;
        while (current != null) {
            Node found = searchRec(current.child, data);
            if (found != null) return found;
            current = current.next;
        }
        return null; // no encontrado
    }

    // Inserta un hijo en un nodo padre
    public void insert(E parentData, E childData) {
        Node parent = search(parentData);

        if (parent == null) {
            System.out.println("No existe el padre: " + parentData);
            return;
        }

        if (countChildren(parent) >= 4) {
            System.out.println("El nodo " + parentData + " ya tiene 4 hijos");
            return;
        }

        Node child = new Node(childData);
        ChildNode newChild = new ChildNode(child);

        if (parent.children == null) {
            parent.children = newChild; // primer hijo
        } else {
            ChildNode current = parent.children;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newChild; // agrega al final
        }
    }

    // Cuenta cuantos hijos tiene un nodo
    private int countChildren(Node node) {
        int count = 0;
        ChildNode current = node.children;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Imprime el arbol con indentacion
    public void printTree() {
        printTreeRec(root, 0);
    }

    private void printTreeRec(Node node, int level) {
        if (node == null) return;

        for (int i = 0; i < level; i++) {
            System.out.print("  "); // espacios segun nivel
        }
        System.out.println(node.data);

        ChildNode current = node.children;
        while (current != null) {
            printTreeRec(current.child, level + 1);
            current = current.next;
        }
    }

    // Muestra el arbol en forma parentizada
    public void parenthesize() {
        parenthesize(root, 0);
        System.out.println();
    }

    private void parenthesize(Node node, int level) {
        if (node == null) return;

        printSpaces(level);
        System.out.print(node.data);

        if (node.children != null) {
            System.out.println(" (");
            ChildNode current = node.children;
            while (current != null) {
                parenthesize(current.child, level + 1);
                current = current.next;
            }
            printSpaces(level);
            System.out.print(")");
        }
        System.out.println();
    }

    // Imprime espacios para dar formato
    private void printSpaces(int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
    }
}
