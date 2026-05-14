package Semana_5.Ejercicios.Ejercicio_6;

import Importar.Node;

public class ListLinked<T> {
    private Node<T> head;

    public ListLinked() {
        head = null;
    }

    // Getter para poder recorrer la lista en métodos estáticos
    public Node<T> getHead() {
        return head;
    }

    // Método para insertar al final (Requisito del lab)
    public void insertLast(T x) {
        Node<T> nuevoNodo = new Node<>(x);
        if (head == null) {
            head = nuevoNodo;
        } else {
            Node<T> aux = head;
            while (aux.next != null) {
                aux = aux.next;
            }
            aux.next = nuevoNodo;
        }
    }
    
    public void mostrar() {
    Node<T> aux = head;
    while (aux != null) {
        System.out.print("[" + aux.getData() + "] -> ");
        aux = aux.next;
    }
    System.out.println("null");
}
}
