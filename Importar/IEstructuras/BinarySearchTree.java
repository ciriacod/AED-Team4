package Importar.IEstructuras;

public interface BinarySearchTree<E extends Comparable<E>> {

    // Operaciones básicas
    void insert(E data);
    E search(E data);
    void delete(E data);
    boolean isEmpty();

    // Recorridos
    void inOrder();
    void preOrder();
    void postOrder();

    // Información del árbol
    int height(E data);
    int amplitude();
    int areaBST();
    int countAllNodes();
    int countInternalNodes();
    int countLeaves();

    // Operaciones auxiliares
    void destroyNodes();
    boolean isValidBST();
    void searchRange(E min, E max);
    void printDescending();

    // Visualización
    void drawBST();
    void parenthesize();
}