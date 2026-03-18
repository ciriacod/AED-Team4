package Semana_1.ejercicios2;

public class Zona {
  private String mineral;
  private double cantidad;
  private double pureza;

  public Zona(String mineral, double cantidad, double pureza) {
    this.mineral = mineral;
    this.cantidad = cantidad;
    this.pureza = pureza;
    }

  public double calcularValor() {                                                        //Método que calcula el valor del mineral mediante su cantidad y pureza
    return this.cantidad * this.pureza;
  }

  public String getMineral() {                                                           //Método que retorna el tipo del mineral
    return mineral;                                                                      
  }

  @Override
  public String toString() {                                                             //Sobrecarga del método toString()para la muestra del mensaje de la clase
    return "[" + mineral + ", cantidad: " + cantidad + ", pureza: " + pureza + "]";
  }
}
