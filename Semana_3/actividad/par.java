package complejidadalgoritmica;

public class ContarPares {
	
	public static int contarPares(int[]arreglo ) {
		int cont=0; //O(1)
		for(int i=0;i < arreglo.length;i++) //O(n)
		{
			if(arreglo[i] % 2==0) //O(1)
			{
				cont+=1; //O(1)
				
			}
			
		}return cont; //O(1)
	}
	
	public static void main(String[] args) {
		
		int[] n= {1,2,3,4,6,9,10};
		System.out.print("existen numeros pares:"+contarPares(n));
	}

}

