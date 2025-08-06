import java.util.Scanner;

public class EventosEspeciales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

                System.out.println("=== MENÚ DE EVENTOS ESPECIALES ===");
                System.out.println("Seleccione el nivel educativo: ");
                System.out.println("1. Preescolar");
                System.out.println("2. Primaria");
                System.out.println("3. Secundaria");
                System.out.println("4. Salir y ver resumen general");
                
                int opcion = Main.verificarInt(sc,">> ");
                    switch (opcion) {
                        case 1:
                            PreescolarEvento(sc);
                            break;
                        case 2:
                            PrimariaEvento(sc);
                            break;
                        case 3:
                            SecundariaEvento(sc);
                            break;
                        case 4:
                            System.out.println("Saliendo de eventos especiales...");
                                Main.mostrarMenu();
                            break;
                        default:
                            System.out.println("Opción inválida. Intenta de nuevo");
                    }   
            
    }

    public static void PreescolarEvento(Scanner sc){
        System.out.println("=== EVENTOS DE PREESCOLAR ===");
        System.out.println("1.- Festival de Navidad");
        System.out.println("2.- Dia del niño");
        System.out.println("3.- Graduación");
        System.out.println("4.- Viaje escolar");
        System.out.println("5.- Regresar al menu anterior");

        int opcion = Main.verificarInt(sc, ">> ");
        double[][] matriz = DataManager.costoEventos();
            switch (opcion) {
                case 1:
                    System.out.println("El costo del Festival de Navidad es de: $ "+ matriz[0][0]);
                        ConfirmarPago(sc, matriz[0][0]);
                    break;
                case 2:
                    System.out.println("El costo del Dia del niño es de: $ "+ matriz[0][1]);
                        ConfirmarPago(sc, matriz[0][1]);
                    break;
                case 3:
                    System.out.println("El costo de la Graduación es de: $ "+ matriz[0][2]);
                        ConfirmarPago(sc, matriz[0][2]);
                    break;
                case 4:
                    System.out.println("El costo del Viaje escolar es de: $ "+ matriz[0][3]);
                        ConfirmarPago(sc, matriz[0][3]);
                    break;
                case 5:
                    System.out.println("Volviendo al menu anterior...");
                        EventosEspeciales.main(null);
                    break;
                default :
                    System.out.println("Selecciona una opcion valida...");
                        EventosEspeciales.PreescolarEvento(sc);;
                    break;
            }
        return;
    }

    public static void PrimariaEvento(Scanner sc){
        System.out.println("=== EVENTOS DE PRIMARIA ===");
        System.out.println("1.- Festival de Navidad");
        System.out.println("2.- Talleres especiales");
        System.out.println("3.- Graduación");
        System.out.println("4.- Fotos escolares");
        System.out.println("5.- Regresar al menu anterior");

        int opcion = Main.verificarInt(sc,">> ");
        double[][] matriz = DataManager.costoEventos();
            switch (opcion) {
                case 1:
                    System.out.println("El costo del Festival de navidad es de: $ "+ matriz[1][0]);
                        ConfirmarPago(sc, matriz[1][0]);
                    break;
                case 2:
                    System.out.println("El costo de los Talleres especiales es de: $ "+ matriz[1][1]);
                        ConfirmarPago(sc, matriz[1][1]);
                    break;
                case 3:
                    System.out.println("El costo de la Graduación es de: $ "+ matriz[1][2]);
                        ConfirmarPago(sc, matriz[1][2]);
                    break;
                case 4:
                    System.out.println("El costo de las Fotos Escolares es de: $ "+ matriz[1][3]);
                        ConfirmarPago(sc, matriz[1][3]);
                    break;
                case 5:
                    System.out.println("Volviendo al menu anterior...");
                        EventosEspeciales.main(null);
                    break;
                default :
                    System.out.println("Selecciona una opcion valida...");
                        EventosEspeciales.PrimariaEvento(sc);
                    break;
            }
        return;
    }

    public static void SecundariaEvento(Scanner sc){
        System.out.println("=== EVENTOS DE SECUNDARIA ===");
        System.out.println("1.- Viaje de generación");
        System.out.println("2.- Talleres / Campamento de verano");
        System.out.println("3.- Graduación");
        System.out.println("4.- Fotos escolares");
        System.out.println("5.- Regresar al menu anterior");

        int opcion = Main.verificarInt(sc, ">> "); 
        double[][] matriz = DataManager.costoEventos();
            switch (opcion) {
                case 1:
                    System.out.println("El costo del Viaje de generación es de: $ "+ matriz[2][0]);
                        ConfirmarPago(sc, matriz[2][0]);
                    break;
                case 2:
                    System.out.println("El costo de los Talleres / Campamento de verano es de: $ "+ matriz[2][1]);
                        ConfirmarPago(sc, matriz[2][1]);
                    break;
                case 3:
                    System.out.println("El costo de la Graduación es de: $ "+ matriz[2][2]);
                        ConfirmarPago(sc, matriz[2][2]);
                    break;
                case 4:
                    System.out.println("El costo de las Fotos escolares es de: $ "+ matriz[2][3]);
                        ConfirmarPago(sc, matriz[2][3]);
                    break;
                case 5:
                    System.out.println("Volviendo al menu anterior...");
                        EventosEspeciales.main(null);
                    break;
                default :
                    System.out.println("Selecciona una opcion valida...");
                        EventosEspeciales.SecundariaEvento(sc);
                    break;
            }
        return;
    }

    public static void ConfirmarPago(Scanner sc, double deuda_total){
        int respuesta;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
            do {
                System.out.println("¿Desea proceder con el pago?");
                System.out.println("1.- SI");
                System.out.println("2.- NO");
                respuesta = Main.verificarInt(sc, ">> ");
                    if (respuesta == 1){
                        double saldo_restante = saldo_disponible - deuda_total;
                        System.out.println("=== RECIBO GENERAL ===");
                        System.out.println("\nSu saldo es: " + saldo_disponible);
                        System.out.println("El total a pagar es de: " + deuda_total);
                        System.out.println("Saldo restante: " + saldo_restante);
                        
                        DataManager.saldos[DataManager.usuarioActual] = saldo_restante;
                        System.out.println("Regresando al menu...");
                        Main.mostrarMenu();
                    }else if (respuesta == 2){
                        System.out.println("No se pudo realizar el pago");
                            EventosEspeciales.main(null);    
                    }else {
                        System.out.println("Ingrese una opcion valida.");
                    }
            }while (respuesta != 1 && respuesta != 2);
        return;
    }

} 