package Semana_11.Ejercicios;

import Semana_11.hash.HashC;

public class Ejercicio5 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 5: FACTOR DE CARGA Y REHASHING DESDE HASHC ===");
        
        // Inicializamos HashC con tamaño 7 y pasamos 'true' para activar el autoRehash preventivo
        HashC tablaConRehash = new HashC(7, true);

        // Insertamos los valores requeridos uno por uno
        tablaConRehash.insert(2, "Dos");
        tablaConRehash.insert(9, "Nueve");
        tablaConRehash.insert(16, "Dieciseis");
        tablaConRehash.insert(23, "Veintitres");
        tablaConRehash.insert(4, "Cuatro");

        System.out.println("\n=== ESTADO DE LA TABLA ANTES DEL REHASHING ===");
        tablaConRehash.printTable();

        // Esta inserción superará el factor de 0.75 y gatillará automáticamente el método interno
        tablaConRehash.insert(11, "Once");

        System.out.println("\n=== ESTADO DE LA TABLA DESPUÉS DEL REHASHING ===");
        tablaConRehash.printTable();
    }
}