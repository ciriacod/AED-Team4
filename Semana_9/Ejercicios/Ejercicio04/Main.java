package Semana_9.Ejercicios.Ejercicio04;

import Semana_9.btree.Btree;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cargarDesdeArchivo("Semana_9/btree/Ejercicios/Ejercicio04/biblioteca.txt");

        System.out.println("\nLibros ordenados por ISBN:");
        biblioteca.mostrarLibrosOrdenados();

        System.out.println("\nBúsqueda con camino:");
        biblioteca.buscarPorISBN("9780132350884");

        System.out.println();
        biblioteca.mostrarAltura();
        biblioteca.mostrarCantidadTotal();

        System.out.println("\nEliminar libro:");
        biblioteca.eliminarLibro("9780201633610");

        System.out.println("\nLibros después de eliminar:");
        biblioteca.mostrarLibrosOrdenados();
    
        Btree<Integer> arbol = new Btree<>(3);
        for(int i = 0 ; i < 11; i++)
            arbol.insert(i);

        System.out.println(arbol.toString());

        for(int i = 3 ; i < 6; i++)
            arbol.delete(i);

        System.out.println(arbol.toString());
    }
}
