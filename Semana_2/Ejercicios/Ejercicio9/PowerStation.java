package Semana_2.Ejercicios.Ejercicio9;

import java.util.ArrayList;

public class PowerStation<T extends Cargable> //Restringe tipos de datos que no sean Cargable, implements no se usa cuando es generico
{
	private ArrayList<T> dispositivo; // almacenar los objetos de tipo T
	
	PowerStation()
	{
		this.dispositivo = new ArrayList<>();//crea la lista dispositivo 
	}
	
	public void conectar(T dispositivos)//recibe objeto de tipo generico T 
	{
		dispositivo.add(dispositivos);//agrega el parametro dentro del arreglo
	}
	
	public double calcularConsumoTotal()  //el metodo calcular por medio de un recorrido arraylist se suma el consumo de vatios y retorna la suma del areglo
	{
		double suma = 0;
		
		for(T d : dispositivo)
		{
			suma = suma + d.getConsumoVatios();
		}
		
		return suma;
	}
	
	public int buscarDispositivo(T prototipo) 
	{											//// recorre la lista de dispositivos y compara cada elemento con el prototipo usando equals;
		// si lo encuentra retorna su posición, en caso contrario retorna -1
		for(int i = 0; i < dispositivo.size(); i++)
		{
			if(dispositivo.get(i).equals(prototipo))
			{
				return i;
			}
		}
		
		return -1; 
	}
	
	public void mostrarReporte()
	{
		System.out.println("----- REPORTE DE DISPOSITIVOS -----");
		
		for(int i = 0; i < dispositivo.size(); i++)
		{
			System.out.println(" Posicion: " + i+ "  Consumo: " + dispositivo.get(i).getConsumoVatios() + "W" +
					           " Bateria: " + dispositivo.get(i).getNivelBateria() + "%");
		}
	}
	/*mostrarReporte() imprime información específica de los 
	 * atributos utilizando métodos definidos en la interfaz, 
	 * permitiendo un formato estructurado. En cambio, mostrar() 
	 * imprime el objeto completo utilizando el método toString(), 
	 * dependiendo de su implementación.
	 */
	
	public void mostrar()
	{
		for(T d : dispositivo)
		{
			System.out.println(d);
		}
	}
}
