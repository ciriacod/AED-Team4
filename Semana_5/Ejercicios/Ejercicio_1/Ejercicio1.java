package Semana_5.Ejercicios.Ejercicio_1;
class Node<T>{
	T data;
	Node<T> next;
	Node(T data){
		this.data = data;
		this.next = null;
	}
	
}

class ListLinked<T>{
	Node<T> head;
	ListLinked(){
		this.head=null;
	}
	
	boolean isEmptyList() {
		return head == null;
	}
	
	void insertFirst(T x) {
		Node<T> temp = new Node<> (x);
		temp.next = head;
		head=temp;
				}
}

class BuscarElemento<T>
{	
    public static <T> boolean buscarElement(ListLinked<T> lista, T dato) {

        Node<T> curr = lista.head; //empezar desde la cabeza real

        while (curr != null) { // recorrer toda la lista

            if (curr.data.equals(dato)) { // comparar el dato
                return true; // encontrado
            }

            curr = curr.next; // avanzar al siguiente nodo
        }

        return false; // no encontrado
    }

}

class Main {
    public static void main(String[] args) {

        ListLinked<Integer> lista = new ListLinked<>();

        lista.insertFirst(10);
        lista.insertFirst(20);
        lista.insertFirst(30);

        System.out.println(BuscarElemento.buscarElement(lista, 20)); 
        System.out.println(BuscarElemento.buscarElement(lista, 5));  
    }
}
