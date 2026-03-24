package Semana_2.Ejercicios.Ejercicio1_7;

public class TestGen {
    public static <T> boolean exist(T[] arreglo, T elemento) {
        for (T item : arreglo) {                                                          //Bucle que permite recorrer el arreglo
            if (item.equals(elemento)) {                                                  //Se valida que el elemento ingresado este en el arreglo
                return true;
            }
        }
        return false;
    }
}
