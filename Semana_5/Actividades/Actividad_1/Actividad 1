public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
}

public class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad; 
    private String estado; 

    public Tarea(String titulo, int prioridad, String estado) {
        this.titulo = titulo;
        this.prioridad = prioridad;
        this.estado = estado;
    }

    public String getEstado() { return estado; }
    
    @Override
    public int compareTo(Tarea otra) {
        return Integer.compare(this.prioridad, otra.prioridad);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tarea) {
            Tarea otra = (Tarea) obj;
            return this.titulo.equals(otra.titulo);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + titulo + " | Prioridad: " + prioridad + " | Estado: " + estado + "]";
    }
}

public class ListLinked<T> {
    public Node<T> head;

    public void insertFirst(T x) {
        Node<T> nuevo = new Node<>(x);
        nuevo.next = head;
        head = nuevo;
    }

    public void insertLast(T x) {
        if (isEmptyList()) {
            insertFirst(x);
            return;
        }
        Node<T> actual = head;
        while (actual.next != null) {
            actual = actual.next;
        }
        actual.next = new Node<>(x);
    }

    public boolean removeNode(T x) {
        if (isEmptyList()) return false;

        if (head.value.equals(x)) {
            head = head.next;
            return true;
        }

        Node<T> actual = head;
        while (actual.next != null && !actual.next.value.equals(x)) {
            actual = actual.next;
        }

        if (actual.next != null) {
            actual.next = actual.next.next;
            return true;
        }
        return false;
    }

    public boolean search(T x) {
        Node<T> actual = head;
        while (actual != null) {
            if (actual.value.equals(x)) return true;
            actual = actual.next;
        }
        return false;
    }

    public int length() {
        int cantidad = 0;
        Node<T> actual = head;
        while (actual != null) {
            cantidad++;
            actual = actual.next;
        }
        return cantidad;
    }

    public boolean isEmptyList() {
        return head == null;
    }

    public void print() {
        Node<T> actual = head;
        while (actual != null) {
            System.out.println(actual.value);
            actual = actual.next;
        }
    }

    public void reverse() {
        Node<T> anterior = null;
        Node<T> actual = head;
        Node<T> siguiente;

        while (actual != null) {
            siguiente = actual.next;
            actual.next = anterior;
            anterior = actual;
            actual = siguiente;
        }
        head = anterior;
    }
}

public class GestorDeTareas<T extends Comparable<T>> {
    public ListLinked<T> lista = new ListLinked<>();

    public void agregarTarea(T tarea) { lista.insertLast(tarea); }
    public boolean eliminarTarea(T tarea) { return lista.removeNode(tarea); }
    public boolean contieneTarea(T tarea) { return lista.search(tarea); }
    public void imprimirTareas() { lista.print(); }
    public int contarTareas() { return lista.length(); }
    public void invertirTareas() { lista.reverse(); }

    public T obtenerTareaMasPrioritaria() {
        if (lista.isEmptyList()) return null;

        Node<T> actual = lista.head;
        T masPrioritaria = actual.value;

        while (actual != null) {
            if (actual.value.compareTo(masPrioritaria) < 0) {
                masPrioritaria = actual.value;
            }
            actual = actual.next;
        }
        return masPrioritaria;
    }
}


public class Main {
    public static void main(String[] args) {
        GestorDeTareas<Tarea> gestor = new GestorDeTareas<>();

        System.out.println("--- 2. Agregando 5 tareas ---");
        gestor.agregarTarea(new Tarea("Diseñar BD", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Deploy produccion", 1, "pendiente"));
        gestor.agregarTarea(new Tarea("Documentar API", 3, "completada"));
        gestor.agregarTarea(new Tarea("Code review", 2, "pendiente"));
        gestor.agregarTarea(new Tarea("Corregir bug #42", 1, "completada"));
        gestor.imprimirTareas();

        System.out.println("\n--- 3. Eliminando 'Code review' ---");
        gestor.eliminarTarea(new Tarea("Code review", 0, "")); 

        System.out.println("\n--- 4 y 5. Imprimir y Buscar ---");
        gestor.imprimirTareas();
        System.out.println("\n¿Existe 'Diseñar BD'?: " + gestor.contieneTarea(new Tarea("Diseñar BD", 0, "")));

        System.out.println("\n--- 6. Más prioritaria ---");
        System.out.println(gestor.obtenerTareaMasPrioritaria());

        System.out.println("\n--- 7. Invertir lista ---");
        gestor.invertirTareas();
        gestor.imprimirTareas();

        System.out.println("\n--- 8. Transferir Completadas ---");
        GestorDeTareas<Tarea> completadas = new GestorDeTareas<>();
        Node<Tarea> actual = gestor.lista.head;

        while (actual != null) {
            if (actual.value.getEstado().equals("completada")) {
                completadas.agregarTarea(actual.value);
            }
            actual = actual.next;
        }

        System.out.println("Lista de COMPLETADAS:");
        completadas.imprimirTareas();
    }
}
