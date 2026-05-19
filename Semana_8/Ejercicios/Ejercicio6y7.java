package Semana_8.Ejercicios;

import Importar.Exceptions.ItemDuplicated;
import Importar.Exceptions.ItemNotFound;
import Semana_8.avltree.AVLTree;

public class Ejercicio6y7 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIOS 6 Y 7: CASOS CRÍTICOS DE ROTACIÓN ===");
        AVLTree<Integer> avl = new AVLTree<>();

        try {
            // Secuencia que provoca desbalance Izquierda-Izquierda (Requiere rotación simple derecha)
            System.out.println("Insertando 30, 20, 10 (Fuerza desbalance Izquierda-Izquierda en 30)...");
            avl.insert(30);
            avl.insert(20);
            avl.insert(10); 
            System.out.print("Preorden tras balanceo (20 debería ser la raíz): "); 
            avl.preOrder();
            System.out.println(avl);

            // Secuencia que provoca desbalance Derecha-Derecha (Requiere rotación simple izquierda)
            System.out.println("Insertando 40, 50 (Fuerza desbalance Derecha-Derecha en 30)...");
            avl.insert(40);
            avl.insert(50);
            System.out.print("Preorden tras balanceo: "); 
            avl.preOrder();
            System.out.println(avl);

            // Provocar nuevas rotaciones mediante una eliminación drástica
            System.out.println("Eliminando la raíz actual (30) para forzar una reestructuración interna...");
            avl.delete(30);
            System.out.print("Estructura final en Amplitud: ");
            avl.breadthFirstRecursive();
            System.out.println(avl);

        } catch (Exception e) {
            System.out.println("Error en las pruebas de rotación: " + e.getMessage());
        }
        System.out.println("--------------------------------------------------");



                
        System.out.println("==========================================================");
        System.out.println("   ACTIVIDAD 7: SIMULACION DE INSERCION Y ELIMINACION AVL");
        System.out.println("==========================================================\n");

        AVLTree<Integer> arbolEjer7 = new AVLTree<>();

        try {
            // =================================================================
            // FASE 1: INSERCIÓN (Construcción con desbalances)
            // =================================================================
            System.out.println("----- FASE 1: INSERCION EN CADENA -----");
            
            // Insertamos valores que provocan rotaciones para estabilizarse
            int[] clavesAInsertar = {50, 25, 75, 15, 40, 60, 90, 10, 30, 45};
            System.out.println("Insertando secuencialmente: 50, 25, 75, 15, 40, 60, 90, 10, 30, 45\n");
            
            for (int clave : clavesAInsertar) {
                arbolEjer7.insert(clave);
            }

            System.out.println(">>> ARBOL AVL RESULTANTE TRAS LAS INSERCIONES:");
            System.out.println(arbolEjer7);
            
            // =================================================================
            // FASE 2: ELIMINACIÓN (Fuerza nuevas rotaciones post-borrado)
            // =================================================================
            System.out.println("----- FASE 2: ELIMINACION Y RE-BALANCEO -----");

            // Prueba A: Eliminar la clave 90 (Nodo Hoja en el extremo derecho)
            // Al quitar el 90, el subárbol derecho de 75 pierde altura, lo que dejará
            // al 75 inclinado a la izquierda y podría propagar ajustes.
            System.out.println(">>> Paso A: Eliminando el nodo 90 (Hoja derecha)...");
            arbolEjer7.delete(90);
            System.out.println(arbolEjer7);

            // Prueba B: Eliminar la clave 60 (Ahora el 75 queda como hoja aislada)
            System.out.println(">>> Paso B: Eliminando el nodo 60...");
            arbolEjer7.delete(60);
            System.out.println(arbolEjer7);

            // Prueba C: Eliminar la clave 75 (Forzar desbalance crítico en los niveles superiores)
            // Al remover la rama derecha por completo (75), el nodo raíz (50 u otro dependiente)
            // va a quedar severamente cargado a la izquierda (bf = -2), obligando al árbol
            // a aplicar una nueva rotación (Simple o Doble) para no romperse.
            System.out.println(">>> Paso C: Eliminando el nodo 75 (Provoca rotacion estructural)...");
            arbolEjer7.delete(75);
            System.out.println(">>> ARBOL AVL FINAL ESTABILIZADO:");
            System.out.println(arbolEjer7);

            System.out.print("Recorrido por Amplitud Final: ");
            arbolEjer7.breadthFirstRecursive();

        } catch (ItemDuplicated | ItemNotFound e) {
            System.out.println(" Error detectado en la simulación: " + e.getMessage());
        }

    }
}