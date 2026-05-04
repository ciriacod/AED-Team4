package Semana_6.Actividades.Actividad_1;

public class TestQueue {
    public static void main(String[] args) throws ExceptionIsEmpty {
        QueueArray<Integer> colaEnteros = new QueueArray<>(2);
        QueueArray<String> colaCadena = new QueueArray<>(3);

        colaEnteros.enqueue(2);
        colaEnteros.enqueue(3);
        System.out.println(colaEnteros.toString());

        colaEnteros.dequeue();
        System.out.println(colaEnteros.toString());

        colaEnteros.enqueue(10);
        System.out.println(colaEnteros.toString());

        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");

        colaCadena.enqueue("Hola");
        colaCadena.enqueue("Mundo");
        colaCadena.enqueue("Astng");
        System.out.println(colaCadena.toString());

        colaCadena.dequeue();
        System.out.println(colaCadena.toString());
        colaCadena.dequeue();
        System.out.println(colaCadena.toString());

        colaCadena.enqueue("IDK");
        System.out.println(colaCadena.toString());
    }
}
