package Importar.Estructuras.listLinked;

import Importar.IEstructuras.List;

public class ListLinked<T extends Comparable<T>> implements List<T> {
    Node<T> root;
    int size;

    public ListLinked() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void insert(T data) {
        Node<T> newNodo = new Node<T>(data);
        newNodo.setNext(root);
        root = newNodo;
        size++;
    }

    @Override
    public boolean search(T data) {
        Node<T> find = root;
        while (find != null) {
            if (find.getData().compareTo(data) == 0)
                return true;
            find = find.getNext();
        }
        return false;
    }

    @Override
    public void remove(T data) {
        if (isEmpty()) return;

        // Caso 1: El dato esta en el primer nodo (root)
        if (root.getData().compareTo(data) == 0) {
            root = root.getNext();
            size--;
            return;
        }

        // Caso 2: El dato esta mas adelante
        Node<T> delete = root;
        while (delete.getNext() != null) {
            if (delete.getNext().getData().compareTo(data) == 0) {
                delete.setNext(delete.getNext().getNext());
                size--;
                return; // Terminamos para evitar NullPointerException
            }
            delete = delete.getNext();
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = root;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) sb.append(", ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}