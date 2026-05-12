package Semana_7.Actividades;

import Importar.Estructuras.BinarySearchTree;
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

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|INSERT|¬+~¬-+~¬-+~¬-+~¬-+~¬ブリキノダンス-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~
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

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|SEARCH|¬+~¬-+~¬-+~¬-+~¬-+~¬-+神っぽいな~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~

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

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|DELETE|¬+~¬-+~¬-+~¬-+~¬-+~¬-+マインドブランド~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+
    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("Arbol Vacio");
        root = delete(root, data);
    }

    private Node delete(Node nodo, E data){
        if (nodo == null) return null;

        int comparador = data.compareTo(nodo.data);
        if (comparador < 0) nodo.left = delete(nodo.left, data);
        else if (comparador > 0) nodo.right = delete(nodo.right, data);
        else {
            if (nodo.right == null && nodo.left == null) return null;                //C1, hoja
            else if (nodo.right == null && nodo.left != null) return nodo.left;      //C2, Solo Raiz Izquierda
            else if (nodo.left == null && nodo.right != null) return nodo.right;     //C2, Solo Raiz Derecha
            else {                                                                   //C3, Dos Raices "calaverita"
                Node sucesor = min(nodo.right);
                nodo.data = sucesor.data;
                nodo.right = delete(nodo.right, sucesor.data);
            }
        }

        return nodo;
    }

    private Node min(Node nodo){            //El menor de los mayores
        while (nodo.left != null) nodo = nodo.left;
        return nodo;
    }

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node nodo){

        if (nodo == null) {
            return "";
        }

        return toString(nodo.left)
            + nodo.data + " "
            + toString(nodo.right);
    }

//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|InOrder|¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node nodo){
        if (nodo == null) return;

        inOrder(nodo.left);
        System.out.print(nodo.data + " ");
        inOrder(nodo.right);
    }
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|PreOrder|¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node nodo){
        if (nodo == null) return;

        System.out.print(nodo.data + " ");
        preOrder(nodo.left);
        preOrder(nodo.right);
    }

    public void postOrder(){
        postOrder(root);
    }
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|PostOrder|¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-
    private void postOrder(Node nodo){
        if (nodo == null) return;

        postOrder(nodo.left);
        postOrder(nodo.right);
        System.out.print(nodo.data + " ");
    }
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|NodoMinimo|¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
    public E findMinNode() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("Arbol Vacio");
        return findMinNode(root);
    }

    private E findMinNode(Node nodo){
        while (nodo.left != null) nodo = nodo.left;
        return nodo.data;
    }
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~|NodoMaximo|¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
    public E findMaxNode() throws ItemNoFound {
        if (isEmpty()) throw new ItemNoFound("Arbol Vacio");
        return findMaxNode(root);
    }

    private E findMaxNode(Node nodo){
        while (nodo.right != null) nodo = nodo.right;
        return nodo.data;
    }
//-+~¬-+~¬--+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬-+~¬
}