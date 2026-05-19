package Semana_8.Actividades;

import Semana_8.avltree.AVLTree;

public class TestAVL {
    public static void main(String[] args) {
        
        // ========== SIN ROTACIÓN ==========
        System.out.println("--- SIN ROTACIÓN ---");
        AVLTree<Integer> a1 = new AVLTree<>();
        a1.insert(50);
        a1.insert(30);
        a1.insert(70);
        a1.inOrder();
        a1.drawBST();
        
        AVLTree<Integer> a2 = new AVLTree<>();
        a2.insert(20);
        a2.insert(10);
        a2.insert(30);
        a2.inOrder();
        a2.drawBST();
        
        // ========== RSR (Izquierda-Izquierda) ==========
        System.out.println("\n--- RSR ---");
        AVLTree<Integer> b1 = new AVLTree<>();
        b1.insert(30);
        b1.insert(20);
        b1.insert(10);
        b1.inOrder();
        b1.drawBST();
        
        AVLTree<Integer> b2 = new AVLTree<>();
        b2.insert(50);
        b2.insert(40);
        b2.insert(30);
        b2.inOrder();
        b2.drawBST();
        
        // ========== RSL (Derecha-Derecha) ==========
        System.out.println("\n--- RSL ---");
        AVLTree<Integer> c1 = new AVLTree<>();
        c1.insert(10);
        c1.insert(20);
        c1.insert(30);
        c1.inOrder();
        c1.drawBST();
        
        AVLTree<Integer> c2 = new AVLTree<>();
        c2.insert(15);
        c2.insert(25);
        c2.insert(35);
        c2.inOrder();
        c2.drawBST();
        
        // ========== RDR (Izquierda-Derecha) ==========
        System.out.println("\n--- RDR ---");
        AVLTree<Integer> d1 = new AVLTree<>();
        d1.insert(30);
        d1.insert(10);
        d1.insert(20);
        d1.inOrder();
        d1.drawBST();
        
        AVLTree<Integer> d2 = new AVLTree<>();
        d2.insert(40);
        d2.insert(20);
        d2.insert(30);
        d2.inOrder();
        d2.drawBST();
        
        // ========== RDL (Derecha-Izquierda) ==========
        System.out.println("\n--- RDL ---");
        AVLTree<Integer> e1 = new AVLTree<>();
        e1.insert(10);
        e1.insert(30);
        e1.insert(20);
        e1.inOrder();
        e1.drawBST();
        
        AVLTree<Integer> e2 = new AVLTree<>();
        e2.insert(20);
        e2.insert(50);
        e2.insert(40);
        e2.inOrder();
        e2.drawBST();
    }
}