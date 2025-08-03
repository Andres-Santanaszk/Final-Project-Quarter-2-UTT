import java.util.Scanner;
import java.text.Normalizer;

public class EventosEspeciales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String opcion;

        while (true) {
            System.out.println("=== MENÚ DE EVENTOS ESPECIALES ===");
            System.out.println("Escribe exactamente una de las siguientes opciones:");
            System.out.println("- Preescolar");
            System.out.println("- Primaria");
            System.out.println("- Secundaria");
            System.out.println("- Salir");

            opcion = normalize(sc.nextLine());

            if (opcion.equals("preescolar")) {
                PreescolarEvento(sc);
            } else if (opcion.equals("primaria")) {
                PrimariaEvento(sc);
            } else if (opcion.equals("secundaria")) {
                SecundariaEvento(sc);
            } else if (opcion.equals("salir")) {
                System.out.println("Saliendo de eventos especiales...");
                Main.mostrarMenu();
                break;
            } else {
                System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        }
    }

    public static void PreescolarEvento(Scanner sc) {
        String opcion;
        int[][] matriz = DataManager.costoEventos();

        while (true) {
            System.out.println("\n=== EVENTOS DE PREESCOLAR ===");
            System.out.println("Escribe exactamente una de las siguientes opciones:");
            System.out.println("- Festival de Navidad");
            System.out.println("- Día del niño");
            System.out.println("- Graduación");
            System.out.println("- Viaje escolar");
            System.out.println("- Regresar al menú anterior");

            opcion = normalize(sc.nextLine());

            switch (opcion) {
                case "festival de navidad":
                    System.out.println("El costo del Festival de Navidad es de: $ " + matriz[0][0]);
                    ConfirmarPago(sc);
                    return;
                case "dia del nino":
                    System.out.println("El costo del Día del niño es de: $ " + matriz[0][1]);
                    ConfirmarPago(sc);
                    return;
                case "graduacion":
                    System.out.println("El costo de la Graduación es de: $ " + matriz[0][2]);
                    ConfirmarPago(sc);
                    return;
                case "viaje escolar":
                    System.out.println("El costo del Viaje escolar es de: $ " + matriz[0][3]);
                    ConfirmarPago(sc);
                    return;
                case "regresar":
                    return;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        }
    }

    public static void PrimariaEvento(Scanner sc) {
        String opcion;
        int[][] matriz = DataManager.costoEventos();

        while (true) {
            System.out.println("\n=== EVENTOS DE PRIMARIA ===");
            System.out.println("Escribe exactamente una de las siguientes opciones:");
            System.out.println("- Festival de Navidad");
            System.out.println("- Talleres especiales");
            System.out.println("- Graduación");
            System.out.println("- Fotos escolares");
            System.out.println("- Regresar al menú anterior");

            opcion = normalize(sc.nextLine());

            switch (opcion) {
                case "festival de navidad":
                    System.out.println("El costo del Festival de Navidad es de: $ " + matriz[1][0]);
                    ConfirmarPago(sc);
                    return;
                case "talleres especiales":
                    System.out.println("El costo de los Talleres especiales es de: $ " + matriz[1][1]);
                    ConfirmarPago(sc);
                    return;
                case "graduacion":
                    System.out.println("El costo de la Graduación es de: $ " + matriz[1][2]);
                    ConfirmarPago(sc);
                    return;
                case "fotos escolares":
                    System.out.println("El costo de las Fotos escolares es de: $ " + matriz[1][3]);
                    ConfirmarPago(sc);
                    return;
                case "regresar":
                    return;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        }
    }

    public static void SecundariaEvento(Scanner sc) {
        String opcion;
        int[][] matriz = DataManager.costoEventos();

        while (true) {
            System.out.println("\n=== EVENTOS DE SECUNDARIA ===");
            System.out.println("Escribe exactamente una de las siguientes opciones:");
            System.out.println("- Viaje de generación");
            System.out.println("- Talleres / Campamento de verano");
            System.out.println("- Graduación");
            System.out.println("- Fotos escolares");
            System.out.println("- Regresar al menú anterior");

            opcion = normalize(sc.nextLine());

            switch (opcion) {
                case "viaje de generacion":
                    System.out.println("El costo del Viaje de generación es de: $ " + matriz[2][0]);
                    ConfirmarPago(sc);
                    return;
                case "talleres campamento de verano":
                case "talleres / campamento de verano":
                    System.out.println("El costo de los Talleres / Campamento de verano es de: $ " + matriz[2][1]);
                    ConfirmarPago(sc);
                    return;
                case "graduacion":
                    System.out.println("El costo de la Graduación es de: $ " + matriz[2][2]);
                    ConfirmarPago(sc);
                    return;
                case "fotos escolares":
                    System.out.println("El costo de las Fotos escolares es de: $ " + matriz[2][3]);
                    ConfirmarPago(sc);
                    return;
                case "regresar":
                    return;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.\n");
            }
        }
    }

    public static void ConfirmarPago(Scanner sc) {
        String respuesta;

        while (true) {
            System.out.println("\n¿Desea proceder con el pago? (s/n)");
            respuesta = normalize(sc.nextLine());

            if (respuesta.equals("s")) {
                System.out.println("Pago exitoso.\n");
                return;
            } else if (respuesta.equals("n")) {
                System.out.println("No se pudo realizar el pago.\n");
                return;
            } else {
                System.out.println("Opción inválida. Ingresa 's' o 'n'.");
            }
        }
    }

    /** 
     * Normaliza la cadena: elimina acentos, convierte a minúsculas y recorta espacios.
     */
    private static String normalize(String input) {
        String sinAcentos = Normalizer.normalize(input, Normalizer.Form.NFD)
            .replaceAll("\\p{M}", "");
        return sinAcentos.trim().toLowerCase();
    }
}
