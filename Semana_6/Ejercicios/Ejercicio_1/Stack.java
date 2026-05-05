package Semana_6.Ejercicios.Ejercicio_1;

public interface Stack<E> {
    void push(E x);
    E pop() throws ExceptionIsEmpty;
    E top() throws ExceptionIsEmpty;
    boolean isEmpty();
}
