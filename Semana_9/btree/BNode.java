package Semana_9.btree;

import java.util.ArrayList;


public class BNode <E extends  Comparable <E>>{
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
    protected int count;

    protected static int contGlobalNodes = 0;
    protected int idNode;

    public BNode(int n){
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);

        this.count = 0;
        for(int i = 0 ; i < n ; i++){
            this.keys.add(null);
            this.childs.add(null);
        }
        
        contGlobalNodes++;
        idNode = contGlobalNodes;
    }

    public boolean nodeFull(int maxKeys){
        return count == maxKeys;
    }

    public boolean nodeEmpty(){
        return count == 0;
    }

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        
        // Recorremos SOLO hasta count para ignorar los nulls sobrantes
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i));
            if (i < this.count - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return "Nodo Id " + this.idNode + ": " + sb.toString();
    }
}
