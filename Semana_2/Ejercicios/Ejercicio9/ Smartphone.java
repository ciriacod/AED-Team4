package ejercicio9;

public class Smartphone implements Cargable 
{
	private double consumoVatios;
	private String modelo;
	
	Smartphone ( String modelo,double consumoVatios){
		this.consumoVatios=consumoVatios;
		this.modelo=modelo;
	}
	
	public void setconsumoVatios(double consumoVatios) {
		this.consumoVatios=consumoVatios;
	}
	public void setconsumoVatios(String modelo) {
		this.modelo=modelo;
	} 
	@Override
	public double getConsumoVatios() {
		return consumoVatios;
		} 
	public String getmodelo() {
		return modelo;
	}
	@Override
	public int getNivelBateria() 
	{
		return ((int)consumoVatios *100) ;
		
	}
	//cuanta cantidad 
	@Override
	public void cargar(int cantidad) {
		if(cantidad<100) {
			System.out.println("cargando ...");
		}else 
		{
			System.out.println("Esta al 100% ");
		}
	}
	
	@Override
	public String toString() {
		return "-Consumo: "+consumoVatios + " -Modelo" + modelo;
		
	}

}
