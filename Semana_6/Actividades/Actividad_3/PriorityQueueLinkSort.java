package Semana_6.Actividades.Actividad_3;

import Semana_6.Actividades.Actividad_1.ExceptionIsEmpty;

public class PriorityQueueLinkSort<E, N> implements PriorityQueue<E, N> {
    
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enqueue'");
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'dequeue'");
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'front'");
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'back'");
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }
}
