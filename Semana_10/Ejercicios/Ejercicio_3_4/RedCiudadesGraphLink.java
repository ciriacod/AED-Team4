package Semana_10.Ejercicios.Ejercicio_3_4;

import Semana_10.TAD_Graph.listLinked.ListLinked;

public class RedCiudadesGraphLink {
    private GraphLink<String> redCiudades;

    public RedCiudadesGraphLink() {
        this.redCiudades = new GraphLink<>();
    }

    public void cargarRedPorDefecto() {
        redCiudades.insertVertex("Arequipa");
        redCiudades.insertVertex("Cusco");
        redCiudades.insertVertex("Puno");
        redCiudades.insertVertex("Tacna");
        redCiudades.insertVertex("Moquegua");

        redCiudades.insertEdgeWeight("Arequipa", "Cusco", 510);
        redCiudades.insertEdgeWeight("Arequipa", "Moquegua", 230);
        redCiudades.insertEdgeWeight("Moquegua", "Tacna", 160);
        redCiudades.insertEdgeWeight("Cusco", "Puno", 390);
        redCiudades.insertEdgeWeight("Puno", "Tacna", 420);
    }

    public void mostrarEstructuraCompleta() {
        System.out.println("=== ESTRUCTURA DE LA RED (TDA Propio GraphLink) ===");
        System.out.print(redCiudades.toString());
    }

    public void probarMetodosDeBusqueda() {
        System.out.println("\n=== PRUEBA DE MÉTODOS DE BÚSQUEDA ===");
        System.out.println("¿Existe Cusco?: " + redCiudades.searchVertex("Cusco"));
        System.out.println("¿Existe una ciudad falsa (Lima)?: " + redCiudades.searchVertex("Lima"));
        System.out.println("¿Hay carretera directa Moquegua <-> Tacna?: " + redCiudades.searchEdge("Moquegua", "Tacna"));
        
        System.out.println("\n=== VÉRTICES ADYACENTES (Vecinos directos) ===");
        String ciudadEvaluada = "Arequipa";
        ListLinked<String> vecinos = redCiudades.adjacentVertices(ciudadEvaluada);
        System.out.print("Ciudades conectadas directamente con " + ciudadEvaluada + ": ");
        for (int i = 0; i < vecinos.size(); i++) {
            System.out.print(vecinos.get(i) + (i < vecinos.size() - 1 ? ", " : ""));
        }
        System.out.println();
    }

    public void calcularRutaOptima(String origen, String destino) {
        System.out.println("\n=== CÁLCULO DE RUTA ÓPTIMA (Tu Dijkstra) ===");
        ListLinked<String> camino = redCiudades.shortPath(origen, destino);

        if (camino.size() == 0) {
            System.out.println("No se encontró una ruta válida entre " + origen + " y " + destino);
            return;
        }

        System.out.println("Ruta desde " + origen + " hasta " + destino + ":");
        System.out.print("El camino más corto es: [");
        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i) + (i < camino.size() - 1 ? ", " : ""));
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        RedCiudadesGraphLink app = new RedCiudadesGraphLink();

        app.cargarRedPorDefecto();
        app.mostrarEstructuraCompleta();
        app.probarMetodosDeBusqueda();
        app.calcularRutaOptima("Arequipa", "Puno");
    }
}