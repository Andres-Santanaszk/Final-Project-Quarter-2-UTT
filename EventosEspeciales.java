import java.util.Scanner;

public class EventosEspeciales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
            System.out.println("\n=== MENÚ DE EVENTOS ESPECIALES ===");
            System.out.println("Seleccione el nivel educativo: ");
            System.out.println("1. Preescolar");
            System.out.println("2. Primaria");
            System.out.println("3. Secundaria");
            System.out.println("4. Salir");
            
            int opcion = sc.nextInt();
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
                    main.mostrarMenu();
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
                    EventosEspeciales.main(args);

            }   
    }

    public static void PreescolarEvento(Scanner sc){
        System.out.println("=== EVENTOS DE PREESCOLAR ===");
        System.out.println("1.- Posada");
        System.out.println("2.- Dia del niño");
        System.out.println("3.- Graduación");
        System.out.println("4.- Otro");
        System.out.println("5.- Regresar al menu anterior");
        int respuesta;
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("El costo de la Posada es de $350");
                
                    do {
                        System.out.println("¿Desea proceder con el pago?");
                        System.out.println("1.- SI");
                        System.out.println("2.- NO");
                        respuesta = sc.nextInt();
                            if (respuesta == 1){
                                System.out.println("hola");
                            }else if (respuesta == 2){
                                System.out.println("No se pudo realizar el pago");
                                EventosEspeciales.PreescolarEvento(sc);    
                            }else {
                                System.out.println("Ingrese una opcion valida.");
                            }
                    }while (respuesta != 1 && respuesta != 2);
                break;
            case 2:
                System.out.println("El costo del Dia del niño es de $350");
                
                    do {
                        System.out.println("¿Desea proceder con el pago?");
                        System.out.println("1.- SI");
                        System.out.println("2.- NO");
                        respuesta = sc.nextInt();
                            if (respuesta == 1){
                                System.out.println("hola");
                            }else if (respuesta == 2){
                                System.out.println("No se pudo realizar el pago");
                                EventosEspeciales.PreescolarEvento(sc);    
                            }else {
                                System.out.println("Ingrese una opcion valida.");
                            }
                    }while (respuesta != 1 && respuesta != 2);
                break;
            case 3:
                System.out.println("El costo de la Graduación es de $350");
                do {
                        System.out.println("¿Desea proceder con el pago?");
                        System.out.println("1.- SI");
                        System.out.println("2.- NO");
                        respuesta = sc.nextInt();
                            if (respuesta == 1){
                                System.out.println("hola");
                            }else if (respuesta == 2){
                                System.out.println("No se pudo realizar el pago");
                                EventosEspeciales.PreescolarEvento(sc);    
                            }else {
                                System.out.println("Ingrese una opcion valida.");
                            }
                    }while (respuesta != 1 && respuesta != 2);
                break;
            case 4:
                System.out.println("Ingrese el nombre del evento:");
                String nombreEvento = sc.next();
                System.out.println("Ingrese el monto requerido:");
                Double montoEvento = sc.nextDouble();
                do {
                        System.out.println("¿Desea proceder con el pago?");
                        System.out.println("1.- SI");
                        System.out.println("2.- NO");
                        respuesta = sc.nextInt();
                            if (respuesta == 1){
                                System.out.println("hola");
                            }else if (respuesta == 2){
                                System.out.println("No se pudo realizar el pago");
                                EventosEspeciales.PreescolarEvento(sc);    
                            }else {
                                System.out.println("Ingrese una opcion valida.");
                            }
                    }while (respuesta != 1 && respuesta != 2);
                break;
            default:
                System.out.println("Volviendo al menu anterior...");
                EventosEspeciales.main(null);
                break;
        }
        return;
    
    }

    public static void PrimariaEvento(Scanner sc){
        System.out.println("=== EVENTOS DE PRIMARIA ===");
        System.out.println("1.- Posada");
        System.out.println("2.- Dia del niño");
        System.out.println("3.- Graduación");
        System.out.println("4.- Otro");
        System.out.println("5.- Regresar al menu anterior");

        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("El costo de la posada es de $350");
                System.out.println("¿Desea proceder con el pago?");
                break;
            case 2:
                System.out.println("El costo de la posada es de $350");
                System.out.println("¿Desea proceder con el pago?");
                break;
            case 3:
                System.out.println("El costo de la posada es de $350");
                System.out.println("¿Desea proceder con el pago?");
                break;
            case 4:
                System.out.println("Ingrese el nombre del evento:");
                String nameEvento = sc.nextLine();
                System.out.println("Ingrese el monto requerido:");
                Double montoEvento = sc.nextDouble();
                System.out.println("¿Desea proceder con el pago?");
                break;
            default:
                System.out.println("Volviendo al menu anterior...");
                EventosEspeciales.main(null);
                break;
        }
        return;
    
    }

    public static void SecundariaEvento(Scanner sc){
        System.out.println("=== EVENTOS DE SECUNDARIA ===");
        System.out.println("1.- Posada");
        System.out.println("2.- Dia del niño");
        System.out.println("3.- Graduación");
        System.out.println("4.- Otro");
        System.out.println("5.- Regresar al menu anterior");

        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("El costo de la posada es de $350");
                System.out.println("¿Desea proceder con el pago?");
                break;
            case 2:
                System.out.println("El costo de la posada es de $350");
                System.out.println("¿Desea proceder con el pago?");
                break;
            case 3:
                System.out.println("El costo de la posada es de $350");
                System.out.println("¿Desea proceder con el pago?");
                break;
            case 4:
                System.out.println("Ingrese el nombre del evento:");
                String nameEvento = sc.nextLine();
                System.out.println("Ingrese el monto requerido:");
                Double montoEvento = sc.nextDouble();
                System.out.println("¿Desea proceder con el pago?");
                break;
            default:
                System.out.println("Volviendo al menu anterior...");
                EventosEspeciales.main(null);
                break;
        }
        return;
    
    }
}
