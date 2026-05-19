package Semana_8.avltree;

import Importar.Estructuras.LinkedBST;
import Importar.Exceptions.ItemDuplicated;

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
    
    // INICIO INSERTAR
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
            // DERECHA
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
            // IZQUIERDA
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
    // FIN INSERTAR
    
    // INICIO ROTACIONES
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
    // FIN ROTACIONES

    // INICIO AUX ROTACIONES
    private NodeAVL rotateSL (NodeAVL node){
        NodeAVL p = (NodeAVL)node.right;
        node.right = p.left;
        p.left = node;
        node = p;
        return node;
    }

    private NodeAVL rotateSR (NodeAVL node){
        NodeAVL p = (NodeAVL)node.left;
        node.left = p.right;
        p.right = node;
        node = p;
        return node;
    }
    // FIN AUX ROTACIONES

}
