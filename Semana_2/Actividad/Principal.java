package Semana_2.Actividad;

public class Principal {
    public static void main(String[] args) {
        Bolsa <Chocolatina> bolsaCho = new Bolsa<Chocolatina>(3);
        Chocolatina c = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("milka");
        Chocolatina c3 = new Chocolatina("ferrero");
        bolsaCho.agregar(c);
        bolsaCho.agregar(c2);
        bolsaCho.agregar(c3);

        for(Chocolatina chocolatina : bolsaCho){
            System.out.println(chocolatina.getMarca());
        }

        Bolsa <Golosina> bolsaGol = new Bolsa<Golosina>(3);
        Golosina g = new Golosina("chicle", 0.5);
        Golosina g2 = new Golosina("chicle", 0.5);
        Golosina g3 = new Golosina("caramelo", 0.5);
        bolsaGol.agregar(g);
        bolsaGol.agregar(g2);
        bolsaGol.agregar(g3);
        for(Golosina golosina : bolsaGol){
            System.out.println(golosina.getNombre() + " " + golosina.getPeso());
        }
    }
}