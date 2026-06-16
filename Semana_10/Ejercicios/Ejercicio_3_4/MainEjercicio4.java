package Semana_10.Ejercicios.Ejercicio_3_4;

public class MainEjercicio4 {
    public static void main(String[] args) {
        System.out.println("=== PRUEBA EJERCICIO 4 (Especificaciones de la Ingeniera) ===");

        // Crear grafo no dirigido para validar conectividad y pesos automáticos
        GraphListEdge<String> g1 = new GraphListEdge<>(false);
        g1.insertVertex("A");
        g1.insertVertex("B");
        g1.insertVertex("C");

        // Probando la regla: arista sin peso explícito usa 1 por defecto
        g1.insertEdge("A", "B"); 
        g1.insertEdge("B", "C", 5); // Con peso explícito

        System.out.println("¿El grafo 1 es conexo?: " + g1.isConexo());

        // Validando propiedad auto-complementaria de un camino de 4 nodos (clásico grafo autocomplementario)
        GraphListEdge<String> gAuto = new GraphListEdge<>(false);
        gAuto.insertVertex("1");
        gAuto.insertVertex("2");
        gAuto.insertVertex("3");
        gAuto.insertVertex("4");
        gAuto.insertEdge("1", "2");
        gAuto.insertEdge("2", "3");
        gAuto.insertEdge("3", "4");

        System.out.println("¿El grafo lineal de 4 nodos es Auto-complementario?: " + gAuto.isAutoComplementario());
    }
}