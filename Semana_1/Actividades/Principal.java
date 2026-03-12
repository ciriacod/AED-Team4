import java.util.Scanner;

public class Principal {

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Ingrese las coordenadas de la esquina inferior izquierda del primer rectangulo");
        double x1_menor = scanner(System.in).nextDouble();
        double y1_menor = scanner(System.in).nextDouble();
        System.out.println("Ingrese la coordenadas de la esquina superior derecha del primer rectangulo")
        double x1_mayor = scanner(System.in).nextDouble();
        double y1_mayor = scanner(System.in).nextDouble();

        System.out.println("Ingrese las coordenadas de la esquina inferior izquierda del segundo rectangulo");
        double x2_menor = scanner(System.in).nextDouble();
        double y2_menor = scanner(System.in).nextDouble();
        System.out.println("Ingrese la coordenadas de la esquina superior derecha del segundo rectangulo")
        double x2_mayor = scanner(System.in).nextDouble();
        double y2_mayor = scanner(System.in).nextDouble();

        Rectangulo rectangulo1 = new Rectangulo(new Coordenada(x1_menor, y1_menor), new Coordenada(x1_mayor, y1_mayor));
        Rectangulo rectangulo2 = new Rectangulo(new Coordenada(x2_menor, y2_menor), new Coordenada(x2_mayor, y2_mayor));



    }
}