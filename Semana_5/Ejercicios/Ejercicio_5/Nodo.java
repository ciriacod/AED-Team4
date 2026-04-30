
package Ejercicio5;

public class Nodo<T> {
    T dato;
    Nodo<T> next;
    
    public Nodo(T dato){
        this.dato = dato;
        this.next = null;
    }
}
