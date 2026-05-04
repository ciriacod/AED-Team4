public class Cola {
    private Nodo frente;
    private Nodo fin;

    public Cola() {
        frente = fin = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void encolar(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (estaVacia()) {
            frente = fin = nuevo;
        } else {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public int desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("Cola vacía");
        }

        int dato = frente.dato;
        frente = frente.siguiente;

        if (frente == null) {
            fin = null;
        }

        return dato;
    }
}
