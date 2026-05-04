package Semana_6.Actividades.Actividad_1;

public interface Queue<E>{
    void enqueue (E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    boolean isEmpty();
}