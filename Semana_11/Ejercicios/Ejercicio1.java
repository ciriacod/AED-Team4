package Semana_11.Ejercicios;

import Semana_11.hash.HashC;

public class Ejercicio1 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 1: ANALISIS DE FUNCIÓN HASH USANDO HASHC ===");
        
        // Instanciamos tu estructura oficial de dispersión cerrada con tamaño 11
        HashC<String> tabla = new HashC<>(11);
        
        // Las claves indicadas en el enunciado de la guía
        int[] claves = {3, 14, 25, 36, 47, 58};

        System.out.println("-> Insertando elementos en la estructura...");
        for (int i = 0; i < claves.length; i++) {
            // Usamos tu método insert sobrecargado que encapsula el Register
            tabla.insert(claves[i], "Elemento_" + (i + 1));
        }

        // Mostramos el estado de la tabla usando tu método oficial printTable()
        System.out.println("\n=== ESTADO FINAL DE LA TABLA HASH (CON SONDEO LINEAL) ===");
        tabla.printTable();
    }
}