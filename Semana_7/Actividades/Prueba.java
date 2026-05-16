package Semana_7.Actividades;

import Importar.Estructuras.LinkedBST;

public class Prueba {
    public static void main(String[] args) {
        LinkedBST<Integer> arbol1 = new LinkedBST<>();
        arbol1.insert(23);
        arbol1.insert(43);
        arbol1.insert(11);

        LinkedBST<Integer> arbol2 = new LinkedBST<>();
        arbol1.insert(21);
        arbol1.insert(43);
        arbol1.insert(10);

        System.out.println(2);
    }

    public static <E extends Comparable<E>> boolean sameArea(
        LinkedBST<E> tree1, LinkedBST<E> tree2){
            return tree1.areaBST() == tree2.areaBST();
        }
}