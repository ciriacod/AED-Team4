package Semana_7.Actividades;

import Importar.BinarySearchTree;
import Importar.Exceptions.ExceptionIsEmpty;
import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNoFound;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    class Node{
        public E data;
        public Node left;
        public Node right;

        public Node(E data){
            this (data, null, null);
        }

        public Node(E data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;
    public LinkedBST(){
        this.root = null;
    }

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|INSERT|¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
    @Override
    public void insert(E data) throws ItemDuplicated {
        try{
            search(data);
            throw new ItemDuplicated("Item Duplicado");         //Duplicado
        } catch (ItemNoFound e) {
            insert(root, data);                                     // CR
        }
    }

    private void insert(Node nodo, E data){
        if (isEmpty()){
            root = new Node(data);                                  // CB, inserta
            return;
        } 
        
        int comparador = data.compareTo(nodo.data);                         //Caso Recursivo.
        if (comparador < 0) {                                               //Caso es menor, se inserta nodo izquierdo
            if (nodo.left == null) nodo.left = new Node(data);      //CB, Inserta
            else insert(nodo.left, data);
        } else {                                                            //Caso es mayor, se inserta nodo derecho
            if (nodo.right == null) nodo.right = new Node(data);    //CB, Inserta        
            else insert(nodo.right, data);
        }
    }
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|SEARCH|¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬

    @Override
    public E search(E data) throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("Item no Encontrado, Arbol Vacio");
        return search(root, data);          //Inicio Rercursion
    }

    private E search(Node nodo, E data) throws ItemNoFound{
        if(nodo == null) {throw new ItemNoFound("Item no Encontrado");} //Caso Base, No encontrado

        int comparador = data.compareTo(nodo.data);

        if (comparador == 0) return nodo.data;                              //Caso Base, Encontrado
        else if (comparador < 0) return search (nodo.left, data);           //Es menor, analizando nodo izquierdo (menores)
        else return search (nodo.right, data);                              //Es mayor, analizando nodo derecho (mayores)
    }

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
    
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }
}
