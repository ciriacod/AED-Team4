package Semana_2.Ejercicios.Ejercicio1_7;

public class Caja<T extends Comparable<T>> {
    private String color;
    private T contenido;

    public Caja(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public T getContenido() {
        return contenido;
    }

    public void setContenido(T contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Caja [color=" + color + ", contenido=" + contenido + "]";
    }
}
