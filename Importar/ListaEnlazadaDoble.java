package Importar;

public class ListaEnlazadaDoble<T extends Comparable<T>> {
    private NodoDoble<T> primero;
    private NodoDoble<T> ultimo;

    public ListaEnlazadaDoble() {
        this.primero = null;
        this.ultimo = null;
    }

    public boolean esVacia() {
        return this.primero == null;
    }

    public void agregarPrimero(T dato) {
        NodoDoble<T> nuevoNodo = new NodoDoble<>(dato);
        if (esVacia()) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            nuevoNodo.ultimo = this.primero;
            this.primero.primero = nuevoNodo;
            this.primero = nuevoNodo;
        }
    }

    public void agregarUltimo(T dato) {
        NodoDoble<T> nuevoNodo = new NodoDoble<>(dato);
        if (esVacia()) {
            this.primero = nuevoNodo;
            this.ultimo = nuevoNodo;
        } else {
            nuevoNodo.primero = this.ultimo;
            this.ultimo.ultimo = nuevoNodo;
            this.ultimo = nuevoNodo;
        }
    }

    public void eliminar(T dato) {
        if (esVacia()) return;

        NodoDoble<T> actual = this.primero;
        while (actual != null && !actual.dato.equals(dato)) {
            actual = actual.ultimo;
        }

        if (actual == null) return;

        if (actual == this.primero) {
            this.primero = actual.ultimo;
            if (this.primero != null) {
                this.primero.primero = null;
            }
        } else if (actual == this.ultimo) {
            this.ultimo = actual.primero;
            if (this.ultimo != null) {
                this.ultimo.ultimo = null;
            }
        } else {
            actual.primero.ultimo = actual.ultimo;
            actual.ultimo.primero = actual.primero;
        }
    }

    public int longitud() {
        int count = 0;
        NodoDoble<T> actual = this.primero;
        while (actual != null) {
            count++;
            actual = actual.ultimo;
        }
        return count;
    }

    public int buscarIndex(T dato) {
        int index = 0;
        NodoDoble<T> actual = this.primero;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return index;
            }
            index++;
            actual = actual.ultimo;
        }
        return -1; // Retorna -1 si no se encuentra el dato
    }

    
}
