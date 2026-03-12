import java.util.*;

public class Rectangulo {
    public static void main(String[] args) {
        private Coordenada esquina1;
        private Coordenada esquina2;

        public Rectangulo(Coordenada c1, Coordenada c2) {
            this.esquina1 = c1;
            this.esquina2 = c2;
        }

        public void setEsquina1(Coordenada c) {
            this.esquina1 = c;
        }

        public void setEsquina2(Coordenada c) {
            this.esquina2 = c;
        }

        public Coordenada getEsquina1() {
            return this.esquina1;
        }

        public Coordenada getEsquina2() {
            return this.esquina2;
        }

        public String toString() {
            return "Rectangulo: Esquina 1: " + this.esquina1.toString() + ", Esquina 2: " + this.esquina2.toString();
        }
}