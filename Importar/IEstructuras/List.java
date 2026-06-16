package Importar.IEstructuras;

public interface List<T> {
    boolean isEmpty();
    int size();
    void insert(T data);
    void addLast(T data);
    T get(int index);
    boolean search(T data);
    void remove(T data);
    void clear();
}