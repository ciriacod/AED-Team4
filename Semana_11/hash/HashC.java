package Semana_11.hash;

public class HashC<E> {

    // Clase interna que ahora conoce el tipo genérico E del exterior
    private static class Element<T> {
        Register<T> register;
        int estado; // 0 = EMPTY, 1 = OCCUPIED, -1 = DELETED

        public Element() {
            this.register = null;
            this.estado = 0;
        }
    }

    private Element<E>[] table; // Arreglo estático parametrizado con <E>
    private int size;
    private int n;
    private boolean autoRehash;
    private int tipoSondeo; // 0 = Lineal, 1 = Cuadrático

    // Constructor estándar
    public HashC(int size) {
        this(size, false, 0);
    }

    // Constructor Maestro Genérico
    @SuppressWarnings("unchecked")
    public HashC(int size, boolean autoRehash, int tipoSondeo) {
        this.size = size;
        this.n = 0;
        this.autoRehash = autoRehash;
        this.tipoSondeo = tipoSondeo;
        
        // El truco definitivo de Java: Creamos el arreglo crudo y lo casteamos a Element<E>[]
        // La anotación @SuppressWarnings("unchecked") le dice al compilador que nosotros sabemos lo que hacemos
        this.table = (Element<E>[]) new Element[size];
        
        for (int i = 0; i < size; i++) {
            table[i] = new Element<E>();
        }
    }

    private int hash(int key, int currentSize) {
        int index = key % currentSize;
        return index < 0 ? index + currentSize : index;
    }

    // El valor que entra ahora es de tipo genérico E
    public void insert(int key, E value) {
        if (autoRehash) {
            double alphaFuturo = (double) (n + 1) / size;
            if (alphaFuturo > 0.75) {
                System.out.println("\n[ALERTA] Al insertar " + key + ", alpha superaría 0.75. Rehash...");
                rehashing();
            }
        }

        Register<E> reg = new Register<>(key, value);
        int homeIndex = hash(key, size);
        int index = homeIndex;
        int firstDeletedIndex = -1;
        int i = 0;

        do {
            if (table[index].estado == 0) {
                break;
            }

            if (table[index].estado == -1) {
                if (firstDeletedIndex == -1) {
                    firstDeletedIndex = index;
                }
            } else if (table[index].estado == 1 && table[index].register.getKey() == key) {
                table[index].register = reg; // Actualiza duplicado
                return;
            }

            i++;
            if (tipoSondeo == 1) {
                index = (homeIndex + (i * i)) % size;
            } else {
                index = (homeIndex + i) % size;
            }

            if (i >= size) {
                System.out.println("Error: Bucle infinito por Sondeo Cuadrático.");
                return;
            }
        } while (index != homeIndex);

        int targetIndex = (firstDeletedIndex != -1) ? firstDeletedIndex : index;

        if (table[targetIndex].estado == 0 || table[targetIndex].estado == -1) {
            table[targetIndex].register = reg;
            table[targetIndex].estado = 1;
            n++;
            System.out.println("Clave " + key + " -> Índice " + targetIndex + " (Saltos: " + i + ")");
        } else {
            System.out.println("Error: Tabla Hash llena.");
        }
    }

    // Devuelve el Register<E> genérico completo
    public Register<E> search(int key) {
        int homeIndex = hash(key, size);
        int index = homeIndex;
        int i = 0;

        do {
            if (table[index].estado == 0) {
                return null;
            }

            if (table[index].estado == 1 && table[index].register.getKey() == key) {
                return table[index].register;
            }

            i++;
            if (tipoSondeo == 1) {
                index = (homeIndex + (i * i)) % size;
            } else {
                index = (homeIndex + i) % size;
            }
        } while (index != homeIndex && i < size);

        return null;
    }

    public void delete(int key) {
        int homeIndex = hash(key, size);
        int index = homeIndex;
        int i = 0;

        do {
            if (table[index].estado == 0) {
                System.out.println("Clave " + key + " no encontrada.");
                return;
            }

            if (table[index].estado == 1 && table[index].register.getKey() == key) {
                table[index].estado = -1;
                table[index].register = null;
                n--;
                System.out.println("Clave " + key + " eliminada lógicamente.");
                return;
            }

            i++;
            if (tipoSondeo == 1) {
                index = (homeIndex + (i * i)) % size;
            } else {
                index = (homeIndex + i) % size;
            }
        } while (index != homeIndex && i < size);
    }

    @SuppressWarnings("unchecked")
    private void rehashing() {
        int oldSize = size;
        Element<E>[] oldTable = table;

        this.size = 17;
        this.n = 0;
        this.table = (Element<E>[]) new Element[size]; // Nuevo casteo para la tabla expandida
        for (int i = 0; i < size; i++) {
            table[i] = new Element<E>();
        }

        System.out.println("--- Migrando datos de tabla tamaño " + oldSize + " a nueva tabla tamaño " + size + " ---");

        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i].estado == 1) {
                insert(oldTable[i].register.getKey(), oldTable[i].register.getValue());
            }
        }
    }

    public void printTable() {
        System.out.println("Índice\tEstado\tContenido");
        System.out.println("------------------------------------");
        for (int i = 0; i < size; i++) {
            String estStr = table[i].estado == 0 ? "[VACÍO]" : (table[i].estado == -1 ? "[DELETED]" : "[OCCUPIED]");
            String regStr = table[i].register == null ? "empty" : table[i].register.toString();
            System.out.println(i + "\t" + estStr + "\t" + regStr);
        }
    }
}