package Semana_4.Actividades.Actividad_4;

public class MergeSort {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) { // Verifica que haya mas de un elemento
            int mid = (left + right) / 2; // Calcula el punto medio

            mergeSort(arr, left, mid); // Ordena la primera mitad
            mergeSort(arr, mid + 1, right); // Ordena la segunda mitad

            merge(arr, left, mid, right); // Combina ambas mitades
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1; // Tamaño subarreglo izquierdo
        int n2 = right - mid;    // Tamaño subarreglo derecho

        int[] L = new int[n1];   // Temporal izquierdo
        int[] R = new int[n2];   // Temporal derecho

        for (int i = 0; i < n1; i++) L[i] = arr[left + i]; // Copia L[]
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j]; // Copia R[]

        int i = 0, j = 0, k = left; // Índices iniciales

        while (i < n1 && j < n2) { // Combina mientras haya elementos
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }

        while (i < n1) arr[k++] = L[i++]; // Copia restantes de L
        while (j < n2) arr[k++] = R[j++]; // Copia restantes de R
    }

    public static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " "); // Imprime cada elemento
        System.out.println(); 
    }

    public static void main(String[] args) {
        int[] arr = {12, 11, 13, 5, 6, 7,9,12}; // Arreglo inicia
        int[] arr2 = {5,7,9,2,6};
        int[] arr3 = {9,8,5,7,3,6,1,21,19,11}; 
        
        System.out.println("Arreglo original:");
        printArray(arr); // Muestra arreglo original
        printArray(arr2); 
        printArray(arr3); 

        mergeSort(arr, 0, arr.length - 1); // Llama a MergeSort
        mergeSort(arr2, 0, arr2.length - 1); 
        mergeSort(arr3, 0, arr3.length - 1); 
        System.out.println("Arreglo ordenado:");
        printArray(arr); // Muestra arreglo ordenado
        printArray(arr2); 
        printArray(arr3); 
    }
}