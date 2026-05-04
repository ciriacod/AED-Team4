public class Nodo<T> { // Clase genérica Nodo para la implementación de la cola
    T dato;// Dato almacenado en el nodo
    Nodo<T> siguiente;// Referencia al siguiente nodo en la cola

    public Nodo(T dato) {// Constructor para inicializar el nodo con un dato
        this.dato = dato;
        this.siguiente = null;
    }
}
