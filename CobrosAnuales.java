import java.util.Scanner;

public class CobrosAnuales {

    public static void main(String[] args) {

        // declaracion
        Scanner sc = new Scanner(System.in);

        
        double[][] tarifas = {
            {800, 400},  // kinder
            {1000, 500}, // primaria
            {1200, 600}   // secundaria
        };

        double[][] TarifaUnif = {
            {1600, 1600, 1300, 1300},
            {1800, 180, 1390,1390},
            {2170, 2170, 1575, 1575}
        };

        String[] nombresPapeleria = {
            "Cuadernos",
            "Libro de inglés",
            "Colores (12 piezas)",
            "Lápices (paquete de 6)",
            "Plumas (paquete de 6)",
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
            45.40,
            50.60,
            10.75,
            15.40,
            12.20,
            180.30,
            25.30
        };

        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[] nombreUniformes = {"Formal masculino", "Formal femenino", 
                                    "Deportivo masuclino", "Deportivo femenino"};
        
        double[][] acumulados = new double[3][2];     
        double[][] acumuladosPap = new double[3][2];  
        double [][] acumuladosUnif = new double[3][4];

        double deuda_total = 0;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;

        int nivelEducativo = 0;
        double monto = 0;

        while (true) {
            System.out.println("\nSeleccione el nivel educativo:");
            System.out.println("1. Preescolar");
            System.out.println("2. Primaria");
            System.out.println("3. Secundaria");
            System.out.println("0. Finalizar y ver resumen");
            System.out.print("Nivel educativo: ");
            int entrada = sc.nextInt();

            if (entrada == 0) break;

            if (entrada < 1 || entrada > 3) {
                System.out.println(">> Nivel educativo inválido");
                continue;
            }

            nivelEducativo = entrada - 1;

            while (true) {
                System.out.println("\n=== MENÚ COBROS ANUALES [" + nombresNivel[nivelEducativo] + "] ===");
                System.out.println("1. Cobro inscripción");
                System.out.println("2. Cobro mantenimiento");
                System.out.println("3. Cobro opcionales");
                System.out.println("0. Cambiar nivel educativo");
                System.out.print("Opción: ");
                int opcion = sc.nextInt();

                if (opcion == 0) break;

                switch (opcion) {
                    case 1:
                        int tipo = 0;
                        monto = tarifas[nivelEducativo][tipo];
                        if (procesarCobro(monto, saldo_disponible, "Inscripción")){
                            acumulados[nivelEducativo][tipo] += monto;
                            saldo_disponible -= monto;
                            System.out.println("Cobro inscripción: " + tarifas[nivelEducativo][tipo] + "$");
                        }
                        break;
                    case 2:
                        tipo = 1;
                        monto = tarifas[nivelEducativo][tipo];
                        if (procesarCobro(monto, saldo_disponible, "Mantenimiento")){
                        acumulados[nivelEducativo][tipo] += monto;
                        saldo_disponible -= monto;
                        System.out.println("Cobro mantenimiento: " + nombresNivel[nivelEducativo] + " " + monto + "$");
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.println("\n  -- OPCIONALES [" + nombresNivel[nivelEducativo] + "] --");
                            System.out.println("  1. Papelería:");
                            System.out.println("  2. Uniformes:" );
                            System.out.println("  0. Volver");
                            System.out.print(">> ");
                            int opc = sc.nextInt();

                            switch (opc) {
                                case 0:
                                    break;
                                case 1: 
                                        while (true) {
                                            System.out.println("\n  -- Papelería --");
                                            for (int i = 0; i < nombresPapeleria.length; i++) {
                                                System.out.printf("%d.- %s ($%.2f)\n", (i + 1), nombresPapeleria[i], preciosPapeleria[i]);
                                            }
                                            System.out.println("0.- Volver al menú anterior");
                                            System.out.print(">> ");
                                            int opcionpap = sc.nextInt();

                                            if (opcionpap == 0) break;

                                            if (opcionpap < 1 || opcionpap > nombresPapeleria.length) {
                                                System.out.println(">> Opción inválida");
                                                continue;
                                            }

                                            int index = opcionpap - 1;
                                            double montoPap = preciosPapeleria[index];

                                            if (procesarCobro(montoPap, saldo_disponible, nombresPapeleria[index])) {
                                                acumuladosPap[nivelEducativo][0] += montoPap;  
                                                saldo_disponible -= montoPap;
                                                System.out.printf("  Compraste: %s por $%.2f\n", nombresPapeleria[index], montoPap);
                                            }
                                        }
                                        break;
                                case 2: 
                                    while (true) {
                                        System.out.println("\n  -- Elija un uniforme --  [" + nombresNivel[nivelEducativo] + "]");
                                        for (int i = 0; i < nombreUniformes.length; i++) {
                                            System.out.printf("%d.- %s ($%.2f)\n", (i + 1), nombreUniformes[i], TarifaUnif[nivelEducativo][i]);
                                        }
                                        System.out.println("0.- para volver al menú anterior");
                                        System.out.print(">> ");
                                        int opcionUnif = sc.nextInt();

                                        if (opcionUnif == 0) break;

                                        if (opcionUnif < 1 || opcionUnif > nombreUniformes.length) {
                                            System.out.println(">> Opción inválida");
                                            continue;
                                        }

                                        int index = opcionUnif - 1;
                                        double montoUniformes = TarifaUnif[nivelEducativo][index];

                                        if (procesarCobro(montoUniformes, saldo_disponible, nombreUniformes[index])) {
                                            acumuladosUnif[nivelEducativo][index] += montoUniformes;
                                            saldo_disponible -= montoUniformes;
                                            System.out.printf("  Cobraste: %s por $%.2f\n", nombreUniformes[index], montoUniformes);
                                        }
                                    }
                                break;
                                default:
                                    System.out.println("  >> Opción inválida");
                            }

                                if (opc == 0) break; 
                            }
                            break;

                    default:
                        System.out.println(">> Opción inválida");
                }
            }
        }
    System.out.println("\n=== RECIBO GENERAL ===");
    for (int i = 0; i < 3; i++) {
        boolean tieneCobros = ChecarCobros(acumulados, i) ||
                            ChecarCobros(acumuladosPap, i) ||
                            ChecarCobros(acumuladosUnif, i);

        if (!tieneCobros) continue;

        System.out.println("Nivel educativo: " + nombresNivel[i]);

        if (acumulados[i][0] > 0) {
            System.out.printf("  Inscripción:   $%.2f\n", acumulados[i][0]);
            deuda_total += acumulados[i][0];
        }
        if (acumulados[i][1] > 0) {
            System.out.printf("  Mantenimiento: $%.2f\n", acumulados[i][1]);
            deuda_total += acumulados[i][1];
        }
        if (acumuladosPap[i][0] > 0) {
            System.out.printf("  Papelería:     $%.2f\n", acumuladosPap[i][0]);
            deuda_total += acumuladosPap[i][0];
        }
        for (int j = 0; j < 4; j++) {
            if (acumuladosUnif[i][j] > 0) {
                System.out.printf("  Uniformes:     $%.2f\n", acumuladosUnif[i][j]);
                deuda_total += acumuladosUnif[i][j];
            }
        }
    }

    System.out.printf("Saldo inicial:    $%.2f\n", saldo_inicial);
    System.out.printf("Total a pagar:    $%.2f\n", deuda_total);
    System.out.printf("Saldo restante:   $%.2f\n", saldo_disponible);

    // ya actualizado:
    DataManager.saldos[DataManager.usuarioActual] = saldo_disponible;
        Main.mostrarMenu();

    }

        public static boolean procesarCobro(double precio, double saldo, String concepto) {
        if (saldo < precio) {
            System.out.println("Lo sentimos, no tienes el saldo suficiente para " + concepto);
            return false;
        }
        return true;
    }

        public static boolean ChecarCobros(double[][] matriz, int fila) {
            for (int j = 0; j < matriz[fila].length; j++) {
                if (matriz[fila][j] > 0) {
                    return true;
                }
            }
            return false;
        }
}
