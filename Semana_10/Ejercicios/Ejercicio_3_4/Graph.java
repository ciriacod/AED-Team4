package Semana_10.Ejercicios.Ejercicio_3_4;

import Semana_10.TAD_Graph.listLinked.ListLinked;

public interface Graph<V, E> {
    void insertVertex(V data);
    void insertEdge(V origin, V destination);
    void removeVertex(V data);
    void removeEdge(V origin, V destination);
    boolean searchVertex(V data);
    boolean searchEdge(V origin, V destination);
    ListLinked<V> adjacentVertices(V data);
}