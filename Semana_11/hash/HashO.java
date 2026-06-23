package Semana_11.hash;

import Importar.Estructuras.listLinked.ListLinked;

public class HashO {

    // En Hash Abierto, el elemento no necesita marca de borrado lógico
    private static class Element implements Comparable<Element> {
        Register register;

        public Element(Register register) {
            this.register = register;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.register.getKey(), o.register.getKey());
        }

        @Override
        public String toString() {
            return register != null ? register.toString() : "null";
        }
    }

    private ListLinked<Element>[] table; // Arreglo de listas enlazadas
    private int size;                     // Tamaño de la tabla (m)

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size = size;
        this.table = (ListLinked<Element>[]) new ListLinked[size];
        
        // Inicializamos cada casilla con una lista vacía para evitar NullPointerException
        for (int i = 0; i < size; i++) {
            this.table[i] = new ListLinked<Element>();
        }
    }

    // Función hash base (residuo)
    private int hash(int key) {
        int index = key % size;
        return index < 0 ? index + size : index; // Previene índices negativos
    }

//------------------------------Insert---------------------------------

    /**
     * Método oficial para insertar un objeto Register completo.
     * Si la clave ya existe dentro de la lista, actualiza su valor.
     */
    public void insert(Register reg) {
        int index = hash(reg.getKey());
        ListLinked<Element> list = table[index];

        // Buscar si la clave ya existe en la lista para actualizarla (evita duplicados)
        for (int i = 0; i < list.size(); i++) {
            Element current = list.get(i);
            if (current.register.getKey() == reg.getKey()) {
                current.register = reg; // Actualización de datos
                return;
            }
        }

        // Si no existía, creamos el elemento y lo insertamos en la lista
        Element newElement = new Element(reg);
        list.insert(newElement); // Tu ListLinked lo añade a la cadena
    }

    /**
     * Método sobrecargado para comodidad del Main (No requiere importar Register externamente)
     */
    public void insert(int key, String value) {
        Register nuevoRegistro = new Register(key, value);
        this.insert(nuevoRegistro);
    }

//------------------------------Search---------------------------------

    /**
     * Método oficial para buscar un registro por clave.
     * Devuelve el objeto Register completo o null si no existe.
     */
    public Register search(int key) {
        int index = hash(key);
        ListLinked<Element> list = table[index];

        // Recorremos la lista encadenada de esa posición
        for (int i = 0; i < list.size(); i++) {
            Element current = list.get(i);
            if (current.register.getKey() == key) {
                return current.register; // Encontrado
            }
        }
        return null; // No está en la lista
    }

//------------------------------Delete---------------------------------

    /**
     * Método oficial para eliminar un registro.
     * Aplica ELIMINACIÓN FÍSICA real sobre la lista enlazada.
     */
    public void delete(int key) {
        int index = hash(key);
        ListLinked<Element> list = table[index];

        for (int i = 0; i < list.size(); i++) {
            Element current = list.get(i);
            if (current.register.getKey() == key) {
                list.remove(current); 
                System.out.println("Clave " + key + " eliminada físicamente de la lista.");
                return;
            }
        }
        System.out.println("Clave " + key + " no encontrada para eliminar.");
    }

//------------------------------Print---------------------------------

    /**
     * Método de impresión limpio corregido.
     * Elimina el doble índice que causaba el desalineamiento en consola.
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("D. Real\tD. Hash\tList (Registers)\n");
        s.append("--------------------------------------------------\n");
        for (int i = 0; i < size; i++) {
            s.append(i).append("\t");
            ListLinked<Element> list = table[i];

            if (list.isEmpty()) {
                s.append("\tempty\n");
            } else {
                // Imprimimos el hash correspondiente (que es el mismo índice real i)
                s.append(i).append("\t");
                
                StringBuilder listStr = new StringBuilder("[");
                for (int j = 0; j < list.size(); j++) {
                    if (j > 0) listStr.append(" -> ");
                    listStr.append(list.get(j).register.toString());
                }
                listStr.append("]");
                s.append(listStr.toString()).append("\n");
            }
        }
        return s.toString();
    }
}