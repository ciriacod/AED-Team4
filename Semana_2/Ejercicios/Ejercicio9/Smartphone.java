package Semana_2.Ejercicios.Ejercicio9;

public class Smartphone implements Cargable
//Clase que representa un dispositivo Smartphone que implementa la interfaz Cargable
{
	private double consumoVatios;
	private String modelo;
	private int nivelBateria;
	// Clase que representa un dispositivo Smartphone que implementa la interfaz Cargable en 0
	Smartphone(String modelo, double consumoVatios)
	{
		this.consumoVatios = consumoVatios;
		this.modelo = modelo;
		this.nivelBateria = 0;
	}
	//set y get
	public void setconsumoVatios(double consumoVatios)
	{
		this.consumoVatios = consumoVatios;
	}
	
	public void setmodelo(String modelo)
	{
		this.modelo = modelo;
	}
	
	public void setNivelBateria(int nivelBateria)
	{
		this.nivelBateria = nivelBateria;
	}
	
	@Override
	public double getConsumoVatios()
	{
		return consumoVatios;
	}
	
	public String getmodelo()
	{
		return modelo;
	}
	
	@Override
	public int getNivelBateria()
	{
		return nivelBateria;
	}
	//// Método que incrementa la batería del dispositivo
	@Override
	public void cargar(int cantidad)
	{
		nivelBateria += cantidad;
		//// Método que incrementa la batería del dispositivo
		
		if(nivelBateria > 100)
		{
			nivelBateria = 100;
		}	// Control para que no supere el 100%
		
		System.out.println("bateria del smartphone: " + nivelBateria + "%");
	}
	
	@Override
	public String toString()
	{
		return "-Consumo: " + consumoVatios + " -Modelo: " + modelo + " -Bateria: " + nivelBateria + "%";
	}
	
	@Override
	public boolean equals(Object obj)// Método equals para comparar dos objetos Smartphone
	// Permite verificar si tienen el mismo modelo y consumo
	{
		if(this == obj)
		{
			return true;
		}
		
		if(obj == null || getClass() != obj.getClass())
		{
			return false;
		}
		
		Smartphone otro = (Smartphone) obj;
		
		return this.modelo.equals(otro.modelo) && this.consumoVatios == otro.consumoVatios;
	}
}

