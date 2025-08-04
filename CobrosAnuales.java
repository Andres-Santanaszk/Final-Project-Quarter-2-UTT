import java.util.Scanner;

public class CobrosAnuales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double[][] tarifas = {
            {800.0, 400.0},  
            {1000.0, 500.0},
            {1200.0, 600.0}   
        };

        double[][] tarifasOpc = {
            {150.0, 300.0},   // Preescolar
            {200.0, 350.0},   // Primaria
            {250.0, 400.0}    // Secundaria
        };

        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[] nombresOpcionales = {"papelería", "uniformes"};

        double[][] acumulados = new double[3][2];     
        double[][] acumuladosOpc = new double[3][2];  

        double deuda_total = 0;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];

        int nivel = 0;

        while (true) {
            System.out.println("\nSeleccione nivel educativo:");
            System.out.println("1. Preescolar");
            System.out.println("2. Primaria");
            System.out.println("3. Secundaria");
            System.out.println("0. Finalizar y ver resumen");
            System.out.print("Nivel: ");
            int entrada = sc.nextInt();

            if (entrada == 0) break;

            if (entrada < 1 || entrada > 3) {
                System.out.println(">> Nivel inválido");
                continue;
            }

            nivel = entrada - 1;

            while (true) {
                System.out.println("\n=== MENÚ COBROS ANUALES [" + nombresNivel[nivel] + "] ===");
                System.out.println("1. Cobro inscripción");
                System.out.println("2. Cobro mantenimiento");
                System.out.println("3. Cobro opcionales");
                System.out.println("0. Cambiar nivel");
                System.out.print("Opción: ");
                int opcion = sc.nextInt();

                if (opcion == 0) break;

                switch (opcion) {
                    case 1:
                        int tipo = 0;
                        double monto = tarifas[nivel][tipo];
                        System.out.printf("Cobro %s [%s]: $%.2f\n", "inscripción", nombresNivel[nivel], monto);
                        acumulados[nivel][tipo] += monto;
                        break;

                    case 2:
                        tipo = 1;
                        monto = tarifas[nivel][tipo];
                        System.out.printf("Cobro %s [%s]: $%.2f\n", "Mantenimiento", nombresNivel[nivel], monto);
                        acumulados[nivel][tipo] += monto;
                        break;

                    case 3:
    while (true) {
        System.out.println("\n  -- OPCIONALES [" + nombresNivel[nivel] + "] --");
        System.out.println("  1. Papelería: $" + String.format("%.2f", tarifasOpc[nivel][0]));
        System.out.println("  2. Uniformes: $" + String.format("%.2f", tarifasOpc[nivel][1]));
        System.out.println("  0. Volver");
        System.out.print("  Opción: ");
        int opc = sc.nextInt();

        switch (opc) {
            case 0:
                break;

            case 1: 
                double montoPapeleria = tarifasOpc[nivel][0];
                System.out.printf("  Cobro papelería [%s]: $%.2f\n", nombresNivel[nivel], montoPapeleria);
                acumuladosOpc[nivel][0] += montoPapeleria;
                break;

            case 2: 
                double montoUniformes = tarifasOpc[nivel][1];
                System.out.printf("  Cobro uniformes [%s]: $%.2f\n", nombresNivel[nivel], montoUniformes);
                acumuladosOpc[nivel][1] += montoUniformes;
                break;

            default:
                System.out.println("  >> Opción inválida");
        }

        if (opc == 0) break; // Salir del submenú de opcionales
    }
    break;


                    default:
                        System.out.println(">> Opción inválida");
                }
            }
        }

        System.out.println("\n=== RECIBO GENERAL ===");
        for (int i = 0; i < 3; i++) {
            boolean tieneCobros = acumulados[i][0] > 0 || acumulados[i][1] > 0 || acumuladosOpc[i][0] > 0 || acumuladosOpc[i][1] > 0;
            if (!tieneCobros) continue;  // para pasar al siguiente valor de i

            System.out.println("Nivel: " + nombresNivel[i]);

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
            if (acumuladosOpc[i][1] > 0) {
                System.out.printf("  Uniformes:     $%.2f\n", acumuladosOpc[i][1]);
                deuda_total += acumuladosOpc[i][1];
            }
        }

        double saldo_restante = saldo_disponible - deuda_total;
        System.out.println("\nSu saldo es: " + saldo_disponible);
        System.out.println("El total a pagar es de: " + deuda_total);
        System.out.println("Saldo restante: " + saldo_restante);

        DataManager.saldos[DataManager.usuarioActual] = saldo_restante;
        Main.mostrarMenu();
    }
}
