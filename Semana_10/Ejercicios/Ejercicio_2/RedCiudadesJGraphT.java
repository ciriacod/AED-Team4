package Semana_10.Ejercicios.Ejercicio_2;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;

import javax.swing.JFrame;
import java.util.List;
import java.util.Map;

/**
 * Clase principal que modela la Red de Ciudades usando la librería JGraphT
 * Cumple con los requisitos de la guía del Ejercicio 2.
 */
public class RedCiudadesJGraphT {
    // Definición del grafo ponderado no dirigido exclusivamente con JGraphT
    private final Graph<String, DefaultWeightedEdge> redCiudades;

    public RedCiudadesJGraphT() {
        // SimpleWeightedGraph crea un grafo NO dirigido y PONDERADO
        this.redCiudades = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
    }

    /**
     * Carga los datos de prueba solicitados en la guía del laboratorio
     */
    public void cargarRedPorDefecto() {
        // Agregar los vértices (Ciudades)
        redCiudades.addVertex("Arequipa");
        redCiudades.addVertex("Cusco");
        redCiudades.addVertex("Puno");
        redCiudades.addVertex("Tacna");
        redCiudades.addVertex("Moquegua");

        // Agregar las aristas (Carreteras) con sus respectivas distancias en km
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
            System.out.println("📍 " + ciudad);
        }
    }

    public void mostrarCarreteras() {
        System.out.println("\n=== CARRETERAS REGISTRADAS ===");
        for (DefaultWeightedEdge carretera : redCiudades.edgeSet()) {
            String origen = redCiudades.getEdgeSource(carretera);
            String destino = redCiudades.getEdgeTarget(carretera);
            double distancia = redCiudades.getEdgeWeight(carretera);
            System.out.printf("🛣️ %s <---> %s (%.0f km)\n", origen, destino, distancia);
        }
    }

    /**
     * Calcula Dijkstra, lo imprime detalladamente en consola y levanta la GUI interactiva
     * mostrando las ponderaciones (kilómetros) sobre cada carretera.
     */
    public void procesarYVisualizarRuta(String origen, String destino) {
        // 1. CÁLCULO E IMPRESIÓN EN CONSOLA (Requisito de la guía)
        System.out.println("\n=== CALCULO DE RUTA OPTIMA (Dijkstra) ===");
        
        DijkstraShortestPath<String, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(redCiudades);
        GraphPath<String, DefaultWeightedEdge> path = dijkstra.getPath(origen, destino);

        if (path == null) {
            System.out.println("Error: No existe ruta entre las ciudades seleccionadas.");
            return;
        }

        List<String> caminoCorto = path.getVertexList();
        List<DefaultWeightedEdge> carreterasDelCamino = path.getEdgeList(); 
        double costoTotal = path.getWeight();

        System.out.println("El camino mas corto en consola es: " + caminoCorto);
        System.out.printf("Costo total (Distancia): %.0f km\n", costoTotal);

        // 2. CONSTRUCCIÓN DE LA INTERFAZ GRÁFICA CON PONDERACIONES
        // Sobreescribimos el método para que devuelva el peso (los km) como etiqueta de la arista
        // CONSTRUCCIÓN DE LA INTERFAZ GRÁFICA CON PONDERACIONES
        JGraphXAdapter<String, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<String, DefaultWeightedEdge>(this.redCiudades) {
            public String convertEdgeToString(DefaultWeightedEdge edge) {
                // Usamos directamente tu variable 'redCiudades' para extraer el peso
                double peso = redCiudades.getEdgeWeight(edge);
                return String.format("%.0f km", peso);
            }
        };

        // CONFIGURACIÓN ESTÍLICA: Personalizar el diseño de las etiquetas y líneas
        mxStylesheet stylesheet = graphAdapter.getStylesheet();
        Map<String, Object> edgeStyle = stylesheet.getDefaultEdgeStyle();
        
        edgeStyle.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE); // Quita las flechas directivas
        edgeStyle.put(mxConstants.STYLE_STROKECOLOR, "#6482AD");     // Color de carretera normal (Azul)
        edgeStyle.put(mxConstants.STYLE_STROKEWIDTH, 2);             // Grosor normal
        edgeStyle.put(mxConstants.STYLE_FONTCOLOR, "#333333");       // Color de los números (Gris oscuro)
        edgeStyle.put(mxConstants.STYLE_FONTSIZE, 11);               // Tamaño de la fuente de los km

        // Pintar de Rojo Grueso únicamente las aristas que pertenecen a la ruta óptima de Dijkstra
        for (DefaultWeightedEdge carretera : carreterasDelCamino) {
            Object cell = graphAdapter.getEdgeToCellMap().get(carretera);
            if (cell != null) {
                // Mantiene el texto de los km pero resalta la carretera en rojo brillante
                graphAdapter.getModel().setStyle(cell, "strokeColor=#FF0000;strokeWidth=4;endArrow=none;fontColor=#FF0000;fontSize=12;fontWeight=bold;");
            }
        }

        // Diseño en círculo para distribuir los nodos de forma balanceada
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.setRadius(140);
        layout.execute(graphAdapter.getDefaultParent());

        // Montar la ventana contenedora Swing
        JFrame frame = new JFrame("Ejercicio 2: Red Vial con Ponderaciones (km)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mxGraphComponent graphComponent = new mxGraphComponent(graphAdapter);
        graphComponent.setConnectable(false); // Evita que el usuario dibuje líneas nuevas arrastrando
        graphAdapter.setCellsMovable(true);   // Permite mover los nodos/ciudades libremente
        
        frame.getContentPane().add(graphComponent);
        frame.setSize(650, 550);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        RedCiudadesJGraphT app = new RedCiudadesJGraphT();

        // Flujo de ejecución ordenado
        app.cargarRedPorDefecto();
        app.mostrarCiudades();
        app.mostrarCarreteras();
        
        // Ejecutamos la prueba viajando de Arequipa a Puno
        app.procesarYVisualizarRuta("Arequipa", "Puno");
    }
}