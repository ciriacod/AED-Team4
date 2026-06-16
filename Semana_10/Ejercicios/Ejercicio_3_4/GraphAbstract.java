package Semana_10.Ejercicios.Ejercicio_3_4;

public abstract class GraphAbstract<V> implements Graph<V, Integer> {
    protected boolean isDirected;

    public GraphAbstract(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public abstract void insertEdge(V origin, V destination, int weight);

    @Override
    public void insertEdge(V origin, V destination) {
        insertEdge(origin, destination, 1);
    }
}