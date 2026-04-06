package complejidadalgoritmica;

public class Max 
{
	public  static void numeromaximo(int x, int y) 
	{
		if(x==y) { //O(1)
			System.out.println("x:"+x +"y:"+ y);//O(1)
		}else if(x >y){   //O(1)
			System.out.println("x:"+x);	//O(1)
			
		}else {
			System.out.println("y:"+y);
		}
		
	}
public static void main(String[] args) {
	numeromaximo(3,7);
	}

}
