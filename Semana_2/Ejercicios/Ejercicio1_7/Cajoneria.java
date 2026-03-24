package Semana_2.Ejercicios.Ejercicio1_7;

import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T> implements Iterable<Caja<T>> {                        //Permite guardar objetos tipo Caja 
    private ArrayList<Caja<T>> lista = new ArrayList<>();
    private int tope;

    public Cajoneria(int tope) {
        this.tope = tope;
    }

    public void add(Caja<T> caja) {
        if (lista.size() < tope) {
            lista.add(caja);
        } else {
            throw new RuntimeException("No caben más cajas en la cajonería");
        }
    }
    
    // --- Casos del ejercicio n°4 ---
    public String search(T elemento) {                                          //Método search
        for (int i = 0; i < lista.size(); i++) {
            T contenidoCaja = lista.get(i).getContenido();
            if (contenidoCaja != null && contenidoCaja.equals(elemento)) {      //Se verifica si la caja tiene contenido y si coincide usando equals 
                return "Elemento encontrado en la Posición: " + (i + 1) + 
                        ", Color de caja: " + lista.get(i).getColor();
            }
        }
        return "Elemento no encontrado.";                                       //Si no se encuentra
    }
    
    public T delete(T elemento) {                                               //Método delete
        for (int i = 0; i < lista.size(); i++) {
            T contenidoCaja = lista.get(i).getContenido();
            if (contenidoCaja != null && contenidoCaja.equals(elemento)) {
                T objetoEliminado = contenidoCaja;
                lista.get(i).setContenido(null);                                //Se vacia la caja pero se mantiene en la cajonería
                return objetoEliminado;
            }
        }
        return null;                                                            //Si no se encuentra
    }

    @Override                                                                   
    public String toString() {                                                  //Sobrescritura del método toString
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-10s %-15s %-20s\n", "Posición", "Color Caja", "Objeto"));
        sb.append("----------------------------------------------------------\n");
        
        for (int i = 0; i < lista.size(); i++) {
            Caja<T> caja = lista.get(i);
            String datosObjeto = (caja.getContenido() != null) ? caja.getContenido().toString() : "<vacía>";
            sb.append(String.format("%-10d %-15s %-20s\n", (i + 1), caja.getColor(), datosObjeto));
        }
        return sb.toString();
    }
    
    // --- Casos del ejercicio n°6 ---
    public int contar(T elemento) {
        int contador = 0;
        for (Caja<T> caja : lista) {
            T contenido = caja.getContenido();
            if (contenido != null && contenido.equals(elemento)) {              //Se verifica que la caja no esté vacía y que el contenido sea igual
                contador++;
            }
        }
        return contador;
    }

    @Override
    public Iterator<Caja<T>> iterator() {
        return lista.iterator();
    }
}
