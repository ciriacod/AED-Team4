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

        System.out.println(BuscarElemento.buscarElement(lista, 20)); 
        System.out.println(BuscarElemento.buscarElement(lista, 5));  

        ListLinked<Integer> invertida = InvertirLista.invertirLista(lista);

        invertida.print();
    }
}
