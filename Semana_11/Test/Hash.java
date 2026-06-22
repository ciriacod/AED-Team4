package Semana_11.Test;

import Semana_11.hash.HashC;
import Semana_11.hash.HashO;

public class Hash {

    public static void main(String[] args) {
        // Creamos la tabla hash. Pasamos un tamaño estimado (por ejemplo, 11 o 13)
        // El constructor usará este valor como 'm' para la función hash.
        HashC<String> tableC = new HashC<>(11);

        // 1. Insertar los valores solicitados con diferentes nombres
        System.out.println("--- Insertando elementos en la Tabla Hash ---");
        tableC.insert(34, "Alejandro");
        tableC.insert(3,  "Beatriz");
        tableC.insert(7,  "Carlos");
        tableC.insert(30, "Diana");
        tableC.insert(11, "Eduardo");
        tableC.insert(8,  "Fernanda");
        tableC.insert(7,  "Claudio"); // Clave repetida (actualizará el valor o resolverá según lógica)
        tableC.insert(23, "Gabriela");
        tableC.insert(41, "Hugo");
        tableC.insert(16, "Irene");
        tableC.insert(34, "Alonso");   // Clave repetida

        // 2. Mostrar la tabla hash inicial
        System.out.println("\n=== TABLA HASH ANTES DE LA ELIMINACION ===");
        System.out.println(tableC.toString());

        // 3. Buscar la clave 23 antes o después de los cambios
        System.out.println("--- Buscando la clave 23 ---");
        String personaBuscada = tableC.search(23);
        if (personaBuscada != null) {
            System.out.println("Resultado de búsqueda: Clave 23 pertenece a -> " + personaBuscada);
        } else {
            System.out.println("Resultado de búsqueda: Clave 23 no fue encontrada.");
        }

        // 4. Eliminar la clave 30
        System.out.println("\n--- Eliminando la clave 30 ---");
        tableC.delete(30);

        // 5. Mostrar la tabla hash después de la eliminación
        System.out.println("\n=== TABLA HASH DESPUES DE ELIMINAR LA CLAVE 30 ===");
        System.out.println(tableC.toString());
        
        // Intentar buscar la clave 30 para verificar que ya no existe
        System.out.println("--- Verificacion: Buscando la clave eliminada 30 ---");
        String personaEliminada = tableC.search(30);
        if (personaEliminada != null) {
            System.out.println("Error: Clave 30 aún aparece -> " + personaEliminada);
        } else {
            System.out.println("Correcto: Clave 30 ya no se encuentra en la tabla.");
        }
        
        // Creamos la tabla hash abierta con un tamaño de 11 (m = 11)
        HashO<String> table = new HashO<>(11);

        // 1. Insertar los mismos valores con diferentes nombres
        System.out.println("--- Insertando elementos en la Tabla Hash Abierta ---");
        table.insert(34, "Alejandro");
        table.insert(3,  "Beatriz");
        table.insert(7,  "Carlos");
        table.insert(30, "Diana");
        table.insert(11, "Eduardo");
        table.insert(8,  "Fernanda");
        table.insert(7,  "Claudio"); // Clave repetida (actualizará el valor)
        table.insert(23, "Gabriela");
        table.insert(41, "Hugo");
        table.insert(16, "Irene");
        table.insert(34, "Alonso");   // Clave repetida (actualizará el valor)

        // 2. Mostrar la tabla hash abierta antes de la eliminación
        System.out.println("\n=== TABLA HASH (ABIERTA) ANTES DE LA ELIMINACIÓN ===");
        System.out.println(table.toString());

        // 3. Buscar la clave 23
        System.out.println("--- Buscando la clave 23 ---");
        String personaBuscadaO = table.search(23);
        if (personaBuscadaO != null) {
            System.out.println("Resultado de búsqueda: Clave 23 pertenece a -> " + personaBuscadaO);
        } else {
            System.out.println("Resultado de búsqueda: Clave 23 no fue encontrada.");
        }

        // 4. Eliminar la clave 30
        System.out.println("\n--- Eliminando la clave 30 ---");
        table.delete(30);

        // 5. Mostrar la tabla hash abierta después de la eliminación
        System.out.println("\n=== TABLA HASH (ABIERTA) DESPUÉS DE ELIMINAR LA CLAVE 30 ===");
        System.out.println(table.toString());
        
        // 6. Verificación de la clave eliminada
        System.out.println("--- Verificación: Buscando la clave eliminada 30 ---");
        String personaEliminadaO = table.search(30);
        if (personaEliminadaO != null) {
            System.out.println("Error: Clave 30 aún aparece -> " + personaEliminadaO);
        } else {
            System.out.println("Correcto: Clave 30 ya no se encuentra en la tabla.");
        }
    }
}
