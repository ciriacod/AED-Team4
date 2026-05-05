
package actividadEjercicio4;

/*
 ***********************************************************************************************
 *************  ----------------------   Ejercicio N4°   ------------------  *******************
 ***********************************************************************************************
 */



// Clase Hibrida: lista de niveles, donde cada nivel tiene una lista de elementos.
public class PriorityQueueHybrid<E> {
    private LevelNode<E> rootLevel; // Inicio de la lista de niveles (el nivel más alto)

    public PriorityQueueHybrid() {
        this.rootLevel = null;
    }

    // Metodo que permite insertar un elemento. Primero busca el nivel de prioridad y luego inserta ordenado por el valor secundario
    public void enqueue(E x, int priorityLevel, int prioriryInList) {
        LevelNode<E> level = getOrCreateLevel(priorityLevel); // Crea o actualiza una lista por nivel
        Node<E> newNode = new Node<>(x, prioriryInList);

        // Verifica si la sub-cola esta vacía o el nuevo Nodo tiene menor valor secundario que el primero
        if (level.firstElement == null || prioriryInList < level.firstElement.secondary) {
            newNode.next = level.firstElement;
            level.firstElement = newNode;
        } else {
            // Recorrer la sub-cola para encontrar la posición correcta
            Node<E> current = level.firstElement;
            while (current.next != null && current.next.secondary <= prioriryInList) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    
    // Metodo que busca un nivel de prioridad especifico. Mantiene la lista de niveles ordenada de mayor a menor para facilitar el dequeue
    private LevelNode<E> getOrCreateLevel(int priorityLevel) {
        
        // Verifica si no hay niveles o el nuevo es mayor que el nivel más alto actual
        if (rootLevel == null || priorityLevel > rootLevel.priorityLevelInList) {
            LevelNode<E> newLevel = new LevelNode<>(priorityLevel); // Se instancia una nueva lista con un nuevo nivel de prioridad
            
            // Realiza un intercambio de elementos colocando la mayor prioridad a la cabeza
            newLevel.nextLevel = rootLevel;
            rootLevel = newLevel;
            return newLevel;
        }

        LevelNode<E> curr = rootLevel;  // Se asigna el inicio de la lista hibrida a una lista por nivel
        
        // Busca el nivel en el resto de la cola
        while (curr != null) {
            if (curr.priorityLevelInList == priorityLevel) return curr; // Retorna el Nodo con nivel encontrado

            // Verifica si el siguiente Nodo actual es nulo o el nivel buscado es mayor que el siguiente
            if (curr.nextLevel == null || priorityLevel > curr.nextLevel.priorityLevelInList) {
                LevelNode<E> newLevel = new LevelNode<>(priorityLevel);
                newLevel.nextLevel = curr.nextLevel;
                curr.nextLevel = newLevel;
                return newLevel;  // Retorna el nuevo Nodo
            }
            curr = curr.nextLevel;  // Avanza al siguiente Nodo
        }
        return null; 
    }

    
    // Elimina y retorna el elemento con mayor prioridad
    public E dequeue() {
        
        // Verifica si existe una cola
        if (isEmpty()) {
            System.out.println("La cola se encuentra vacia");
            return null;
        }

        // Busca el elemento de menor prioridad en la lista general
        LevelNode<E> currLevel = rootLevel;
        while (currLevel != null) {
            if (currLevel.firstElement != null) {
                E data = currLevel.firstElement.data;  // Se asigna el dato encontrado a un elemento temporal
                currLevel.firstElement = currLevel.firstElement.next;   // Elimina el nodo frontal de la sub-cola
                return data;  // Retorna el elemento temporal
            }
            currLevel = currLevel.nextLevel;
        }
        return null;
    }

    
    // Verifica si existe al menos un elemento en algún nivel de prioridad.
    public boolean isEmpty() {
        LevelNode<E> curr = rootLevel;
        while (curr != null) {
            if (curr.firstElement != null) return false;
            curr = curr.nextLevel;
        }
        return true;
    }

    // Muestra el estado interno de la estructura para verificar el orden
    public void display() {
        LevelNode<E> currL = rootLevel;
        while (currL != null) {
            System.out.print("Nivel " + currL.priorityLevelInList + ": ");
            Node<E> currE = currL.firstElement;
            if (currE == null) System.out.print("vacio");
            while (currE != null) {
                System.out.print("(" + currE.data + "," + currE.secondary + ") -> ");
                currE = currE.next;
            }
            System.out.println("null");
            currL = currL.nextLevel;
        }
    }
    
    // Metodo Main
    public static void main(String[] args) {
        PriorityQueueHybrid<String> pq = new PriorityQueueHybrid<>();

        // Ingreso de datos a la cola
        pq.enqueue("A", 2, 5);
        pq.enqueue("B", 2, 1);
        pq.enqueue("C", 1, 3);
        pq.enqueue("D", 2, 3);
        
        // Muestra el proceso de estructura de la cola
        System.out.println("--- Estado Interno ---");
        pq.display();
        
        // Muestra la cola de prioridad
        System.out.println("\n--- Orden de Salida ---");
        while (!pq.isEmpty()) {
            String valor = pq.dequeue();
            if (valor != null) {
                System.out.print(valor + " ");
            }
        }
    }
}
