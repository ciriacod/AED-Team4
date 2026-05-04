public class Cola {
    private Nodo frente; // Referencia al frente de la cola
    private Nodo fin; // Referencia al fin de la cola

    public Cola() { // Constructor para inicializar la cola vacía
        frente = fin = null;
    }

    public boolean estaVacia() { // Metodo para verificar si la cola esta vacia
        return frente == null;
    }

    public void encolar(int dato) {// Metodo para agregar un nuevo elemento al final de la cola
        Nodo nuevo = new Nodo(dato); // Crear un nuevo nodo con el dato dado

        if (estaVacia()) {
            frente = fin = nuevo; // Si la cola esta vacia, el nuevo nodo es tanto el frente como el fin
        } else {
            fin.siguiente = nuevo; // Enlazar el nuevo nodo al final de la cola
            fin = nuevo;    // Actualizar la referencia al fin de la cola
        }
    }

    public int desencolar() {// Metodo para eliminar y retornar el elemento al frente de la cola
        if (estaVacia()) {
            throw new RuntimeException("Cola vacia");// Si la cola esta vacia, lanzar una excepcion
        }

        int dato = frente.dato; // Obtener el dato del nodo al frente de la cola
        frente = frente.siguiente; // Actualizar la referencia al frente de la cola

        if (frente == null) {// Si la cola queda vacia despues de desencolar, actualizar la referencia al fin
            fin = null;// Si la cola queda vacia despues de desencolar, actualizar la referencia al fin
        }

        return dato;// Retornar el dato del nodo desencolado
    }
}
