package Semana_8.Ejercicio_extra;

import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNotFound;
import Semana_8.avltree.AVLTree;

public class Ejercicio2 {

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("   CLÍNICA - GESTIÓN DE TURNOS AVL");
        System.out.println("=================================================\n");

        AVLTree<Integer> turnos = new AVLTree<>();

        // ===================== INSERCIÓN =====================
        System.out.println("----- REGISTRO DE TURNOS -----");

        int[] turnosInsertar = {30, 10, 20, 40, 50, 25};

        for (int turno : turnosInsertar) {

            System.out.println("\n>>> Registrando turno: " + turno);

            try {

                turnos.insert(turno);

                System.out.println("Turno registrado correctamente.");
                System.out.println(turnos);

                System.out.print("Recorrido Inorden: ");
                turnos.inOrder();

            } catch (ItemDuplicated e) {

                System.out.println("Error: el turno ya existe.");
            }
        }

        // ===================== BÚSQUEDA =====================
        System.out.println("\n=================================================");
        System.out.println("            BÚSQUEDA DE TURNOS");
        System.out.println("=================================================");

        int[] turnosBuscar = {20, 60};

        for (int turno : turnosBuscar) {

            System.out.println("\n>>> Buscando turno: " + turno);

            try {

                turnos.search(turno);

                System.out.println("Turno encontrado.");

            } catch (ItemNotFound e) {

                System.out.println("Turno no encontrado.");
            }
        }

        // ===================== ELIMINACIÓN =====================
        System.out.println("\n=================================================");
        System.out.println("          ELIMINACIÓN DE TURNOS");
        System.out.println("=================================================");

        int[] turnosEliminar = {10, 40};

        for (int turno : turnosEliminar) {

            System.out.println("\n>>> Eliminando turno: " + turno);

            try {

                turnos.delete(turno);

                System.out.println("Turno eliminado correctamente.");
                System.out.println(turnos);

                System.out.print("Recorrido Inorden: ");
                turnos.inOrder();

            } catch (ItemNotFound e) {

                System.out.println("Error: turno no encontrado.");
            }
        }

        // ===================== ESTADO FINAL =====================
        System.out.println("\n=================================================");
        System.out.println("             ÁRBOL AVL FINAL");
        System.out.println("=================================================");

        System.out.println(turnos);

        System.out.print("Recorrido final Inorden: ");
        turnos.inOrder();
    }
}