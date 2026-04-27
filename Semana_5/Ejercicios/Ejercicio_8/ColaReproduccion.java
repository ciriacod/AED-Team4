
package Ejercicio8;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°8   ------------------  *******************
 ***********************************************************************************************
 */

//Se importan librerias de java para el uso del algoritmo Fisher-Yates
import java.util.Collections;
import java.util.ArrayList;

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
    
    // Metodo que mezacla las canciones utilizando el algoritmo de Fisher-Yates
    public void mezclar() {
        
        // Verifica si Head es nula para retornar nada
        if (head == null) return;
        
        ArrayList<T> temp = new ArrayList<>();      // Se instancia un ArrayList temporal para almacenar Nodos
        NodeDoble<T> aux = head;       // Se define como un Nodo auxiliar a Head
        
        // Mientras el auxiliar no sea nulo, se agregan los Nodos al ArrayList temporal
        while (aux != null) {
            temp.add(aux.dato);
            aux = aux.next;
        }
        
        Collections.shuffle(temp); // Metodo importante de la clase Collections que permite realizar una mezacla ordenada de los datos

        // Reconstruye la lista con el nuevo orden
        head = tail = null;     // Se establece momentaneamente como nulos a Head y Tail
        
        // Se agrega Nodo por Nodo a la lista
        for (T item : temp) {
            agregarCancion(item);
        }
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
