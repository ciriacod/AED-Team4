package Semana_7.Ejercicios.Ejercicio_4;


public class Main {
    public static void main(String[] args) {

        LinkedQuaternaryT<String> tree = new LinkedQuaternaryT<>();
        

        tree.insertRoot("Sales");

        tree.insert("Sales", "Domestic");
        tree.insert("Sales", "International");

        tree.insert("International", "Canada");
        tree.insert("International", "S. America");
        tree.insert("International", "Overseas");

        tree.insert("Overseas", "Africa");
        tree.insert("Overseas", "Europe");
        tree.insert("Overseas", "Asia");
        tree.insert("Overseas", "Australia");

        System.out.println("Forma de arbol:");
        tree.printTree();

        System.out.println("\nForma parentizada:");
        tree.parenthesize();
       
        
                LinkedQuaternaryT<String>.Node resultado = tree.search("Africa");

                if (resultado != null) {
                    System.out.println("\nNodo encontrado: " + resultado.data);
                } else {
                    System.out.println("\nNodo no encontrado");
                }
                
                //aqui  observamos el metodo de la  clase LinkedBST que nos pidio el ejercicio 
                LinkedBST<Integer> trees = new LinkedBST<>();

                trees.insertRoot(50);

                System.out.println(trees.isValidBST());
            }
    
        
      
    }


