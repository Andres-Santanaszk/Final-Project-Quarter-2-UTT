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
        Esperar(1);

        while (true) {
            String[] opciones = {
                "Cobros anuales (inscripciones, mantenimiento)",
                "Pago de mensualidades",
                "Eventos especiales",
                "Consulta de saldo",
                "Cambiar de usuario",
                "Salir del programa"
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
                System.out.println("Su saldo es de " + saldo_disponible + "$");
                break;
            case 5:
                Main.main(null);
                break;
            case 6:
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

    // funcion para convertir arrays a menus
    public static int menuVentana(Scanner sc, String titulo, String[] opciones) {
        System.out.println(Color.RED + "╔══════════════════════════════════════════════════════════════╗" + Color.RESET);
        
        System.out.print(Color.RED + "║" + Color.RESET);
        System.out.printf("%-62s", String.format("%" + ((62 + titulo.length()) / 2) + "s", titulo));
        System.out.println(Color.RED + "║" + Color.RESET);
        
        System.out.println(Color.RED + "╠══════════════════════════════════════════════════════════════╣" + Color.RESET);
        
        
        for (int i = 0; i < opciones.length; i++) {
            System.out.print(Color.BLUE + "║  " + Color.RESET); // borde izquierdo
            System.out.printf("%-2s %-56s", (i + 1) + ".", opciones[i]); // texto en blanco
            System.out.println(Color.BLUE + " ║" + Color.RESET); // borde derecho
        }
        
        System.out.println(Color.BLUE + "╚══════════════════════════════════════════════════════════════╝" + Color.RESET);
        
        return verificarInt(sc, ">> ");
    }

}