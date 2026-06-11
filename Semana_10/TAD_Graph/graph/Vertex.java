package Semana_10.TAD_Graph.graph;

public class Vertex <E> implements Comparable<Vertex<E>> {
    private E data;

    public Vertex(E data){
        this.data = data;
    }

    public E getData(){
        return data;
    }

    public void setData(E data){
        this.data = data;
    }

    @Override
    public String toString(){
        return data.toString();
    }

    @Override
    public int compareTo(Vertex<E> o) {
        // Casteamos temporalmente a Comparable para poder usar compareTo con el dato genérico
        if (this.data instanceof Comparable && o.getData() instanceof Comparable) {
            return ((Comparable<E>) this.data).compareTo(o.getData());
        }
        return 0;
    }
}
