package Ejercicio8;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N 8   ------------------  *******************
 ***********************************************************************************************
 */

// Al poner extends Cancion T sabe que es Cancion y puede usar sus metodos
public class ColaReproduccion<T extends Cancion> {

    private NodeDoble<T> head;
    private NodeDoble<T> tail;
    private NodeDoble<T> actual; // Indica que cancion se esta reproduciendo

    // Metodo que agrega canciones a la lista doble
    public void agregarCancion(T cancion) {
        NodeDoble<T> nuevo = new NodeDoble<>(cancion);

        if (head == null) {
            head = tail = actual = nuevo;
        } else {
            tail.next = nuevo;
            nuevo.prev = tail;
            tail = nuevo;
        }
    }

    // Metodo que avanza al siguiente nodo y retorna la cancion
    public T reproducirSiguiente() {
        if (actual != null && actual.next != null) {
            actual = actual.next;
            return actual.dato;
        }

        return (actual != null) ? actual.dato : null;
    }

    // Metodo que retrocede al nodo anterior y retorna la cancion
    public T reproducirAnterior() {
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
            return actual.dato;
        }

        return (actual != null) ? actual.dato : null;
    }

    // Metodo que mezcla las canciones sin usar ArrayList ni Collections
    public void mezclar() {
        int n = contarNodos();
        if (n < 2) return;

        Random rnd = new Random();
        
        // Recorremos de atrás hacia adelante (i = n-1 hasta 1)
        for (int i = n - 1; i > 0; i--) {
            // 1. Elegir un índice aleatorio j entre 0 e i
            int j = rnd.nextInt(i + 1);

            // 2. Obtener los nodos en las posiciones i y j
            NodeDoble<T> nodoI = obtenerNodoEnPosicion(i);
            NodeDoble<T> nodoJ = obtenerNodoEnPosicion(j);

            // 3. Intercambiar los datos (SWAP)
            T temp = nodoI.dato;
            nodoI.dato = nodoJ.dato;
            nodoJ.dato = temp;
        }
        // Al terminar, reseteamos el puntero 'actual' al inicio
        actual = head;
    }

    // Método auxiliar para obtener un nodo específico recorriendo la lista
    private NodeDoble<T> obtenerNodoEnPosicion(int index) {
        NodeDoble<T> temp = head;
        for (int i = 0; i < index && temp != null; i++) {
            temp = temp.next;
        }
        return temp;
    }

    // Metodo que cuenta cuantos nodos tiene la lista
    private int contarNodos() {
        int contador = 0;
        NodeDoble<T> aux = head;

        while (aux != null) {
            contador++;
            aux = aux.next;
        }

        return contador;
    }

    // Metodo que obtiene un nodo segun su posicion
    private NodeDoble<T> obtenerNodo(int posicion) {
        NodeDoble<T> aux = head;
        int contador = 0;

        while (aux != null && contador < posicion) {
            aux = aux.next;
            contador++;
        }

        return aux;
    }

    // Metodo que calcula la duracion total de todas las canciones
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> aux = head;

        while (aux != null) {
            total += aux.dato.getDuracionSeg();
            aux = aux.next;
        }

        return total;
    }

    // Metodo que muestra los elementos presentes en la lista
    public void mostrarCola() {
        NodeDoble<T> aux = head;

        while (aux != null) {
            String indicador = (aux == actual) ? "-> " : "   ";
            System.out.println(indicador + aux.dato);
            aux = aux.next;
        }
    }
}
