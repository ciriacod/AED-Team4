package Semana_11.Ejercicios;

import Semana_11.hash.HashO;
import Semana_11.hash.Register;

public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 3: TABLA HASH ABIERTA ===");
        HashO tabla = new HashO(7);

        tabla.insert(10, "Juan");
        tabla.insert(17, "Ana");
        tabla.insert(24, "Luis");
        tabla.insert(31, "Rosa");
        tabla.insert(5, "Pedro");
        tabla.insert(12, "Carla");

        System.out.println("\n=== ESTADO INICIAL DE LA TABLA ===");
        System.out.println(tabla.toString());

        // 1. Búsqueda de la clave 24
        System.out.println("--- 1. OPERACIÓN DE BÚSQUEDA ---");
        Register reg = tabla.search(24);
        if (reg != null) {
            System.out.println("Clave 24 encontrada. Nombre asociado: " + reg.getValue());
        }

        // 2. Eliminación de la clave 17
        System.out.println("\n--- 2. OPERACIÓN DE ELIMINACIÓN ---");
        tabla.delete(17);

        System.out.println("\n=== ESTADO FINAL DE LA TABLA LUEGO DE ELIMINAR ===");
        System.out.println(tabla.toString());
    }
}