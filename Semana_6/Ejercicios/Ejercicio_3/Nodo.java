public class Nodo {
    int dato; // El dato que se almacenara en el nodo
    Nodo siguiente; // Referencia al siguiente nodo en la cola

    public Nodo(int dato) { // Constructor para crear un nuevo nodo con el dato dado
        this.dato = dato;
        this.siguiente = null;
    }
}
