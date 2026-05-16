package Semana_7.Actividades;

import Importar.Exceptions.ExceptionIsEmpty;

public class Prueba {
    public static <E extends Comparable<E>> boolean sameArea(
        LinkedBST<E> tree1, LinkedBST<E> tree2) throws ExceptionIsEmpty {
            return tree1.areaBST() == tree2.areaBST();
        }
    public static void main(String[] args) {
        
    }
}