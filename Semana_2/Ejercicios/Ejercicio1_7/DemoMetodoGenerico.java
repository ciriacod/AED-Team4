


public class DemoMetodoGenerico {
    
    static <T extends Comparable<T>> boolean igualArrays(T[] x, T[] y) {            //Método genérico con restricción: T debe ser Comparable
        if (x.length != y.length)                                                   //Valida si las longitudes no son iguales
            return false;

        for (int i = 0; i < x.length; i++) {                                        // Compara elemento por elemento
            if (!x[i].equals(y[i]))
                return false;
        }
        return true;                                                                // Contenido equivalente
    }
    
    // --- Caso del ejercicio 2 ---
    static <T> void imprimirContenidoBolsa(Bolsa<T> bolsa) {
        System.out.println("--- Contenido de la Bolsa ---");
        
        for (T elemento : bolsa) {
            System.out.println(elemento.toString());
        }
        System.out.println("-----------------------------");
    }
}
