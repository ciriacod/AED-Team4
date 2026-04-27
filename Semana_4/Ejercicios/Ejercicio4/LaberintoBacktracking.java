package Ejercicio4;

public class LaberintoBacktracking {
    private static final int N = 4;                                             //Se inicializa el tamaño del arreglo

    public static void resolver(int[][] laberinto) {                            //Metodo general el cual busca el mejor camino posible
        int[][] solucion = new int[N][N];                                       //Se inicailiza la solucion como un arreglo de tamaño N

        if (explorar(laberinto, 0, 0, solucion)) {                              //Verfica si el laberinto puede ser resuelto mediante la
            imprimirSolucion(solucion);                                         // funcion recursiva explorar
        } else {
            System.out.println("No existe un camino posible.");
        }
    }

    private static boolean explorar(int[][] laberinto, int x, int y, int[][] solucion) {        //Metodo recursivo que explora el laberinto
        if (x == N - 1 && y == N - 1) {                                         //CASO BASE: Si se llega a la meta (esquina inferior derecha)
            solucion[x][y] = 1;
            return true;
        }

        if (esSeguro(laberinto, x, y)) {                                        //Verifica si la posicion actual es valida
            solucion[x][y] = 1;                                                 //Marca esta celda como parte del camino

            if (explorar(laberinto, x + 1, y, solucion)) {                      //Verifica si hay camino hacia abajo
                return true;
            }

            if (explorar(laberinto, x, y + 1, solucion)) {                      //Verifica si hay camino hacia la derecha
                return true;
            }

            solucion[x][y] = 0;                                                 //Si ninguna dirección sirve se desmarca la celda
            return false;
        }

        return false;
    }

    private static boolean esSeguro(int[][] laberinto, int x, int y) {          //Funcion que verifica estar dentro de los límites 
        return (x >= 0 && x < N && y >= 0 && y < N && laberinto[x][y] == 0);    // y no ser una pared (0)
    }

    private static void imprimirSolucion(int[][] solucion) {                    //Metodo que imprime el laberinto
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + solucion[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] laberinto = {
            { 0, 1, 0, 0 },
            { 0, 1, 0, 1 },
            { 0, 0, 0, 1 },
            { 0, 1, 0, 0 }
        };

        resolver(laberinto);
    }
}
