package Ejercicio_1;

public class SubconjuntoSuma {

    // Método principal de backtracking
    public static boolean puedeSumar(int[] arr, int index, int suma, int objetivo) {

        // Caso base: si se recorrió todo el arreglo
        if (index == arr.length) {
            return suma == objetivo;
        }

        // Si el número es múltiplo de 3 → se incluye obligatoriamente
        if (arr[index] % 3 == 0) {
            return puedeSumar(arr, index + 1, suma + arr[index], objetivo);
        }

        // Si el número es par y el siguiente también es par → no se puede elegir
        if (arr[index] % 2 == 0 && index + 1 < arr.length && arr[index + 1] % 2 == 0) {
            return puedeSumar(arr, index + 1, suma, objetivo);
        }

        // Backtracking: probar incluir o no incluir el elemento
        return puedeSumar(arr, index + 1, suma + arr[index], objetivo) ||
               puedeSumar(arr, index + 1, suma, objetivo);
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 8, 6};
        int objetivo = 11;

        boolean resultado = puedeSumar(arr, 0, 0, objetivo);
        System.out.println("¿Es posible sumar " + objetivo + " con las restricciones? " + resultado);

        int[] arr2 = {3,4,5,3};
        int objetivo2 = 15;
        boolean resultado2 = puedeSumar(arr2, 0, 0, objetivo2);
        System.out.println("¿Es posible sumar " + objetivo2 + " con las restricciones? " + resultado2);
    }
}