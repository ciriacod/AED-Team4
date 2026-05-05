package Semana_6.Ejercicios.Ejercicio_1;

public class Node<E> { //creamos una clase nodo con atributos dentro
    private E data;    //dato de tipo generico
    private Node<E> next;    //creamos una referencia de tipo nodo llamado siguiente

    public Node(E data) {        //constructores
        this.data = data;
        this.next = null;        //nodo siguiente sera nulo
    }

    public E getData() { return data; }            //metodo getfata para mostrar dato
    public Node<E> getNext() { return next; }        //metodo next de tipo objeto node reotrnara siguiente
    public void setNext(Node<E> next) { this.next = next; }        //metodo void que ingresaremos un parametro de tipo nodo siguiente para cambiar el valor del nodo next
}
