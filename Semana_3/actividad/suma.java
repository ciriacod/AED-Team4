package complejidadalgoritmica;

public class Suma {
    public static int suma(int [] arreglo) { // O(1)
        int resultado = 0;  // O(1)
        if(arreglo.length <= 0) {   // O(1)
            return 0;   // O(1)
        }
        resultado = 0;  // O(1)
        
        for(int i = 0; i < arreglo.length; i++) {   // O(n)
            if(arreglo[i] >= 0) {   // O(1)
                resultado += arreglo[i];    // O(1)
            }else {
                resultado = resultado + arreglo[1]; // O(1)
            }
        }
        return resultado;   // O(1)
    }

    public static void main(String[] args) {
        int [] arreglo = {2,3,4};
        System.out.print(suma(arreglo)); // O(n)
    }
}

/*
T(n) = O(n) * O(1) = O(n)

Más las operaciones constantes:
T(n) = O(n) + O(1) ≈ O(n)

La complejidad es lineal (O(n)), porque el tiempo de ejecución
crece proporcionalmente al tamaño del arreglo.
*/

