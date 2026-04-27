package Semana_3.actividad;

public class Ordenar {
    
    // Complejidad: O(n^2) en peor y promedio caso
    public static int[] ordenar(int [] vector) {
        
        int aux=0;
        if(vector.length <= 0) { // O(1)
            return vector;       // O(1)
        }
        for(int i = 0; i< vector.length; i++) { // O(n)
            for(int j = 0;j < vector.length -1 -i;j++) { // O(n)
                if(vector[j] > vector [j+1]) { // O(1)
                    aux=vector[j];            // O(1)
                    vector[j]=vector[j+1];    // O(1)
                    vector[j+1]=aux;          // O(1)
                }
            }
        }
        return vector; // O(1)
    }
    
    public static void main(String[] args) {
        int [] ra= {2,1,3,4};
        int [] resultado= ordenar(ra);
        for (int num: resultado) { // O(n)
            System.out.print(num); // O(1) por cada elemento → O(n)
        }
    }
}

/*
Se multiplica porque son bucles anidados:
T(n) = O(n) * O(n) = O(n^2)

Más las operaciones constantes:
T(n) = O(n^2) + O(1) ≈ O(n^2)

La complejidad es cuadrática (O(n^2)) en el peor y promedio caso.
Esto significa que el tiempo de ejecución crece proporcionalmente
al cuadrado del tamaño del vector.
*/

