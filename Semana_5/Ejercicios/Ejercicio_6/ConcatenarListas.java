
package Semana_5.Ejercicios.Ejercicio_6;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°6   ------------------  *******************
 ***********************************************************************************************
 */

public class ConcatenarListas {
    
    // Metodo que permite concatenar dos listas sin modificar el contenido inicial
    public static <T> ListLinked<T> concatenarListas(ListLinked<T> lista1, ListLinked<T> lista2) {
        ListLinked<T> listaResultante = new ListLinked<>();    // Instancia de la clase ListLinked

        // Copia de elementos de la primera lista
        Nodo<T> actual = lista1.getHead();     // Instancia de una variable de tipo Nodo con el valor Head de una primera lista
        
        // Verifica si el Nodo de lista1 en algun momento se vuelve nulo
        while (actual != null) {
            listaResultante.insertLast(actual.dato);        //Se almacena el Nodo de lista1 a la nueva lista creada
            actual = actual.next;           // Se asigna al Nodo siguiente como el Nodo actual
        }
        
        // Copia de elementos de la segunda lista
        actual = lista2.getHead();
        
        // Verifica si el Nodo de lista2 en algun momento se vuelve nulo
        while (actual != null) {
            listaResultante.insertLast(actual.dato);        //Se almacena el Nodo de lista2 a la nueva lista creada
            actual = actual.next;           // Se asigna al Nodo siguiente como el Nodo actual
        }

        return listaResultante;         // Retorna la nueva lista concatenada con las listas pasadas por parametro.
    }
}
