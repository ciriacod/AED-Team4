package Ejercicio04;

import java.io.BufferedReader;
import java.io.FileReader;

import Semana_9.btree.Btree;


public class Biblioteca {

    private Btree<Libro> arbol;

    public void cargarDesdeArchivo(String archivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));

            String primeraLinea = br.readLine();

            if (primeraLinea == null || primeraLinea.trim().isEmpty()) {
                System.out.println("El archivo está vacío.");
                br.close();
                return;
            }

            int orden = Integer.parseInt(primeraLinea.trim());
            arbol = new Btree<Libro>(orden);

            String linea;

            while ((linea = br.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length != 4) {
                    System.out.println("Línea incorrecta: " + linea);
                    continue;
                }

                String isbn = datos[0].trim();
                String titulo = datos[1].trim();
                String autor = datos[2].trim();
                int anio = Integer.parseInt(datos[3].trim());

                Libro libro = new Libro(isbn, titulo, autor, anio);

                if (!arbol.search(libro)) {
                    arbol.insert(libro);
                } else {
                    System.out.println("ISBN duplicado ignorado: " + isbn);
                }
            }

            br.close();
            System.out.println("Biblioteca cargada correctamente.");

        } catch (Exception e) {
            System.out.println("Error al cargar archivo: " + e.getMessage());
        }
    }

    public void agregarLibro(Libro libro) {
        if (arbol == null) {
            System.out.println("Primero debe cargar el archivo.");
            return;
        }

        if (!arbol.search(libro)) {
            arbol.insert(libro);
        } else {
            System.out.println("ISBN duplicado: " + libro.getIsbn());
        }
    }

    public void buscarPorISBN(String isbn) {
        if (arbol == null) {
            System.out.println("Primero debe cargar el archivo.");
            return;
        }

        Libro libro = new Libro(isbn, "", "", 0);
        arbol.searchPath(libro);
    }

    public void eliminarLibro(String isbn) {
        if (arbol == null) {
            System.out.println("Primero debe cargar el archivo.");
            return;
        }

        Libro libro = new Libro(isbn, "", "", 0);
        arbol.remove(libro);
    }

    public void mostrarLibrosOrdenados() {
        if (arbol == null) {
            System.out.println("Primero debe cargar el archivo.");
            return;
        }

        arbol.inOrder();
    }

    public void mostrarAltura() {
        if (arbol == null) {
            System.out.println("Primero debe cargar el archivo.");
            return;
        }

        System.out.println("Altura del Árbol B: " + arbol.height());
    }

    public void mostrarCantidadTotal() {
        if (arbol == null) {
            System.out.println("Primero debe cargar el archivo.");
            return;
        }

        System.out.println("Cantidad total de libros: " + arbol.size());
    }
}
