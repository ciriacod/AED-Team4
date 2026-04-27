
package Ejercicio8;

public class Main {
    public static void main(String[] args) {
        // 1. Crear instancia y agregar al menos 6 canciones
        ColaReproduccion<Cancion> cola = new ColaReproduccion<>();
        
        cola.agregarCancion(new Cancion("Volvi a Nacer", "Carlos Vives Ft. J Alvarez", 201));
        cola.agregarCancion(new Cancion("Fallen Angel", "Poison", 235));
        cola.agregarCancion(new Cancion("Mechita", "Fiesta Criolla", 177));
        cola.agregarCancion(new Cancion("La Puerta del Colegio", "Magneto", 239));
        cola.agregarCancion(new Cancion("Suspiros de Amor", "Rosita de Espinar", 292));
        cola.agregarCancion(new Cancion("El Primer Beso", "Grupo Nectar", 259));

        // 2. Mostrar la cola inicial
        System.out.println("=== Cola de Reproducción Inicial ===");
        cola.mostrarCola();
        System.out.println();

        // 3. Avanzar 3 canciones con reproducirSiguiente(
        System.out.println("--- Avanzando 3 canciones ---");
        for (int i = 0; i < 3; i++) {
            System.out.println("► Reproduciendo ahora: " + cola.reproducirSiguiente());
        }
        System.out.println();

        // 4. Retroceder 1 canción con reproducirAnterior()
        System.out.println("--- Retrocediendo ---");
        System.out.println("Anterior: " + cola.reproducirAnterior());
        System.out.println();

        // 5. Mostrar duración total en formato mm:ss
        int totalSegundos = cola.duracionTotal();
        int minutos = totalSegundos / 60;
        int segundos = totalSegundos % 60;
        System.out.printf("Duración total de la cola: %02d:%02d\n", minutos, segundos);
        System.out.println();

        // 6. Activar mezclar() y mostrar resultado
        System.out.println("=== Mezclando (Shuffle Mode) ===");
        cola.mezclar();
        cola.mostrarCola();
    }
}
