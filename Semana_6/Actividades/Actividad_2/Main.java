package Semana_6.Actividades.Actividad_2;

import Semana_6.Actividades.Actividad_1.*;

public class Main {
    public static void main(String[] args) throws ExceptionIsEmpty {

        DequeLink<Integer> dequeEnteros = new DequeLink<>();

        dequeEnteros.addFirst(10);
        dequeEnteros.addLast(20);
        dequeEnteros.addFirst(5);
        dequeEnteros.addLast(30);

        System.out.println("Deque de enteros:");
        System.out.println("Primer elemento: " + dequeEnteros.getFirst());
        System.out.println("Ultimo elemento: " + dequeEnteros.getLast());

        System.out.println("RemoveFirst: " + dequeEnteros.removeFirst());
        System.out.println("RemoveLast: " + dequeEnteros.removeLast());

        System.out.println("Primer elemento actual: " + dequeEnteros.getFirst());
        System.out.println("Ultimo elemento actual: " + dequeEnteros.getLast());

        System.out.println("---------------------------------------");

        DequeLink<String> dequeCadenas = new DequeLink<>();

        dequeCadenas.addFirst("Java");
        dequeCadenas.addLast("Python");
        dequeCadenas.addFirst("C++");
        dequeCadenas.addLast("JavaScript");

        System.out.println("Deque de cadenas:");
        System.out.println("Primer elemento: " + dequeCadenas.getFirst());
        System.out.println("Ultimo elemento: " + dequeCadenas.getLast());

        System.out.println("RemoveFirst: " + dequeCadenas.removeFirst());
        System.out.println("RemoveLast: " + dequeCadenas.removeLast());

        System.out.println("Primer elemento actual: " + dequeCadenas.getFirst());
        System.out.println("Ultimo elemento actual: " + dequeCadenas.getLast());
    }
}
