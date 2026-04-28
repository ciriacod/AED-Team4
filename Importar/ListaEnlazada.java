package Importar;

public class ListaEnlazada<T extends Comparable<T>> {
    private Node<T> primero;
    public ListaEnlazada() {
        this.primero = null;
    }

    public boolean esVacia() {
        return this.primero == null;
    }

    public void agregarPrimero(T dato) {
        Node<T> nuevoNodo = new Node<>(dato);
        nuevoNodo.next = this.primero;
        this.primero = nuevoNodo;
    }

    public void agregarUltimo(T dato){
        if (esVacia()) {
            agregarPrimero(dato);
            return;
        }
        Node<T> actual = this.primero;
        while (actual.next != null) {
            actual = actual.next;
        }
        actual.next = new Node<>(dato);
    }

    public void eliminar(T dato) {
        if (esVacia()) return;

        if (this.primero.value.equals(dato)) {
            this.primero = this.primero.next;
            return;
        }

        Node<T> actual = this.primero;
        while (actual.next != null && !actual.next.value.equals(dato)) {
            actual = actual.next;
        }

        if (actual.next != null) {
            actual.next = actual.next.next;
        }
    }

    public int longitud() {
        int count = 0;
        Node<T> actual = this.primero;
        while (actual != null) {
            count++;
            actual = actual.next;
        }
        return count;
    }

    public int buscarIndex(T dato) {
        int index = 0;
        Node<T> actual = this.primero;
        while (actual != null) {
            if (actual.value.equals(dato)) {
                return index;
            }
            actual = actual.next;
            index++;
        }
        return -1; // Retorna -1 si el dato no se encuentra en la lista
    }

    public boolean sonIguales(ListaEnlazada<T> otraLista) {
        Node<T> actual1 = this.primero;
        Node<T> actual2 = otraLista.primero;

        while (actual1 != null && actual2 != null) {
            if (!actual1.value.equals(actual2.value)) {
                return false; // Si un dato es diferente, las listas no son iguales
            }
            actual1 = actual1.next;
            actual2 = actual2.next;
        }
        return actual1 == null && actual2 == null; // Verifica si ambas listas son del mismo tamaño
    }

    public void concatenar(ListaEnlazada<T> otraLista) {
        if (esVacia()) {
            this.primero = otraLista.primero;
            return;
        }
        Node<T> actual = this.primero;
        while (actual.next != null) {
            actual = actual.next;
        }
        actual.next = otraLista.primero;
    }

    public void invertir() {
        Node<T> prev = null;
        Node<T> current = this.primero;
        Node<T> next = null;

        while (current != null) {
            next = current.next; // Guarda el siguiente nodo
            current.next = prev; // Invierte el enlace
            prev = current;      // Mueve prev a current
            current = next;      // Mueve current a next
        }
        this.primero = prev; // Actualiza el primer nodo al nuevo head
    }

    public void imprimir() {
        Node<T> actual = this.primero;
        while (actual != null) {
            System.out.print(actual.value + " -> ");
            actual = actual.next;
        }
        System.out.println("null");
    }

}