package Semana_11.hash;

public class HashC {

    private static class Element {
        Register<String> register;
        int estado; // 0 = EMPTY, 1 = OCCUPIED, -1 = DELETED

        public Element() {
            this.register = null;
            this.estado = 0; // Inicialmente vacía (EMPTY)
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(int key, String value) {
        Register<String> reg = new Register<>(key, value);
        int homeIndex = hash(key);
        int index = homeIndex;
        int firstDeletedIndex = -1;

        do {
            // Si encontramos una celda limpia (EMPTY), terminamos la búsqueda de la secuencia
            if (table[index].estado == 0) {
                break;
            }

            // Si encontramos una celda borrada (DELETED), recordamos la primera para reutilizarla
            if (table[index].estado == -1) {
                if (firstDeletedIndex == -1) {
                    firstDeletedIndex = index;
                }
            } 
            // Si está ocupada, verificamos duplicados para actualizar
            else if (table[index].estado == 1 && table[index].register.getKey() == key) {
                table[index].register = reg;
                return;
            }

            index = (index + 1) % size;
        } while (index != homeIndex);

        // Priorizamos la reutilización del espacio DELETED
        int targetIndex = (firstDeletedIndex != -1) ? firstDeletedIndex : index;

        if (table[targetIndex].estado == 0 || table[targetIndex].estado == -1) {
            table[targetIndex].register = reg;
            table[targetIndex].estado = 1; // Cambia a OCCUPIED
        } else {
            System.out.println("Error: Tabla Hash llena.");
        }
    }

    public Register<String> search(int key) {
        int homeIndex = hash(key);
        int index = homeIndex;

        do {
            // Si la celda está EMPTY (0), la cadena de colisión se rompió: no existe
            if (table[index].estado == 0) {
                return null;
            }

            // Si está OCCUPIED (1) y coincide la clave, se encontró
            if (table[index].estado == 1 && table[index].register.getKey() == key) {
                return table[index].register;
            }

            // Si está DELETED (-1), ignoramos el registro inerte y continuamos el sondeo lineal
            index = (index + 1) % size;
        } while (index != homeIndex);

        return null;
    }

    public void delete(int key) {
        int homeIndex = hash(key);
        int index = homeIndex;

        do {
            if (table[index].estado == 0) {
                System.out.println("Clave " + key + " no encontrada.");
                return;
            }

            if (table[index].estado == 1 && table[index].register.getKey() == key) {
                table[index].estado = -1; // Marcamos como DELETED lógica
                System.out.println("Clave " + key + " eliminada lógicamente (Estado: -1).");
                return;
            }

            index = (index + 1) % size;
        } while (index != homeIndex);
    }

    public void printTable() {
        System.out.println("D. Real\tEstado Num\tSignificado\tContenido");
        System.out.println("---------------------------------------------------------");
        for (int i = 0; i < size; i++) {
            String estStr = table[i].estado == 0 ? "EMPTY" : (table[i].estado == 1 ? "OCCUPIED" : "DELETED");
            String regStr = table[i].register == null ? "empty" : table[i].register.toString();
            System.out.println(i + "\t" + table[i].estado + "\t\t[" + estStr + "]\t" + regStr);
        }
    }
}