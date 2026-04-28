
package Semana_5.Ejercicios.Ejercicio_7;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°7   ------------------  *******************
 ***********************************************************************************************
 */

// La restricción <T extends Comparable<T>> es obligatoria para poder comparar los elementos de tipo T
public class SortedListLinked<T extends Comparable<T>> extends ListLinked<T> {
    
    //Metodo que inserta un elemento dependiendo del orden de la lista
    public void insertOrden(T x) {
        Nodo<T> nuevo = new Nodo<>(x);      // Se instancia un Nodo

        // Caso 1: La lista está vacia o el nuevo elemento es menor que el primero, se usa comparteTo() para comparar los elementos de cada Nodo
        if (getHead() == null || x.compareTo(getHead().dato) < 0) {
            nuevo.next = getHead();     // El nodo Head pasa a ser segundo Nodo
            setHead(nuevo);     // Se actualiza Head con el nuevo Nodo
            return;        // Retorna el metodo sin ningun valor
        }

        // Caso 2: Busca la posicion correcta en el medio o al final
        Nodo<T> actual = getHead();     //Se asigna Head a un Nodo
        
        // Avanza mientras el siguiente no sea nulo y sea menor que el nuevo valor
        while (actual.next != null && actual.next.dato.compareTo(x) < 0) {
            actual = actual.next;
        }

        // Inserta el nodo entre el Nodo 'actual' y 'actual.next'
        nuevo.next = actual.next;
        actual.next = nuevo;
    }
}
