package Importar.Estructuras.listLinked;

import Importar.IEstructuras.List;

public class ListLinked<T> implements List<T> {
    public Node<T> root;
    private int size;

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
    public void addLast(T data) {
        Node<T> newNodo = new Node<T>(data);
        if (isEmpty()) {
            root = newNodo;
        } else {
            Node<T> current = root;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNodo);
        }
        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }
        Node<T> current = root;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    public boolean search(T data) {
        Node<T> find = root;
        while (find != null) {
            if (find.getData().equals(data))
                return true;
            find = find.getNext();
        }
        return false;
    }

    @Override
    public void remove(T data) {
        if (isEmpty()) return;

        if (root.getData().equals(data)) {
            root = root.getNext();
            size--;
            return;
        }

        Node<T> delete = root;
        while (delete.getNext() != null) {
            if (delete.getNext().getData().equals(data)) {
                delete.setNext(delete.getNext().getNext());
                size--;
                return; 
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