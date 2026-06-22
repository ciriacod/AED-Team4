package Importar.Estructuras.graph;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AdjList<?> other = (AdjList<?>) obj;
        // Dos listas de adyacencia son iguales si le pertenecen al mismo vértice
        return this.vertex.getData().equals(other.vertex.getData());
    }
}
