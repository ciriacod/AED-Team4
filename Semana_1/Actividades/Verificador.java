public class Verificador {

    public static Boolean esSobrePos(Rectangulo r1, Rectangulo r2) {
        return !(r1.getEsquina2().getX() < r2.getEsquina1().getX() ||  // r1 a la izquierda
                 r1.getEsquina1().getX() > r2.getEsquina2().getX() ||  // r1 a la derecha
                 r1.getEsquina2().getY() < r2.getEsquina1().getY() ||  // r1 abajo
                 r1.getEsquina1().getY() > r2.getEsquina2().getY());   // r1 arriba
    }

    // Se tocan en un borde
    public static Boolean esJunto(Rectangulo r1, Rectangulo r2) {
        boolean tocaHorizontal = (r1.getEsquina2().getX() == r2.getEsquina1().getX() ||
                                  r2.getEsquina2().getX() == r1.getEsquina1().getX());
        boolean tocaVertical   = (r1.getEsquina2().getY() == r2.getEsquina1().getY() ||
                                  r2.getEsquina2().getY() == r1.getEsquina1().getY());

        return (tocaHorizontal || tocaVertical) && !esSobrePos(r1, r2);
    }

    // Est separados
    public static Boolean esDisjunto(Rectangulo r1, Rectangulo r2) {
        return (r1.getEsquina2().getX() < r2.getEsquina1().getX() ||  // r1 completamente a la izquierda
                r1.getEsquina1().getX() > r2.getEsquina2().getX() ||  // r1 completamente a la derecha
                r1.getEsquina2().getY() < r2.getEsquina1().getY() ||  // r1 completamente abajo
                r1.getEsquina1().getY() > r2.getEsquina2().getY());   // r1 completamente arriba
    }
}

