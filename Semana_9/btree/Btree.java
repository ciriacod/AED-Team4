package Semana_9.btree;

import java.util.concurrent.ForkJoinPool;

public class Btree <E extends Comparable<E>>{
    private Bnode<E> root;
    private int orden;
    private boolean up;
    private Bnode<E> nDes;

    public Btree(int orden){
        this.orden = orden;
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public void insert (E c1){
        up = false;
        E mediana;
        Bnode<E> pnew;
        mediana = push(this.root, c1);
        if(up){
            pnew = new Bnode<>(this.orden);
            pnew.count = 1;

            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }
    }
    
    private E push(Bnode<E> current, E c1){
        int pos[] = new int[1];
        E mediana;
        if (current == null){
            up = true;
            nDes = null;
            return c1;
        }
        else{
            boolean f1;
            f1 = current.searchNode(c1, pos);
            if (f1){
                System.out.println("ITEM DUPLICADO\n");
                up = false;
                return null;
            }
            mediana = push(current.childs.get(pos[0]), c1);
            if (up){
                if (current.nodeFull(this.orden-1))
                    mediana = dividedNode(current, mediana, pos[0]);
                else{
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }
            return mediana;
        }
    }

    private void putNode(Bnode<E> current, E c1, Bnode<E> rd, int k){
        int i;
        for (i = current.count-1; i >= k ; i --){
            current.keys.set(i+1, current.keys.get(i));
            current.childs.set(i+2, current.childs.get(i+1));
        }
        current.keys.set(k, c1);
        current.childs.set(k+1, rd);
        current.count++;
    }

    private E dividedNode(Bnode<E> current, E c1, int k){
        Bnode<E> rd = nDes;
        int i , posMdna;
        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new Bnode<E>(this.orden);
        for (i = posMdna ; i < this.orden-2 ; i++){
            nDes.keys.set(i-posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }
        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;
        if (k <= this.orden / 2)
            putNode(current, c1, rd, k);
        else
            putNode(nDes, c1, rd, k - posMdna);
        E median = current.keys.get(current.count-1);
        nDes.childs.set(0, current.childs.get(current.count-1));
        current.count--;
        return median;
    }

}
