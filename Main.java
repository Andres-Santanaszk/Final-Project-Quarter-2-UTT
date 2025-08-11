import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

import utils.AsciiArt;
import utils.Color;

public class Main {

    public static double saldoInicial = -1;

    public static void main(String[] args) {
        Login.main(null);
        saldoInicial = DataManager.saldos[DataManager.usuarioActual];
        mostrarMenu();
    }

    public static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        Esperar(1);
        Color.mostrarAscii(AsciiArt.COLEGIO_NOMBRE);
        Esperar(1);

        while (true) {
            String[] opciones = {
                "Pagos anuales (inscripciones, mantenimiento)",
                "Pago de mensualidades",
                "Eventos especiales",
                "Consulta de saldo",
                "Cambiar de usuario",
                "Salir del programa e imprimir recibo total de la sesión"
            };

            int opcion = menuVentana(sc, "Menú principal", opciones);

        switch (opcion) {
            case 1:
                CobrosAnuales.main(null);
                break;
            case 2:
                CobroMensualidades.main(null);
                break;
            case 3:
                EventosEspeciales.main(null);
                break;
            case 4:
                System.out.println(Color.GREEN + "Su saldo es de " + saldo_disponible + "$");
                break;
            case 5:
                generarReciboFinal();
                Main.main(null);
                break;
            case 6:
                System.out.println(Color.RED + "Saliendo del sistema...." + Color.RESET);
                generarReciboFinal();
                System.exit(0);
            default:
                System.out.println("No se ingresó una opción valida.");
                break;
            }
        }
    }

    // funcion que devuelve si es posible completar el pago en base al saldo disponible y el precio del producto
    public static boolean procesarCobro(double precio, double saldo, String concepto) {
        if (saldo < precio) {
            System.out.println("Lo sentimos, no tienes el saldo suficiente para " + concepto);
            return false;
        }
        return true;
    }

    // es para esperar determinado tiempo en segundos
    public static void Esperar(int segundos) {
        try {
        Thread.sleep(segundos * 1000); 
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
    }
    
    public static boolean confirmarPago(Scanner sc) {
        while (true) {
            System.out.print("¿Desea proceder con el pago? (si/no): ");
            String respuesta = sc.nextLine().trim().toLowerCase();

            if (respuesta.equals("si")) return true;
            
            else if (respuesta.equals("no")) return false;
            
            else{
                System.out.println(">> Respuesta inválida, intenta de nuevo. Escribe 'si' o 'no'");
            }
        }
    }

    // funcion que verifica que se introduzca un numero entero, no una letra u otro caracter
    public static int verificarInt(Scanner sc, String mensaje) {
        int valor;
        while (true) {
            System.out.print(mensaje);
                try {
                valor = sc.nextInt();
                sc.nextLine(); // si no hago esto simplemente no funciona
                return valor;
            } catch (InputMismatchException e) {
                System.out.println(">> Respuesta inválida, intenta de nuevo.");
                sc.nextLine(); 
                // el programa se queda en un bucle infinito, como me ha pasado en ocasiones anteriores
            }
        }
    }

    // funcion para convertir arrays de opciones a menus
    public static int menuVentana(Scanner sc, String titulo, String[] opciones) {
        System.out.println(Color.RED + "╔══════════════════════════════════════════════════════════════╗" + Color.RESET);
        
        System.out.print(Color.RED + "║" + Color.RESET);
        System.out.printf("%-62s", String.format("%" + ((62 + titulo.length()) / 2) + "s", titulo));
        System.out.println(Color.RED + "║" + Color.RESET);
        
        System.out.println(Color.RED + "╠══════════════════════════════════════════════════════════════╣" + Color.RESET);
        
        
        for (int i = 0; i < opciones.length; i++) {
            System.out.print(Color.BLUE + "║  " + Color.RESET); 
            System.out.printf("%-2s %-56s", (i + 1) + ".", opciones[i]); 
            System.out.println(Color.BLUE + " ║" + Color.RESET); 
        }
        
        System.out.println(Color.BLUE + "╚══════════════════════════════════════════════════════════════╝" + Color.RESET);
        
        return verificarInt(sc, ">> ");
    }

    public static void generarReciboFinal() {
        double saldoFinal = DataManager.saldos[DataManager.usuarioActual];
        double totalGastado = saldoInicial - saldoFinal;

        // si no se gasto nada, entonces no se imprime el recibo.
        if (totalGastado <= 0) {
            System.out.println("No se realizaron gastos en esta sesión. No se generará recibo.");
            return;
        }
        String usuario = DataManager.usuarios[DataManager.usuarioActual][0];
        String nombreArchivo = "recibo_usuario_" + usuario + ".txt";

        try (PrintWriter writer = new PrintWriter(nombreArchivo, StandardCharsets.UTF_8)) {
            writer.println("======================================");
            writer.println("        RECIBO DE TRANSACCIONES");
            writer.println("======================================");
            writer.println("Usuario: " + usuario);
            writer.println("--------------------------------------");
            writer.printf("Saldo Inicial:  $%,.2f%n", saldoInicial);
            writer.printf("Saldo Final:    $%,.2f%n", saldoFinal);
            writer.println("--------------------------------------");
            writer.printf("TOTAL GASTADO:  $%,.2f%n", totalGastado);
            writer.println("======================================");
            writer.println("\nGracias por su preferencia.");

            System.out.println(Color.GREEN + "\nRecibo generado exitosamente como '" + nombreArchivo + "'" + Color.RESET);

        } catch (IOException e) {
            System.err.println("Error al generar el recibo: " + e.getMessage());
        }
    }

}