package Importar;

public class NodoDoble<T> {
    private T dato;
    private NodoDoble<T> primero;
    private NodoDoble<T> ultimo;

    public NodoDoble(T dato) {
        this.dato = dato;
        this.primero = null;
        this.ultimo = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoDoble<T> getPrimero() {
        return primero;
    }

    public void setPrimero(NodoDoble<T> primero) {
        this.primero = primero;
    }

    public NodoDoble<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoDoble<T> ultimo) {
        this.ultimo = ultimo;
    }

}
