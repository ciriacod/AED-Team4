package complejidadalgoritmica;

public class Ordenar {
    
    // Complejidad: O(n^2) en peor y promedio caso
    public static int[] ordenar(int [] vector) {
        
        int aux=0;
        if(vector.length <= 0) { // O(1)
            return vector;
        }
        for(int i = 0; i< vector.length; i++) { // O(n)
            for(int j = 0;j < vector.length -1 -i;j++) { // O(n)
                if(vector[j] > vector [j+1]) { // O(1)
                    aux=vector[j];
                    vector[j]=vector[j+1];
                    vector[j+1]=aux;
                }
            }
        }
        return vector; // O(1)
    }
    
    public static void main(String[] args) {
        int [] ra= {2,1,3,4};
        int [] resultado= ordenar(ra);
        for (int num: resultado) {
            System.out.print(num);
        }
    }
}

