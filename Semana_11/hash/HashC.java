package Semana_11.hash;

import java.util.ArrayList;

public class HashC<E extends Comparable<E>> {
    // Clase interna para representar una celda de la tabla hash
    private class Element {
        int mark; // 0: Vacío, 1: Ocupado, 2: Eliminado
        Register<E> reg;
        
        public Element(int mark, Register<E> reg) {
            this.mark = mark; 
            this.reg = reg;
        }
    }
    
    private ArrayList<Element> table; // Cambiado a ArrayList
    protected int m; // Tamaño de la tabla
    
    public HashC (int n) {
        this.m = n; // Calcular el primo cercano a n
        this.table = new ArrayList<Element>(m);
        
        // Inicializamos el ArrayList con elementos vacíos para poder acceder por índice
        for (int i = 0; i < m; i++) {
            this.table.add(new Element(0, null));
        }
    }
    
    // Funcion hash para calcular el indice a partir de la clave
    private int functionHash(int key) {
        return key % m;
    }
    
    // Método de exploración lineal
    private int linearProbing(int dressHash, int key) {
        // Retorna la siguiente posición de manera circular
        return (dressHash + 1) % m;
    }
    
    /* 
     * Metodo para insertar un nuevo registro en la tabla hash
     * Utiliza sondeo lineal para encontrar una celda disponible
     * Si la tabla esta llena, muestra un mensaje de error.
    */
    public void insert(int key, E reg) {
        int homeIndex = functionHash(key);
        int index = homeIndex;
        
        // Buscamos una celda libre (0 o -1) recorriendo mientras esté ocupada
        while (table.get(index).mark == 1) {
            // Evitar duplicados de claves si ya existe
            if (table.get(index).reg.getKey() == key) {
                table.get(index).reg = new Register<E>(key, reg);
                return;
            }
            index = linearProbing(index, key);
            
            if (index == homeIndex) {
                System.out.println("Error: Tabla Hash llena.");
                return;
            }
        }
        
        // Insertamos el nuevo registro en la posición hallada
        table.get(index).reg = new Register<E>(key, reg);
        table.get(index).mark = 1; // Ocupado
    }
    
    /*
     * Metodo para buscar un registro en la table por su clave
     * Debe recorrer usando sondeo lineal hasta encontrar la clave o determinar que no esta
    */
    public E search(int key) {
        int homeIndex = functionHash(key);
        int index = homeIndex;
        
        // Se detiene solo si encuentra una posición totalmente vacía (mark == 0)
        while (table.get(index).mark != 0) {
            // Si está ocupado (1) y coincide la clave, lo encontramos
            if (table.get(index).mark == 1 && table.get(index).reg.getKey() == key) {
                return table.get(index).reg.getValue();
            }
            index = linearProbing(index, key);
            
            if (index == homeIndex) {
                break;
            }
        }
        return null; // No encontrado
    }
    
    /*
     * Metodo para eliminar un registro de forma logica
     * Marcar la celda como disponible sin eliminar el objeto Element
    */
    public void delete(int key) {
        int homeIndex = functionHash(key);
        int index = homeIndex;
        
        while (table.get(index).mark != 0) {
            if (table.get(index).mark == 1 && table.get(index).reg.getKey() == key) {
                table.get(index).mark = -1; // -1 significa "posición eliminada"
                table.get(index).reg = null; 
                return;
            }
            index = linearProbing(index, key);
            if (index == homeIndex) {
                break;
            }
        }
    }
    
    /*
     * Metodo para imprimir el estado actual de la tabla hash
     * Debe recorrer la tabla e imprimir cada indice con su contenido
    */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("D. Real\tD. Hash\tRegister\n");
        for (int i = 0; i < m; i++) {
            Element item = table.get(i);
            s.append(i).append("\t");
            
            if (item.mark == 1) {
                int homeHash = functionHash(item.reg.getKey());
                s.append(homeHash).append("\t").append(item.reg.toString()).append("\n");
            } else {
                s.append("\tempty\n");
            }
        }
        return s.toString();
    }
}
