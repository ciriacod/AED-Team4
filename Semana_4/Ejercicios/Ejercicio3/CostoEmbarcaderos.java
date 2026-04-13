package Ejercicio3;

public class CostoEmbarcaderos {
    private static int[][] memo;                                                //Matriz de almacen

    public static int costoMinimo(int i, int j, int[][] T) {                    //Funcion recursiva que busca el menor valor de costo
        if (i == j) return 0;                                                   //Caso base
        
        if (memo[i][j] != -1) return memo[i][j];                                //Si el valor ya fue calculado, se devuelve a la memoria

        int min = T[i][j];                                                      //Se inicializa el costo actual como minimo

        for (int k = i + 1; k < j; k++) {                                       //Se calcula el costo menor a travez de escalas intermedias 'k'
            int costoActual = costoMinimo(i, k, T) + costoMinimo(k, j, T);
            if (costoActual < min) {                                            //Se verifica si el costoActual es menor con el costo inicial min
                min = costoActual;
            }
        }
        
        memo[i][j] = min;                                                       //Se guarda el resultado en la memoria
        return min;                                                             
    }
    
    public static void main(String[] args) {
        int[][] T = {
            {0, 2, 5, 10},
            {0, 0, 2, 7},
            {0, 0, 0, 4},
            {0, 0, 0, 0}
        };

        int n = T.length;
        memo = new int[n][n];
        
        for (int i = 0; i < n; i++) {                                           //Se inicializa la memoria con -1 indicando que no se ha calculado
            for (int j = 0; j < n; j++) {                                       // ningun costo
                memo[i][j] = -1;
            }
        }

        int origen = 0;                                                         
        int destino = 3;
        System.out.println("Costo minimo: " + costoMinimo(origen, destino, T));
    }
}