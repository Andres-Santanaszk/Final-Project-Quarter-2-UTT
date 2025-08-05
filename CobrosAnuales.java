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

        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[] nombresOpcionales = {"papelería", "uniformes"};
        String[] nombreUniformes = {"Formal masculino", "Formal femenino", 
                                    "Deportivo masuclino", "Deportivo femenino"};
        String[] itemsPapeleria = {"Libros, "};
        
        double[][] acumulados = new double[3][2];     
        double[][] acumuladosOpc = new double[3][2];  
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
                                    // double montoPapeleria = tarifasOpc[nivel][0];
                                    // System.out.printf("  Cobro papelería [%s]: $%.2f\n", nombresNivel[nivel], montoPapeleria);
                                    // acumuladosOpc[nivel][0] += montoPapeleria;
                                    break;

                                case 2: 
                                    while (true) {
                                        System.out.println("\n  -- Elija un uniforme -- " + "[" + nombresNivel[nivelEducativo] + "]");
                                        for (int i = 0; i < nombreUniformes.length; i++) {
                                            System.out.println((i + 1) + ".- " + nombreUniformes[i] + "");
                                        }
                                        System.out.println("0.- para volver al menú anterior");
                                        System.out.print(">> ");
                                        int opcion2 = sc.nextInt();
                                        if (opcion2 == 0) break;

                                        switch (opcion2) {
                                            case 1:
                                                double montoUniformes = TarifaUnif[nivelEducativo][0];
                                                System.out.printf("  Cobro uniformes [%s]: $%.2f\n", nombresNivel[nivelEducativo], montoUniformes);
                                                acumuladosUnif[nivelEducativo][0] += montoUniformes;
                                                break;

                                            case 2:
                                                montoUniformes = TarifaUnif[nivelEducativo][1];
                                                System.out.printf("  Cobro uniformes [%s]: $%.2f\n", nombresNivel[nivelEducativo], montoUniformes);
                                                acumuladosUnif[nivelEducativo][1] += montoUniformes;
                                                break;

                                            case 3:
                                                montoUniformes = TarifaUnif[nivelEducativo][2];
                                                System.out.printf("  Cobro uniformes [%s]: $%.2f\n", nombresNivel[nivelEducativo], montoUniformes);
                                                acumuladosUnif[nivelEducativo][2] += montoUniformes;
                                                break;

                                            case 4:
                                                montoUniformes = TarifaUnif[nivelEducativo][3];
                                                System.out.printf("  Cobro uniformes [%s]: $%.2f\n", nombresNivel[nivelEducativo], montoUniformes);
                                                acumuladosUnif[nivelEducativo][3] += montoUniformes;
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
                            ChecarCobros(acumuladosOpc, i) ||
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
        if (acumuladosOpc[i][0] > 0) {
            System.out.printf("  Papelería:     $%.2f\n", acumuladosOpc[i][0]);
            deuda_total += acumuladosOpc[i][0];
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
