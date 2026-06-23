package Semana_11.hash;

import Importar.Estructuras.listLinked.ListLinked;

public class HashO<E> {

    private static class Element<T> implements Comparable<Element<T>> {
        Register<T> register;

        public Element(Register<T> register) {
            this.register = register;
        }

        @Override
        public int compareTo(Element<T> o) {
            return Integer.compare(this.register.getKey(), o.register.getKey());
        }

        @Override
        public String toString() {
            return register != null ? register.toString() : "null";
        }
    }

    private ListLinked<Element<E>>[] table; // Arreglo de listas enlazadas parametrizadas
    private int size;                        // Tamaño actual de la tabla (M)
    private int n;                           // Contador de elementos totales insertados
    private boolean autoRehash;              // Bandera para habilitar/deshabilitar el crecimiento

    // Constructor estándar
    public HashO(int size) {
        this(size, false);
    }

    // Constructor maestro con control de rehashing dinámico
    @SuppressWarnings("unchecked")
    public HashO(int size, boolean autoRehash) {
        this.size = isPrime(size) ? size : nextPrime(size);;
        this.n = 0;
        this.autoRehash = autoRehash;
        // Casteo controlado para el arreglo de estructuras genéricas
        this.table = (ListLinked<Element<E>>[]) new ListLinked[size];
        
        for (int i = 0; i < size; i++) {
            this.table[i] = new ListLinked<Element<E>>();
        }
    }

    // Función hash base
    private int hash(int key, int currentSize) {
        int index = key % currentSize;
        return index < 0 ? index + currentSize : index;
    }

    // Algoritmo auxiliar para encontrar el siguiente número primo
    private int nextPrime(int min) {
        int p = min % 2 == 0 ? min + 1 : min; // Empezamos desde un impar
        while (true) {
            if (isPrime(p)) return p;
            p += 2;
        }
    }

    // Verifica si un número es primo de forma eficiente O(sqrt(p))
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;
        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) return false;
        }
        return true;
    }

//------------------------------Insert---------------------------------

    public void insert(Register<E> reg) {
        // Control preventivo de factor de carga (Mantenemos promedio alfa <= 0.75)
        if (autoRehash) {
            double alphaFuturo = (double) (n + 1) / size;
            if (alphaFuturo > 0.75) {
                System.out.println("\n[ALERTA] En HashO, alpha superará 0.75 (" + String.format("%.2f", alphaFuturo) + "). Ejecutando REHASHING PRIMO...");
                rehashing();
            }
        }

        int index = hash(reg.getKey(), size);
        ListLinked<Element<E>> list = table[index];

        // Buscar duplicados y actualizar en caso de colisión en la misma clave
        for (int i = 0; i < list.size(); i++) {
            Element<E> current = list.get(i);
            if (current.register.getKey() == reg.getKey()) {
                current.register = reg; // Actualización
                return;
            }
        }

        // Si es una clave nueva, se introduce físicamente a la lista enlazada
        Element<E> newElement = new Element<>(reg);
        list.insert(newElement); 
        n++; // Incrementamos contador general
    }

    // Sobrecarga por comodidad para inserciones directas
    public void insert(int key, E value) {
        Register<E> nuevoRegistro = new Register<>(key, value);
        this.insert(nuevoRegistro);
    }

//------------------------------Search---------------------------------

    public Register<E> search(int key) {
        int index = hash(key, size);
        ListLinked<Element<E>> list = table[index];

        for (int i = 0; i < list.size(); i++) {
            Element<E> current = list.get(i);
            if (current.register.getKey() == key) {
                return current.register; // Encontrado
            }
        }
        return null;
    }

//------------------------------Delete---------------------------------

    public void delete(int key) {
        int index = hash(key, size);
        ListLinked<Element<E>> list = table[index];

        for (int i = 0; i < list.size(); i++) {
            Element<E> current = list.get(i);
            if (current.register.getKey() == key) {
                list.remove(current); // ELIMINACIÓN FÍSICA REAL
                n--; // Decrementamos el contador general
                System.out.println("Clave " + key + " eliminada físicamente de la lista.");
                return;
            }
        }
        System.out.println("Clave " + key + " no encontrada.");
    }

//------------------------------Rehashing------------------------------

    @SuppressWarnings("unchecked")
    private void rehashing() {
        int oldSize = size;
        ListLinked<Element<E>>[] oldTable = table;

        // Calculamos el tamaño duplicado mínimo y buscamos el primo más cercano
        this.size = nextPrime(oldSize * 2);
        this.n = 0; // Se reiniciará en cascada al reinsertar
        
        System.out.println("--- Migrando de tamaño " + oldSize + " a nuevo tamaño primo " + size + " ---");
        
        this.table = (ListLinked<Element<E>>[]) new ListLinked[size];
        for (int i = 0; i < size; i++) {
            this.table[i] = new ListLinked<Element<E>>();
        }

        // Recuperamos los elementos recorriendo las listas anteriores
        for (int i = 0; i < oldSize; i++) {
            ListLinked<Element<E>> list = oldTable[i];
            for (int j = 0; j < list.size(); j++) {
                Register<E> reg = list.get(j).register;
                insert(reg); // Se reasigna bajo la nueva operación: key % nuevo_primo
            }
        }
    }

//------------------------------Print---------------------------------

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("D. Real\tD. Hash\tList (Registers) [Elementos activos totales: " + n + "]\n");
        s.append("-------------------------------------------------------------------------\n");
        for (int i = 0; i < size; i++) {
            s.append(i).append("\t");
            ListLinked<Element<E>> list = table[i];

            if (list.isEmpty()) {
                s.append("\tempty\n");
            } else {
                s.append(i).append("\t");
                StringBuilder listStr = new StringBuilder("[");
                for (int j = 0; j < list.size(); j++) {
                    if (j > 0) listStr.append(" -> ");
                    listStr.append(list.get(j).register.toString());
                }
                listStr.append("]");
                s.append(listStr.toString()).append("\n");
            }
        }
        return s.toString();
    }
}