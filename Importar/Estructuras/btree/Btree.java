package Importar.Estructuras.btree;

public class Btree<E extends Comparable<E>> {

    private BNode<E> root;
    private int orden;
    private boolean up;
    private BNode<E> nDes;
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
        BNode<E> pnew;

        mediana = push(this.root, c1);

        if (up) {
            pnew = new BNode<>(this.orden);
            pnew.count = 1;
            pnew.keys.set(0, mediana);
            pnew.childs.set(0, this.root);
            pnew.childs.set(1, nDes);
            this.root = pnew;
        }

        if (mediana != null) {
            size++;
        }
    }

    private E push(BNode<E> current, E c1) {
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

    private void putNode(BNode<E> current, E c1, BNode<E> rd, int k) {
        int i;
        for (i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1, current.keys.get(i));
            current.childs.set(i + 2, current.childs.get(i + 1));
        }

        current.keys.set(k, c1);
        current.childs.set(k + 1, rd);
        current.count++;
    }

    private E dividedNode(BNode<E> current, E c1, int k) {
        BNode<E> rd = nDes;
        int i, posMdna;

        posMdna = (k <= this.orden / 2) ? this.orden / 2 : this.orden / 2 + 1;
        nDes = new BNode<E>(this.orden);

        for (i = posMdna; i < this.orden - 1; i++) {
            nDes.keys.set(i - posMdna, current.keys.get(i));
            nDes.childs.set(i - posMdna + 1, current.childs.get(i + 1));
            current.keys.set(i, null);
            current.childs.set(i + 1, null);
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
        
        current.keys.set(current.count - 1, null);
        current.childs.set(current.count, null);
        current.count--;

        return median;
    }

    public int size() {
        return size;
    }

    public boolean search(E key) {
        return search(this.root, key);
    }

    private boolean search(BNode<E> current, E key) {
        if (current == null) {
            return false;
        }

        int pos[] = new int[1];

        if (current.searchNode(key, pos)) {
            System.out.println(key + " se encuentra en el nodo " + current.idNode + " en la posicion " + pos[0]);
            return true;
        }

        return search(current.childs.get(pos[0]), key);
    }

    public void searchPath(E key) {
        searchPath(this.root, key);
    }

    private void searchPath(BNode<E> current, E key) {
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

    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(BNode<E> current) {
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

    public int height() {
        int altura = 0;
        BNode<E> aux = this.root;

        while (aux != null) {
            altura++;
            aux = aux.childs.get(0);
        }

        return altura;
    }

    public void searchRange(E min, E max) {
        if (min == null || max == null || min.compareTo(max) > 0) {
            System.out.println("Rango invalido: El valor minimo no puede ser mayor que el maximo.");
            return;
        }

        System.out.print("Claves en el rango [" + min + ", " + max + "]: ");
        boolean[] foundAny = new boolean[1];

        searchRange(this.root, min, max, foundAny);

        if (!foundAny[0]) {
            System.out.print("Rango inexistente en el arbol.");
        }

        System.out.println();
    }

    private void searchRange(BNode<E> current, E min, E max, boolean[] foundAny) {
        if (current == null) {
            return;
        }

        int i = 0;
        while (i < current.count && current.keys.get(i).compareTo(min) < 0) {
            i++;
        }

        while (i < current.count) {
            E currentKey = current.keys.get(i);

            if (currentKey.compareTo(max) > 0) {
                searchRange(current.childs.get(i), min, max, foundAny);
                return;
            }
            
            searchRange(current.childs.get(i), min, max, foundAny);
            System.out.print(currentKey + " ");
            foundAny[0] = true;
            i++;
        }

        searchRange(current.childs.get(i), min, max, foundAny);
    }

    public void remove(E key) {
        if (delete(key)) {
            size--;
            System.out.println("Elemento eliminado.");
        } else {
            System.out.println("Elemento no encontrado.");
        }
    }

    public boolean delete(E key) {
        if (isEmpty()) {
            return false;
        }

        boolean result = delete(this.root, key);

        if (this.root != null && this.root.count == 0) {
            if (this.root.childs.get(0) == null) {
                this.root = null;
            } else {
                this.root = this.root.childs.get(0);
            }
        }

        return result;
    }

    private boolean delete(BNode<E> current, E key) {
        int pos[] = new int[1];
        boolean found = current.searchNode(key, pos);

        if (found) {
            if (current.childs.get(0) == null) {
                removeFromLeaf(current, pos[0]);
            } else {
                removeFromNonLeaf(current, pos[0]);
            }
            return true;
        } else {
            if (current.childs.get(0) == null) {
                return false;
            }

            boolean deleted = delete(current.childs.get(pos[0]), key);
            int minKeys = (this.orden - 1) / 2;
            
            if (deleted && current.childs.get(pos[0]).count < minKeys) {
                fixUnderflow(current, pos[0]);
            }

            return deleted;
        }
    }

    private void removeFromLeaf(BNode<E> current, int idx) {
        for (int i = idx; i < current.count - 1; i++) {
            current.keys.set(i, current.keys.get(i + 1));
        }
        current.keys.set(current.count - 1, null);
        current.count--;
    }

    private void removeFromNonLeaf(BNode<E> current, int idx) {
        BNode<E> successorNode = current.childs.get(idx + 1);
        
        while (successorNode.childs.get(0) != null) {
            successorNode = successorNode.childs.get(0);
        }

        E successorKey = successorNode.keys.get(0);
        current.keys.set(idx, successorKey);

        delete(current.childs.get(idx + 1), successorKey);

        int minKeys = (this.orden - 1) / 2;
        if (current.childs.get(idx + 1).count < minKeys) {
            fixUnderflow(current, idx + 1);
        }
    }

    private void fixUnderflow(BNode<E> parent, int idx) {
        int minKeys = (this.orden - 1) / 2;

        if (idx > 0 && parent.childs.get(idx - 1).count > minKeys) {
            borrowFromLeft(parent, idx);
        } else if (idx < parent.count && parent.childs.get(idx + 1).count > minKeys) {
            borrowFromRight(parent, idx);
        } else {
            if (idx > 0) {
                mergeNodes(parent, idx - 1);
            } else {
                mergeNodes(parent, idx);
            }
        }
    }

    private void borrowFromLeft(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx - 1);

        for (int i = child.count; i > 0; i--) {
            child.childs.set(i + 1, child.childs.get(i));
        }
        child.childs.set(1, child.childs.get(0));

        for (int i = child.count - 1; i >= 0; i--) {
            child.keys.set(i + 1, child.keys.get(i));
        }

        child.keys.set(0, parent.keys.get(idx - 1));
        child.childs.set(0, sibling.childs.get(sibling.count));

        parent.keys.set(idx - 1, sibling.keys.get(sibling.count - 1));

        sibling.keys.set(sibling.count - 1, null);
        sibling.childs.set(sibling.count, null);

        child.count++;
        sibling.count--;
    }

    private void borrowFromRight(BNode<E> parent, int idx) {
        BNode<E> child = parent.childs.get(idx);
        BNode<E> sibling = parent.childs.get(idx + 1);

        child.keys.set(child.count, parent.keys.get(idx));
        child.childs.set(child.count + 1, sibling.childs.get(0));

        parent.keys.set(idx, sibling.keys.get(0));

        for (int i = 0; i < sibling.count - 1; i++) {
            sibling.keys.set(i, sibling.keys.get(i + 1));
        }
        
        for (int i = 0; i < sibling.count; i++) {
            sibling.childs.set(i, sibling.childs.get(i + 1));
        }
        
        sibling.keys.set(sibling.count - 1, null);
        sibling.childs.set(sibling.count, null);

        child.count++;
        sibling.count--;
    }

    private void mergeNodes(BNode<E> parent, int idx) {
        BNode<E> left = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        left.keys.set(left.count, parent.keys.get(idx));
        left.count++;

        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count, right.keys.get(i));
            left.childs.set(left.count, right.childs.get(i));
            left.count++;
        }
        left.childs.set(left.count, right.childs.get(right.count));

        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.keys.set(parent.count - 1, null);
        parent.childs.set(parent.count, null);
        parent.count--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Id.Nodo\t\tClaves Nodo\tId.Padre\tId.Hijos\n");
        buildTableString(this.root, null, sb);
        return sb.toString();
    }

    private void buildTableString(BNode<E> current, BNode<E> parent, StringBuilder sb) {
        if (current == null) {
            return;
        }

        sb.append(current.idNode).append("\t\t");

        sb.append("(");
        for (int i = 0; i < current.count; i++) {
            sb.append(current.keys.get(i));
            if (i < current.count - 1) {
                sb.append(", ");
            }
        }
        sb.append(")\t\t");

        if (parent == null) {
            sb.append("--\t\t");
        } else {
            sb.append("[").append(parent.idNode).append("]\t\t");
        }

        if (current.childs.get(0) != null) {
            sb.append("[");
            for (int i = 0; i <= current.count; i++) {
                if (current.childs.get(i) != null) {
                    sb.append(current.childs.get(i).idNode);
                    if (i < current.count && current.childs.get(i + 1) != null) {
                        sb.append(", ");
                    }
                }
            }
            sb.append("]\n");
        } else {
            sb.append("--\n");
        }

        for (int i = 0; i <= current.count; i++) {
            buildTableString(current.childs.get(i), current, sb);
        }
    }
}