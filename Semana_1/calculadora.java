import java.util.Scanner;
import imagen.java;

public class Calculadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese el primer número: ");
        double num1 = scanner.nextDouble();
        
        System.out.print("Ingrese el operador (+, -, *, /): ");
        char operador = scanner.next().charAt(0);
        
        System.out.print("Ingrese el segundo número: ");
        double num2 = scanner.nextDouble();
        
        double resultado = 0;
        boolean valido = true;
        
        switch (operador) {
            case '+':
                resultado = num1 + num2;
                break;
            case '-':
                resultado = num1 - num2;
                break;
            case '*':
                resultado = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    resultado = num1 / num2;
                } else {
                    System.out.println("Error: División por cero.");
                    valido = false;
                }
                break;
            default:
                System.out.println("Operador no válido.");
                valido = false;
        }
        
        if (valido) {
            System.out.println("Resultado: " + resultado);
        }
        
        scanner.close();

        mostrarImagen();
    }
}