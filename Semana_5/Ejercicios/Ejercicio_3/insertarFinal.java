package Semana_5.Ejercicios.Ejercicio_3;

// Clase Nodo generico
class Node<T> {
    T data;          // dato almacenado en el nodo
    Node<T> next;    // referencia al siguiente nodo

    Node(T data) {
        this.data = data;
        this.next = null;
    }
}

// Clase Lista Enlazada
class ListLinked<T> {
    Node<T> head;    // referencia al primer nodo de la lista

    ListLinked() {
        this.head = null;
    }

    // Verifica si la lista esta vacia
    boolean isEmptyList() {
        return head == null;
    }

    // Inserta un elemento al inicio de la lista
    void insertFirst(T x) {
        Node<T> temp = new Node<>(x);
        temp.next = head;
        head = temp; //ahora temp con dato x es el primero en la lista enlazada
    }

    // Inserta un elemento al final de la lista
    void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> curr = head; //aqui el nodo cursor pasa a estar ->al mismo que lapunta head
            while (curr.next != null) {
                curr = curr.next; //la
            }
            curr.next = newNode;
        }
    }

    // Imprime todos los elementos de la lista
    void print() {
        Node<T> curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}

// Clase InsertarFinal
class InsertarFinal<T> {
    // Metodo estatico que inserta directamente al final de la lista original
    public static <T> void insertarFinal(ListLinked<T> lista, T x) {
        lista.insertLast(x);
    }
}

// Clase principal para probar
public class insertarFinal {
    public static void main(String[] args) {
        ListLinked<Integer> lista = new ListLinked<>();

        // Insertamos elementos al inicio
        lista.insertFirst(10);
        lista.insertFirst(20);
        lista.insertFirst(30);

        System.out.println("Lista original:");
        lista.print();

        // Insertamos un elemento al final
        InsertarFinal.insertarFinal(lista, 40);

        System.out.println("Lista con elemento al final:");
        lista.print();
    }
}
