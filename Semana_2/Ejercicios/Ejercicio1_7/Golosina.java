
package com.Ejercicio1_7;

import java.util.Objects;                                                           //Se importa librerias externas para el funcionamiento de ciertos métodos

public class Golosina implements Comparable<Golosina> {
    private String nombre;
    private double peso;

    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPeso() {
        return peso;
    }
    
    @Override
    public int compareTo(Golosina otra) {
        // Compara por peso, por ejemplo
        return Double.compare(this.peso, otra.peso);
    }
    
    // --- Caso del ejercicio n°5 ---
    @Override
    public boolean equals(Object o) {                                           //Sobrescritura del método equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Golosina golosina = (Golosina) o;
        return Double.compare(golosina.peso, peso) == 0 && 
               Objects.equals(nombre, golosina.nombre);
    }
    
    @Override
    public String toString() {
        return "Golosina [nombre=" + nombre + ", peso=" + peso + "]";
    }
}
