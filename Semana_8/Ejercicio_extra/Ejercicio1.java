package Semana_8.Ejercicio_extra;

import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNotFound;
import Semana_8.avltree.AVLTree;

public class Ejercicio1 {

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("   REGISTRO DE PRODUCTOS - ÁRBOL AVL");
        System.out.println("=================================================\n");

        AVLTree<Integer> productos = new AVLTree<>();

        // ===================== INSERTAR PRODUCTOS =====================
        System.out.println("----- REGISTRO DE PRODUCTOS -----");

        int[] codigosInsertar = {30, 10, 20, 40, 50, 25};

        for (int codigo : codigosInsertar) {

            System.out.println("\n>>> Insertando producto: " + codigo);

            try {

                productos.insert(codigo);

                System.out.println("Producto registrado correctamente.");
                System.out.println(productos);

                System.out.print("Recorrido Inorden: ");
                productos.inOrder();

            } catch (ItemDuplicated e) {

                System.out.println("Error: el producto ya existe.");
            }
        }

        // ===================== BUSQUEDA =====================
        System.out.println("\n=================================================");
        System.out.println("           BÚSQUEDA DE PRODUCTOS");
        System.out.println("=================================================");

        int[] productosBuscar = {20, 60};

        for (int codigo : productosBuscar) {

            System.out.println("\n>>> Buscando producto: " + codigo);

            try {

                productos.search(codigo);

                System.out.println("Producto encontrado.");

            } catch (ItemNotFound e) {

                System.out.println("Producto no encontrado.");
            }
        }

        // ===================== ELIMINACION =====================
        System.out.println("\n=================================================");
        System.out.println("          ELIMINACIÓN DE PRODUCTOS");
        System.out.println("=================================================");

        int[] productosEliminar = {10, 40};

        for (int codigo : productosEliminar) {

            System.out.println("\n>>> Eliminando producto: " + codigo);

            try {

                productos.delete(codigo);

                System.out.println("Producto eliminado correctamente.");
                System.out.println(productos);

                System.out.print("Recorrido Inorden: ");
                productos.inOrder();

            } catch (ItemNotFound e) {

                System.out.println("Error: producto no encontrado.");
            }
        }

        // ===================== ESTADO FINAL =====================
        System.out.println("\n=================================================");
        System.out.println("              ÁRBOL AVL FINAL");
        System.out.println("=================================================");

        System.out.println(productos);

        System.out.print("Recorrido final Inorden: ");
        productos.inOrder();
    }
}