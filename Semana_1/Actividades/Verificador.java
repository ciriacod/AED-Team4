public class Verificador {
    // Caso 1: Hay una intersección real de áreas (mayor a cero)
    public static Boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        return r1.getEsquina1().getX() < r2.getEsquina2().getX() &&
               r1.getEsquina2().getX() > r2.getEsquina1().getX() &&
               r1.getEsquina1().getY() < r2.getEsquina2().getY() &&
               r1.getEsquina2().getY() > r2.getEsquina1().getY(); 
    }

    // Caso 2: No se sobreponen, pero al menos una coordenada coincide
    public static boolean esJunto(Rectangulo r1, Rectangulo r2) {
        if (esSobrePos(r1, r2)) return false;
        // Verifica si el contacto es en X o en Y dentro de los rangos del otro rectángulo
        boolean contactoX = (r1.getEsquina2().getX() == r2.getEsquina1().getX() || 
                             r1.getEsquina1().getX() == r2.getEsquina2().getX());
        boolean contactoY = (r1.getEsquina2().getY() == r2.getEsquina1().getY() || 
                             r1.getEsquina1().getY() == r2.getEsquina2().getY());
        // Para que esten "juntos", deben compartir un borde o punto, 
        // no basta con que la coordenada coincida, deben estar alineados.
        boolean alineadoY = r1.getEsquina1().getY() <= r2.getEsquina2().getY() && 
                            r1.getEsquina2().getY() >= r2.getEsquina1().getY();
        boolean alineadoX = r1.getEsquina1().getX() <= r2.getEsquina2().getX() && 
                            r1.getEsquina2().getX() >= r2.getEsquina1().getX();
        return (contactoX && alineadoY) || (contactoY && alineadoX);
    }

    // Caso 3: No están ni sobrepuestos ni juntos
    public static boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return !esSobrePos(r1, r2) && !esJunto(r1, r2);
    }
}

