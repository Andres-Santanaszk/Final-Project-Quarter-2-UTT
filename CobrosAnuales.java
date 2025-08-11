import java.util.Scanner;

import utils.Color;

public class CobrosAnuales {

    public static void main(String[] args) {
        // declaracion de variables, matrices, arrays, etc
        Scanner sc = new Scanner(System.in);

        double[][] tarifas = {
            {2800, 400},  // kinder 
            {3000, 500}, // primaria
            {4200, 600}   // secundaria
        };

        double[][] TarifaUnif = {
            {1600, 1600, 1300, 1300}, 
            {1800, 1800, 1390,1390}, 
            {2170, 2170, 1575, 1575} 
        };

        String[] nombresPapeleria = {
            "Cuadernos",
            "Libro de inglés",
            "Colores (12 piezas)",
            "Borrador",
            "Sacapuntas metálico",
            "Regla de 30cm",
            "Papel bond (paquete)",
            "Tijeras escolares"
        };

        double[] preciosPapeleria = {
            120.00,
            390.00,
            80.30,
            10.75,
            15.40,
            12.20,
            180.30,
            25.30
        };

        String[] nombresNivel = {"preescolar", "primaria", "secundaria"};
        String[] nombreUniformes = {"Formal masculino", "Formal femenino", "Deportivo masuclino", "Deportivo femenino"};
        String[] nombresOpciones = {"Inscripción", "Mantenimiento"};

        double[][] acumulados = new double[3][2];     
        double[][] acumuladosPap = new double[3][1];  
        double [][] acumuladosUnif = new double[3][4];
        boolean[] mantPagado = new boolean[3];
    
        double deuda_total = 0;
        //parte de la logica para asignar saldo en base a un usuario
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;

        int nivelEducativo = -1;
        double monto = 0;
       
        // menu principal
        while (true) {
            String[] nivelesEducativos = {
                "Preescolar",
                "Primaria",
                "Secundaria",
                "Finalizar y ver resumen"
            };

            int entrada = Main.menuVentana(sc, "Seleccione el nivel educativo", nivelesEducativos);

            if (entrada == 4) break;

            if (entrada < 1 || entrada > 4) {
                System.out.println(">> Nivel educativo inválido");
                continue;
            }

            nivelEducativo = entrada - 1; // nos indica la fila de la matriz tarifas

            while (true) {
                String[] opcionesCobrosAnuales = {
                    "Inscripciones",
                    "Mantenimiento",
                    "Opcionales",
                    "Cambiar nivel educativo" 
                };

                int opcion = Main.menuVentana(sc, "Menú de cobros anuales: " + nombresNivel[nivelEducativo], opcionesCobrosAnuales);

                if (opcion == 4) break;
                
                switch (opcion) {
                    case 1:
                        int tipo = 0; // nos indica la columna de la matriz tarifas
                        monto = tarifas[nivelEducativo][tipo];

                        System.out.println(Color.RED + "El alumno será inscrito en el nivel educativo " + nombresNivel[nivelEducativo] + ", el precio es de: " + tarifas[nivelEducativo][tipo] + "$" + Color.RESET);
                        System.out.print("Ingrese el nombre del alumno: ");
                        String nombreAlumno = sc.nextLine().toLowerCase();

                        if (!Main.confirmarPago(sc)) break;
                        
                        int indiceAlumno = registrarAlumno(nivelEducativo, nombreAlumno);
                        if (indiceAlumno == -1) break;
                        
                        if (Main.procesarCobro(monto, saldo_disponible, "Inscripción")) {
                            acumulados[nivelEducativo][tipo] += monto;
                            saldo_disponible -= monto;
                            System.out.println("Cobro inscripción: " + monto + "$");
                            System.out.println("El alumno ha sido inscrito correctamente.");
                        }
                        else {
                            DataManager.alumnosInscritos[nivelEducativo][indiceAlumno] = null; 
                            // aqui lo desinscribimos si procesarCobro es false
                        }
                        break;
                    case 2:
                        if (mantPagado[nivelEducativo]) {
                                System.out.println();
                                System.out.println("Usted ya ha pagado el mantenimiento en el nivel educativo: " + nombresNivel[nivelEducativo] + ".");
                                break;
                            }
                        tipo = 1;
                        monto = tarifas[nivelEducativo][tipo];

                        System.out.println(Color.RED + "╔══════════════════════════════════════════════════════════════╗" + Color.RESET);
                        System.out.print(Color.RED + "║" + Color.RESET);
                        System.out.printf("%-62s", String.format("%" + ((62 + "Cobro de Mantenimiento".length()) / 2) + "s", "Cobro de Mantenimiento"));
                        System.out.println(Color.RED + "║" + Color.RESET);
                        System.out.println(Color.RED + "╠══════════════════════════════════════════════════════════════╣" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", "El mantenimiento es un cobro anual, contribuye a:");
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", "- Limpieza y sanitización");
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", "- Reparaciones menores");
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", "- Mantenimiento de áreas verdes");
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", "- Reparación y reposición de mobiliario");
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", "- Mantenimiento de equipos");
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.print(Color.BLUE + "║  " + Color.RESET);
                        System.out.printf("%-59s", String.format("El monto total anual a pagar es de $%.2f", monto));
                        System.out.println(Color.BLUE + " ║" + Color.RESET);
                        System.out.println(Color.BLUE + "╚══════════════════════════════════════════════════════════════╝" + Color.RESET);

                        if (Main.confirmarPago(sc)) {
                            if (Main.procesarCobro(monto, saldo_disponible, "Mantenimiento")) {
                                acumulados[nivelEducativo][tipo] += monto;
                                saldo_disponible -= monto;
                                mantPagado[nivelEducativo] = true;
                                System.out.printf("Pago de mantenimiento registrado a nivel educativo %s por $%.2f\n", nombresNivel[nivelEducativo], monto);           
                                }
                            }
                        break;

                    case 3:
                        while (true) {
                            String tituloOpcionales = "Opcionales - " + nombresNivel[nivelEducativo];
                            String[] opcionesOpcionales = {
                                "Papelería",
                                "Uniformes",
                                "Volver"
                            };

                            int opc = Main.menuVentana(sc, tituloOpcionales, opcionesOpcionales);
                            if (opc == 3) break;

                            switch (opc) {
                                case 1: 
                                    while (true) {
                                        String titulo = "Papelería";
                                        String[] opcionesPap = {
                                            String.format("%s ($%.2f)", nombresPapeleria[0], preciosPapeleria[0]),
                                            String.format("%s ($%.2f)", nombresPapeleria[1], preciosPapeleria[1]),
                                            String.format("%s ($%.2f)", nombresPapeleria[2], preciosPapeleria[2]),
                                            String.format("%s ($%.2f)", nombresPapeleria[3], preciosPapeleria[3]),
                                            String.format("%s ($%.2f)", nombresPapeleria[4], preciosPapeleria[4]),
                                            String.format("%s ($%.2f)", nombresPapeleria[5], preciosPapeleria[5]),
                                            String.format("%s ($%.2f)", nombresPapeleria[6], preciosPapeleria[6]),
                                            String.format("%s ($%.2f)", nombresPapeleria[7], preciosPapeleria[7]),
                                            "Volver al menú anterior"
                                        };
                                        int opcionpap = Main.menuVentana(sc, titulo, opcionesPap);

                                        if (opcionpap == 9) break;

                                        if (opcionpap < 1 || opcionpap > nombresPapeleria.length) {
                                            System.out.println(">> Opción inválida");
                                            continue;
                                        }

                                        int indice = opcionpap - 1;
                                        double montoPap = preciosPapeleria[indice];

                                        if (Main.confirmarPago(sc)) {
                                            if (Main.procesarCobro(montoPap, saldo_disponible, nombresPapeleria[indice])) {
                                                acumuladosPap[nivelEducativo][0] += montoPap;  
                                                saldo_disponible -= montoPap;
                                                System.out.printf("  Compraste: %s por $%.2f\n", nombresPapeleria[indice], montoPap);
                                            }
                                        }
                                    }
                                    break;
                                case 2: 
                                    while (true) {
                                        String titulo = "Elija un uniforme: " + nombresNivel[nivelEducativo];
                                        String[] opcionesUni = {
                                            String.format("%s ($%.2f)", nombreUniformes[0], TarifaUnif[nivelEducativo][0]),
                                            String.format("%s ($%.2f)", nombreUniformes[1], TarifaUnif[nivelEducativo][1]),
                                            String.format("%s ($%.2f)", nombreUniformes[2], TarifaUnif[nivelEducativo][2]),
                                            String.format("%s ($%.2f)", nombreUniformes[3], TarifaUnif[nivelEducativo][3]),
                                            "Volver al menú anterior"
                                        };
                                        int opcionUnif = Main.menuVentana(sc, titulo, opcionesUni);

                                        if (opcionUnif == 5) break;

                                        if (opcionUnif < 1 || opcionUnif > nombreUniformes.length) {
                                            System.out.println(">> Opción inválida");
                                            continue;
                                        }

                                        int indice = opcionUnif - 1;
                                        double montoUniformes = TarifaUnif[nivelEducativo][indice];

                                        if (Main.confirmarPago(sc)){
                                            if (Main.procesarCobro(montoUniformes, saldo_disponible, nombreUniformes[indice])) {
                                                acumuladosUnif[nivelEducativo][indice] += montoUniformes;
                                                saldo_disponible -= montoUniformes;
                                                System.out.printf("Has comprado: %s por $%.2f\n", nombreUniformes[indice], montoUniformes);
                                            }
                                        }    
                                    }
                                break; // cierra case 2
                                default:
                                    System.out.println(">> Opción inválida");
                            }

                                if (opc == 0) break; // cierra el while del menu de opcionales
                            }
                                break; // sale del switch(opcion)

                        default:
                        System.out.println(">> Opción inválida");
                }
            }
        }
    System.out.println("\n=== RECIBO GENERAL ===");
        for (int i = 0; i < 3; i++) {
            if (!checarCobros(acumulados, i) && 
                !checarCobros(acumuladosPap, i) &&
                !checarCobros(acumuladosUnif, i)) continue;

            System.out.println("Nivel educativo: " + nombresNivel[i]);

            
            for (int j = 0; j < acumulados[i].length; j++) {
                if (acumulados[i][j] > 0) {
                    System.out.printf("  %-14s $%.2f\n", nombresOpciones[j] + ":", acumulados[i][j]);
                    deuda_total += acumulados[i][j];
                }
            }

            if (acumuladosPap[i][0] > 0) {
                System.out.printf("  %-14s $%.2f\n", "Papelería:", acumuladosPap[i][0]);
                deuda_total += acumuladosPap[i][0];
            }

            for (int j = 0; j < acumuladosUnif[i].length; j++) {
                if (acumuladosUnif[i][j] > 0) {
                    System.out.printf("  %-14s $%.2f\n", nombreUniformes[j] + ":", acumuladosUnif[i][j]);
                    deuda_total += acumuladosUnif[i][j];
                }
            }
}


        System.out.printf("Saldo inicial:    $%.2f\n", saldo_inicial);
        System.out.printf("Total a pagar:    $%.2f\n", deuda_total);
        System.out.printf("Saldo restante:   $%.2f\n", saldo_disponible);

        
        DataManager.saldos[DataManager.usuarioActual] = saldo_disponible;
        Main.Esperar(2);
        Main.mostrarMenu();

    }
        // funcion para verificar si hay cupo en la escuela (en la matriz)
        public static int registrarAlumno(int nivel, String nombre) {
            for (int i = 0; i < DataManager.alumnosInscritos[nivel].length; i++) {
                if (DataManager.alumnosInscritos[nivel][i] == null) {
                    DataManager.alumnosInscritos[nivel][i] = nombre;
                    return i; 
                }
            }
            System.out.println("No hay cupo para más alumnos.");
            return -1;
        }

        // es simplemente para saber si hay cobros o no, cuando recorremos todas las matrices/arreglos
        public static boolean checarCobros(double[][] matriz, int fila) {
            for (int j = 0; j < matriz[fila].length; j++) {
                if (matriz[fila][j] > 0) {
                    return true;
                }
            }
            return false;
        }
}
