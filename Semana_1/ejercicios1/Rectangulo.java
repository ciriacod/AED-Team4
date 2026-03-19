package Semana_1.ejercicios1;

public class Rectangulo {
        //atributos
        private Coordenada esquina1;
        private Coordenada esquina2;
//constructor parametrizado
        public Rectangulo(Coordenada c1, Coordenada c2) {
            this.esquina1 = c1;
            this.esquina2 = c2;
        }
//metodos get y set
        public void setEsquina1(Coordenada coo) {
            this.esquina1 = coo;
        }

        public void setEsquina2(Coordenada coo) {
            this.esquina2 = coo;
        }

        public Coordenada getEsquina1() {
            return this.esquina1;
        }

        public Coordenada getEsquina2() {
            return this.esquina2;
        }
//metodo para mostrar
        public String toString() {
            return "(" + this.esquina1.toString() + ", " + this.esquina2.toString() + ")";
        }
        //calculos que pidieron
        public double calcularArea() {
            double base = Math.abs(esquina2.getX() - esquina1.getX());
            double altura = Math.abs(esquina2.getY() - esquina1.getY());
            return base * altura;
        }
        
        public double calcularDistancia() {
            double dx = esquina2.getX() - esquina1.getX();
            double dy = esquina2.getY() - esquina1.getY();
            return Math.sqrt(dx*dx + dy*dy);
        }
}
