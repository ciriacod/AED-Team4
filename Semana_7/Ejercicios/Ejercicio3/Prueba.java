
package Ejercicio3;

public class Prueba {
    
    // Compara si dos arboles son iguales en area
    public static <T extends Comparable<T>> boolean sameArea(LinkedBST_Ejercicio3<T> tree1, LinkedBST_Ejercicio3<T> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
    
}
