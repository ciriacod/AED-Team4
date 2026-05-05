 public class Main {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) {
        ColaPrioridad cp = new ColaPrioridad(4); // Crear una cola de prioridad con 4 niveles (0 a 3)

        cp.insertar("A", 0); // baja prioridad
        cp.insertar("B", 2); // alta prioridad
        cp.insertar("C", 1);
        cp.insertar("D", 2); // alta prioridad
        System.out.println(cp.eliminar()); 
        System.out.println(cp.eliminar()); 
        System.out.println(cp.eliminar()); 
    }
} 
    
