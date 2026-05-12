package Semana_7.Actividades;

import Importar.Exceptions.ExceptionIsEmpty;
import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNoFound;

public class Prueba {

    public static void main(String[] args)
            throws ItemDuplicated, ItemNoFound, ExceptionIsEmpty {

        LinkedBST<Integer> arbol = new LinkedBST<>();

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~|INSERTANDO DATOS|~¬-+~¬-+~¬-+~¬-+~¬

        // Nivel 1
        arbol.insert(50);

        // Nivel 2
        arbol.insert(30);
        arbol.insert(70);

        // Nivel 3
        arbol.insert(20);
        arbol.insert(40);
        arbol.insert(60);
        arbol.insert(80);

        // Nivel 4
        arbol.insert(10);
        arbol.insert(25);
        arbol.insert(35);
        arbol.insert(45);
        arbol.insert(55);
        arbol.insert(65);
        arbol.insert(75);
        arbol.insert(90);

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~|MOSTRAR ARBOL|~¬-+~¬-+~¬-+~¬-+~¬

        System.out.println("ARBOL BST:");
        System.out.println(arbol);

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~|SEARCH|~¬-+~¬-+~¬-+~¬-+~¬

        System.out.println("\nBUSQUEDA:");

        System.out.println("Buscando 40...");
        System.out.println("Encontrado: " + arbol.search(40));

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~|RECORRIDOS|~¬-+~¬-+~¬-+~¬-+~¬

        System.out.println("\nINORDER:");
        arbol.inOrder();

        System.out.println("\n\nPREORDER:");
        arbol.preOrder();

        System.out.println("\n\nPOSTORDER:");
        arbol.postOrder();

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~|MIN Y MAX|~¬-+~¬-+~¬-+~¬-+~¬

        System.out.println("\n\nMINIMO:");
        System.out.println(arbol.findMinNode());

        System.out.println("\nMAXIMO:");
        System.out.println(arbol.findMaxNode());

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~|DELETE|~¬-+~¬-+~¬-+~¬-+~¬

        System.out.println("\nELIMINANDO 70...");
        arbol.delete(70);

        System.out.println("\nARBOL ACTUALIZADO:");
        System.out.println(arbol);
    }
}