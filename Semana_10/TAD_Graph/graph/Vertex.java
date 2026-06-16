package Semana_10.TAD_Graph.graph;

public class Vertex<E> {
    private E data;

    public Vertex(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}