import java.util.Scanner;

public class CobroMensualidades {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
            System.out.println("Menú de pago de colegiatura(mensualidad): ");
            System.out.println("Seleccione su nivel escolar");
            System.out.println("1.- Preescolar");
            System.out.println("2.- Primaria");
            System.out.println("3.- Secundaria");
            System.out.println("4.- Salir");
            
            int opcion = Main.verificarInt(sc, ">> ");
                switch (opcion) {
                    case 1:
                        MensualidadNivel(sc, "Preescolar", 2500.00);
                        break;
                    case 2:
                        MensualidadNivel(sc, "Primaria", 2700.00);
                        break;
                    case 3:
                        MensualidadNivel(sc, "Secundaria", 3000.00);
                        break;
                    case 4:
                        System.out.println("Volviendo al Menú Principal");
                        Main.mostrarMenu();
                        break;
                    default:
                        System.out.println("Opción invalida, intente de nuevo");
                        CobroMensualidades.main(args);
                        break;
                }
        }

        public static void MensualidadNivel(Scanner sc, String nivel, double costoBase) {
            
            System.out.println("=== Menú de Pago de "+ nivel + " ===");
            int meses = mesesNombres(sc);
            double costo;

            // Marzo (3) y Diciembre (10) cuestan doble
            if (meses == 3 || meses == 10) {
                costo = costoBase * 2;
            } else {
                costo = costoBase;
            }
            System.out.println("El costo de la mensualidad es: "+ costo);

            ConfirmarPagoMensualidad(sc, costo);
        }
         
            public static int mesesNombres (Scanner sc){

                System.out.println("Seleccione el mes a pagar:");
                System.out.println("1.- Enero");
                System.out.println("2.- Febrero");
                System.out.println("3.- Marzo");
                System.out.println("4.- Abril");
                System.out.println("5.- Mayo");
                System.out.println("6.- Junio");
                System.out.println("7.- Septiembre");
                System.out.println("8.- Octubre");
                System.out.println("9.- Noviembre");
                System.out.println("10.- Diciembre");
                System.out.println("0.- Regresar al menu anterior");
                System.out.println("El costo de la mensualidad de Marzo y Diciembre es del doble debido a los 2 meses de vacaciones de verano (Julio y Agosto)");
                int mes = Main.verificarInt(sc, ">> ");

                while ( mes < 0 || mes > 10){
                System.out.println("Opcion invalida. Intente de nuevo");
                mes = sc.nextInt();
                } 
                if (mes == 0){
                CobroMensualidades.main(null);
                return -1;
                }
                
                return mes;
            }

        public static void ConfirmarPagoMensualidad(Scanner sc, double deuda_total){
        int respuesta;
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
            do {
                System.out.println("¿Desea proceder con el pago?");
                System.out.println("1.- SI");
                System.out.println("2.- NO");
                respuesta = Main.verificarInt(sc, ">> ");
                    if (respuesta == 1){
                        if (!Main.procesarCobro(deuda_total, saldo_disponible, "pagar la colegiatura")) {
                        System.out.println("Regresando al menú...");
                        CobroMensualidades.main(null);
                        return;
                        }else{
                            double saldo_restante = saldo_disponible - deuda_total;
                            System.out.println("=== RECIBO GENERAL ===");
                            System.out.println("\nSu saldo es: " + saldo_disponible);
                            System.out.println("El total a pagar es de: " + deuda_total);
                            System.out.println("Saldo restante: " + saldo_restante);

                        DataManager.saldos[DataManager.usuarioActual] = saldo_restante;
                        System.out.println("Regresando al menu...");
                        Main.mostrarMenu();}
                    }else if (respuesta == 2){
                        System.out.println("No se pudo realizar el pago");
                            CobroMensualidades.main(null);    
                    }else {
                        System.out.println("Ingrese una opcion valida.");
                    }
            }while (respuesta != 1 && respuesta != 2);
            return;
        }
}