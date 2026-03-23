package ejercicio9;

public class TestStation 
{
	public static void main(String[] args) { 
		
		PowerStation<Smartphone> zonaCelulares = new PowerStation<>(); 
		Smartphone s1 = new Smartphone("iPhone 15", 20.5); 
		Smartphone s2 = new Smartphone("Galaxy S24", 25.0);
		Laptop l1 = new Laptop("Lenovo",190);
		Laptop l2=new Laptop("hp",50.0);
		l1.cargar(100);
		System.out.println(s2);
		System.out.println(s1);
		s2.cargar(50);
		zonaCelulares.conectar(s1); 
		zonaCelulares.conectar(s2); 
		System.out.println("Consumo Total: " + zonaCelulares.calcularConsumoTotal() + "W"); 
		}

}
