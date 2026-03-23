package ejercicio9;

public class Laptop  implements Cargable

{
	private double consumoVatioslap;
	private String modelolap;
	Laptop( String modelolap,double consumoVatioslap){
		this.consumoVatioslap=consumoVatioslap;
		this.modelolap=modelolap;
	}
	
	public void setconsumoVatioslap(double consumoVatioslap) {
		this.consumoVatioslap=consumoVatioslap;
	}
	public void setconsumoVatioslap(String modelolap) {
		this.modelolap=modelolap;
	} 
	@Override
	public double getConsumoVatios() {
		return consumoVatioslap;
		} 
	public String getmodelolap() {
		return modelolap;
	}
	@Override
	public int getNivelBateria() 
	{
		return ((int)consumoVatioslap *100) ;
		
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
		return "-Consumo: "+consumoVatioslap + " -Modelo" + modelolap;
		
	}

}
