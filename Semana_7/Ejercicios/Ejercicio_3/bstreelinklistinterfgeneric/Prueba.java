package bstreelinklistinterfgeneric;

public class Prueba {
    
    // Compara si dos arboles son iguales en area
    public static <T extends Comparable<T>> boolean sameArea(LinkedBST<T> tree1, LinkedBST<T> tree2) {
        return tree1.areaBST() == tree2.areaBST();
    }
    
    // Imprime las estadisticas del arbol    
    public static void printTreeStats(String nombre, LinkedBST<Integer> tree) {
        System.out.println(">>> " + nombre);
        System.out.println("Estructura visual:");
        System.out.println(tree.toString()); // Usa tu buildString recursivo //funcion auxiliar
        System.out.println("Resultado areaBST(): " + tree.areaBST());
        System.out.println("----------------------------------------\n");
    }
}
