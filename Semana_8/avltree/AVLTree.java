package Semana_8.avltree;

import Importar.Estructuras.LinkedBST;
import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNotFound;

public class AVLTree<E extends Comparable<E>> extends LinkedBST<E> {
    private boolean changeHeight;

    // INICIO NODO AVL
    class NodeAVL extends Node<E>{
        protected int bf;

        public NodeAVL (E data){
            super(data);
            this.bf = 0;
        }

        public String toString(){
            return data + " bf =(" + bf + ")\n";
        }
    }
    // FIN NODO AVL
    
// INICIO INSERTAR--------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void insert(E x) throws ItemDuplicated{
        this.changeHeight = false;
        this.root = insert(x , (NodeAVL) this.root);
    }

    protected Node<E> insert(E x, NodeAVL node) throws ItemDuplicated{
        NodeAVL fat = node;

        if (node == null){
            this.changeHeight = true;
            fat = new NodeAVL (x);
        } else {
            int resC = node.data.compareTo(x); // NODO.REV COMPARADO CON VALOR INSERTARDO
            // DUP
            if (resC == 0) throw new ItemDuplicated("Ya se encuentra en el arbol");
            // INSERTAR DERECHA
            if (resC < 0){
                fat.right = insert(x, (NodeAVL)node.right);
                if (this.changeHeight){
                    switch (fat.bf) {
                        case -1:
                            fat.bf = 0;
                            this.changeHeight = false;
                            break;
                        case 0:
                            fat.bf = 1;
                            this.changeHeight = true;
                            break;
                        case 1:     // bf = 2
                            fat = balanceToLeft(fat);
                            this.changeHeight = false;
                            break;
                    }
                }
            // INSERTAR IZQUIERDA
            } else {
                fat.left = insert(x, (NodeAVL)node.left);
                if (this.changeHeight){
                    switch (fat.bf) {
                        case 1:
                            fat.bf = 0;
                            this.changeHeight = false;
                            break;
                        case 0:
                            fat.bf = -1;
                            this.changeHeight = true;
                            break;
                        case -1:    // bf = -2
                            fat = balanceToRight(fat);
                            this.changeHeight = false;
                            break;
                    }
                }
            }
        }
    return fat;
    }
// FIN INSERTAR--------------------------------------------------------------------------------------------------------------------------------------------
    
