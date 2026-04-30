
package Ejercicio6;

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
    
    public static void main(String[] args) {
        // --- PREPARACIÓN DE DATOS ---
        ListLinked<Integer> L1 = new ListLinked<>();
        L1.insertLast(10);
        L1.insertLast(20);
        L1.insertLast(30);

        ListLinked<Integer> L2 = new ListLinked<>();
        L2.insertLast(10);
        L2.insertLast(20);
        L2.insertLast(30);

        ListLinked<Integer> L3 = new ListLinked<>();
        L3.insertLast(40);
        L3.insertLast(50);

        // --- PRUEBA EJERCICIO 6: CONCATENAR ---
        System.out.println("=== EJERCICIO 6: CONCATENACION ===");
        ListLinked<Integer> L_Resultante = concatenarListas(L1, L3);
        
        System.out.print("Lista 1: "); L1.mostrar();
        System.out.print("Lista 3: "); L3.mostrar();
        System.out.print("Resultado Concatenacion: "); L_Resultante.mostrar();
        System.out.println("(Verifica que L1 y L3 no cambiaron)");
    }
}
