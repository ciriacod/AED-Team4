import java.util.ArrayList;
import java.util.List;

public class InventarioBST {
    Producto raiz; // El primer producto que entra al almacén

    public InventarioBST() {
        this.raiz = null;
    }

    public void insertar(int codigo) {
        if (this.raiz == null) {
            this.raiz = new Producto(codigo);
        } else {
            acomodarProducto(this.raiz, codigo);
        }
    }

    private void acomodarProducto(Producto nodoActual, int codigo) {
        if (codigo < nodoActual.codigo) {
            if (nodoActual.izq == null) {
                nodoActual.izq = new Producto(codigo);
            } else {
                acomodarProducto(nodoActual.izq, codigo);
            }
        } else if (codigo > nodoActual.codigo) {
            if (nodoActual.der == null) {
                nodoActual.der = new Producto(codigo);
            } else {
                acomodarProducto(nodoActual.der, codigo);
            }
        }
    }

    public List<Integer> searchRange(int minimo, int maximo) {
        List<Integer> resultados = new ArrayList<>();
        buscarEnRango(this.raiz, minimo, maximo, resultados);
        return resultados;
    }

    private void buscarEnRango(Producto nodo, int minimo, int maximo, List<Integer> resultados) {
        if (nodo == null) {
            return;
    }
        if (nodo.codigo > minimo) {
            buscarEnRango(nodo.izq, minimo, maximo, resultados);
        }
        if (nodo.codigo >= minimo && nodo.codigo <= maximo) {
            resultados.add(nodo.codigo);
        }
        if (nodo.codigo < maximo) {
            buscarEnRango(nodo.der, minimo, maximo, resultados);
        }
    }


    public int countLeaves() {
        return contarHojas(this.raiz);
    }

    private int contarHojas(Producto nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izq == null && nodo.der == null) {
            return 1; // Es una hoja
        }
        return contarHojas(nodo.izq) + contarHojas(nodo.der);
    }


    public void printDescending() {
        imprimirDeMayorAMenor(this.raiz);
    }

    private void imprimirDeMayorAMenor(Producto nodo) {
        if (nodo == null) {
            return;
        }
        // Derecha -> Centro -> Izquierda
        imprimirDeMayorAMenor(nodo.der);
        System.out.println(nodo.codigo);
        imprimirDeMayorAMenor(nodo.izq);
    }
}
