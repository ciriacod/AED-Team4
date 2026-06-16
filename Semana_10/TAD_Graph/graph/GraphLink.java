package Semana_10.TAD_Graph.graph;

import Importar.Estructuras.LinkedQueue;
import Importar.Estructuras.PriorityQueueLinkSort;
import Importar.Estructuras.StackLink;
import Semana_10.TAD_Graph.listLinked.*;

public class GraphLink<E> {
    private ListLinked<AdjList<E>> graph;

    public GraphLink(){
        graph = new ListLinked<>();
    }

    public void insertVertex(E data){
        if (findVertex(data) != null) return;

        Vertex<E> vertex = new Vertex<>(data);
        graph.addLast(new AdjList<>(vertex));
    }

    private AdjList<E> findVertex(E data){
        for(int i = 0; i < graph.size(); i++){
            AdjList<E> adj = graph.get(i);

            if(adj.getVertex().getData().equals(data))
                return adj;
        }
        return null;
    }

    public void insertEdge(E origin, E destination){
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if(v1 == null || v2 == null)
            return;

        v1.getEdges().addLast(new Edge<>(v2.getVertex()));
        v2.getEdges().addLast(new Edge<>(v1.getVertex()));
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < graph.size(); i++){
            AdjList<E> adj = graph.get(i);

            sb.append(adj.getVertex()).append(" -> ");

            for(int j = 0; j < adj.getEdges().size(); j++){
                sb.append(adj.getEdges().get(j).getDestination()).append(" ");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
    // METODOS OPCIONALES SEGUN GUIA

    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        // Si alguno de los dos vértices no existe, no hay arista que borrar
        if (v1 == null || v2 == null) return;

        // Al ser un Edge objeto, necesitamos pasarle una instancia que sea "igual" para que .remove la encuentre.
        // Como tu ListLinked usa .equals(), y en Edge comparamos por el vértice destino, esto funcionará impecable.
        v1.getEdges().remove(new Edge<>(v2.getVertex()));
        v2.getEdges().remove(new Edge<>(v1.getVertex()));
    }

    public void removeVertex(E data) {
        AdjList<E> vToRemove = findVertex(data);
        
        // Si el vértice no existe en el grafo, no hacemos nada
        if (vToRemove == null) return;

        // Mientras el vértice tenga aristas, eliminamos la primera continuamente.
        // Usamos un bucle while porque a medida que removeEdge borra caminos, 
        // la lista de aristas se va achicando automáticamente.
        while (vToRemove.getEdges().size() > 0) {
            E neighborData = vToRemove.getEdges().get(0).getDestination().getData();
            removeEdge(data, neighborData);
        }

        // Finalmente, borramos el vértice por completo de la lista principal
        graph.remove(vToRemove);
    }

    public void bfs(E startData) {
        AdjList<E> startVertex = findVertex(startData);
        if (startVertex == null) return;

        // Usamos tu estructura de Cola dedicada
        LinkedQueue<AdjList<E>> queue = new Importar.Estructuras.LinkedQueue<>();
        // Mantenemos tu ListLinked únicamente para controlar los ya visitados
        ListLinked<E> visited = new ListLinked<>();

        // Iniciamos el algoritmo encolando y marcando el origen
        queue.enqueue(startVertex);
        visited.addLast(startData);

        System.out.print("Recorrido BFS: ");

        while (!queue.isEmpty()) {
            try {
                // Desencolamos usando tu propio método (elimina y retorna el frente)
                AdjList<E> current = queue.dequeue();
                System.out.print(current.getVertex().getData() + " ");

                // Revisamos los vecinos adyacentes del nodo actual
                for (int i = 0; i < current.getEdges().size(); i++) {
                    Edge<E> edge = current.getEdges().get(i);
                    E neighborData = edge.getDestination().getData();

                    // Si no ha sido visitado, se marca como visitado y se encola
                    if (!visited.search(neighborData)) {
                        visited.addLast(neighborData);
                        AdjList<E> neighborVertex = findVertex(neighborData);
                        if (neighborVertex != null) {
                            queue.enqueue(neighborVertex);
                        }
                    }
                }
            } catch (Exception e) {
                // Capturamos el ExceptionIsEmpty que arroja tu dequeue()
                System.out.println("\nError al procesar la cola: " + e.getMessage());
            }
        }
        System.out.println();
    }

    public void dfs(E startData) {
        AdjList<E> startVertex = findVertex(startData);
        if (startVertex == null) return;

        ListLinked<E> visited = new ListLinked<>();
        System.out.print("Recorrido DFS: ");
        
        // Llamamos al método auxiliar recursivo
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    // Método auxiliar que aprovecha la pila de ejecución recursiva de la memoria
    private void dfsRecursive(AdjList<E> current, ListLinked<E> visited) {
        // Marcar el nodo actual como visitado e imprimirlo
        visited.addLast(current.getVertex().getData());
        System.out.print(current.getVertex().getData() + " ");

        // Explorar recursivamente cada vecino no visitado
        for (int i = 0; i < current.getEdges().size(); i++) {
            Edge<E> edge = current.getEdges().get(i);
            E neighborData = edge.getDestination().getData();

            if (!visited.search(neighborData)) {
                AdjList<E> neighborVertex = findVertex(neighborData);
                if (neighborVertex != null) {
                    dfsRecursive(neighborVertex, visited);
                }
            }
        }
    }

    //**********************************FIN ACTIVIDADES **********************************

    // ============================================== Ejercicio 1 ===================================
    
    public void insertEdgeWeight(E origin, E destination, int weight) {
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if(v1 == null || v2 == null)
            return;

        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
    }
        
    public boolean isConexo() {
        if(graph.size() == 0)
            return true;

        boolean[] visited = new boolean[graph.size()];
        LinkedQueue<Vertex<E>> queue = new LinkedQueue<>(); 

        visited[0] = true;
        try {
            queue.enqueue(graph.get(0).getVertex());

            int count = 1; 

            while(!queue.isEmpty()) {
                Vertex<E> current = queue.dequeue();
                AdjList<E> adj = findVertex(current.getData());

                for(int i = 0; i < adj.getEdges().size(); i++) {
                    Vertex<E> neigh = adj.getEdges().get(i).getDestination(); 
                    int idx = indexOfVertex(neigh.getData());

                    if(!visited[idx]) {
                        visited[idx] = true;
                        count++;
                        queue.enqueue(neigh);
                    }
                }
            }
            return count == graph.size();
        } catch (Exception e) {
            return false;
        }
    }
    
    private int indexOfVertex(E data) {
        for(int i = 0; i < graph.size(); i++) {
            if(graph.get(i).getVertex().getData().equals(data))
                return i;
        }
        return -1;
    }
    
    public StackLink<E> Dijkstra(E origin, E destination) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] prev = new int[n];

        for(int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        int start = indexOfVertex(origin);
        int end = indexOfVertex(destination);

        if(start == -1 || end == -1)
            return null;

        dist[start] = 0;

        PriorityQueueLinkSort<Vertex<E>, Integer> pq = new PriorityQueueLinkSort<>();
        pq.enqueue(graph.get(start).getVertex(), 0);

        while(!pq.isEmpty()) {
            try {
                Vertex<E> currentVertex = pq.dequeue();
                int u = indexOfVertex(currentVertex.getData());
                AdjList<E> adj = graph.get(u);

                for(int i = 0; i < adj.getEdges().size(); i++) {
                    Edge<E> edge = adj.getEdges().get(i);
                    Vertex<E> neigh = edge.getDestination(); 

                    int v = indexOfVertex(neigh.getData());
                    int newDist = dist[u] + edge.getWeight();

                    if(newDist < dist[v]) {
                        dist[v] = newDist;
                        prev[v] = u;
                        pq.enqueue(neigh, -newDist);
                    }
                }
            } catch (Exception e) {
                break;
            }
        }

        StackLink<E> path = new StackLink<>();
        int current = end;

        while(current != -1) {
            path.push(graph.get(current).getVertex().getData());
            current = prev[current];
        }

        return path;
    }
    
    public ListLinked<E> shortPath(E origin, E destination) {
        StackLink<E> stack = Dijkstra(origin, destination);
        if (stack == null) return new ListLinked<>();

        ListLinked<E> path = new ListLinked<>();
        
        try {
            while(!stack.isEmpty()) {
                path.addLast(stack.pop());
            }
        } catch (Exception e) {
        }

        return path;
    }
}