package Semana_6.Actividades.Actividad_3;

import Semana_6.Actividades.Actividad_1.ExceptionIsEmpty;

public class Main {
    public static void main(String[] args) {

        PriorityQueue<String, Integer> pq = new PriorityQueueLinkSort<>();

        try {
            // Insertar elementos
            pq.enqueue("A", 2);
            pq.enqueue("B", 8);
            pq.enqueue("C", 5);
            pq.enqueue("D", 1);
            pq.enqueue("E", 10);

            System.out.println("Elementos insertados.");

            // Probar front y back
            System.out.println("Front (mayor prioridad): " + pq.front());
            System.out.println("Back (menor prioridad): " + pq.back());

            // Probar dequeue
            System.out.println("\nDequeue:");
            while (!pq.isEmpty()) {
                System.out.println("Sale: " + pq.dequeue());
            }

        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
    }
}