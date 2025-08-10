import java.util.Scanner;

public class EventosEspeciales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            //Declaracion de las variables, matrices y listas
        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[][] nombresEventos = {
            {"Festival de Navidad", "Día del Niño", "Graduación", "Viaje Escolar"},
            {"Festival de Navidad", "Talleres Especiales", "Graduación", "Fotos Escolares"},
            {"Viaje de Generación", "Campamento de Verano", "Graduación", "Fotos Escolares"}
        };

        double[][] costos = DataManager.costoEventos();  
        double[][] acumulados = new double[3][4]; 
        boolean[][] pagado = new boolean[3][4];           

        double deuda_total = 0;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;
            //Aqui es donde imprime los niveles educativos y te pide seleccionar uno
        while (true) {
            String[] nivelesEducativos = {
                    "Preescolar",
                    "Primaria",
                    "Secundaria",
                    "Finalizar y ver resumen"
                };

            int entrada = Main.menuVentana(sc, "Menú de eventos especiales", nivelesEducativos);

            if (entrada == 4) {break;}

            if (entrada < 1 || entrada > 3) {
                System.out.println(">> Nivel educativo inválido");
                continue;
            }

            int nivel = entrada - 1;

                        
            System.out.print("Ingresa el nombre del alumno al que desea pagar algun evento: ");
            String nombreIngresado = sc.nextLine().trim();

            boolean inscrito = false;

            // Aqui se recorre la matriz que tiene los nombres de los alumnos por cada nivel escolar
            //y se corrobora si esta o no el alumno
            for (int i = 0; i < DataManager.alumnosInscritos[nivel].length; i++) {
                String alumno = DataManager.alumnosInscritos[nivel][i];
                if (alumno != null && alumno.equalsIgnoreCase(nombreIngresado)) {
                    inscrito = true;
                    break;
                }
            }
                    
            if (!inscrito) {
                System.out.println("Alumno no inscrito. Por favor, inscríbelo primero.");
                continue; 
            }

            System.out.println("Alumno seleccionado: " + nombreIngresado);
            
            //Te muestra los eventos y tambien los precios 
            while (true) {
                String tituloEventos = "Elija un evento: " + nombresNivel[nivel];
                String[] opcionesEventos = {
                    String.format("%s ($%.2f)", nombresEventos[nivel][0], costos[nivel][0]),
                    String.format("%s ($%.2f)", nombresEventos[nivel][1], costos[nivel][1]),
                    String.format("%s ($%.2f)", nombresEventos[nivel][2], costos[nivel][2]),
                    String.format("%s ($%.2f)", nombresEventos[nivel][3], costos[nivel][3]),
                    "Volver al menú anterior"
                };

                int opcionEvento = Main.menuVentana(sc, tituloEventos, opcionesEventos);

                if (opcionEvento == 5) break;
                
                 

                if (opcionEvento < 1 || opcionEvento > nombresEventos[nivel].length) {
                    System.out.println(">> Opción inválida");
                    continue;
                }

                int indice = opcionEvento - 1;

                // Aqui te dice si el evento ya fue pagado para que no se pueda volver a pagar
                if (pagado[nivel][indice]) {
                    System.out.println("Este evento ya fue pagado.");
                    continue;
                }

                double montoEvento = costos[nivel][indice];
                String nombreEvento = nombresEventos[nivel][indice];

                //Aqui llamamos a una matriz para que pregunte si deseamos proceder con el pago  
                if (Main.confirmarPago(sc)) {
                    if (Main.procesarCobro(montoEvento, saldo_disponible, nombreEvento)) {
                        acumulados[nivel][indice] += montoEvento;
                        pagado[nivel][indice] = true;     
                        saldo_disponible -= montoEvento; 
                        System.out.printf("Has pagado: %s por $%.2f\n", nombreEvento, montoEvento);
                    }
                }
            }
        }
            // Es el recibo donde se muestran todos los eventos que pagamos y cuanto fue el total y lo que nos queda de dinero
            System.out.println("\n=== RECIBO GENERAL DE EVENTOS ===");
            for (int i = 0; i < 3; i++) {
                boolean tieneEventos = false;
                for (int j = 0; j < 4; j++) {
                    if (acumulados[i][j] > 0) {
                        if (!tieneEventos) {
                            System.out.println("Nivel educativo: " + nombresNivel[i]);
                            tieneEventos = true;
                        }
                        System.out.printf("  %s: $%.2f\n", nombresEventos[i][j], acumulados[i][j]);
                        deuda_total += acumulados[i][j];
                    }
                }
            }

            System.out.printf("Saldo inicial:    $%.2f\n", saldo_inicial);
            System.out.printf("Total pagado:     $%.2f\n", deuda_total);
            System.out.printf("Saldo restante:   $%.2f\n", saldo_disponible);

            DataManager.saldos[DataManager.usuarioActual] = saldo_disponible;
            Main.mostrarMenu();
        
    }
}