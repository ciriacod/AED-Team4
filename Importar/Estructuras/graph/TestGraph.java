package Importar.Estructuras.graph;

public class TestGraph {
    public static void main(String[] args) {
        GraphLink<String> g = new GraphLink<>(true);
        
        // 1. Insertamos los vértices
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");

        // 2. Insertamos las aristas (conexiones mutuas)
        g.insertEdge("A", "B");
        g.insertEdge("A", "C");
        g.insertEdge("B", "D");

        System.out.println("--- Estructura del Grafo ---");
        System.out.println(g);

        // 3. Probamos los recorridos
        System.out.println("--- Ejecutando Recorridos ---");
        g.bfs("A"); // Probará tu LinkedQueue internamente
        g.dfs("A");
    }
}