package Semana_11.hash;

public class HashC {

    private static class Element {
        Register<String> register;
        int estado; // 0 = EMPTY, 1 = OCCUPIED, -1 = DELETED

        public Element() {
            this.register = null;
            this.estado = 0;
        }
    }

    private Element[] table;
    private int size;
    private int n;
    private boolean autoRehash;
    private int tipoSondeo; // 0 = Lineal, 1 = Cuadrático

    // Constructor estándar por defecto (Lineal, sin rehash)
    public HashC(int size) {
        this(size, false, 0);
    }

    // Constructor estándar por defecto (Lineal, rehash)
    public HashC(int size, boolean autoRehash) {
        this(size, autoRehash, 0);
    }

    // Constructor flexible para los Ejercicios
    public HashC(int size, boolean autoRehash, int tipoSondeo) {
        this.size = size;
        this.n = 0;
        this.autoRehash = autoRehash;
        this.tipoSondeo = tipoSondeo; // 0: Lineal, 1: Cuadrático
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }

    private int hash(int key, int currentSize) {
        int index = key % currentSize;
        return index < 0 ? index + currentSize : index;
    }

    public void insert(int key, String value) {
        if (autoRehash) {
            double alphaFuturo = (double) (n + 1) / size;
            if (alphaFuturo > 0.75) {
                System.out.println("\n[ALERTA] Al insertar " + key + ", alpha superaría 0.75. Rehash...");
                rehashing();
            }
        }

        Register<String> reg = new Register<>(key, value);
        int homeIndex = hash(key, size);
        int index = homeIndex;
        int firstDeletedIndex = -1;
        int i = 0; // Contador de saltos/intentos

        do {
            if (table[index].estado == 0) {
                break;
            }

            if (table[index].estado == -1) {
                if (firstDeletedIndex == -1) {
                    firstDeletedIndex = index;
                }
            } else if (table[index].estado == 1 && table[index].register.getKey() == key) {
                table[index].register = reg;
                return;
            }

            i++;
            // APLICACIÓN DE LA FÓRMULA DE SONDEO
            if (tipoSondeo == 1) {
                index = (homeIndex + (i * i)) % size; // Cuadrático: h(x) + i²
            } else {
                index = (homeIndex + i) % size;         // Lineal: h(x) + i
            }

            if (i >= size) { // Control preventivo de bucle infinito
                System.out.println("Error: No se pudo insertar " + key + " debido a un bucle infinito por Sondeo Cuadrático.");
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

    public Register<String> search(int key) {
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

    private void rehashing() {
        int oldSize = size;
        Element[] oldTable = table;

        this.size = 17;
        this.n = 0;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element();
        }

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