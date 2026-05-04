package Semana_6.Actividades.Actividad_1;

public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int front;
    private int rear;
    private int size;

    public QueueArray(int n){
        array = (E[]) new Object[n];
        front = 0;
        rear = -1;
        size = 0;
    }

    @Override
    public void enqueue(E x) {
        if(size == array.length) throw new RuntimeException("Cola llena");
        rear = (rear + 1) % array.length;
        array[rear] = x; 
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if(isEmpty()) throw new ExceptionIsEmpty("Cola Vacia");
        E out = array[front];
        array[front] = null;
        front = (front + 1) % array.length;
        size--;
        return out;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if(isEmpty()) throw new ExceptionIsEmpty("Cola Vacia");
        return array[front];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            int index = (front + i) % array.length;
            sb.append(array[index]).append(" ");
        }

        return sb.toString();
    }
}
