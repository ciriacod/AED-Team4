package Semana_11.Test;

import Semana_11.hash.HashC;
import Semana_11.hash.HashO;

public class Hash {
    public static void main(String[] args) {
        
        // =================================================================
        // PART 1: HASH CERRADO (HashC) - Tamaño 11
        // =================================================================
        System.out.println("--- PRUEBA DE HASH CERRADO (Sondeo Lineal) ---");
        HashC tablaCerrada = new HashC(11);

        tablaCerrada.insert(34, "Lucas");
        tablaCerrada.insert(3, "Mateo");
        tablaCerrada.insert(7, "Juan");
        tablaCerrada.insert(30, "Maria");
        tablaCerrada.insert(11, "Pedro");
        tablaCerrada.insert(8, "Ana");
        tablaCerrada.insert(7, "Sofia");   // Actualiza clave 7
        tablaCerrada.insert(23, "Diego");
        tablaCerrada.insert(41, "Luis");
        tablaCerrada.insert(16, "Elena");
        tablaCerrada.insert(34, "Carlos"); // Actualiza clave 34

        System.out.println("\n=== HashC: Antes de eliminar ===");
        tablaCerrada.printTable();

        System.out.println("\n-> Eliminando clave 30...");
        tablaCerrada.delete(30);

        System.out.println("\n=== HashC: Después de eliminar ===");
        tablaCerrada.printTable();


        // =================================================================
        // PART 2: HASH ABIERTO (HashO) - Tamaño 5 (Fuerza colisiones en índice 0)
        // =================================================================
        System.out.println("\n\n--- PRUEBA DE HASH ABIERTO (Encadenamiento) ---");
        HashO tablaAbierta = new HashO(5);

        // Forzamos colisiones múltiples en la lista del índice 0 (5%5=0, 10%5=0, 15%5=0)
        System.out.println("-> Insertando elementos con colisiones directas en índice 0...");
        tablaAbierta.insert(5, "Usuario_A");
        tablaAbierta.insert(10, "Usuario_B");
        tablaAbierta.insert(15, "Usuario_C");
        
        // Insertamos datos en otros índices
        tablaAbierta.insert(1, "Usuario_D");
        tablaAbierta.insert(6, "Usuario_E"); // Colisiona en índice 1 (6%5=1)
        tablaAbierta.insert(10, "Usuario_B_Actualizado"); // Duplicado

        System.out.println("\n=== HashO: Contenido de cada lista (Antes de eliminar) ===");
        System.out.println(tablaAbierta.toString());

        System.out.println("-> Eliminando físicamente la clave 10 de la lista...");
        tablaAbierta.delete(10);

        System.out.println("\n=== HashO: Contenido de cada lista (Después de eliminar) ===");
        System.out.println(tablaAbierta.toString());
    }
}