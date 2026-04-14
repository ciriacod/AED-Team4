package Semana_4.Actividades.Actividad_6;

public class naiveSolution {

    // Funcion recursiva que calcula el valor maximo
    static int getValue(int[] values, int length) {
        // Caso base: si la longitud es menor o igual a 0 retorna 0
        if (length <= 0)
            return 0;

        // Variable para guardar el maximo temporal
        int tmpMax = -1;

        // Recorre todas las posibles formas de cortar la barra
        for (int i = 0; i < length; i++) {
            // Calcula el maximo entre el actual y el valor de cortar en i
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }

        // Retorna el valor maximo encontrado
        return tmpMax;
    }

    public static void main(String[] args) {

        // Arreglo de valores por longitud de corte
        int[] values = new int[]{3, 7, 1, 3, 9};

        // Longitud de la barra (igual al tamaño del arreglo)
        int rodLength = values.length;

        // Imprime el valor maximo calculado
        System.out.println("El valor maximo: " + getValue(values, rodLength));
    }
}
