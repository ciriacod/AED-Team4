package Ejercicio04;

import Semana_9.btree.Bnode;
import Semana_9.btree.Btree;

public class Main {
    public static void main(String[] args) {
        /*
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.cargarDesdeArchivo("biblioteca.txt");

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
        */
       Btree<Integer> arbol = new Btree<>(3);
       arbol.insert(10);
       arbol.insert(20);
       arbol.insert(30);

       System.out.println(arbol.toString());
       arbol.search(10);

       arbol.searchPath(40);

    }
}
