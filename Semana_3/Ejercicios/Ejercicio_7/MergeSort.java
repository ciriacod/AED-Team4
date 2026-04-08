
package Ejercicio_7;

import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {              //Metodo de clase que implementa la recursividad
        if (left < right) {                                                     //Validacion del mayor valor entre left y right
            int mid = left + (right - left) / 2;                                //Se le asigna el valor medio del indice del arreglo

            mergeSort(arr, left, mid);                                          //Se llama a si mismo con el valor de left y el de mid
            mergeSort(arr, mid + 1, right);                                     //Se llama a si mismo ahora con el valor de mid + 1 y el de right

            merge(arr, left, mid, right);                                       //Llama a otro metodo de clase llamado merge con las 3 partes del arreglo
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {        //Metodo de clase que realiza el ordenamiento del arreglo
        int n1 = mid - left + 1;                                                //Se almacenan posiciones dependiendo de la vuelta de la recursividad
        int n2 = right - mid;

        int[] L = new int[n1];                                                  //Se crean nuevos arreglos y se coloca la posicion como el tamaño del
        int[] R = new int[n2];                                                  //nuevo arreglo

        for (int i = 0; i < n1; i++) {                                          //Se Copia manualmente los elementos a L
            L[i] = arr[left + i];
        }
        
        for (int j = 0; j < n2; j++) {                                          //Se copia manualmente los elementos a R
            R[j] = arr[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {                                              //Se mezcla L y R y se devuelve al arreglo original
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {                                                        //Se copia los elementos restantes de L
            arr[k++] = L[i++];
        }

        while (j < n2) {                                                        //Se copia los elementos restantes de R
            arr[k++] = R[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Arreglo original: " + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Arreglo ordenado: " + Arrays.toString(arr));
    }
}

