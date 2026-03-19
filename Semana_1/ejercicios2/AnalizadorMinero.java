package Semana_1.ejercicios2;

import java.util.*;                                                                    //Se importan librerias escenciales
import java.io.*;                                                                      

public class AnalizadorMinero {
  private Zona[][] terreno;                                                            //Se implementa Zona como un tipo de atributo
  private int filas;
  private int columnas;
  
  public void cargarDesdeArchivo(String ruta) throws FileNotFoundException {           //Método que permite la lectura de un archivo de texto
    Scanner sc = new Scanner(new File(ruta));
    if (sc.hasNextInt()) {                                                             //Lectura de la primera linea del contenido del archivo
      this.filas = sc.nextInt();                                                       //almacenando en los atributos de la clase AnalizadorMinero
      this.columnas = sc.nextInt();
      this.terreno = new Zona[filas][columnas];
      
      for (int i = 0; i < filas; i++) {                                                //Lectura del contenido del archivo almacenandolo
        for (int j = 0; j < columnas; j++) {                                           //en un arreglo formado con los atributos de la clase
          String min = sc.next();
          double cant = sc.nextDouble();
          double pur = sc.nextDouble();
          terreno[i][j] = new Zona(min, cant, pur);
        }
      }
    }
    sc.close();                                                                        //Cierre de la clase Scanner
  }
  
  public void ejecutarAnalisis(int k) {                                                //Método que permite el analizis de una zona segun un valor ingresado
    if (k > filas || k > columnas || k <= 0) {                                         //Validación del número ingresado para que este no sea
        System.out.println("Error: Tamaño no permitido");                              //mayor al perimetro total del área minera
        return;
    }
    double valorMaximo = -1;
    int mejorF = 0, mejorC = 0;
    
    for (int i = 0; i <= filas - k; i++) {                                             //Bucle que analiza todo un terreno segun el valor ingresado
      for (int j = 0; j <= columnas - k; j++) {
        double sumaActual = 0;                                                         //Se define una variable para determinar la suma total del terreno
        for (int r = i; r < i + k; r++) {                                              //Bucle que permite moverse dentro del arreglo
          for (int c = j; c < j + k; c++) {
            sumaActual += terreno[r][c].calcularValor();                               //Se almacena cada calcula de la zona del terreno en la variable definida
          }
        }
        if (sumaActual > valorMaximo) {                                                //Condicional que determina si la suma anterior es mayor que la suma actual
          valorMaximo = sumaActual;
          mejorF = i;
          mejorC = j;
        }
      }
    }
    imprimirReporte(mejorF, mejorC, k, valorMaximo);                                   //Se invoca un metodo para mostrar los resultados
  }
  
  private void imprimirReporte(int f, int c, int k, double total) {                    //Método que muestra los resultados del análizis
    System.out.println("\nRegión más valiosa encontrada:");
    System.out.println("Posición inicial: (" + f + "," + c + ")");
    System.out.println("Tamaño de la región: " + k + "x" + k);
    System.out.println("Zonas analizadas:");
    
    Map<String, Integer> conteo = new HashMap<>();                                      //Se almacena la informacion dentro de un Map para mostrarlo por consola
    
    for (int i = f; i < f + k; i++) {                                                   //Bucle que permite mostrar todos los datos del terreno seleccionado
        for (int j = c; j < c + k; j++) {
            System.out.println(terreno[i][j]);
            String min = terreno[i][j].getMineral();
            conteo.put(min, conteo.getOrDefault(min, 0) + 1);                           //Función de Map que permite almacenar los datos en un dato estructurado
        }
    }

    String predominante = "";
    int maxFreq = -1;

    for (Map.Entry<String, Integer> entry : conteo.entrySet()) {                        //Bucle que permite escojer dentro del Map el valor predominante
        if (entry.getValue() > maxFreq) {
            maxFreq = entry.getValue();
            predominante = entry.getKey();
        }
    }
  
    System.out.println("Valor total estimado: " + total);
    System.out.println("Mineral predominante en la región: " + predominante);
  }
}
