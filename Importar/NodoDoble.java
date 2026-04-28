package Importar;

public class NodoDoble<T> {
    T dato;
    NodoDoble<T> primero;
    NodoDoble<T> ultimo;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.primero = null;
        this.ultimo = null;
    }
    
}
