package Ejercicio2;

public class BuscarMayor {
    public static int encontrarKEsimo(int[] arr, int k) {                       //Metodo general en donde llama a los otros metodos recursivos
        return quickSelect(arr, 0, arr.length - 1, k - 1);                      //Se llama al metodo recursivo quickSelect 
    }

    private static int quickSelect(int[] arr, int izq, int der, int k) {        //Metodo que tiene como parametros el arreglo y sus respectivos elementos
        if (izq <= der) {
            int indicePivote = particion(arr, izq, der);                        //Se asigna el metodo de busqueda particion a una variable "pivote"

            if (indicePivote == k) {                                            //Verifica si el pivote es exactamente la posición k que se busca
                return arr[indicePivote];                                       //Retorna el arreglo con la posicion pivote
            }
            
            if (indicePivote > k) {                                             //Verifica si el indice por particion se encuentra a la izquierda
                return quickSelect(arr, izq, indicePivote - 1, k);              // del k-esimo
            }
            
            return quickSelect(arr, indicePivote + 1, der, k);                  //Retorna si es el elemento se encuentra en la derecha del indice por particion
        }
        return -1;
    }

    private static int particion(int[] arr, int izq, int der) {                 //Metodo que ordena el arreglo para buscar el k-esimo mas grande
        int pivote = arr[der];                                                  //Se elege el último elemento como el pivote
        int i = izq;
        
        for (int j = izq; j < der; j++) {                                       //Se mueve los más grandes a la izquierda del arreglo
            if (arr[j] >= pivote) {
                intercambiar(arr, i, j);                                        //Se llama al metodo para intercambiar la poscion izquierda mas cercana
                i++;
            }
        }
        intercambiar(arr, i, der);                                              //Se llama al metodo de intercambio para actualizar el nuevo valor
        return i;                                                               // del derecho extremo
    }

    private static void intercambiar(int[] arr, int i, int j) {                 //Metodo que intercambia los elementos de una posicion determinada
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] arr1 = {42, 9, 7, 20, 34, 1};
        int k1 = 2;
        
        System.out.println("Elemento mayor por orden del: " + k1);
        System.out.println("Caso 1: " + encontrarKEsimo(arr1, k1));

        int[] arr2 = {4, 2, 7, 10, 4, 1, 6};
        int k2 = 5;

        System.out.println("Elemento mayor por orden del: " + k2);
        System.out.println("Caso 2: " + encontrarKEsimo(arr2, k2));
    }
}
