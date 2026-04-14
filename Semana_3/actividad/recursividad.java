package complejidadnoiterativa; 

public class Potencial {
    public static double potencial(double x, int y) {    
        if(y==0) {                  // caso base =O(1)
            return 1.0;                             
        }
        if(y%2==1) {                                
            return x * potencial(x, y-1);   // caso recursivo =O(y-1)
        }else {
            double t = potencial(x, y/2);   // caso recursivo = O(y/2)
            return t * t;                   // O(1)
        }   
    }
    public static void main(String[] args){
        System.out.println(potencial(4.0,2)); // O(log n)
    }
}

/*
T(n) = O(log n)
T(n) = O(log n) + O(1) ≈ O(log n)

La complejidad es logarítmica (O(log n)), porque en cada paso
el exponente se reduce a la mitad, haciendo que el tiempo de ejecución
crezca proporcionalmente al logaritmo del valor de y.
*/
