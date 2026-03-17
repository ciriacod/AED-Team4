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

  public double calcularValor() {
    return this.cantidad * this.pureza;
  }

  public String getMineral() {
    return mineral;
  }

  @Override
  public String toString() {
    return "[" + mineral + ", cantidad: " + cantidad + ", pureza: " + pureza + "]";
  }
}
