package Semana_6.Ejercicios.Ejercicio_1;

public class StackLink<E> implements Stack<E> {
    private Node<E> top;

    public StackLink() {
        this.top = null;
    }

    @Override
    public void push(E x) {
        // Insertar al inicio (comportamiento LIFO)
        Node<E> newNode = new Node<>(x);
        newNode.setNext(top);
        top = newNode;
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        // Eliminar el primero
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía. No se puede realizar pop.");
        }
        E data = top.getData();
        top = top.getNext();
        return data;
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        // Retornar el primero sin eliminarlo
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La pila está vacía. No hay tope.");
        }
        return top.getData();
    }

    @Override
    public boolean isEmpty() {
        // Verificar si está vacía
        return top == null;
    }

    @Override
    public String toString() {
        // Mostrar desde el tope hacia abajo
        if (isEmpty()) return "Pila vacía";
        
        StringBuilder sb = new StringBuilder();
        Node<E> current = top;
        sb.append("Tope -> ");
        while (current != null) {
            sb.append(current.getData()).append("\n        ");
            current = current.getNext();
        }
        return sb.toString().trim();
    }
}
