

import java.util.ArrayList;                                                     //Librerias importadas para manejar el uso de datos agrupados
import java.util.Iterator;

public class Bolsa<T> implements Iterable<T> {                                  //Clase que implementa Iterable para poder usar
    private ArrayList<T> lista = new ArrayList<>();                             //la bolsa en bucles for-each
    private int tope;

    public Bolsa(int tope) {                                                    //Constructor que establece el límite de la bolsa
        this.tope = tope; 
    }

    public void add(T objeto) {                                                 //Método que agrega objetos a la bolsa
        if (lista.size() < tope) {                                              //Verifica si hay espacio antes de agregar
            lista.add(objeto);
        } else {
            throw new RuntimeException("no caben más");                         //Lanza excepción si se alcanza el límite
        }
    }

    @Override
    public Iterator<T> iterator() {                                             //Sobrescritura del método iterator que permite recorrer los elementos
        return lista.iterator();                                                
    }
}
