public class Producto {
    int codigo;
    Producto izq; // Pasillo izquierdo (para códigos menores)
    Producto der; // Pasillo derecho (para códigos mayores)

    // Al crear un nuevo producto, le asignamos su código y dejamos los pasillos vacíos
    public Producto(int codigo) {
        this.codigo = codigo;
        this.izq = null;
        this.der = null;
    }
}
