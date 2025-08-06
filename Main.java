import java.util.InputMismatchException;
import java.util.Scanner;

import utils.AsciiArt;
import utils.Color;

public class Main {
    public static void main(String[] args) {
        Login.main(null);
        mostrarMenu();
    }

    public static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        Esperar(1);
        Color.mostrarAscii(AsciiArt.COLEGIO_NOMBRE);

        while (true) {
        System.out.println(Color.RED + "╔════════════════════════════════════════════╗" + Color.RESET);
    System.out.println(Color.RED + "║         Colegio Independencia              ║" + Color.RESET);
    System.out.println(Color.RED + "╠════════════════════════════════════════════╣" + Color.RESET);
    System.out.print(Color.BLUE);
    System.out.printf("║  %-2s %-38s ║\n", "1.", "Cobros anuales");
    System.out.printf("║  %-2s %-38s ║\n", "2.", "Pago de mensualidades");
    System.out.printf("║  %-2s %-38s ║\n", "3.", "Eventos especiales");
    System.out.printf("║  %-2s %-38s ║\n", "4.", "Consulta de saldo");
    System.out.printf("║  %-2s %-38s ║\n", "5.", "Cambiar de usuario");
    System.out.printf("║  %-2s %-38s ║\n", "6.", "Cerrar sesión");
    System.out.print(Color.RESET); 
    System.out.println(Color.RED + "╚════════════════════════════════════════════╝" + Color.RESET);
    int opcion = verificarInt(sc, ">> ");

    
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
                System.out.println("Su saldo es de " + saldo_disponible + "$");
                break;
            case 5:
                Main.main(null);
                break;
            case 6:
                Main.main(null);
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

    // funcion que verifica que se introduzca un numero entero, no una letra u otro caracter
        public static int verificarInt(Scanner sc, String mensaje) {
            int valor;
            while (true) {
                System.out.print(mensaje);
                try {
                    valor = sc.nextInt();
                    return valor;
                } catch (InputMismatchException e) {
                    System.out.println("  >> Entrada inválida.");
                    sc.nextLine(); // si no hago esto simplemente no funciona
                    // el programa se queda en un bucle infinito, como me ha pasado en ocasiones anteriores
                }
            }
        }

}