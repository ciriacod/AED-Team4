
package Ejercicio8;

public class NodeDoble<T> {
    T dato;
    NodeDoble<T> next;
    NodeDoble<T> prev;

    public NodeDoble(T dato) {
        this.dato = dato;
        this.next = null;
        this.prev = null;
    }
}
