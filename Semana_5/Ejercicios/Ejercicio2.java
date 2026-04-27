package laboratorio5;
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
	void print() {
	    Node<T> curr = head;

	    while (curr != null) {
	        System.out.print(curr.data + " ");
	        curr = curr.next;
	    }

	    System.out.println();
	}
}


class InvertirLista<T>
{
	public static <T> ListLinked<T> invertirLista(ListLinked<T> lista)
	{
		ListLinked<T> nueva = new ListLinked<> (); // nueva lista vacia
		Node<T> curr = lista.head; //empezar desde la cabeza
		while (curr !=null) { //inserta al inico invertimos el orden
			nueva.insertFirst(curr.data); 
			curr = curr.next; //avanzamos
		}
		return nueva; //retorna la nueva lista invertida
		
	}
	
}

class Main {
    public static void main(String[] args) {

        ListLinked<Integer> lista = new ListLinked<>();

        lista.insertFirst(10);
        lista.insertFirst(20);
        lista.insertFirst(30);
        lista.print();

      

        ListLinked<Integer> invertida = InvertirLista.invertirLista(lista);

        invertida.print();
    }
}
