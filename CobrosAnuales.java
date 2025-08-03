import java.util.Scanner;

public class CobrosAnuales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Niveles: 0-Preescolar, 1-Primaria, 2-Secundaria
        // Tipos: 0-Inscripción, 1-Mantenimiento
        double[][] tarifas = {
            {800.0, 400.0},   // Preescolar
            {1000.0, 500.0},  // Primaria
            {1200.0, 600.0}   // Secundaria
        };

        // Opcionales: 0-Papelería, 1-Uniformes
        double[][] tarifasOpc = {
            {150.0, 300.0},   // Preescolar
            {200.0, 350.0},   // Primaria
            {250.0, 400.0}    // Secundaria
        };

        // Acumuladores de cobros
        double[][] acumulados = new double[3][2];       // [nivel][tipo]
        double[][] acumuladosOpc = new double[3][2];    // [nivel][opcional]
        int deuda_total = 0;
        int indiceUsuario = DataManager.usuarioActual;
        double saldo_disponible = DataManager.saldos[indiceUsuario];

        while (true) {
            System.out.println("\n=== MENÚ COBROS ANUALES ===");
            System.out.println("1. Cobro inscripción");
            System.out.println("2. Cobro mantenimiento");
            System.out.println("3. Cobro opcionales");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();

            if (opcion == 0) break;

            int nivel = seleccionarNivel(sc); // indice del nivel educativo

            switch (opcion) {
                case 1:
                case 2:
                    int tipo = opcion - 1;  // parte del indice
                    double monto = tarifas[nivel][tipo];
                    if (tipo == 0) {
                    System.out.printf("Cobro %s [%s]: $%.2f\n", "inscripción", obtenerNombreNivel(nivel), monto);
                    }
                    else {
                        System.out.printf("Cobro %s [%s]: $%.2f\n", "Mantenimiento", obtenerNombreNivel(nivel), monto);
                    }
                    acumulados[nivel][tipo] += monto;
                    break;

                case 3:
                    while (true) {
                        System.out.println("\n  -- OPCIONALES [" + obtenerNombreNivel(nivel) + "] --");
                        System.out.println("  1. Papelería: $" + String.format("%.2f", tarifasOpc[nivel][0]));
                        System.out.println("  2. Uniformes: $" + String.format("%.2f", tarifasOpc[nivel][1]));
                        System.out.println("  0. Volver");
                        System.out.print("  Opción: ");
                        int opc = sc.nextInt();

                        if (opc == 0) break;

                        if (opc == 1 || opc == 2) {
                            int opcIndex = opc - 1;
                            double montoOpc = tarifasOpc[nivel][opcIndex];
                            System.out.printf("  Cobro %s [%s]: $%.2f\n",
                                opcIndex == 0 ? "papelería" : "uniformes",
                                obtenerNombreNivel(nivel), montoOpc);
                            acumuladosOpc[nivel][opcIndex] += montoOpc;
                        } else {
                            System.out.println("  >> Opción inválida");
                        }
                    }
                    break;

                default:
                    System.out.println(">> Opción inválida");
            }
        }

        // Mostrar resumen final
        System.out.println("\n=== RECIBO ===");
        for (int i = 0; i < 3; i++) {
            System.out.println("Nivel: " + obtenerNombreNivel(i));
            if (acumulados[i][0] > 0){
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
            if (acumuladosOpc[i][1] > 0) {
                System.out.printf("  Uniformes:     $%.2f\n", acumuladosOpc[i][1]);
                deuda_total += acumuladosOpc[i][1];
            }
        }
        System.out.println("Su saldo es: " + saldo_disponible
        + "\nEl total a pagar es de: " + deuda_total + "\nSaldo restante: " + (saldo_disponible - deuda_total));
        Main.mostrarMenu();
    }

    public static int seleccionarNivel(Scanner sc) {
        System.out.println("\nSeleccione nivel:");
        System.out.println("1. Preescolar");
        System.out.println("2. Primaria");
        System.out.println("3. Secundaria");
        System.out.print("Nivel: ");
        int nivel = sc.nextInt();
        return nivel - 1;
    }

    public static String obtenerNombreNivel(int nivel) {
        String[] nombres = {"Preescolar", "Primaria", "Secundaria"};
        return nombres[nivel];
    }
}
