import java.util.Arrays;
import java.util.Scanner;

public class CobroMensualidades {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Declaracion de variables y listas
        String[] nombresNivel = {"Preescolar", "Primaria", "Secundaria"};
        String[] nombresMeses = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Septiembre", "Octubre", "Noviembre", "Diciembre", "Salir al menu anterior"
        };

        // costos de las mensualidades
        double[] costosBase = {2500.0, 2700.0, 3000.0};

        double[][] acumulados = new double[3][11]; 

        double deuda_total = 0;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;
        // Aqui te pide que selecciones el nivel educativo
        while (true) {
            String[] nivelesEducativos = {
                "Preescolar",
                "Primaria",
                "Secundaria",
                "Finalizar y ver resumen"
            };

            int entrada = Main.menuVentana(sc, "Menu de mensualidades", nivelesEducativos);

            if (entrada == 4) break;

                if (entrada < 1 || entrada > 3) {
            System.out.println("Nivel educativo inválido");
            continue;
            }

            int nivel = entrada -1;
                //Te pide el nombre del alumno y checa si esta o no inscrito
                    System.out.print("Ingresa el nombre del alumno al que deseas pagar la mensualidad: ");
            String nombreIngresado = sc.nextLine().trim();

            boolean inscrito = Arrays.asList(DataManager.alumnosInscritos[nivel]).contains(nombreIngresado);

            if (!inscrito) {
                System.out.println("Alumno no esta inscrito. Por favor, inscríbelo primero.");
                continue;
            }

            System.out.println("Alumno seleccionado: " + nombreIngresado);

                        while (true) {

                
                String[] opcionesMeses = new String[nombresMeses.length];
                
                for (int i = 0; i < nombresMeses.length; i++) {

    
                    if (i == nombresMeses.length - 1) {
                        opcionesMeses[i] = nombresMeses[i];
                        continue;
                    }

                    double mensualidad = costosBase[nivel];
                    
                    // Aqui es donde si el seleccionas marzo o abril el costo de estos meses es del doble
                    if (nombresMeses[i].equals("Marzo") || nombresMeses[i].equals("Diciembre")) {
                        mensualidad *= 2;
                    }

                    opcionesMeses[i] = String.format("%s ($%.2f)", nombresMeses[i], mensualidad);
                }
                    
                int mes = Main.menuVentana(sc, "Mensualidades de " + nombresNivel[nivel], opcionesMeses);
                    
                // Si selecciona 11 pues te manda al menu anterior
                if (mes == nombresMeses.length) {
                    break; 
                }

                if (mes < 1 || mes > nombresMeses.length) {
                    System.out.println(">> Mes inválido");
                    continue;
                }

                // Le reste uno para que el indice iniciara bien
                int mesIndex = mes - 1;

                boolean yaPagado = false;

                     for (int i = 0; i < DataManager.alumnosInscritos[nivel].length;i++){
                        String registro = DataManager.alumnosInscritos[nivel][i];
                            if (registro != null && registro.equalsIgnoreCase(nombreIngresado + "-" + nombresMeses[mesIndex])){
                        yaPagado = true;
                        break;
                        }
                    }
                        // Aqui te dice si el mes ya fue pagado para que no se pueda volver a pagar
                    if (yaPagado) {
                        System.out.println("Este mes ya fue pagado.");
                        continue;
                    }


                double costo;
                if (nombresMeses[mesIndex].equals("Marzo") || nombresMeses[mesIndex].equals("Diciembre")) {
                    costo = costosBase[nivel] * 2;
                    System.out.println("Los meses de Marzo y Diciembre tiene un costo extra por las vacaciones de verano(Julio y Agosto)");
                } else {
                    costo = costosBase[nivel];
                }
                    // Llama la funcion solo para confirmar el pago
                if (Main.confirmarPago(sc)) {
                    if (Main.procesarCobro(costo, saldo_disponible, "Mensualidad " + nombresMeses[mesIndex])) {
                        for (int i = 0; i < DataManager.alumnosInscritos[nivel].length; i++){
                            if (DataManager.alumnosInscritos[nivel][i] == null){
                            DataManager.alumnosInscritos[nivel][i] = nombreIngresado + "-" + nombresMeses[mesIndex];
                            break;
                            }
                        }
                        
                        acumulados[nivel][mesIndex] += costo;
                        saldo_disponible -= costo;
                        System.out.printf("Pagaste %s por $%.2f\n", nombresMeses[mesIndex], costo);
                    }
                }
            }
        }

        // Aqui se muestran las mensualidades que pago, cuando gasto en total y el saldo que le queda
        System.out.println("\n=== RECIBO GENERAL ===");
            for (int i = 0; i < 3; i++) {
                boolean tieneCobros = CobrosAnuales.checarCobros(acumulados, i);
                if (!tieneCobros) continue;

                System.out.println("Nivel educativo: " + nombresNivel[i]);
                for (int mes = 0; mes <= 10; mes++) {
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
