package Semana_1.ejercicios1;

public class Coordenada 
{
	private double x;
	private double y;

	//constructores
	public Coordenada(){
		x=0;
		y=0;
	}
	public Coordenada(double x, double y ){
		this.x= x;
		this.y=y;
		
	}
	public Coordenada(Coordenada c ){
		this.x=c.x;
		this.y=c.y;
	}

	void setX(double x) {
		this.x=x;
		
	}
	void setY(double y){
		this.y=y;
		
	}
	double getX(){
		return this.x;
		
	}
	double getY(){
		return this.y;
		
	}
	//metodos de instancia que necesita tener un objeto concreto ya creado antes
	double distancia(Coordenada c)
	{
		double dx= this.x - c.x;
		double dy=this.y - c.y;
		return Math.sqrt(dx*dx + dy*dy);
		
	}
	//metodo de clase que accede a los datos creados de la clase misma
	static double distancia(Coordenada c1, Coordenada c2) 
	{
		double cx=c1.x-c2.x;
		double cy=c1.y-c2.y;
		return Math.sqrt(cx*cx+cy*cy);
		
	}
	@Override
	public String toString(){
		return "x: "+x+"y:"+y;
	}
}
