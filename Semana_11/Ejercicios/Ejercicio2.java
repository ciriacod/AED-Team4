package Semana_11.Ejercicios;

import Semana_11.hash.HashC;

public class Ejercicio2 {
    public static void main(String[] args) {
        int[] valores = {12, 19, 26, 33, 40, 47};

        // 1. Instanciamos HashC configurando Sondeo Lineal (tipo 0)
        System.out.println("=== PRUEBA UTILIZANDO HASHC: SONDEO LINEAL ===");
        HashC<String> tablaLineal = new HashC<>(7, false, 0);
        for (int val : valores) {
            tablaLineal.insert(val, "Val_" + val);
        }
        System.out.println("\nESTADO FINAL (LINEAL):");
        tablaLineal.printTable();

        // 2. Instanciamos HashC configurando Sondeo Cuadrático (tipo 1)
        System.out.println("\n=== PRUEBA UTILIZANDO HASHC: SONDEO CUADRÁTICO ===");
        HashC<String> tablaCuadratica = new HashC<>(7, false, 1);
        for (int val : valores) {
            tablaCuadratica.insert(val, "Val_" + val);
        }
        System.out.println("\nESTADO FINAL (CUADRÁTICO):");
        tablaCuadratica.printTable();
    }
}