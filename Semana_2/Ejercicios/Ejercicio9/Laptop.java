package Semana_2.Ejercicios.Ejercicio9;

// Clase que representa una Laptop que puede ser cargada
public class Laptop implements Cargable
{
	private double consumoVatioslap; 
	private String modelolap;      
	private int nivelBateria;        // nivel de batería (0 - 100)
	
	Laptop(String modelolap, double consumoVatioslap) // constructor que inicializa atributos
	{
		this.consumoVatioslap = consumoVatioslap; 
		this.modelolap = modelolap;               
		this.nivelBateria = 0;                   // batería inicia en 0%
	}
	
	public void setconsumoVatioslap(double consumoVatioslap) // modifica consumo
	{
		this.consumoVatioslap = consumoVatioslap;
	}
	
	public void setmodelolap(String modelolap) // modifica modelo
	{
		this.modelolap = modelolap;
	}
	
	public void setNivelBateria(int nivelBateria) // modifica batería manualmente
	{
		this.nivelBateria = nivelBateria;
	}
	
	@Override
	public double getConsumoVatios() // retorna consumo en vatios
	{
		return consumoVatioslap;
	}
	
	public String getmodelolap() // retorna modelo
	{
		return modelolap;
	}
	
	@Override
	public int getNivelBateria() // retorna nivel de batería
	{
		return nivelBateria;
	}
	
	@Override
	public void cargar(int cantidad) // incrementa batería
	{
		nivelBateria += cantidad; // suma carga
		
		if(nivelBateria > 100) // controla límite máximo
		{
			nivelBateria = 100;
		}
		
		System.out.println("bateria de la laptop: " + nivelBateria + "%"); // muestra estado
	}
	
	@Override
	public String toString() // representación del objeto
	{
		return "-Consumo: " + consumoVatioslap + 
			   " -Modelo: " + modelolap + 
			   " -Bateria: " + nivelBateria + "%";
	}
	
	@Override
	public boolean equals(Object obj) // compara dos laptops
	{
		if(this == obj) // misma referencia
		{
			return true;
		}
		
		if(obj == null || getClass() != obj.getClass()) // validación
		{
			return false;
		}
		
		Laptop otro = (Laptop) obj; // conversión de tipo
		
		return this.modelolap.equals(otro.modelolap) && 
		       this.consumoVatioslap == otro.consumoVatioslap; // comparación de atributos
	}
}
