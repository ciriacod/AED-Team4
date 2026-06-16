package Semana_10.Ejercicios.Ejercicio_3_4;

import Importar.Estructuras.LinkedQueue;
import Semana_10.TAD_Graph.graph.AdjList;

public class GraphListEdge<E> extends GraphLink<E> {

    public GraphListEdge(boolean isDirected) {
        super(isDirected);
    }

    // ========================================== ALGORITMOS EJERCICIO 4 ==========================================

    public boolean isConexo() {
        if (graph.size() == 0) return true;
        boolean[] visited = new boolean[graph.size()];
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        visited[0] = true;
        queue.enqueue(0);
        int count = 1;

        try {
            while (!queue.isEmpty()) {
                int currIdx = queue.dequeue();
                AdjList<E> currVertex = graph.get(currIdx);
                for (int i = 0; i < currVertex.getEdges().size(); i++) {
                    E neighborData = currVertex.getEdges().get(i).getDestination().getData();
                    int neighborIdx = getVertexIndex(neighborData);
                    if (neighborIdx != -1 && !visited[neighborIdx]) {
                        visited[neighborIdx] = true;
                        count++;
                        queue.enqueue(neighborIdx);
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return count == graph.size();
    }

    private int getVertexIndex(E data) {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).getVertex().getData().equals(data)) return i;
        }
        return -1;
    }

    public boolean isIsomorfo(GraphListEdge<E> otroGrafo) {
        if (this.graph.size() != otroGrafo.graph.size()) return false;

        int aristasEste = 0;
        int aristasOtro = 0;
        for (int i = 0; i < this.graph.size(); i++) aristasEste += this.graph.get(i).getEdges().size();
        for (int i = 0; i < otroGrafo.graph.size(); i++) aristasOtro += otroGrafo.graph.get(i).getEdges().size();

        if (aristasEste != aristasOtro) return false;

        int[] gradosEste = obtenerGradosOrdenados();
        int[] gradosOtro = otroGrafo.obtenerGradosOrdenados();

        for (int i = 0; i < gradosEste.length; i++) {
            if (gradosEste[i] != gradosOtro[i]) return false;
        }
        return true;
    }

    private int[] obtenerGradosOrdenados() {
        int[] grados = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            grados[i] = graph.get(i).getEdges().size();
        }
        for (int i = 0; i < grados.length - 1; i++) {
            for (int j = 0; j < grados.length - i - 1; j++) {
                if (grados[j] > grados[j + 1]) {
                    int temp = grados[j];
                    grados[j] = grados[j + 1];
                    grados[j + 1] = temp;
                }
            }
        }
        return grados;
    }

    public boolean isAutoComplementario() {
        GraphListEdge<E> complemento = new GraphListEdge<>(this.isDirected);

        for (int i = 0; i < this.graph.size(); i++) {
            complemento.insertVertex(this.graph.get(i).getVertex().getData());
        }

        for (int i = 0; i < this.graph.size(); i++) {
            E origen = this.graph.get(i).getVertex().getData();
            for (int j = 0; j < this.graph.size(); j++) {
                E destino = this.graph.get(j).getVertex().getData();
                if (!origen.equals(destino)) {
                    if (!this.searchEdge(origen, destino)) {
                        complemento.insertEdge(origen, destino, 1);
                    }
                }
            }
        }
        return this.isIsomorfo(complemento);
    }
}