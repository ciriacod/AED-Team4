public class Coordenada {
    // Atributos
    private double x;
    private double y;

    // Constructores
    public Coordenada() {
        this.x = 0;
        this.y = 0;
    }

    public Coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordenada(Coordenada c) {
        this.x = c.getX();
        this.y = c.getY();
    }

    // Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    // Getters
    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    // Métodos
    public double distancia(Coordenada c) {
        double dx = this.getX() - c.getX();
        double dy = this.getY() - c.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double distancia(Coordenada c1, Coordenada c2) {
        double dx = c1.getX() - c2.getX();
        double dy = c1.getY() - c2.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
