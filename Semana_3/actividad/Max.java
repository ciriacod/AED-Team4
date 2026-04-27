package Semana_3.actividad;

public class Max 
{
    public static void numeromaximo(int x, int y) 
    {
        if(x==y) { //O(1)
            System.out.println("x:"+x +"y:"+ y);//O(1)
        }else if(x >y){   //O(1)
            System.out.println("x:"+x); //O(1)
        }else {
            System.out.println("y:"+y); //O(1)
        }
    }

    public static void main(String[] args) {
        numeromaximo(3,7); //O(1)
    }
}

/*
1 comparación + 1 impresión = O(1) + O(1) = O(2)

T(n) = O(2) ≈ O(1)

La complejidad es constante porque el número de operaciones
no depende del tamaño de la entrada, siempre se mantiene fijo.
*/
