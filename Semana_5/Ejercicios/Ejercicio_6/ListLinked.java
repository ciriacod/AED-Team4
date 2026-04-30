
package Ejercicio6;

public class ListLinked<T> {
    private Nodo<T> head;

    public ListLinked() {
        head = null;
    }

    // Getter para poder recorrer la lista en métodos estáticos
    public Nodo<T> getHead() {
        return head;
    }

    // Método para insertar al final (Requisito del lab)
    public void insertLast(T x) {
        Nodo<T> nuevoNodo = new Nodo<>(x);
        if (head == null) {
            head = nuevoNodo;
        } else {
            Nodo<T> aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = nuevoNodo;
        }
    }
    
    public void mostrar() {
    Nodo<T> aux = head;
    while (aux != null) {
        System.out.print("[" + aux.dato + "] -> ");
        aux = aux.next;
    }
    System.out.println("null");
}
}
