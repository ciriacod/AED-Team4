import java.util.Scanner;

public class Principal {
    public static void main(String[] args) 
    {
        //ingresamos los datos que nos piden como las ezquinas de dos coordenadas
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese las coordenadas de la esquina inferior izquierda del primer rectangulo");
            double x1_menor = scanner.nextDouble();
            double y1_menor = scanner.nextDouble();
            System.out.println("Ingrese la coordenadas de la esquina superior derecha del primer rectangulo");
            double x1_mayor = scanner.nextDouble();
            double y1_mayor = scanner.nextDouble();

            System.out.println("Ingrese las coordenadas de la esquina inferior izquierda del segundo rectangulo");
            double x2_menor = scanner.nextDouble();
            double y2_menor = scanner.nextDouble();
            System.out.println("Ingrese la coordenadas de la esquina superior derecha del segundo rectangulo");
            double x2_mayor = scanner.nextDouble();
            double y2_mayor = scanner.nextDouble();
//creamos  objeto instanciado  de clase Rectangulo y le damos dos parametros
            Rectangulo rectangulo1 = new Rectangulo(new Coordenada(x1_menor, y1_menor), new Coordenada(x1_mayor, y1_mayor));
            Rectangulo rectangulo2 = new Rectangulo(new Coordenada(x2_menor, y2_menor), new Coordenada(x2_mayor, y2_mayor));
            //hacemos las comparaciones y llamamos metodos essobre, es junto o es disjunto
            if (Verificador.esSobrePos(rectangulo1, rectangulo2)) {
                System.out.println("Los rectángulos están sobrepuestos.");
            } else if (Verificador.esJunto(rectangulo1, rectangulo2)) {
                System.out.println("Los rectángulos están juntos (tocan sus bordes).");
            } else if (Verificador.esDisjunto(rectangulo1, rectangulo2)) {
                System.out.println("Los rectángulos son disjuntos (separados).");
            } else {
                System.out.println("No se cumple ninguna condición específica.");
            }
        }

    }
}
