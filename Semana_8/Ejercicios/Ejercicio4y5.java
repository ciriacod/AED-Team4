package Semana_8.Ejercicios;

import Semana_8.avltree.AVLTree;

public class Ejercicio4y5 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIOS 4 Y 5: RECORRIDO POR AMPLITUD RECURSIVO ===");
        AVLTree<Integer> avl = new AVLTree<>();

        // Insertamos los datos en un orden que al balancearse formen el árbol objetivo
        int[] datos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 65};
        for (int d : datos) {
            try { avl.insert(d); } catch (Exception e) {}
        }

        // Test del recorrido solicitado en el Ejercicio 5
        System.out.print("Recorrido por Niveles (Debe ser: 50 30 70 20 40 60 80 10 25 65): \n--> ");
        avl.breadthFirstRecursive();
        System.out.println("--------------------------------------------------");

        // Comparación con otros recorridos heredados de la interfaz
        System.out.print("Comparación - Recorrido InOrden (Ordenado): ");
        avl.inOrder(); 
        
        System.out.print("Comparación - Recorrido PreOrden (Especializado): ");
        avl.preOrder();
        System.out.println("--------------------------------------------------");
    }
}