package Importar.Estructuras.graph;

public class Edge<E> {
    private Vertex<E> destination;
    private int weight;

    public Edge(Vertex<E> destination){
        this(destination, 1);
    }

    public Edge(Vertex<E> destination , int weight){
        this.destination = destination;
        this.weight = weight;
    }

    public Vertex<E> getDestination(){
        return destination;
    }

    public int getWeight(){
        return weight;
    }

    public void setDestination(Vertex<E> destination){
        this.destination = destination;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    @Override
    public String toString(){
        return destination.toString() + "(" + weight + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge<?> other = (Edge<?>) obj;
        // Dos aristas son iguales si apuntan al mismo vértice destino
        return this.destination.getData().equals(other.destination.getData());
    }

}