import java.util.Scanner;

public class EventosEspeciales {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[][] nombresEventos = {
            {"Festival de Navidad", "Día del Niño", "Graduación", "Viaje Escolar"},
            {"Festival de Navidad", "Talleres Especiales", "Graduación", "Fotos Escolares"},
            {"Viaje de Generación", "Campamento de Verano", "Graduación", "Fotos Escolares"}
        };

        double[][] costos = DataManager.costoEventos();  // [nivel][evento]
        double[][] acumulados = new double[3][4];         // [nivel][evento]
        boolean[][] pagado = new boolean[3][4];           // [nivel][evento]

        double deuda_total = 0;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;

        while (true) {
            System.out.println("\n=== MENÚ DE EVENTOS ESPECIALES ===");
            System.out.println("Seleccione el nivel educativo:");
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
                System.out.println("\n=== EVENTOS [" + nombresNivel[nivel] + "] ===");
                for (int i = 0; i < 4; i++) {
                    String estado;
                        if (pagado[nivel][i]) {
                            estado = "(Pagado)";
                        } else {
                            estado = "";
                        }
                    System.out.printf("%d. %s %s\n", i + 1, nombresEventos[nivel][i], estado);
                }
                System.out.println("0. Cambiar nivel educativo");
                int opcion = Main.verificarInt(sc, ">> ");

                if (opcion == 0) break;

                if (opcion < 1 || opcion > 4) {
                    System.out.println(">> Opción inválida");
                    continue;
                }

                int evento = opcion - 1;

                if (pagado[nivel][evento]) {
                    System.out.println("Este evento ya fue pagado.");
                    continue;
                }

                double costo = costos[nivel][evento];
                String nombreEvento = nombresEventos[nivel][evento];
                if (Main.confirmarPago(sc)) {
                    if (Main.procesarCobro(costo, saldo_disponible, "Evento: " + nombreEvento)) {
                        acumulados[nivel][evento] += costo;
                        pagado[nivel][evento] = true;
                        saldo_disponible -= costo;
                        System.out.printf("Pagaste '%s' por $%.2f\n", nombreEvento, costo);
                    }
                }
            }
        }

        // RESUMEN FINAL
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
