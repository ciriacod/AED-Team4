public class ColaPrioridad {
    private Cola[] colas;

    public ColaPrioridad(int niveles) {
        colas = new Cola[niveles];
        for (int i = 0; i < niveles; i++) {
            colas[i] = new Cola();
        }
    }

    public void insertar(int dato, int prioridad) {
        if (prioridad < 0 || prioridad >= colas.length) {
            throw new RuntimeException("Prioridad inválida");
        }
        colas[prioridad].encolar(dato);
    }

    public int eliminar() {
        for (int i = 0; i < colas.length; i++) {
            if (!colas[i].estaVacia()) {
                return colas[i].desencolar();
            }
        }
        throw new RuntimeException("Cola vacía");
    }
}
