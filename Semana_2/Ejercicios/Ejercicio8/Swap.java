package Semana_2.Ejercicios.Ejercicio8;

public class Swap {
    public static <T> void swap(T[] arreglo, int i, int j) {
        if (i < 0 || i >= arreglo.length || j < 0 || j >= arreglo.length) {                 //Método que verifica el ccorrecto valor de los indices
            System.out.println("Error: Indices fuera de los límites del arreglo");
            return;
        }
        T temporal = arreglo[i];                                                            //Se intercambian los valores almacenandolo
        arreglo[i] = arreglo[j];                                                            //en una variable temporal para hacer el cambio
        arreglo[j] = temporal;
    }

    public static void main(String[] args) {
        String[] letras = {"A", "B", "C", "D"};
        System.out.println("String Antes: " + java.util.Arrays.toString(letras));
        swap(letras, 1, 3);                                                                 // Intercambia "B" con "D"
        System.out.println("String Despues: " + java.util.Arrays.toString(letras));

        System.out.println();

        Integer[] numeros = {10, 20, 30, 40};
        System.out.println("Integer Antes: " + java.util.Arrays.toString(numeros));
        swap(numeros, 0, 2);                                                                // Intercambia 10 con 30
        System.out.println("Integer Despues: " + java.util.Arrays.toString(numeros));
        
        System.out.println();

        Objecto[] objetos = {new Objecto("Persona"), new Objecto("Numero"), new Objecto("Animal"), new Objecto("Objeto")};
        System.out.println("Objeto Antes: " + java.util.Arrays.toString(objetos));
        swap(objetos, 0, 3);                                                                // Intercambia Persona con Objeto
        System.out.println("Objeto Despues: " + java.util.Arrays.toString(objetos));
    }
}
