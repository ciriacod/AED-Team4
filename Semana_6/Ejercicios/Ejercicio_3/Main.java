 public class Main {
    public static void main(String[] args) {
        ColaPrioridad cp = new ColaPrioridad(7); // 7 niveles de prioridad (0-6)

        cp.insertar(10, 0); // baja prioridad
        cp.insertar(20, 6); // alta prioridad
        cp.insertar(30, 5);

        System.out.println(cp.eliminar()); 
        System.out.println(cp.eliminar()); 
        System.out.println(cp.eliminar()); 
    }
} 
    
