public class Cola<T> { //
    private Nodo<T> frente;// Referencia al frente de la cola
    private Nodo<T> fin;// Referencia al fin de la cola

    public Cola() {
        this.frente = null;// Inicializar el frente de la cola como null
        this.fin = null;// Inicializar el fin de la cola como null
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(T dato) { // Metodo para agregar un elemento al final de la cola
        Nodo<T> nuevo = new Nodo<>(dato); // Crear un nuevo nodo con el dato a encolar

        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;// Enlazar el nuevo nodo al final de la cola
            fin = nuevo;// Actualizar la referencia al fin de la cola
        }
    }

    public T desencolar() { //retorna 
        if (estaVacia()) {
            throw new RuntimeException("Cola vacia");
        }

        T dato = frente.dato; // Obtener el dato del nodo frente
        frente = frente.siguiente;// Actualizar el frente de la cola al siguiente nodo

        if (frente == null) {
            fin = null;
        }

        return dato;// Retornar el dato desencolado
    }
}
