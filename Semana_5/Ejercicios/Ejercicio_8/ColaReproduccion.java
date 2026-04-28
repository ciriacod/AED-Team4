
package Ejercicio8;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°8   ------------------  *******************
 ***********************************************************************************************
 */

//Se importan librerias de java para el uso del algoritmo Fisher-Yates
import java.util.Random;

// Al poner "extends Cancion", T sabe que es Cancion por lo que puede usar sus metodos
public class ColaReproduccion<T extends Cancion> {
    private NodeDoble<T> head;
    private NodeDoble<T> tail;
    private NodeDoble<T> actual; // Atributo que indica que la playlist se esta reproduciendo
    
    // Metodo que agrega Nodos a la lista
    public void agregarCancion(T cancion) {
        NodeDoble<T> nuevo = new NodeDoble<>(cancion);      // Instancia de una lista doble
        
        // Verifica si la lista se encuentra vacia para agregar un Nodo
        if (head == null) {
            head = tail = actual = nuevo;     
        } else {
            tail.next = nuevo;      // Nuevo se asigna al siguiente de la cola
            nuevo.prev = tail;      // La cola se asigna al anterior del nuevo
            tail = nuevo;       //Ahora nuevo sera laa cola
        }
    }
    
    // Metodo que avanza al siguiente Nodo y retorna la cancion
    public T reproducirSiguiente() {
        
        // Verifica si la cancion actual y la siguiente no son nulas
        if (actual != null && actual.next != null) {
            actual = actual.next;
            return actual.dato;     // Retorna la cancion del siguiente Nodo
        }
        return (actual != null) ? actual.dato : null; // Si la cancion siguiente es nula, solo se verifica si la cancion actual no es nula y retorna la cancion actual
    }
    
    // Metodo que retrocede al siguiente Nodo y retorna la cancion
    public T reproducirAnterior() {
        
        // Verifica si la cancion actual y la anterior no son nulas
        if (actual != null && actual.prev != null) {
            actual = actual.prev;
            return actual.dato;     // Retorna la cancion del anterior Nodo
        }
        return (actual != null) ? actual.dato : null; // Si la cancion anterior es nula, solo se verifica si la cancion actual no es nula y retorna la cancion actual
    }
    
    // Metodo que mezcla las canciones utilizando el metodo Fisher-Yates
    public void mezclar() {
        int n = contarNodos();
        if (n < 2) return;

        Random rnd = new Random();
        
        // Recorremos de atras hacia adelante (i = n-1 hasta 1)
        for (int i = n - 1; i > 0; i--) {
            //Elegir un indice aleatorio j entre 0 e i
            int j = rnd.nextInt(i + 1);

            //Obtener los nodos en las posiciones i y j
            NodeDoble<T> nodoI = obtenerNodoEnPosicion(i);
            NodeDoble<T> nodoJ = obtenerNodoEnPosicion(j);

            //Intercambiar los datos (SWAP)
            T temp = nodoI.dato;
            nodoI.dato = nodoJ.dato;
            nodoJ.dato = temp;
        }
        // Al terminar, reseteamos el puntero 'actual' al inicio
        actual = head;
    }
    
    // Metodo que utiliza el atributo duracionSeg de Cancion
    public int duracionTotal() {
        int total = 0;
        NodeDoble<T> aux = head;    // Se inicializa un Nodo auxiliar
        
        // mIentras el nodo no sea nulo, se acumula el tiempo de las canciones en una sola variable total
        while (aux != null) {
            
            // Al utilizar <T extends Cancion> ya no se necesita castear ni usar instanceof
            total += aux.dato.getDuracionSeg(); 
            aux = aux.next; 
        }
        return total;  // Retorna la suma total del tiempo de todas las canciones
    }
    
    //Metodo que muestra los elementos presentes en la lista
    public void mostrarCola() {
        NodeDoble<T> aux = head;
        while (aux != null) {
            String indicador = (aux == actual) ? "► " : "  ";
            System.out.println(indicador + aux.dato);
            aux = aux.next;
        }
    }
}
