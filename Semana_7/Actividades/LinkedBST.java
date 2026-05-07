package Semana_7.Actividades;

import Importar.BinarySearchTree;
import Importar.Exceptions.ExceptionIsEmpty;
import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNoFound;

public class LinkedBST<E> implements BinarySearchTree<E> {

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

    @Override
    public void insert(E data) throws ItemDuplicated {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public E search(E data) throws ItemNoFound {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

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
