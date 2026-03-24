package Semana_2.Ejercicios.Ejercicio1_7;

public class Principal {
    public static void main(String[] args) {
        Bolsa<Chocolatina> bolsaCho = new Bolsa<>(3);                                           //Se define una bolsa de Chocolatinas

        Chocolatina c = new Chocolatina("milka");
        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ferrero");

        bolsaCho.add(c);                                                                        //Se agregan Chocolatinas a las bolsas
        bolsaCho.add(c1);
        bolsaCho.add(c2);

        System.out.println("Marcas en la bolsa de chocolatinas:");                              //Se recorre la bolsa y se muestra
        for (Chocolatina chocolatina : bolsaCho) {                                              //la marca por pantalla gracias a Iterator
            System.out.println("- " + chocolatina.getMarca());
        }
        
        System.out.println("----------------------------------------------------------\n");
        
        // --- Caso del ejercicio n°1 ---
        System.out.println("Casos del ejercicio n1");
        String[] v = {"Perez", "Sanchez", "Rodriguez"};                                         //Se ingresan arreglos Integer y String
        Integer[] w = {12, 34, 56};

        System.out.println("Existe 'Sanchez' en v?: " + TestGen.exist(v, "Sanchez"));
        System.out.println("Existe 34 en w?: " + TestGen.exist(w, 34));
        
        System.out.println("----------------------------------------------------------\n");
                    
        // --- Caso del ejercicio n°2 ---
        Bolsa<Golosina> bolsaGol = new Bolsa<>(2);                                              // Se crea una bolsa de Golosinas
        bolsaGol.add(new Golosina("Gomitas", 0.15));
        bolsaGol.add(new Golosina("Caramelo", 0.05));

        DemoMetodoGenerico.imprimirContenidoBolsa(bolsaCho);
        DemoMetodoGenerico.imprimirContenidoBolsa(bolsaGol);

        System.out.println("----------------------------------------------------------\n");
        
        // --- Caso del ejercicio n°5 ---
        Cajoneria<Golosina> miCajoneriaGolosina = new Cajoneria<>(5);                   //Se crea una Cajoneria para 5 golosinas

        Caja<Golosina> caja1 = new Caja<>("Rojo");                              //Se crea y agrega 5 golosinas a sus respectivas cajas
        caja1.setContenido(new Golosina("Caramelo", 5.0));
        
        Caja<Golosina> caja2 = new Caja<>("Azul");
        caja2.setContenido(new Golosina("Gomita", 10.5));
        
        Caja<Golosina> caja3 = new Caja<>("Verde");
        caja3.setContenido(new Golosina("Chocolate", 20.0));
        
        Caja<Golosina> caja4 = new Caja<>("Amarillo");
        caja4.setContenido(new Golosina("Paleta", 15.0));
        
        Caja<Golosina> caja5 = new Caja<>("Naranja");
        caja5.setContenido(new Golosina("Chicle", 2.0));

        miCajoneriaGolosina.add(caja1);
        miCajoneriaGolosina.add(caja2);
        miCajoneriaGolosina.add(caja3);
        miCajoneriaGolosina.add(caja4);
        miCajoneriaGolosina.add(caja5);

        System.out.println("--- REPORTE INICIAL ---");
        System.out.println(miCajoneriaGolosina);

        Golosina aBuscar = new Golosina("Chocolate", 20.0);                     
        System.out.println("Buscando Chocolate: " + miCajoneriaGolosina.search(aBuscar));//Se Verifica existencia de Golosina

        Golosina aEliminar = new Golosina("Gomita", 10.5);                      
        Golosina eliminado = miCajoneriaGolosina.delete(aEliminar);                     //Se Prueba el método delete
        
        if (eliminado != null) {
            System.out.println("\nSe ha eliminado: " + eliminado);
        } else {
            System.out.println("\nNo se encontró el elemento para eliminar.");
        }

        System.out.println("\n--- REPORTE TRAS ELIMINACIÓN ---");
        System.out.println(miCajoneriaGolosina);
        
        System.out.println("----------------------------------------------------------\n");
        
        // --- Casos del ejercicio n°6 ---
        Caja<Golosina> cajaExtraGolosina = new Caja<>("Gris");                  //Se agrega una golosina igual a una que ya existe
        cajaExtraGolosina.setContenido(new Golosina("Caramelo", 5.0));
        
        Golosina buscarGolosina = new Golosina("Caramelo", 5.0);
        int cantidadGolosina = miCajoneriaGolosina.contar(buscarGolosina);      //Se realiza la prueba del conteo
        
        System.out.println("\n--- PRUEBA DE CONTEO ---");
        System.out.println("La golosina [" + buscarGolosina + "] aparece: " + cantidadGolosina + " veces.");

        System.out.println("----------------------------------------------------------\n");

        // --- Casos del ejercicio n°7 ---
        Cajoneria<Chocolatina> miCajoneriaChocolatina = new Cajoneria<>(3);     //Se crea una Cajoneria para 3 Chocolatinas
        Caja<Chocolatina> cajaChocolatina = new Caja<>("Marron");
        cajaChocolatina.setContenido(new Chocolatina("milka"));                 
        
        miCajoneriaChocolatina.add(cajaChocolatina);                            //Se agrega Chocolatina a Cajoneria

        Chocolatina buscarChocolatina = new Chocolatina("milka");
        int cantidadChocolatina = miCajoneriaChocolatina.contar(buscarChocolatina);//Se realiza la prueba del conteo
        
        System.out.println("\n--- PRUEBA DE CONTEO ---");
        System.out.println("La chocolotina [" + buscarChocolatina + "] aparece: " + cantidadChocolatina + " veces.");
    }
}
