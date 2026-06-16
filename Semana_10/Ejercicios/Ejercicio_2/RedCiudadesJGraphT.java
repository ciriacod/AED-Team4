package Semana_10.Ejercicios.Ejercicio_2;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import java.util.List;

public class RedCiudadesJGraphT {
    private Graph<String, DefaultWeightedEdge> redCiudades;

    public RedCiudadesJGraphT() {
        this.redCiudades = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    public void cargarRedPorDefecto() {
        redCiudades.addVertex("Arequipa");
        redCiudades.addVertex("Cusco");
        redCiudades.addVertex("Puno");
        redCiudades.addVertex("Tacna");
        redCiudades.addVertex("Moquegua");

        agregarCarretera("Arequipa", "Cusco", 510);
        agregarCarretera("Arequipa", "Moquegua", 230);
        agregarCarretera("Moquegua", "Tacna", 160);
        agregarCarretera("Cusco", "Puno", 390);
        agregarCarretera("Puno", "Tacna", 420);
    }

    private void agregarCarretera(String v1, String v2, double peso) {
        DefaultWeightedEdge edge = redCiudades.addEdge(v1, v2);
        if (edge != null) {
            redCiudades.setEdgeWeight(edge, peso);
        }
    }

    public void mostrarCiudades() {
        System.out.println("=== LISTA DE CIUDADES ===");
        for (String ciudad : redCiudades.vertexSet()) {
            System.out.println("- " + ciudad);
        }
    }

    public void mostrarCarreteras() {
        System.out.println("\n=== CARRETERAS REGISTRADAS ===");
        for (DefaultWeightedEdge carretera : redCiudades.edgeSet()) {
            String origen = redCiudades.getEdgeSource(carretera);
            String destino = redCiudades.getEdgeTarget(carretera);
            double distancia = redCiudades.getEdgeWeight(carretera);
            System.out.println(origen + " <---> " + destino + " (" + distancia + " km)");
        }
    }

    public void calcularYMostrarRutaOptima(String origen, String destino) {
        System.out.println("\n=== CÁLCULO DE RUTA ÓPTIMA (Dijkstra) ===");
        
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(redCiudades);
        List<String> caminoCorto = dijkstra.getPath(origen, destino).getVertexList();
        double costoTotal = dijkstra.getPathWeight(origen, destino);

        System.out.println("Ruta desde " + origen + " hasta " + destino + ":");
        System.out.println("El camino más corto es: " + caminoCorto);
        System.out.println("Costo total (Distancia): " + costoTotal + " km");
    }

    public static void main(String[] args) {
        RedCiudadesJGraphT app = new RedCiudadesJGraphT();

        app.cargarRedPorDefecto();
        app.mostrarCiudades();
        app.mostrarCarreteras();
        
        app.calcularYMostrarRutaOptima("Arequipa", "Puno");
    }
}