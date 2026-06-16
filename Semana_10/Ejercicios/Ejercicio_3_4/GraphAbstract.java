package Semana_10.Ejercicios.Ejercicio_3_4;

public abstract class GraphAbstract<V> implements Graph<V, Integer> {
    protected boolean isDirected;

    public GraphAbstract(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    // Método abstracto para insertar con peso (para cumplir la regla del peso por defecto)
    public abstract void insertEdge(V origin, V destination, int weight);

    // Sobrecarga exigida: si no se especifica peso, se asume 1 por defecto
    @Override
    public void insertEdge(V origin, V destination) {
        insertEdge(origin, destination, 1);
    }
}