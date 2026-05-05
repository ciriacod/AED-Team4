public class SistemaTienda {

    // Clase interna para la Cola utilizando un Arreglo Circular
    static class ColaArreglo {
        private int[] cola;
        private int frente;
        private int finalCola;
        private int tamano;
        private int capacidad;

        public ColaArreglo(int capacidad) {
            this.capacidad = capacidad;
            this.cola = new int[capacidad];
            this.frente = 0;
            this.finalCola = -1;
            this.tamano = 0;
        }

        public void encolar(int id) {
            if (tamano == capacidad) {
                System.out.println("Cola llena");
            } else {
                // Comportamiento circular para el índice final
                finalCola = (finalCola + 1) % capacidad;
                cola[finalCola] = id;
                tamano++;
            }
        }

        public void desencolar() {
            if (tamano == 0) {
                System.out.println("Cola vacía");
            } else {
                int clienteAtendido = cola[frente];
                System.out.println("Atendiendo cliente: " + clienteAtendido);
                // Comportamiento circular para el índice frente
                frente = (frente + 1) % capacidad;
                tamano--;
            }
        }

        public void mostrarFrente() {
            if (tamano == 0) {
                System.out.println("Cola vacía");
            } else {
                System.out.println("Cliente en frente: " + cola[frente]);
            }
        }
        
        public boolean estaVacia() {
            return tamano == 0;
        }
    }

    // Método principal para probar el funcionamiento exacto requerido
    public static void main(String[] args) {
        // La cola debe tener una capacidad fija (5 clientes).
        ColaArreglo cola = new ColaArreglo(5);

        // 1. Encola los clientes: 101, 102, 103, 104, 105
        cola.encolar(101);
        cola.encolar(102);
        cola.encolar(103);
        cola.encolar(104);
        cola.encolar(105);

        // 2. Intenta encolar un cliente más (106) → debe mostrar que la cola está llena.
        cola.encolar(106);

        // 3. Desencola 2 clientes.
        cola.desencolar();
        cola.desencolar();

        // 4. Muestra el cliente que está al frente.
        cola.mostrarFrente();

        // 5. Encola 2 clientes más: 106, 107 (aquí se verifica el comportamiento circular).
        cola.encolar(106);
        cola.encolar(107);

        // 6. Desencola todos los elementos hasta que la cola quede vacía.
        while (!cola.estaVacia()) {
            cola.desencolar();
        }

        // 7. Intenta desencolar uno más → debe mostrar que la cola está vacía.
        cola.desencolar();
    }
}
