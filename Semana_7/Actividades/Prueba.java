package Semana_7.Actividades;

import Importar.Exceptions.*;

public class Prueba {

    public static void main(String[] args) {

        try {

            // =====================================================
            // EJERCICIO 01
            // =====================================================

            System.out.println("=================================");
            System.out.println("EJERCICIO 01");
            System.out.println("=================================");

            LinkedBST<Integer> bst = new LinkedBST<>();

            // Inserciones
            bst.insert(15);
            bst.insert(8);
            bst.insert(22);
            bst.insert(5);
            bst.insert(12);
            bst.insert(18);
            bst.insert(30);

            // Mostrar árbol
            System.out.println("\nARBOL BST:");
            bst.drawBST();

            // -----------------------------------------------------
            // RECORRIDOS
            // -----------------------------------------------------

            System.out.println("INORDER:");
            bst.inOrder();

            System.out.println("PREORDER:");
            bst.preOrder();

            System.out.println("POSTORDER:");
            bst.postOrder();

            // -----------------------------------------------------
            // BUSQUEDAS
            // -----------------------------------------------------

            System.out.println("\nBUSQUEDA DE 12:");
            System.out.println("Elemento encontrado: " + bst.search(12));

            System.out.println("\nBUSQUEDA DE 21:");

            try {
                System.out.println(bst.search(21));
            } catch (ItemNotFound e) {
                System.out.println(e.getMessage());
            }

            // -----------------------------------------------------
            // ANALISIS BASICO
            // -----------------------------------------------------

            System.out.println("\nALTURA DEL ARBOL:");
            System.out.println(bst.height(15));

            System.out.println("\nTOTAL DE NODOS:");
            System.out.println(bst.countAllNodes());

            System.out.println("\nNODOS NO HOJA:");
            System.out.println(bst.countNodes());

            System.out.println("\nNODOS HOJA:");
            System.out.println(bst.countLeaves());

            System.out.println("\nAMPLITUD DEL ARBOL:");
            System.out.println(bst.amplitude());

            // =====================================================
            // EJERCICIO 02
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("EJERCICIO 02");
            System.out.println("=================================");

            System.out.println("\nAREA DEL BST:");
            System.out.println(bst.areaBST());

            // =====================================================
            // EJERCICIO 03
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("EJERCICIO 03");
            System.out.println("=================================");

            LinkedBST<Integer> arbol1 = new LinkedBST<>();
            LinkedBST<Integer> arbol2 = new LinkedBST<>();

            // Árbol 1
            arbol1.insert(10);
            arbol1.insert(5);
            arbol1.insert(20);

            // Árbol 2
            arbol2.insert(50);
            arbol2.insert(30);
            arbol2.insert(80);

            System.out.println("\nARBOL 1:");
            arbol1.drawBST();

            System.out.println("\nARBOL 2:");
            arbol2.drawBST();

            System.out.println("\nMISMA AREA?");
            System.out.println(sameArea(arbol1, arbol2));

            // =====================================================
            // EJERCICIO 04
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("EJERCICIO 04");
            System.out.println("=================================");

            System.out.println("\nREPRESENTACION PARENTHESIZE:");
            bst.parenthesize();

            // =====================================================
            // EJERCICIO 05
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("EJERCICIO 05");
            System.out.println("=================================");

            System.out.println("\nVALIDACION BST:");
            System.out.println(bst.isValidBST());

            // =====================================================
            // EJERCICIO 06
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("EJERCICIO 06");
            System.out.println("=================================");

            LinkedBST<Integer> inventario = new LinkedBST<>();

            inventario.insert(100);
            inventario.insert(80);
            inventario.insert(150);
            inventario.insert(60);
            inventario.insert(90);
            inventario.insert(120);
            inventario.insert(200);

            System.out.println("\nARBOL INVENTARIO:");
            inventario.drawBST();

            // -----------------------------------------------------
            // searchRange()
            // -----------------------------------------------------

            System.out.println("\nPRODUCTOS ENTRE 80 Y 150:");
            inventario.searchRange(80, 150);

            // -----------------------------------------------------
            // countLeaves()
            // -----------------------------------------------------

            System.out.println("\nCANTIDAD DE HOJAS:");
            System.out.println(inventario.countLeaves());

            // -----------------------------------------------------
            // printDescending()
            // -----------------------------------------------------

            System.out.println("\nORDEN DESCENDENTE:");
            inventario.printDescending();

            // =====================================================
            // DELETE
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("PRUEBA DELETE");
            System.out.println("=================================");

            System.out.println("\nARBOL ORIGINAL:");
            bst.drawBST();

            bst.delete(22);

            System.out.println("\nARBOL DESPUES DE ELIMINAR 22:");
            bst.drawBST();

            // =====================================================
            // DESTROY NODES
            // =====================================================

            System.out.println("\n=================================");
            System.out.println("PRUEBA destroyNodes()");
            System.out.println("=================================");

            bst.destroyNodes();

            System.out.println("ARBOL ELIMINADO");

            System.out.println("¿ESTA VACIO?");
            System.out.println(bst.isEmpty());

        } catch (Exception e) {

            System.out.println("ERROR:");
            System.out.println(e.getMessage());

        }
    }

    // =====================================================
    // sameArea()
    // =====================================================

    public static <E extends Comparable<E>> boolean sameArea(
            LinkedBST<E> tree1,
            LinkedBST<E> tree2) throws ExceptionIsEmpty {

        return tree1.areaBST() == tree2.areaBST();
    }
}