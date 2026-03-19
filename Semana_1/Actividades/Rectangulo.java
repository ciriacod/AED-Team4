public class Rectangulo {
        private Coordenada esquina1;
        private Coordenada esquina2;

        public Rectangulo(Coordenada c1, Coordenada c2) {
            this.esquina1 = c1;
            this.esquina2 = c2;
        }

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

        public String toString() {
            return "Rectangulo: Esquina 1: " + this.esquina1.toString() + ", Esquina 2: " + this.esquina2.toString();
        }
        //metodo para hallar area mediante las coordenadas ya definidas antes

        public double calculoArea(){
            double base = Math.abs(this.esquina2.getX() - this.esquina1.getX());
            double altura = Math.abs(this.esquina2.getY() - this.esquina1.getY());
            return base * altura;
        }
}
