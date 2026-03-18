package Semana_1.ejercicios2;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);                                         //Se instancia la clase Scanner
        AnalizadorMinero analizador = new AnalizadorMinero();                            //Se instancia la clase AnalizadorMinero
        
        try {                        
            System.out.print("Ingrese la ruta del archivo (.txt): ");                    
            String ruta = lector.nextLine();                                              //Se ingresa por teclado la ruta del archivo
            analizador.cargarDesdeArchivo(ruta);                                          //Se envia la ruta mediante un método de la clase AnalizadorMinero
            
            System.out.print("Ingrese el tamaño de la subregión (terreno de 3x3): ");
            int k = lector.nextInt();                                                     //Se ingresa el tamaño de la subregión que se desea calcular
            
            analizador.ejecutarAnalisis(k);                                               //Se envia el tamaño mediante un método
            
            } catch (Exception e) {
                System.out.println("Error durante la ejecución: " + e.getMessage());      //Muestra un mensaje de error en caso que no se ejecuto correctamente
            } finally {
                lector.close();                                                           //Se cierra Scanner
        }
    }
}
