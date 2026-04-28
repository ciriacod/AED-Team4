package Importar;

public class Node<T> {
    T dato;
    Node<T> next;
    public Object value;

    public Node(T dato) {
        this.dato = dato;
        this.next = null;
    }
    
}
