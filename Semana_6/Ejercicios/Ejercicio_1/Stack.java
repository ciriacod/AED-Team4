package Semana_6.Ejercicios.Ejercicio_1;

public interface Stack<E> { //Implementamos una interfaz con los metodos
    void push(E x);        //ingresar elemento al final de tipo generico
    E pop() throws ExceptionIsEmpty;        //metodo sacar nodo del inicio
    E top() throws ExceptionIsEmpty;        //metodo mostrar el tope o rear
    boolean isEmpty();
}
