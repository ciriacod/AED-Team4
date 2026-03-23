package Semana_2.Actividad;

import java.util.ArrayList;
import java.util.Iterator;

public class Bolsa<T> implements Iterable{
    private ArrayList<T> lista = new ArrayList<T>();
    private int tope;

    public Bolsa(int tope) {
        super();
        this.tope = tope;
    }

    public void agregar(T elemento){
        if(lista.size() < tope){
            lista.add(elemento);
        }else{
            throw new RuntimeException("No Caben mas elementos");
        }
    }
    
    @Override
    public Iterator iterator() {
        return lista.iterator();
    }
    
}