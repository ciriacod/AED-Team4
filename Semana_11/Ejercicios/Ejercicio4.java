package Semana_11.Ejercicios;

import Semana_11.hash.HashC;
import Semana_11.hash.Register;

public class Ejercicio4 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 4: ELIMINACIÓN LÓGICA POR ESTADOS (0, 1, -1) ===");
        
        // Tabla hash de tamaño 7
        HashC<String> tabla = new HashC<>(7);

        // Inserción inicial de claves: 5, 12, 19, 26
        tabla.insert(5, "Cinco");
        tabla.insert(12, "Doce");
        tabla.insert(19, "Diecinueve");
        tabla.insert(26, "Veintiseis");

        System.out.println("\n=== ESTADO INICIAL DE LA TABLA ===");
        tabla.printTable();

        // 1. Eliminar lógicamente la clave 12
        System.out.println("\n--- 1. ELIMINACIÓN LÓGICA ---");
        tabla.delete(12);
        tabla.printTable();

        // 2. Buscar la clave 19 tras el borrado
        System.out.println("\n--- 2. PRUEBA DE BÚSQUEDA TRAS ELIMINACIÓN ---");
        Register<String> reg19 = tabla.search(19);
        System.out.println("Buscando clave 19 -> Resultado: " + (reg19 != null ? "ENCONTRADA: " + reg19.getValue() : "NO ENCONTRADA"));

        // 3. Reinsertar la clave 33 (33 % 7 = 5) para verificar reutilización
        System.out.println("\n--- 3. REINSERCIÓN Y REUTILIZACIÓN DE CELDAS ---");
        System.out.println("Insertando clave 33...");
        tabla.insert(33, "Treinta y tres");
        tabla.printTable();
    }
}