package Semana_6.Actividades.Actividad_2;

import Semana_6.Actividades.Actividad_1.ExceptionIsEmpty;

public interface Deque<E> {
    void addFirst(E x);
    void addLast(E x);
    E removeFirst() throws ExceptionIsEmpty;
    E removeLast() throws ExceptionIsEmpty;
    E getFirst() throws ExceptionIsEmpty;
    E getLast() throws ExceptionIsEmpty;
    boolean isEmpty();    
}
