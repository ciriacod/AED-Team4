package Importar.Estructuras.graph;

import Importar.Estructuras.LinkedQueue;
import Importar.Estructuras.PriorityQueueLinkSort;
import Importar.Estructuras.StackLink;
import Semana_10.TAD_Graph.listLinked.ListLinked;

public class GraphLink<E>{
    protected ListLinked<AdjList<E>> graph;
    private boolean isDirected;
    public GraphLink(boolean isDirected){
        this.isDirected = isDirected;
        this.graph = new ListLinked<>();
    }

    public void insertVertex(E data){
        if (findVertex(data) != null) return;
        graph.addLast(new AdjList<>(new Vertex<>(data)));
    }

    protected AdjList<E> findVertex(E data){
        for(int i = 0; i < graph.size(); i++){
            AdjList<E> adj = graph.get(i);
            if(adj.getVertex().getData().equals(data)) return adj;
        }
        return null;
    }

    public void insertEdge(E origin, E destination, int weight){
        AdjList<E> v1 = findVertex(origin);
        AdjList<E> v2 = findVertex(destination);

        if(v1 == null || v2 == null) return;

        v1.getEdges().addLast(new Edge<>(v2.getVertex(), weight));
        if (!isDirected) {
            v2.getEdges().addLast(new Edge<>(v1.getVertex(), weight));
        }
    }

    public void insertEdge(E origin, E destination){
        insertEdge(origin, destination, 1);
        
    }

    public boolean searchVertex(E data) {
        return findVertex(data) != null;
    }

    public boolean searchEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        if (v1 == null) return false;

        for (int i = 0; i < v1.getEdges().size(); i++) {
            if (v1.getEdges().get(i).getDestination().getData().equals(destination)) return true;
        }
        return false;
    }

    public ListLinked<E> adjacentVertices(E data) {
        AdjList<E> v = findVertex(data);
        ListLinked<E> adjacents = new ListLinked<>();
        if (v == null) return adjacents;

        for (int i = 0; i < v.getEdges().size(); i++) {
            adjacents.addLast(v.getEdges().get(i).getDestination().getData());
        }
        return adjacents;
    }

    public void removeEdge(E origin, E destination) {
        AdjList<E> v1 = findVertex(origin);
        if (v1 != null) v1.getEdges().remove(new Edge<>(new Vertex<>(destination)));
        if (!isDirected) {
            AdjList<E> v2 = findVertex(destination);
            if (v2 != null) v2.getEdges().remove(new Edge<>(new Vertex<>(origin)));
        }
    }

    public void removeVertex(E data) {
        AdjList<E> vToRemove = findVertex(data);
        if (vToRemove == null) return;

        for (int i = 0; i < graph.size(); i++) {
            removeEdge(graph.get(i).getVertex().getData(), data);
        }
        graph.remove(vToRemove);
    }

    public void bfs(E startData) {
        AdjList<E> startVertex = findVertex(startData);
        if (startVertex == null) return;

        LinkedQueue<AdjList<E>> queue = new LinkedQueue<>();
        ListLinked<E> visited = new ListLinked<>();

        queue.enqueue(startVertex);
        visited.addLast(startData);

        System.out.print("Recorrido BFS: ");

        while (!queue.isEmpty()) {
            try {
                AdjList<E> current = queue.dequeue();
                System.out.print(current.getVertex().getData() + " ");

                for (int i = 0; i < current.getEdges().size(); i++) {
                    Edge<E> edge = current.getEdges().get(i);
                    E neighborData = edge.getDestination().getData();

                    if (!visited.search(neighborData)) {
                        visited.addLast(neighborData);
                        AdjList<E> neighborVertex = findVertex(neighborData);
                        if (neighborVertex != null) {
                            queue.enqueue(neighborVertex);
                        }
                    }
                }
            } catch (Exception e) {
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
        
        dfsRecursive(startVertex, visited);
        System.out.println();
    }

    private void dfsRecursive(AdjList<E> current, ListLinked<E> visited) {
        visited.addLast(current.getVertex().getData());
        System.out.print(current.getVertex().getData() + " ");

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

    public boolean isIsomorfo(GraphLink<E> otroGrafo) {
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
}