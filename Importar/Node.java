package Importar;

public class Node<T> {
    T dato;
    public Node<T> next;

    public Node(T dato) {
        this.dato = dato;
        this.next = null;
    }
    
    public T getDato(){
        return this.dato;
    }
    
}
