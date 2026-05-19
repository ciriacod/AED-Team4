package Semana_8.Ejercicios;

import Semana_8.avltree.AVLTree;

public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 3: ELIMINACIÓN EN UN ÁRBOL AVL ===");
        AVLTree<Integer> avl = new AVLTree<>();

        int[] valores = {50, 30, 70, 20, 40, 60, 80};
        for (int v : valores) {
            try { avl.insert(v); } catch (Exception e) {}
        }

        System.out.print("Árbol original (Amplitud): ");
        avl.breadthFirstRecursive();
        System.out.println("--------------------------------------------------");

        try {
            // Caso 1: Eliminar un nodo hoja (20)
            System.out.println("1. Eliminando nodo hoja (20)...");
            avl.delete(20);
            System.out.print("Resultado: "); avl.breadthFirstRecursive();
            System.out.println(avl);

            // Caso 2: Eliminar un nodo con un hijo (el 30 ahora solo tiene al 40)
            System.out.println("2. Eliminando nodo con un hijo (30)...");
            avl.delete(30);
            System.out.print("Resultado: "); avl.breadthFirstRecursive();
            System.out.println(avl);

            // Caso 3: Eliminar nodo con dos hijos (la raíz 50)
            // Aquí tu código usará el Sucesor Inorden (el mínimo del subárbol derecho, que sería 60)
            System.out.println("3. Eliminando nodo con dos hijos (Raíz 50)...");
            avl.delete(50);
            System.out.print("Resultado final: "); avl.breadthFirstRecursive();
            System.out.println(avl);

        } catch (Exception e) {
            System.out.println("Error en la eliminación: " + e.getMessage());
        }
        System.out.println("--------------------------------------------------");
    }
}