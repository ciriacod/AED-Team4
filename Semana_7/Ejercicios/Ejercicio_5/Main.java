import java.util.List;

public class Main {
    public static void main(String[] args) {
        InventarioBST miAlmacen = new InventarioBST();

        // 1. Insertamos productos
        miAlmacen.insertar(50);
        miAlmacen.insertar(30);
        miAlmacen.insertar(70);
        miAlmacen.insertar(20);
        miAlmacen.insertar(40);
        miAlmacen.insertar(60);
        miAlmacen.insertar(80);

        // 2. Probamos buscar en un rango (por ejemplo, entre 35 y 75)
        System.out.println("Productos entre 35 y 75:");
        List<Integer> encontrados = miAlmacen.searchRange(35, 75);
        System.out.println(encontrados); // Debería mostrar [40, 50, 60, 70]
        
        System.out.println("-----------------");

        // 3. Probamos contar las hojas (los que están al final)
        System.out.println("Cantidad de hojas en el inventario: " + miAlmacen.countLeaves()); // Debería ser 4
        
        System.out.println("-----------------");

        // 4. Probamos imprimir de mayor a menor
        System.out.println("Productos en orden descendente:");
        miAlmacen.printDescending(); // Debería imprimir 80, 70, 60, 50, 40, 30, 20
    }
}
