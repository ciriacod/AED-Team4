package Semana_6.Actividades.Actividad_2;

import Importar.Node;
import Semana_6.Actividades.Actividad_1.ExceptionIsEmpty;

public class DequeLink<E> implements Deque<E> {
    private Node<E> first;
    private Node<E> last;
    public DequeLink() {
        first = null;
        last = null;
    }
    @Override
    public void addFirst(E x) {
        Node<E> novo = new Node<>(x);
        if (isEmpty()) first = last = novo;
        else {
            novo.next = first;
            first = novo;
        }
    }
    @Override
    public void addLast(E x) {
        Node<E> novo = new Node<>(x);
        if (isEmpty()) first = last = novo;
        else{
            last.next = novo;
            last = novo;
        }
    }
    @Override
    public E removeFirst() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Deque Vacia");
        Node<E> morto = first;
        
        if (first.next != null) first = first.next;
        else{
            first = last = null;
        }
        return morto.getDato();
    }
    @Override
    public E removeLast() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Deque Vacia");
        Node<E> iterador = first;
        Node<E> morto = last;
        if (first == last) first = last = null;
        else{
            while (iterador.next != last){
                iterador = iterador.next;
            }
            last = iterador;
            last.next = null;
        }
        return morto.getDato();
    }
    @Override
    public E getFirst() throws ExceptionIsEmpty {
        if(isEmpty()) throw new ExceptionIsEmpty("Deque Vacia");
        return first.getDato();
    }
    @Override
    public E getLast() throws ExceptionIsEmpty {
        if(isEmpty()) throw new ExceptionIsEmpty("Deque Vacia");
        return last.getDato();
    }
    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }
    
}