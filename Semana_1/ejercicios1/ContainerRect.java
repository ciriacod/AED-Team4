package Semana_1.ejercicios1;

public class ContainerRect 
{
	private int n;
	private static int numRec = 0; 
	private Rectangulo[] rectangulos;
	private double[] distancias;
	private double[] areas;
	
	public ContainerRect(int n){
		this.n = n;	
		this.rectangulos = new Rectangulo[n];
		this.distancias = new double[n];
		this.areas = new double[n];
	}
	
	public boolean addRectangulo(Rectangulo r)
	{
		if(numRec <= n) {
			rectangulos[numRec] = r;
			distancias[numRec] = r.calcularDistancia();
			areas[numRec] = r.calcularArea();
			numRec++;
			return true;
		} else {
			System.out.println(" Capacidad máxima alcanzada.");
			return false;
		}
	}
	
	@Override
	public String toString() { 
		String resultado = "Rectangulo Coordenadas Distancia Area\n";
		for(int i = 0; i < numRec; i++) {
			resultado += (i+1) + " " + rectangulos[i].toString() + " " +  ///sale bien el formato de mustra usando este 3f y 2f
						String.format("%.3f", distancias[i]) + " " + 
						String.format("%.2f", areas[i]) + "\n";
		}
		return resultado;
	}
}
