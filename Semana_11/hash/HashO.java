package Semana_11.hash;

import Importar.Estructuras.listLinked.*;

public class HashO<E extends Comparable<E>> {
    
    // 1. CLASE INTERNA IDÉNTICA A LA DE LAS FOTOS (Misma lógica de HashC)
    // Implementa Comparable para que pueda ser usada en tu ListLinked
    protected class Element implements Comparable<Element> {
        int mark;        // 1: Ocupado, -1: Eliminado (aunque en abierto el borrado suele ser físico, mantenemos el estándar)
        Register<E> reg; // El registro con su clave y valor
        
        public Element(int mark, Register<E> reg) {
            this.mark = mark; 
            this.reg = reg;
        }

        // Requerido por ListLinked para poder buscar y remover por clave
        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.reg.getKey(), o.reg.getKey());
        }

        @Override
        public String toString() {
            return reg != null ? reg.toString() : "null";
        }
    }
    
    // Arreglo de listas que ahora contienen 'Element' en vez de solo 'Register'
    protected ListLinked<Element>[] table; 
    protected int m; // Tamaño de la tabla (usamos 'm' como en tus diapositivas)
    
    @SuppressWarnings("unchecked")
    public HashO(int n) {
        this.m = n; 
        this.table = (ListLinked<Element>[]) new ListLinked[m];
        
        // Inicializamos cada posición del arreglo con una lista vacía
        for (int i = 0; i < m; i++) {
            this.table[i] = new ListLinked<Element>();
        }
    }
    
    // Función hash idéntica a la Foto 4
    private int functionHash(int key) {
        int index = key % m;
        return index < 0 ? index + m : index;
    }
    
    // Método Insert (Siguiendo la lógica de HashC)
    public void insert(int key, E value) {
        int index = functionHash(key);
        ListLinked<Element> list = table[index];
        
        // Clonamos la lógica de HashC: si la clave ya existe, se actualiza
        for (int i = 0; i < list.size(); i++) {
            Element current = list.get(i);
            if (current.mark == 1 && current.reg.getKey() == key) {
                current.reg = new Register<E>(key, value);
                return;
            }
        }
        
        // Si no existe, creamos un Element con mark = 1 (Ocupado) e insertamos
        Register<E> newReg = new Register<E>(key, value);
        Element newElement = new Element(1, newReg);
        list.insert(newElement); 
    }
    
    // Método Search (Siguiendo la lógica de HashC)
    public E search(int key) {
        int index = functionHash(key);
        ListLinked<Element> list = table[index];
        
        // Recorremos la lista enlazada de la casilla correspondiente
        for (int i = 0; i < list.size(); i++) {
            Element current = list.get(i);
            // Solo si está ocupado (mark == 1) y coincide la clave
            if (current.mark == 1 && current.reg.getKey() == key) {
                return current.reg.getValue();
            }
        }
        return null; // No encontrado
    }
    
    // Método Delete (Siguiendo la lógica de HashC)
    public void delete(int key) {
        int index = functionHash(key);
        ListLinked<Element> list = table[index];
        
        for (int i = 0; i < list.size(); i++) {
            Element current = list.get(i);
            if (current.mark == 1 && current.reg.getKey() == key) {
                // Aplicamos borrado lógico cambiando el mark a -1 igual que en HashC
                current.mark = -1; 
                current.reg = null; 
                return;
            }
        }
    }
    
    // Método toString formateado en columnas como la última sección de HashC (Foto 1/2)
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("D. Real\tD. Hash\tList (Registers)\n");
        for (int i = 0; i < m; i++) {
            s.append(i).append("\t");
            ListLinked<Element> list = table[i];
            
            if (list.isEmpty()) {
                s.append("\tempty\n");
            } else {
                s.append(i).append("\t");
                // Construimos una visualización limpia de los elementos activos en la lista
                StringBuilder listStr = new StringBuilder("[");
                int activeCount = 0;
                for (int j = 0; j < list.size(); j++) {
                    Element item = list.get(j);
                    if (item.mark == 1) { // Solo mostrar vigentes
                        if (activeCount > 0) listStr.append(", ");
                        listStr.append(item.reg.toString());
                        activeCount++;
                    }
                }
                listStr.append("]");
                s.append(listStr.toString()).append("\n");
            }
        }
        return s.toString();
    }
}