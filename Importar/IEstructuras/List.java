package Importar.IEstructuras;

public interface List<T extends Comparable<T>> {
    boolean isEmpty();
    int size();
    void insert(T data);
    boolean search(T data);
    void remove (T data);
    void clear ();
}