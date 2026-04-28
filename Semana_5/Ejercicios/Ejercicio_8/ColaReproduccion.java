
package Semana_5.Ejercicios.Ejercicio_8;

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
// Metodo que mezacla las canciones utilizando el algoritmo de Fisher-Yates
    public void mezclar() {
        if (head == null) return;

        // Crear lista temporal
        ColaReproduccion<T> copia = new ColaReproduccion<>();
        NodeDoble<T> aux = head;            //Se asigna Head a un Nodo auxiliar

        //Mientras el Nodo auxiliar sea nulo, sigue recorriendo la lista
        while (aux != null) {
            copia.agregarCancion(aux.dato);         //Agrega las canciones a la nueva lista
            aux = aux.next;         //Recorre al siguiente Nodo
        }
        
        NodeDoble<T> temp = copia.head;     //Se crea un nodo temporal
        int n = 0;      //Asignacion de la variable para calcular el tamaño del nodo Temp

        // Mientras el Nodo temporal no se nulo sigue recorriendo
        while (temp != null) {
            n++;
            temp = temp.next;
        }

        // Se realiza el algoritmo de Fisher-Yates para el ordenamiento aleatorio de la lista
        for (int i = n - 1; i > 0; i--) {           // Comienza desde el ultimo elemento hasta el primero
            int j = (int) (Math.random() * (i + 1));           // Se genera una posicion aleatoria desdes 0 hasta el valor actual del nodo

            NodeDoble<T> nodoI = copia.head;        //Se asignan el nodoI para simular el comportamiento de la variable i desde Head
            for (int k = 0; k < i; k++) nodoI = nodoI.next;     //Recorre el nuevo Nodo asignando segun la posicion J

            NodeDoble<T> nodoJ = copia.head;        //Se asigna el nodoJ para simular el comportamiento de la variable j desde Head
            for (int k = 0; k < j; k++) nodoJ = nodoJ.next;     //Recorre el nuevo Nodo asignado segun la posicion J

            // Intercambio de datos
            T tempDato = nodoI.dato;
            nodoI.dato = nodoJ.dato;
            nodoJ.dato = tempDato;
        }

        // Reconstruye la lista original
        head = tail = actual = null;

        NodeDoble<T> recorrer = copia.head;     //Se llama a la cabeza de la lista copiada

        //Mientras el nodo de la lista copia sea nulo, este recorre mientras agrega una nueva cancion
        while (recorrer != null) {
            agregarCancion(recorrer.dato);
            recorrer = recorrer.next;
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
