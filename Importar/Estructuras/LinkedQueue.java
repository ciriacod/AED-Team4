package Importar.Estructuras;

import Importar.Exceptions.ExceptionIsEmpty;
import Importar.IEstructuras.Queue;
import Importar.Node;

public class LinkedQueue<E extends Comparable<E>> implements Queue<E> {

    private Node<E> first;      // Frente de la cola (de donde se sacan datos)
    private Node<E> last;       // Final de la cola (donde se insertan datos)
    private int size;           // Contador de elementos

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // Método para insertar (Encolar)
    @Override
    public void enqueue(E data) {
        Node<E> newNode = new Node<E>(data);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    // Método para retirar (Desencolar)
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty(
            "Cola Vacia, Nada que Desencolar");
        
        E data = first.getData();
        first = first.getNext();
        size--;
        
        if (first == null) {
            last = null;
        }
        return data;
    }

    // Método para ver el primer elemento sin retirarlo
    @Override
    public E front() throws ExceptionIsEmpty {
        return isEmpty() ? null : first.getData();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return this.size;
    }
}
