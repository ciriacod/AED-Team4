package Semana_11.hash;

public class HashC<E> {

    // Clase interna que conoce el tipo genérico E del exterior
    private static class Element<T> {
        Register<T> register;
        int estado; // 0 = EMPTY, 1 = OCCUPIED, -1 = DELETED

        public Element() {
            this.register = null;
            this.estado = 0;
        }
    }

    private Element<E>[] table; // Arreglo estático parametrizado con <E>
    private int size;   // tamaño
    private int n;      // elementos en la tabla
    private boolean autoRehash;
    private int tipoSondeo; // 0 = Lineal, 1 = Cuadrático

    // Constructor estándar
    public HashC(int size) {
        this(size, false, 0);
    }

    // Constructor Maestro Genérico con Inicialización Prima Automática
    @SuppressWarnings("unchecked")
    public HashC(int size, boolean autoRehash, int tipoSondeo) {
        // Forzamos a que el tamaño inicial de la tabla sea primo desde su nacimiento
        this.size = isPrime(size) ? size : nextPrime(size);
        this.n = 0;
        this.autoRehash = autoRehash;
        this.tipoSondeo = tipoSondeo;
        
        // Casteo controlado de tipos genéricos
        this.table = (Element<E>[]) new Element[this.size];
        
        for (int i = 0; i < this.size; i++) {
            table[i] = new Element<E>();
        }
    }

    // Función hash base
    private int hash(int key, int currentSize) {
        int index = key % currentSize;
        return index < 0 ? index + currentSize : index;
    }

    // Algoritmo auxiliar para encontrar el siguiente número primo
    private int nextPrime(int min) {
        int p = min % 2 == 0 ? min + 1 : min; // Empezamos desde un impar
        while (true) {
            if (isPrime(p)) return p;
            p += 2;
        }
    }

    // Verifica si un número es primo
    private boolean isPrime(int num) {
        // Los números menores o iguales a 1 no son primos
        if (num <= 1) {
            return false;
        }
        
        // Probamos si algún número desde 2 hasta la raíz cuadrada de 'num' lo divide
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                return false; // Si el residuo es 0, no es primo
            }
        }
        
        return true; // Si salimos del bucle sin divisiones exactas, es primo
    }

//------------------------------Insert---------------------------------

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
                break; // Espacio completamente vacío encontrado
            }

            if (table[index].estado == -1) {
                if (firstDeletedIndex == -1) {
                    firstDeletedIndex = index; // Guardamos el primer hueco borrado para reciclar
                }
            } else if (table[index].estado == 1 && table[index].register.getKey() == key) {
                table[index].register = reg; // Actualiza clave duplicada
                return;
            }

            i++;
            if (tipoSondeo == 1) {
                index = (homeIndex + (i * i)) % size; // Sondeo Cuadrático
            } else {
                index = (homeIndex + i) % size; // Sondeo Lineal
            }

            if (i >= size) {
                System.out.println("Error: Bucle infinito en la exploración de colisiones.");
                return;
            }
        } while (index != homeIndex);

        // Si encontramos una casilla borrada lógicamente antes, reciclamos ese índice
        int targetIndex = (firstDeletedIndex != -1) ? firstDeletedIndex : index;

        if (table[targetIndex].estado == 0 || table[targetIndex].estado == -1) {
            table[targetIndex].register = reg;
            table[targetIndex].estado = 1;
            n++;
            System.out.println("Clave " + key + " -> Índice " + targetIndex + " (Saltos/Colisiones solucionadas: " + i + ")");
        } else {
            System.out.println("Error: Tabla Hash llena.");
        }
    }

//------------------------------Search---------------------------------

    public Register<E> search(int key) {
        int homeIndex = hash(key, size);
        int index = homeIndex;
        int i = 0;

        do {
            if (table[index].estado == 0) {
                return null; // Si es vacío, la clave definitivamente no existe
            }

            if (table[index].estado == 1 && table[index].register.getKey() == key) {
                return table[index].register; // Encontrado
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

//------------------------------Delete---------------------------------

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
                table[index].estado = -1; // Marcado lógico como DELETED
                table[index].register = null; // Liberamos memoria del objeto anterior
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

//------------------------------Rehashing Dinámico Primo----------------

    @SuppressWarnings("unchecked")
    private void rehashing() {
        int oldSize = size;
        Element<E>[] oldTable = table;

        // Duplica el tamaño actual y busca dinámicamente el siguiente primo
        this.size = nextPrime(oldSize * 2);
        this.n = 0; // Se recalcula desde cero durante la reinserción en cascada
        
        System.out.println("--- Migrando datos de tabla tamaño " + oldSize + " a nueva tabla prima dinámica tamaño " + size + " ---");

        this.table = (Element<E>[]) new Element[size]; 
        for (int i = 0; i < size; i++) {
            table[i] = new Element<E>();
        }

        // Recuperamos los elementos activos de la estructura anterior
        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i].estado == 1) {
                insert(oldTable[i].register.getKey(), oldTable[i].register.getValue());
            }
        }
    }

//------------------------------Print---------------------------------

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