package Semana_8.Ejercicios;

import Importar.Estructuras.LinkedBST;
import Semana_8.avltree.AVLTree;

public class Ejercicio2 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 2: COMPARACIÓN BST vs AVL ===");
        LinkedBST<Integer> bst = new LinkedBST<>();
        AVLTree<Integer> avl = new AVLTree<>();
        
        // Insertar secuencia crítica (ordenada) para forzar desbalance en BST
        int[] secuenciaOrdenada = {10, 20, 30, 40, 50};
        
        for (int num : secuenciaOrdenada) {
            try {
                bst.insert(num);
                avl.insert(num);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        System.out.print("Preorden BST: ");
        bst.preOrder(); 
        System.out.println("--------------------------------------------------\n");
        System.out.println(bst);
        System.out.print("Preorden AVL: ");
        avl.preOrder();
        System.out.println("--------------------------------------------------\n");
        System.out.println(avl);
    }
}
