  // ============================================ Ejercicio 1 ==========================================================

            System.out.println("======================================================");
          
        }try {
        	
        	  System.out.println("   EJERCICIO 1: GESTOR DE TICKETS AVL");
              System.out.println("======================================================\n");

              AVLTree<Integer> gestorTickets = new AVLTree<>();

                System.out.println("----- FASE 1: INSERCION DE TICKETS -----");

                int[] ticketsInsertar = {30, 10, 20, 40, 50, 25};

                for (int ticket : ticketsInsertar) {
                    System.out.println("\n>>> Insertando ticket: " + ticket);
                    try {
                        gestorTickets.insert(ticket);
                        System.out.println(gestorTickets.toString());
                    } catch (ItemDuplicated e) {
                        System.out.println("Error: ticket duplicado -> " + ticket);
                    }
                }


                System.out.println("\n----- FASE 2: BUSQUEDA DE TICKETS -----");

                int[] ticketsBuscar = {20, 60};
                
                for (int ticket : ticketsBuscar) {
                    System.out.println("\n>>> Buscando ticket: " + ticket);

                    try {
                        gestorTickets.search(ticket);
                        System.out.println("Ticket " + ticket + " encontrado.");
                    } catch (ItemNotFound e) {
                        System.out.println("Ticket " + ticket + " no encontrado.");
                    }

                    System.out.println(gestorTickets);
                }

                System.out.println("\n----- FASE 3: ELIMINACION DE TICKETS -----");

                int[] ticketsEliminar = {10, 40, 30};

                for (int ticket : ticketsEliminar) {
                    System.out.println("\n>>> Eliminando ticket: " + ticket);
                    gestorTickets.delete(ticket);
                    System.out.println(gestorTickets);
                    System.out.print("Inorden actual: ");
                    gestorTickets.inOrder();
                }

                System.out.println("\n===== ARBOL AVL FINAL =====");
                System.out.println(gestorTickets);

                System.out.print("Recorrido inorden final: ");
                gestorTickets.inOrder();

            } catch (ItemNotFound e) {
                System.out.println("Error en Ejercicio 1: " + e.getMessage());
            }