// INICIO ROTACIONES--------------------------------------------------------------------------------------------------------------------------------------------
    private NodeAVL balanceToLeft(NodeAVL node){
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) {
            case 1:
                node.bf = 0;
                hijo.bf = 0; 
                node = rotateSL(node);
                break;
        
            case -1:
                NodeAVL nieto = (NodeAVL)hijo.left;

                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;

                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    
                    case 1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        
        switch (hijo.bf) {
            case -1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;
                
            case 1:
                NodeAVL nieto = (NodeAVL) hijo.right;
                
                switch (nieto.bf) {
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case -1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
                break;
        }
        return node;
    }
// FIN ROTACIONES--------------------------------------------------------------------------------------------------------------------------------------------

// INICIO AUX ROTACIONES--------------------------------------------------------------------------------------------------------------------------------------------
    private NodeAVL rotateSL (NodeAVL node){
        if (node == null || node.right == null) {
            return node;  // no se puede rotar
        }
        NodeAVL p = (NodeAVL)node.right;
        node.right = p.left;
        p.left = node;
        node = p;
        return node;
    }

    private NodeAVL rotateSR (NodeAVL node){
        if (node == null || node.left == null) {
            return node;  // no se puede rotar
        }
        NodeAVL p = (NodeAVL)node.left;
        node.left = p.right;
        p.right = node;
        node = p;
        return node;
    }
// FIN AUX ROTACIONES--------------------------------------------------------------------------------------------------------------------------------------------

// INICIO ELIMINACION   -   EJERCICIO 3     ---------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void delete(E x) throws ItemNotFound {
        this.changeHeight = false;
        this.root = removeRec(x, (NodeAVL) this.root);
    }

    protected NodeAVL removeRec(E x, NodeAVL actual) throws ItemNotFound {
        if (actual == null) {
            throw new ItemNotFound(x + " no esta en el arbol");
        }

        NodeAVL res = actual;
        int cmp = actual.data.compareTo(x);

        if (cmp < 0) { // El elemento está en el subárbol DERECHO
            res.right = removeRec(x, (NodeAVL) actual.right);
            if (this.changeHeight) {
                res = balanceAfterRightDelete(res);
            }
        } 
        else if (cmp > 0) { // El elemento está en el subárbol IZQUIERDO
            res.left = removeRec(x, (NodeAVL) actual.left);
            if (this.changeHeight) {
                res = balanceAfterLeftDelete(res);
            }
        } 
        else { // ¡Elemento encontrado! Casos clásicos de eliminación BST
            if (actual.left != null && actual.right != null) { 
                // CASO: Dos hijos. Usamos el SUCESOR INORDEN (Mínimo del subárbol derecho)
                NodeAVL successor = findMinAVL((NodeAVL) actual.right);
                res.data = successor.data;
                // Eliminamos recursivamente el nodo del sucesor duplicado
                res.right = removeRec(successor.data, (NodeAVL) actual.right);
                if (this.changeHeight) {
                    res = balanceAfterRightDelete(res);
                }
            } 
            else { // CASO: Un hijo o nodo hoja
                res = (actual.left != null) ? (NodeAVL) actual.left : (NodeAVL) actual.right;
                this.changeHeight = true; // Al remover un nodo, la altura de este subárbol disminuye
            }
        }
        return res;
    }

    // Auxiliar para buscar el mínimo usando nodos casteados a NodeAVL
    private NodeAVL findMinAVL(NodeAVL node) {
        NodeAVL current = node;
        while (current.left != null) {
            current = (NodeAVL) current.left;
        }
        return current;
    }

    // Se ejecuta cuando se redujo la altura del subárbol DERECHO
    private NodeAVL balanceAfterRightDelete(NodeAVL node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                this.changeHeight = true; // La altura disminuyó, se propaga el cambio hacia arriba
                break;
            case 0:
                node.bf = -1;
                this.changeHeight = false; // Se estabilizó este subárbol, la altura general no cambia
                break;
            case -1: // El lado izquierdo ahora pesa de más (-2), ¡AQUÍ SE CORRIGE EL DESBALANCE!
                NodeAVL hijoIzquierdo = (NodeAVL) node.left;
                if (hijoIzquierdo.bf <= 0) {
                    if (hijoIzquierdo.bf == 0) {
                        node.bf = -1;
                        hijoIzquierdo.bf = 1;
                        this.changeHeight = false; 
                    } else {
                        node.bf = 0;
                        hijoIzquierdo.bf = 0;
                        this.changeHeight = true;
                    }
                    node = rotateSR(node); // Rotación Simple Derecha
                } else {
                    // Caso Doble Izquierda-Derecha (RDR)
                    NodeAVL nieto = (NodeAVL) hijoIzquierdo.right;
                    switch (nieto.bf) {
                        case 1:  node.bf = 0;  hijoIzquierdo.bf = -1; break;
                        case 0:  node.bf = 0;  hijoIzquierdo.bf = 0;  break;
                        case -1: node.bf = 1;  hijoIzquierdo.bf = 0;  break;
                    }
                    nieto.bf = 0;
                    node.left = rotateSL(hijoIzquierdo);
                    node = rotateSR(node);
                    this.changeHeight = true;
                }
                break;
        }
        return node;
    }

    // Se ejecuta cuando se redujo la altura del subárbol IZQUIERDO
    private NodeAVL balanceAfterLeftDelete(NodeAVL node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                this.changeHeight = true; // La altura disminuyó, se propaga hacia arriba
                break;
            case 0:
                node.bf = 1;
                this.changeHeight = false;
                break;
            case 1: // El lado derecho ahora pesa de más (2)
                NodeAVL hijoDerecho = (NodeAVL) node.right;
                if (hijoDerecho.bf >= 0) {
                    if (hijoDerecho.bf == 0) {
                        node.bf = 1;
                        hijoDerecho.bf = -1;
                        this.changeHeight = false;
                    } else {
                        node.bf = 0;
                        hijoDerecho.bf = 0;
                        this.changeHeight = true;
                    }
                    node = rotateSL(node); // Rotación Simple Izquierda
                } else {
                    // Caso Doble Derecha-Izquierda (RDL)
                    NodeAVL nieto = (NodeAVL) hijoDerecho.left;
                    switch (nieto.bf) {
                        case -1: node.bf = 0;  hijoDerecho.bf = 1;  break;
                        case 0:  node.bf = 0;  hijoDerecho.bf = 0;  break;
                        case 1:  node.bf = -1; hijoDerecho.bf = 0;  break;
                    }
                    nieto.bf = 0;
                    node.right = rotateSR(hijoDerecho);
                    node = rotateSL(node);
                    this.changeHeight = true;
                }
                break;
        }
        return node;
    }

