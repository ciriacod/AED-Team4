package Semana_9.btree;
public class Btree<E extends Comparable<E>> {

    private Bnode<E> root;
    private int orden;
    private boolean up;
    private Bnode<E> nDes;

    // /// se usa en biblioteca
    private int size;

    public Btree(int orden) {
        this.orden = orden;
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E c1) {
        up = false;
        E mediana;
        Bnode<E> pnew;

        mediana = push(this.root, c1);

        if (up) {
            pnew = new Bnode<>(this.orden);
            pnew.count = 1;

            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }

        // /// se usa en biblioteca
        if (mediana != null) {
            size++;
        }
    }

    private E push(Bnode<E> current, E c1) {
        int pos[] = new int[1];
        E mediana;

        if (current == null) {
            up = true;
            nDes = null;
            return c1;
        } else {
            boolean f1;
            f1 = current.searchNode(c1, pos);

            if (f1) {
                System.out.println("ITEM DUPLICADO\n");
                up = false;
                return null;
            }

            mediana = push(current.childs.get(pos[0]), c1);

            if (up) {
                if (current.nodeFull(this.orden - 1)) {
                    mediana = dividedNode(current, mediana, pos[0]);
                } else {
                    up = false;
                    putNode(current, mediana, nDes, pos[0]);
                }
            }

            return mediana;
        }
    }

    private void putNode(Bnode<E> current, E c1, Bnode<E> rd, int k) {
        int i;

        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }

        current.keys.set(k, c1);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(Bnode<E> current, E c1, int k) {
        Bnode<E> rd = nDes;
        int i, posMdna;

        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;

        nDes = new Bnode<E>(this.orden);

        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
        }

        nDes.count = (this.orden - 1) - posMdna;
        current.count = posMdna;

        if (k <= this.orden / 2) {
            putNode(current, c1, rd, k);
        } else {
            putNode(nDes, c1, rd, k - posMdna);
        }

        E median = current.keys.get(current.count - 1);

        nDes.childs.set(0, current.childs.get(current.count));
        current.count--;

        return median;
    }

    // /// se usa en biblioteca
    public int size() {
        return size;
    }

    // /// se usa en biblioteca
    public boolean search(E key) {
        return search(this.root, key);
    }

    private boolean search(Bnode<E> current, E key) {
        if (current == null) {
            return false;
        }

        int pos[] = new int[1];

        if (current.searchNode(key, pos)) {
            return true;
        }

        return search(current.childs.get(pos[0]), key);
    }

    // /// se usa en biblioteca
    public void searchPath(E key) {
        searchPath(this.root, key);
    }

    private void searchPath(Bnode<E> current, E key) {
        if (current == null) {
            System.out.println("Elemento no encontrado.");
            return;
        }

        System.out.println("Visitando nodo: " + current);

        int pos[] = new int[1];

        if (current.searchNode(key, pos)) {
            System.out.println("Encontrado: " + current.keys.get(pos[0]));
        } else {
            searchPath(current.childs.get(pos[0]), key);
        }
    }

    // /// se usa en biblioteca
    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(Bnode<E> current) {
        if (current == null) {
            return;
        }

        int i;

        for (i = 0; i < current.count; i++) {
            inOrder(current.childs.get(i));
            System.out.println(current.keys.get(i));
        }

        inOrder(current.childs.get(i));
    }

    // /// se usa en biblioteca
    public int height() {
        int altura = 0;
        Bnode<E> aux = this.root;

        while (aux != null) {
            altura++;
            aux = aux.childs.get(0);
        }

        return altura;
    }

    public void searchRange(E min, E max) {
        if (min.compareTo(max) > 0) {
            System.out.println("Rango invalido.");
            return;
        }

        searchRange(this.root, min, max);
    }

    private void searchRange(Bnode<E> current, E min, E max) {
        if (current == null) {
            return;
        }

        int i;

        for (i = 0; i < current.count; i++) {
            if (current.keys.get(i).compareTo(min) > 0) {
                searchRange(current.childs.get(i), min, max);
            }

            if (current.keys.get(i).compareTo(min) >= 0 &&
                current.keys.get(i).compareTo(max) <= 0) {
                System.out.println(current.keys.get(i));
            }

            if (current.keys.get(i).compareTo(max) > 0) {
                return;
            }
        }

        searchRange(current.childs.get(i), min, max);
    }
    public void remove(E key) {
        if (remove(this.root, key)) {
            size--;
            System.out.println("Elemento eliminado.");
        } else {
            System.out.println("Elemento no encontrado.");
        }
    }
//ejercico4
    private boolean remove(Bnode<E> current, E key) {
        if (current == null) {
            return false;
        }

        int pos[] = new int[1];

        if (current.searchNode(key, pos)) {

            for (int i = pos[0]; i < current.count - 1; i++) {
                current.keys.set(i, current.keys.get(i + 1));
            }

            current.keys.set(current.count - 1, null);
            current.count--;

            if (current == root && current.count == 0) {
                root = null;
            }

            return true;
        }

        return remove(current.childs.get(pos[0]), key);
    }
    @Override
    public String toString() {
        if (isEmpty()) {
            return "BTree is empty...";
        }

        return writeTree(this.root);
    }

    private String writeTree(Bnode<E> current) {
        if (current == null) {
            return "";
        }

        String s = current.toString() + "\n";

        for (int i = 0; i <= current.count; i++) {
            s += writeTree(current.childs.get(i));
        }

        return s;
    }
}
