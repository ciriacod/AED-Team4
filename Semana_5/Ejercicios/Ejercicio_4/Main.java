package Semana_5.Ejercicios.Ejercicio_4;
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
        head = temp;
    }

    // Inserta un elemento al final de la lista
    void insertLast(T x) {
        Node<T> newNode = new Node<>(x);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
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
//CONTARNODOS
class contarNodo<T> {
   // Metodo generico para contar nodos en una lista enlazada
    public static <T> int contarNodos(Node<T> head) {
        int contador = 0;          // inicializamos el contador en cero
        Node<T> curr = head;       // empezamos desde la cabeza de la lista

        // mientras no lleguemos al final de la lista
        while (curr != null) {
            contador++;            // sumamos uno por cada nodo
            curr = curr.next;      // avanzamos al siguiente nodo
        }

        return contador;           // devolvemos el total de nodos
    }

}

// Clase principal para probar
public class Main {
 public static void main(String[] args) {
    ListLinked<Integer> lista = new ListLinked<>();
    lista.insertFirst(10);
    lista.insertFirst(20);
    lista.insertFirst(30);

    // llamamos al metodo contarNodos pasando la cabeza
    int total = contarNodo.contarNodos(lista.head);
    System.out.println("Numero de nodos: " + total);
}

}