// FINAL ELIMINACION--------------------------------------------------------------------------------------------------------------------------------------------

// RECORRIDO POR AMPLITUD RECURSIVO -   EJERCICIO 4     ---------------------------------------------------------------------------------------------------------------------------------------------
    // Método público principal
    public void breadthFirstRecursive() {
        if (this.root == null) {
            System.out.println("Árbol vacío.");
            return;
        }

        // 1. Obtener la altura del árbol de manera recursiva
        int maxLevel = getBreadthHeight((NodeAVL) this.root);

        // 2. Iterar nivel por nivel e imprimir recursivamente cada uno
        for (int i = 0; i <= maxLevel; i++) {
            printGivenLevel((NodeAVL) this.root, i);
        }
        System.out.println(); // Salto de línea al terminar el recorrido
    }

    // Método auxiliar 1: Calcula la altura máxima del subárbol recursivamente
    private int getBreadthHeight(NodeAVL node) {
        if (node == null) return -1; // Un nodo nulo tiene altura -1

        int leftHeight = getBreadthHeight((NodeAVL) node.left);
        int rightHeight = getBreadthHeight((NodeAVL) node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    // Método auxiliar 2: Baja recursivamente hasta el nivel deseado e imprime
    private void printGivenLevel(NodeAVL node, int level) {
        if (node == null) return;

        // Caso base: Llegamos al nivel que queríamos imprimir en esta iteración
        if (level == 0) {
            System.out.print(node.data + " ");
        } 
        // Si no hemos llegado, seguimos bajando decrementando el nivel objetivo
        else if (level > 0) {
            printGivenLevel((NodeAVL) node.left, level - 1);
            printGivenLevel((NodeAVL) node.right, level - 1);
        }
    }
// FINAL RECORRIDO POR AMPLITUD RECURSIVO--------------------------------------------------------------------------------------------------------------------------------------------

// INICIO RECORRIDO EN PREORDEN ESPECIALIZADO   -   EJERCICIO 6   ---------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void preOrder() {
        if (this.root == null) {
            System.out.println("Árbol vacío.");
            return;
        }
        // Iniciamos la recursión casteando la raíz a NodeAVL
        preOrderRecursive((NodeAVL) this.root);
        System.out.println(); // Salto de línea al terminar el recorrido
    }

    // Método privado recursivo: Raíz -> Izquierda -> Derecha
    private void preOrderRecursive(NodeAVL node) {
        if (node != null) {
            // Imprimimos el dato
            System.out.print(node.data + " "); 

            // Procesar el subárbol izquierdo
            preOrderRecursive((NodeAVL) node.left);

            // Procesar el subárbol derecho
            preOrderRecursive((NodeAVL) node.right);
        }
    }

// FINAL RECORRIDO EN PREORDEN ESPECIALIZADO--------------------------------------------------------------------------------------------------------------------------------------------

}