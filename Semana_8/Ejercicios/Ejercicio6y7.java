package Semana_8.Ejercicios;

import Semana_8.avltree.AVLTree;

public class Ejercicio6y7 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIOS 6 Y 7: CASOS CRÍTICOS DE ROTACIÓN ===");
        AVLTree<Integer> avl = new AVLTree<>();

        try {
            // Secuencia que provoca desbalance Izquierda-Izquierda (Requiere rotación simple derecha)
            System.out.println("Insertando 30, 20, 10 (Fuerza desbalance Izquierda-Izquierda en 30)...");
            avl.insert(30);
            avl.insert(20);
            avl.insert(10); 
            System.out.print("Preorden tras balanceo (20 debería ser la raíz): "); 
            avl.preOrder();
            System.out.println(avl);

            // Secuencia que provoca desbalance Derecha-Derecha (Requiere rotación simple izquierda)
            System.out.println("Insertando 40, 50 (Fuerza desbalance Derecha-Derecha en 30)...");
            avl.insert(40);
            avl.insert(50);
            System.out.print("Preorden tras balanceo: "); 
            avl.preOrder();
            System.out.println(avl);

            // Provocar nuevas rotaciones mediante una eliminación drástica
            System.out.println("Eliminando la raíz actual (30) para forzar una reestructuración interna...");
            avl.delete(30);
            System.out.print("Estructura final en Amplitud: ");
            avl.breadthFirstRecursive();
            System.out.println(avl);

        } catch (Exception e) {
            System.out.println("Error en las pruebas de rotación: " + e.getMessage());
        }
        System.out.println("--------------------------------------------------");
    }
}