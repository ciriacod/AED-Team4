package Semana_10.TAD_Graph.graph;

import Semana_10.TAD_Graph.listLinked.ListLinked;

public class AdjList<E> {
    private Vertex<E> vertex;
    private ListLinked<Edge<E>> edges;

    public AdjList(Vertex<E> vertex){
        this.vertex = vertex;
        this.edges = new ListLinked<>();
    }

    public Vertex<E> getVertex(){
        return vertex;
    }

    public ListLinked<Edge<E>> getEdges(){
        return edges;
    }
    
}
