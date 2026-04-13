package act6;

public class dpSolution {

    // Funcion con programacion dinamica para calcular el valor maximo
    static int getValue(int[] values, int rodLength) {
        // Arreglo para guardar soluciones parciales
        int[] subSolutions = new int[rodLength + 1];

        // Recorre cada longitud posible
        for (int i = 1; i <= rodLength; i++) {
            int tmpMax = -1;

            // Calcula el mejor corte para longitud i
            for (int j = 0; j < i; j++) {
                tmpMax = Math.max(tmpMax, values[j] + subSolutions[i - j - 1]);
            }

            // Guarda el mejor resultado para longitud i
            subSolutions[i] = tmpMax;
        }

        // Retorna el valor maximo para la longitud completa
        return subSolutions[rodLength];
    }

    public static void main(String[] args) {
        // Valores por longitud de corte
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;

        // Imprime el valor maximo calculado
        System.out.println("El valor maximo: " + getValue(values, rodLength));
    }
}
