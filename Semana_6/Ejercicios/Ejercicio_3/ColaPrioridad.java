public class ColaPrioridad<T> {
    private Cola<T>[] colas; // Array de colas para cada nivel de prioridad

    public ColaPrioridad(int niveles) {// Constructor para inicializar las colas de prioridad
        colas = new Cola[niveles];// Crear un array de colas con el número de niveles de prioridad
        for (int i = 0; i < niveles; i++) {// Inicializar cada cola en el array
            colas[i] = new Cola<>();// Inicializar cada cola en el array
        }
    }

    public void insertar(T dato, int prioridad) {
        if (prioridad < 0 || prioridad >= colas.length) {
            throw new RuntimeException("Prioridad invalida");
        }
        colas[prioridad].encolar(dato);
    }

    public T eliminar() {// Eliminar el elemento de mayor prioridad (la cola más alta que no esté vacía)
    for (int i = colas.length - 1; i >= 0; i--) {// Recorrer las colas desde la más alta hasta la más baja
        if (!colas[i].estaVacia()) {// Si la cola no esta vacía, eliminar y retornar el elemento
            return colas[i].desencolar();
        }
    }
    throw new RuntimeException("Cola vacia"); // Si todas las colas están vacías, lanzar una excepción
}
}
