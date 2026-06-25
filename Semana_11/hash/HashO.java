package Semana_11.hash;

import Importar.Estructuras.listLinked.ListLinked;
import Importar.Estructuras.listLinked.Node;

public class HashO<E> {

    private ListLinked<Register<E>>[] table; 
    private int size;                        
    private int n;                           
    private boolean autoRehash;              

    public HashO(int size) {
        this(size, false);
    }

    @SuppressWarnings("unchecked")
    public HashO(int size, boolean autoRehash) {
        this.size = isPrime(size) ? size : nextPrime(size);
        this.n = 0;
        this.autoRehash = autoRehash;
        
        this.table = (ListLinked<Register<E>>[]) new ListLinked[this.size];
        
        for (int i = 0; i < this.size; i++) {
            this.table[i] = new ListLinked<Register<E>>();
        }
    }

    private int hash(int key, int currentSize) {
        int index = key % currentSize;
        return index < 0 ? index + currentSize : index;
    }

    private int nextPrime(int min) {
        int p = min % 2 == 0 ? min + 1 : min; 
        while (true) {
            if (isPrime(p)) return p;
            p += 2;
        }
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                return false; 
            }
        }
        return true; 
    }

//------------------------------Insert---------------------------------

    public void insert(Register<E> reg) {
        if (autoRehash) {
            double alphaFuturo = (double) (n + 1) / size;
            if (alphaFuturo > 0.75) {
                System.out.println("\n[ALERTA] En HashO, alpha superará 0.75 (" + String.format("%.2f", alphaFuturo) + "). Ejecutando REHASHING PRIMO...");
                rehashing();
            }
        }

        int index = hash(reg.getKey(), size);
        ListLinked<Register<E>> list = table[index];

        Node<Register<E>> current = list.root;
        while (current != null) {
            if (current.getData().getKey() == reg.getKey()) {
                current.setData(reg); 
                return;
            }
            current = current.getNext();
        }

        list.insert(reg); 
        n++; 
    }

    public void insert(int key, E value) {
        Register<E> nuevoRegistro = new Register<>(key, value);
        this.insert(nuevoRegistro);
    }

//------------------------------Search---------------------------------

    public Register<E> search(int key) {
        int index = hash(key, size);
        ListLinked<Register<E>> list = table[index];

        Node<Register<E>> current = list.root;
        while (current != null) {
            if (current.getData().getKey() == key) {
                return current.getData(); 
            }
            current = current.getNext();
        }
        return null;
    }

//------------------------------Delete---------------------------------

    public void delete(int key) {
        int index = hash(key, size);
        ListLinked<Register<E>> list = table[index];

        Node<Register<E>> current = list.root;
        while (current != null) {
            if (current.getData().getKey() == key) {
                list.remove(current.getData()); 
                n--; 
                System.out.println("Clave " + key + " eliminada físicamente de la lista.");
                return;
            }
            current = current.getNext();
        }
        System.out.println("Clave " + key + " no encontrada.");
    }

//------------------------------Rehashing------------------------------

    @SuppressWarnings("unchecked")
    private void rehashing() {
        int oldSize = size;
        ListLinked<Register<E>>[] oldTable = table;

        this.size = nextPrime(oldSize * 2);
        this.n = 0; 
        
        System.out.println("--- Migrando de tamaño " + oldSize + " a nuevo tamaño primo " + size + " ---");
        
        this.table = (ListLinked<Register<E>>[]) new ListLinked[this.size];
        for (int i = 0; i < size; i++) {
            this.table[i] = new ListLinked<Register<E>>();
        }

        for (int i = 0; i < oldSize; i++) {
            ListLinked<Register<E>> list = oldTable[i];
            Node<Register<E>> current = list.root;
            while (current != null) {
                insert(current.getData()); 
                current = current.getNext();
            }
        }
    }

//------------------------------Print---------------------------------

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("D. Real\tD. Hash\tList (Registers) [activos totales: " + n + "]\n");
        s.append("-------------------------------------------------------------------------\n");
        for (int i = 0; i < size; i++) {
            s.append(i).append("\t");
            ListLinked<Register<E>> list = table[i];

            if (list.isEmpty()) {
                s.append("\tempty\n");
            } else {
                s.append(i).append("\t");
                StringBuilder listStr = new StringBuilder("[");
                Node<Register<E>> current = list.root;
                while (current != null) {
                    listStr.append(current.getData().toString());
                    if (current.getNext() != null) {
                        listStr.append(" -> ");
                    }
                    current = current.getNext();
                }
                listStr.append("]");
                s.append(listStr.toString()).append("\n");
            }
        }
        return s.toString();
    }
}