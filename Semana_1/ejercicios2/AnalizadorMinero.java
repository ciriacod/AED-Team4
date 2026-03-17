package Semana_1.ejercicios2;

import java.util.*;
import java.io.*;

public class AnalizadorMinero {
  private Zona[][] terreno;
  private int filas;
  private int columnas;
  
  public void cargarDesdeArchivo(String ruta) throws FileNotFoundException {
    Scanner sc = new Scanner(new File(ruta));
    if (sc.hasNextInt()) {
      this.filas = sc.nextInt();
      this.columnas = sc.nextInt();
      this.terreno = new Zona[filas][columnas];
      
      for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
          String min = sc.next();
          double cant = sc.nextDouble();
          double pur = sc.nextDouble();
          terreno[i][j] = new Zona(min, cant, pur);
        }
      }
    }
    sc.close();
  }
  
  public void ejecutarAnalisis(int k) {
    double valorMaximo = -1;
    int mejorF = 0, mejorC = 0;
    
    for (int i = 0; i <= filas - k; i++) {
      for (int j = 0; j <= columnas - k; j++) {
        double sumaActual = 0;
        for (int r = i; r < i + k; r++) {
          for (int c = j; c < j + k; c++) {
            sumaActual += terreno[r][c].calcularValor();
          }
        }
        if (sumaActual > valorMaximo) {
          valorMaximo = sumaActual;
          mejorF = i;
          mejorC = j;
        }
      }
    }
    imprimirReporte(mejorF, mejorC, k, valorMaximo);
  }
  
  private void imprimirReporte(int f, int c, int k, double total) {
    System.out.println("\nRegión más valiosa encontrada:");
    System.out.println("Posición inicial: (" + f + "," + c + ")");
    System.out.println("Tamaño de la región: " + k + "x" + k);
    System.out.println("Zonas analizadas:");
    
    Map conteo = new HashMap<>();
    for (int i = f; i < f + k; i++) {
      for (int j = c; j < c + k; j++) {
        System.out.println(terreno[i][j]);
        String min = terreno[i][j].getMineral();
        conteo.put(min, conteo.getOrDefault(min, 0) + 1);
    }
  }
  
  String predominante = "";
  int maxFreq = -1;
  for (var entry : conteo.entrySet()) {
    if (entry.getValue() > maxFreq) {
      maxFreq = entry.getValue();
      predominante = entry.getKey();
    }
  }
  
  System.out.println("Valor total estimado: " + total);
  System.out.println("Mineral predominante en la región: " + predominante);
}
}
