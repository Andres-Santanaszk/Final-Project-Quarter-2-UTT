import java.util.Scanner;

public class CobroMensualidades {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[] nombresMeses = {
            "", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };

        // Costos base por nivel
        double[] costosBase = {2500.0, 2700.0, 3000.0};

        // Matriz de acumulados [nivel][mes]
        double[][] acumulados = new double[3][11]; 
        boolean[][] pagado = new boolean[3][11];

        double deuda_total = 0;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;

        while (true) {
            System.out.println("\nSeleccione el nivel educativo:");
            System.out.println("1. Preescolar");
            System.out.println("2. Primaria");
            System.out.println("3. Secundaria");
            System.out.println("0. Finalizar y ver resumen");
            int entrada = Main.verificarInt(sc, ">> ");

            if (entrada == 0) break;

            if (entrada < 1 || entrada > 3) {
                System.out.println(">> Nivel educativo inválido");
                continue;
            }

            int nivel = entrada - 1;

            while (true) {
                System.out.println("\n=== MENÚ MENSUALIDADES [" + nombresNivel[nivel] + "] ===");
                for (int i = 1; i <= 10; i++) {
                    String textoAdicional;
                        if (pagado[nivel][i]) {
                            textoAdicional = "(Pagado)";
                        } else {
                            textoAdicional = "";
                        }
                        System.out.printf("%d. %s %s\n", i, nombresMeses[i], textoAdicional);
                }
                System.out.println("0. Cambiar nivel educativo");
                int mes = Main.verificarInt(sc, ">> ");

                if (mes == 0) break;

                if (mes < 1 || mes > 10) {
                    System.out.println(">> Mes inválido");
                    continue;
                }

                if (pagado[nivel][mes]) {
                    System.out.println("Este mes ya fue pagado.");
                    continue;
                }

                double costo;
                if (mes == 3 || mes == 10) {
                    costo = costosBase[nivel] * 2;
                } else {
                    costo = costosBase[nivel];
                }

                if (Main.procesarCobro(costo, saldo_disponible, "Mensualidad " + nombresMeses[mes])) {
                    acumulados[nivel][mes] += costo;
                    pagado[nivel][mes] = true;
                    saldo_disponible -= costo;
                    System.out.printf("Pagaste %s por $%.2f\n", nombresMeses[mes], costo);
                }
            }
        }

        // RESUMEN FINAL
        System.out.println("\n=== RECIBO GENERAL ===");
        for (int i = 0; i < 3; i++) {
            boolean tieneCobros = CobrosAnuales.checarCobros(acumulados, i);
            if (!tieneCobros) continue;

            System.out.println("Nivel educativo: " + nombresNivel[i]);
            for (int mes = 1; mes <= 10; mes++) {
                if (acumulados[i][mes] > 0) {
                    System.out.printf("  %s: $%.2f\n", nombresMeses[mes], acumulados[i][mes]);
                    deuda_total += acumulados[i][mes];
                }
            }
        }

        System.out.printf("Saldo inicial:    $%.2f\n", saldo_inicial);
        System.out.printf("Total a pagar:    $%.2f\n", deuda_total);
        System.out.printf("Saldo restante:   $%.2f\n", saldo_disponible);

        DataManager.saldos[DataManager.usuarioActual] = saldo_disponible;
        Main.mostrarMenu();
    }

}
