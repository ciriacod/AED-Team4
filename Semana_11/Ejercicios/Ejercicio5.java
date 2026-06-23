package Semana_11.Ejercicios;

import java.util.Arrays;

public class Ejercicio5 {

    private int[] table;
    private int M; // Tamaño actual de la tabla
    private int n; // Cantidad de elementos insertados

    public Ejercicio5(int initialSize) {
        this.M = initialSize;
        this.n = 0;
        this.table = new int[M];
        Arrays.fill(this.table, -1); // -1 indica celda vacía
    }

    private int hash(int key, int size) {
        return key % size;
    }

    public void insert(int key) {
        // Al comenzar la inserción, calculamos de forma preventiva cómo afectará al factor de carga
        double alphaFuturo = (double) (n + 1) / M;
        
        if (alphaFuturo > 0.75) {
            System.out.println("\n[ALERTA] Al insertar " + key + ", alpha superaría 0.75 (" + String.format("%.2f", alphaFuturo) + "). Ejecutando REHASHING...");
            rehashing();
        }

        int home = hash(key, M);
        int index = home;
        
        while (table[index] != -1) {
            if (table[index] == key) return; // Evitamos duplicados
            index = (index + 1) % M;
        }

        table[index] = key;
        n++;
        
        double alphaActual = (double) n / M;
        System.out.println("Insertado: " + key + " | Elementos (n): " + n + " | Tamaño (M): " + M + " | Alpha (α): " + String.format("%.2f", alphaActual));
    }

    private void rehashing() {
        int oldM = M;
        int[] oldTable = table;

        // Cambiamos al siguiente tamaño primo especificado por la guía
        this.M = 17;
        this.n = 0;
        this.table = new int[M];
        Arrays.fill(this.table, -1);

        System.out.println("--- Migrando datos de tabla tamaño " + oldM + " a nueva tabla tamaño " + M + " ---");
        
        // Reinsertamos todos los elementos válidos de la tabla anterior
        for (int i = 0; i < oldM; i++) {
            if (oldTable[i] != -1) {
                int key = oldTable[i];
                // Se vuelven a insertar bajo las reglas de la nueva longitud M = 17
                int home = hash(key, M);
                int index = home;
                while (table[index] != -1) {
                    index = (index + 1) % M;
                }
                table[index] = key;
                n++;
            }
        }
    }

    public void printTable() {
        System.out.println("Índice\tValor");
        System.out.println("----------------");
        for (int i = 0; i < M; i++) {
            String val = (table[i] == -1) ? "[VACÍO]" : String.valueOf(table[i]);
            System.out.println(i + "\t" + val);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 5: FACTOR DE CARGA Y REHASHING ===");
        Ejercicio5 hashTab = new Ejercicio5(7);

        // Insertamos los valores requeridos uno por uno
        hashTab.insert(2);
        hashTab.insert(9);
        hashTab.insert(16);
        hashTab.insert(23);
        hashTab.insert(4);

        System.out.println("\n=== ESTADO DE LA TABLA ANTES DEL REHASHING ===");
        hashTab.printTable();

        // Esta inserción gatillará el rehashing antes de acomodarse
        hashTab.insert(11);

        System.out.println("\n=== ESTADO DE LA TABLA DESPUÉS DEL REHASHING ===");
        hashTab.printTable();
    }
}