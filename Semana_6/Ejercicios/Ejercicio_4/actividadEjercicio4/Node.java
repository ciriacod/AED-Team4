
package actividadEjercicio4;

class Node<E> {
    E data;
    int secondary; 
    Node<E> next;

    public Node(E data, int secondary) {
        this.data = data;
        this.secondary = secondary;
        this.next = null;
    }
}
