package Semana_9.btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> {
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    // Control global de IDs
    protected static int contGlobalNodes = 0;
    protected int idNode;

    // Constructor inicializa listas con nulos según el orden n
    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;

        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        
        contGlobalNodes++;
        this.idNode = contGlobalNodes;
    }

    // Verifica si el nodo está lleno
    public boolean nodeFull(int maxKeys) {
        return this.count == maxKeys;
    }

    // Verifica si el nodo está vacío
    public boolean nodeEmpty() {
        return this.count == 0;
    }

    // Busca una clave y guarda la posición por referencia en pos[0]
    public boolean searchNode(E bus, int[] pos) {
        int i = 0;

        while (i < this.count && this.keys.get(i).compareTo(bus) < 0) {
            i++;
        }

        pos[0] = i;

        if (i < this.count && this.keys.get(i).compareTo(bus) == 0) {
            return true;
        }

        return false;
    }

    // Getters y Setters
    public int getIdNode() {
        return this.idNode;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.idNode).append("] ( ");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i));
            if (i < this.count - 1) {
                sb.append(", ");
            }
        }
        sb.append(" )");
        return sb.toString();
    }
}