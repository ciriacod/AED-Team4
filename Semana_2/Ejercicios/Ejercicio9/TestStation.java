package ejercicio9;

public class TestStation
{
	public static void main(String[] args)
	{
		PowerStation<Smartphone> zonaCelulares = new PowerStation<>(); //carga que solo acepta objetos de tipo Smartphone
		// Se crean dos dispositivos Smartphone con su modelo y consumo en vatios
		Smartphone s1 = new Smartphone("iPhone 15", 20.5);
		Smartphone s2 = new Smartphone("Galaxy S24", 25.0);
		
		s1.cargar(40);
		s2.cargar(50);
		
		zonaCelulares.conectar(s1);
		zonaCelulares.conectar(s2);
		// Se calcula e imprime el consumo total de la estación
		System.out.println("Consumo Total: " + zonaCelulares.calcularConsumoTotal() + "W");
		// Se busca un dispositivo en la lista utilizando un objeto prototipo
		
		System.out.println("Posicion buscada: " + zonaCelulares.buscarDispositivo(new Smartphone("Galaxy S24", 15.0)));
		System.out.println("Posicion buscada: " + zonaCelulares.buscarDispositivo(new Smartphone("Galaxy S24", 18.0)));
		
		zonaCelulares.mostrarReporte();
		
		System.out.println();
		// Se crea otra estación de carga para dispositivos tipo Laptop
		
		PowerStation<Laptop> zonaLaptops = new PowerStation<>();
		
		Laptop l1 = new Laptop("Lenovo", 190);
		Laptop l2 = new Laptop("HP", 50.0);
		
		l1.cargar(70);
		l2.cargar(100);
		
		zonaLaptops.conectar(l1);
		zonaLaptops.conectar(l2);
		//// Se calcula e imprime el consumo total de las laptops
		System.out.println("Consumo Total Laptops: " + zonaLaptops.calcularConsumoTotal() + "W");
		zonaLaptops.mostrarReporte();
	}
}
