
package Ejercicio_4;

public class PotenciaRapida {

    public static int potenciaRapida(int x, int y) {                            //Metodo recursivo
        if (y == 0) {                                                           //Dato base de la recursividad
            return 1;
        } else if (y % 2 == 0) {                                                //Validacion del segundo elemento si este este es par
            int mitad = potenciaRapida(x, y / 2);                               //Se asigna a una variable el retorno de la funcion recursiva reduciendo
            return mitad * mitad;                                               //el problema a la mitad
        } else {
            return x * potenciaRapida(x, y - 1);                                //Caso en donde se multiplica al elemento por las veces que devuelve el
        }                                                                       //nuevo valor que da el retorno de la funcion recursiva
    }

    public static void main(String[] args) {
        int x = 2, y = 10;
        System.out.println(x + "^" + y + " = " + potenciaRapida(x, y));
    }
}
