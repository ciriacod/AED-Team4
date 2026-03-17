package PyPooEje1;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el numero maximo para almacenar");
        int nmax = scanner.nextInt();
        ContainerRect contenedor = new ContainerRect(nmax);
        
        for(int i = 0; i < nmax; i++) {
            System.out.println("\n :o--- Rectangulo " + (i+1) + " ---");
            System.out.println("Ingrese las coordenadas de la esquina inferior izquierda:");
            double x_menor = scanner.nextDouble();
            double y_menor = scanner.nextDouble();
            System.out.println("Ingrese las coordenadas de la esquina superior derecha:");
            double x_mayor = scanner.nextDouble();
            double y_mayor = scanner.nextDouble();
            
            Rectangulo rect = new Rectangulo(new Coordenada(x_menor, y_menor), new Coordenada(x_mayor, y_mayor));
            contenedor.addRectangulo(rect);
        }
        
        System.out.println();
        System.out.println(contenedor.toString());
        
        scanner.close();
    }
}
