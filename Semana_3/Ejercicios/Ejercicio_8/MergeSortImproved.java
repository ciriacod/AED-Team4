
package Ejercicio_8;

import java.util.Arrays;

public class MergeSortImproved {

    public static void mergeSortImproved(int[] arr) {                           //Se crea un nuevo metodo de clase
        int[] temp = new int[arr.length];                                       //Se define un arreglo temporal
        mergeSort(arr, temp, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right) { // Misma funcion que en el ejercicio anterior
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, temp, left, mid);
            mergeSort(arr, temp, mid + 1, right);
            merge(arr, temp, left, mid, right);
        }
    }

    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;

        while (i <= mid && j <= right) {                                        //Se mezcla las dos mitades en el arreglo temporal
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        while (i <= mid) {                                                      //Se Copia los elementos restantes de la izquierda
            temp[k++] = arr[i++];
        }
        while (j <= right) {                                                    //Se copia los elementos restantes de la derecha
            temp[k++] = arr[j++];
        }
        for (i = left; i <= right; i++) {                                       //Se copia manualmente de vuelta al arreglo original
            arr[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        mergeSortImproved(arr);
        System.out.println(Arrays.toString(arr));
    }
}
