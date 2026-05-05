package Semana_6.Ejercicios.Ejercicio_1;

public interface Stack<E> { //Implementamos una interfaz con los metodos
    void push(E x);        //ingresar elemento en el tope
    E pop() throws ExceptionIsEmpty;        //metodo sacar el ultimo elemento ingresado
    E top() throws ExceptionIsEmpty;        //metodo mostrar el tope
    boolean isEmpty();
}
