package Semana_11.Ejercicios;

import java.util.Arrays;

public class Ejercicio2 {
    
    public static void main(String[] args) {
        int[] valores = {12, 19, 26, 33, 40, 47};
        int size = 7;

        System.out.println("=== SIMULACIÓN: SONDEO LINEAL ===");
        simularSondeoLineal(valores, size);

        System.out.println("\n=== SIMULACIÓN: SONDEO CUADRÁTICO ===");
        simularSondeoCuadratico(valores, size);
    }

    public static void simularSondeoLineal(int[] valores, int size) {
        int[] tabla = new int[size];
        Arrays.fill(tabla, -1);

        for (int x : valores) {
            int home = x % size;
            int i = 0;
            int pos = home;

            // Secuencia lineal: (home + i) % size
            while (tabla[pos] != -1) {
                i++; 
                pos = (home + i) % size;
            }
            tabla[pos] = x;
            System.out.println("Clave " + x + " colocada en índice " + pos + " (Saltos/Colisiones: " + i + ")");
        }
    }

    public static void simularSondeoCuadratico(int[] valores, int size) {
        int[] tabla = new int[size];
        Arrays.fill(tabla, -1);

        for (int x : valores) {
            int home = x % size;
            int i = 0;
            int pos = home;

            // Secuencia cuadrática: (home + i^2) % size
            while (tabla[pos] != -1) {
                i++;
                pos = (home + (i * i)) % size;
                
                // Control para evitar bucles infinitos en tablas pequeñas
                if (i >= size) { 
                    System.out.println("Clave " + x + " -> ¡Error! El sondeo cuadrático entró en bucle infinito.");
                    return;
                }
            }
            tabla[pos] = x;
            System.out.println("Clave " + x + " colocada en índice " + pos + " (Saltos/Colisiones: " + i + ")");
        }
    }
}