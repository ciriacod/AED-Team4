
package Semana_5.Ejercicios.Ejercicio_7;

public class Nodo<T> {
    T dato;
    Nodo<T> next;
    
    public Nodo(T dato){
        this.dato = dato;
        this.next = null;
    }
}
