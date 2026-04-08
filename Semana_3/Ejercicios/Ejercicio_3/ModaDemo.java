
package Ejercicio_3;

import java.util.HashMap;                                                       //Librerias implementadas para la elaboracion de la actividad
import java.util.Map;

public class ModaDemo {

    public static int moda(int[] v) {                                           //Metodo de clase que busca la moda de un arreglo
        Map<Integer, Integer> frecuencia = new HashMap<>();                     //Aplicacion de la interfaz Map
        int maxFrecuencia = 0;
        int moda = v[0];

        for (int num : v) {
            int f = frecuencia.getOrDefault(num, 0) + 1;                        //Se le asigna un dato de Map + 1 a una variable
            frecuencia.put(num, f);                                             //Se ingresa el numero del arreglo mas la variable f

            if (f > maxFrecuencia) {                                            //Se valida si f es mayor a la mayor frecuencia actual
                maxFrecuencia = f;                                              //Se le asigna el valor de f como la maxima frecuencia
                moda = num;                                                     //El num acompañado de f se le asigna a la variable moda
            }
        }
        return moda;
    }

    public static void main(String[] args) {
        int[] datos = {1, 3, 2, 3, 4, 3, 2, 1, 3};
        int resultado = moda(datos);
        System.out.println("La moda es: " + resultado);
    }
}