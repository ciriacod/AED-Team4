package Semana_6.Actividades.Actividad_3;

import Semana_6.Actividades.Actividad_1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    
    class EntryNode {
        E data;
        N priority;
        EntryNode (E data, N priority){
            this.data = data;
            this.priority = priority;
        }
    }
    
    private Node<EntryNode> first;
    private Node<EntryNode> last;
    public PriorityQueueLinkSort() {
        this.first = this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {
        Node<EntryNode> novo = new Node<>(new EntryNode(x, pr));
        if (isEmpty()) first = last = novo;
        else {
            if (pr.compareTo(first.getData().priority) > 0){
                novo.setNext(first);
                first = novo;
                return;
            }

            Node<EntryNode> iterador = first;
            while (iterador.getNext() != null && 
            iterador.getNext().getData().priority.compareTo(pr) >= 0){
                iterador = iterador.getNext();
            }

            novo.setNext(iterador.getNext());
            iterador.setNext(novo);

            if (novo.getNext() == null) last = novo;
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola Vacia");

        Node<EntryNode> morto = first;
        first = first.getNext();

        if (first == null) last = null;

        return morto.getData().data;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola Vacia");
        return first.getData().data;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Cola Vacia");
        return last.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null && last == null;
    }
}