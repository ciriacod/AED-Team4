
package bstreelinklistinterfgeneric2;

public class Node <T> {
    T data;
    Node<T> right;
    Node<T> left;
    
    public Node(T dato){
        this.data = dato;
        this.right = null;
        this.left = null;
    }
    
}
