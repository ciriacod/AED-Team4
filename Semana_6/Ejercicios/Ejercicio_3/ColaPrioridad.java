public class ColaPrioridad {
    private Cola[] colas;// Array de colas para cada nivel de prioridad

    public ColaPrioridad(int niveles) { // Constructor para inicializar las colas de prioridad
        colas = new Cola[niveles];// Crear un array de colas con el número de niveles de prioridad
        for (int i = 0; i < niveles; i++) {// Inicializar cada cola en el array
            colas[i] = new Cola();// Inicializar cada cola en el array
        }
    }

    public void insertar(int dato, int prioridad) {// Metodo para insertar un elemento con una prioridad dada
        if (prioridad < 0 || prioridad >= colas.length) {// Verificar si la prioridad es válida
            throw new RuntimeException("Prioridad inválida");
        }
        colas[prioridad].encolar(dato);// Encolar el dato en la cola correspondiente a su prioridad
    }

    public int eliminar() {// Metodo para eliminar y retornar el elemento de mayor prioridad
        for (int i = 0; i < colas.length; i++) {// Recorrer las colas desde la de mayor prioridad hasta la de menor
            if (!colas[i].estaVacia()) {// Si la cola no esta vacia, desencolar y retornar el elemento
                return colas[i].desencolar();// Si la cola no esta vacia, desencolar y retornar el elemento
            }
        }
        throw new RuntimeException("Cola vacía");
    }
}
