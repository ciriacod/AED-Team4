
package Semana_5.Ejercicios.Ejercicio_5;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N°5   ------------------  *******************
 ***********************************************************************************************
 */

public class SonIguales {
    
    //Metodo que verifica si 2 listas son completamente iguales
    public static <T> boolean sonIguales(ListLinked<T> lista1, ListLinked<T> lista2) {
    
    // Se instancia los Head de ambas listas
    Nodo<T> actual1 = lista1.getHead();
    Nodo<T> actual2 = lista2.getHead();

    // Se recorre ambas listas mientras tengan Nodos
    while (actual1 != null && actual2 != null) {
        
        // Se compara los datos de los nodos actuales con el uso de equals() para manejar los genericos
        if (!actual1.dato.equals(actual2.dato)) {
            return false;    // Si un dato es diferente, las listas no son iguales
        }
        
        // Avanza al siguiente Nodo en ambas listas
        actual1 = actual1.next;
        actual2 = actual2.next;
    }

    // Verifica en este return si ambas listas resultaron de ser del mismo tamaño.
    return actual1 == null && actual2 == null;
}
}
