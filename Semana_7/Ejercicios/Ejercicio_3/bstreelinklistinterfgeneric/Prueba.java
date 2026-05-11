
package bstreelinklistinterfgeneric3;

public class Prueba {
    
    // Compara si dos arboles son iguales en area
    public static <T extends Comparable<T>>
        boolean sameArea(LinkedBST<T> tree1,
                         LinkedBST<T> tree2) {

            return tree1.areaBST() == tree2.areaBST();
        }
}
