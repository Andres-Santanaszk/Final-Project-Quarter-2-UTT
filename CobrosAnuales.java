import java.util.Scanner;

import utils.Color;

public class CobrosAnuales {

    public static void main(String[] args) {

        // declaracion de variables, matrices, arrays, etc
        Scanner sc = new Scanner(System.in);

            //[Nivel educativo][tipo de cobro]
        double[][] tarifas = {
            {800, 400},  // kinder 
            {1000, 500}, // primaria
            {1200, 600}   // secundaria
        };

        double[][] TarifaUnif = {
            {1600, 1600, 1300, 1300}, // kinder
            {1800, 1800, 1390,1390}, // primaria
            {2170, 2170, 1575, 1575} // secundaria
        };

        String[] nombresPapeleria = {
            "Cuadernos",
            "Libro de inglés",
            "Colores (12 piezas)",
            "Lápices (paquete de 6)",
            "Plumas (paquete de 6)",
            "Borrador",
            "Sacapuntas metálico",
            "Regla de 30cm",
            "Papel bond (paquete)",
            "Tijeras escolares"
        };

        double[] preciosPapeleria = {
            120.00,
            390.00,
            80.30,
            45.40,
            50.60,
            10.75,
            15.40,
            12.20,
            180.30,
            25.30
        };

        String[] nombresNivel = {"preescolar", "primaria", "secundaria"};
        String[] nombreUniformes = {"Formal masculino", "Formal femenino", "Deportivo masuclino", "Deportivo femenino"};
        String[] nombresOpciones = {"Inscripción", "Mantenimiento"};
        double[][] acumulados = new double[3][2];     
        double[][] acumuladosPap = new double[3][1];  
        double [][] acumuladosUnif = new double[3][4];
        
    
        double deuda_total = 0;
        //parte de la logica para asignar saldo en base a un usuario
        double saldo_disponible = DataManager.saldos[DataManager.usuarioActual];
        double saldo_inicial = saldo_disponible;

        int nivelEducativo = -1;
        double monto = 0;
       
        // menu principal
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

            nivelEducativo = entrada - 1;

            while (true) {
                System.out.println("\n=== MENÚ COBROS ANUALES [" + nombresNivel[nivelEducativo] + "] ===");
                System.out.println("1. Cobro inscripción");
                System.out.println("2. Cobro mantenimiento");
                System.out.println("3. Cobro opcionales");
                System.out.println("0. Cambiar nivel educativo");
                int opcion = Main.verificarInt(sc, ">> ");

                if (opcion == 0) break;

                switch (opcion) {
                    case 1:
                        int tipo = 0; // nos indica la columna de la matriz tarifas
                        monto = tarifas[nivelEducativo][tipo];

                        sc.nextLine();
                        System.out.println(Color.RED + "El alumno será inscrito en el nivel educativo " + nombresNivel[nivelEducativo] + ", el precio es de: " + tarifas[nivelEducativo][tipo] + "$" + Color.RESET);
                        System.out.print("Ingrese el nombre del alumno: ");
                        String nombreAlumno = sc.nextLine();

                        if (Main.confirmarPago(sc)) {
                            if (registrarAlumno(nivelEducativo, nombreAlumno)) {
                                if (Main.procesarCobro(monto, saldo_disponible, "Inscripción")) {
                                    acumulados[nivelEducativo][tipo] += monto;
                                    saldo_disponible -= monto;
                                    System.out.println("Cobro inscripción: " + monto + "$");
                                }
                            }
                        }
                        break;
                    case 2:
                        tipo = 1;
                        System.out.println("\n>> Pago de mantenimiento");
                        System.out.println("1. Mensual ($" + tarifas[nivelEducativo][tipo] + ")");
                        System.out.println("2. Trimestral (10% de descuento)");
                        System.out.println("3. Anual (20% de descuento)");

                        int opcMantenimiento = Main.verificarInt(sc, "Seleccione el tipo de pago: ");

                        int meses = 0;
                        double descuento = 1.0;

                        switch (opcMantenimiento) {
                            case 1: // mensual
                                meses = 1;
                                break;
                            case 2: // trimestral
                                meses = 3;
                                descuento = 0.90;
                                break;
                            case 3: // anual
                                meses = 10; // tomando en cuenta que solamente se cobran 10 meses
                                descuento = 0.80;
                                break;
                            default:
                                System.out.println(">> Opción inválida");
                                break;
                        }

                        if (meses > 0) {
                            double montoBaseMensual = tarifas[nivelEducativo][tipo];
                            monto = montoBaseMensual * meses * descuento;

                            System.out.println("\nEl cobro incluye:");
                            System.out.println("- Servicios básicos (agua, luz)");
                            System.out.println("- Limpieza y sanitización");
                            System.out.println("- Reparaciones menores");
                            System.out.printf("Monto total a pagar: $%.2f\n", monto);

                            sc.nextLine();

                            if (Main.confirmarPago(sc)) {
                                if (Main.procesarCobro(monto, saldo_disponible, "Mantenimiento")) {
                                    acumulados[nivelEducativo][tipo] += monto;
                                    saldo_disponible -= monto;
                                    System.out.printf("Pago de mantenimiento registrado: %s por $%.2f\n", nombresNivel[nivelEducativo], monto);           
                                }
                            }    
                        }
                        break;

                    case 3:
                        while (true) {
                            System.out.println("\n  -- OPCIONALES [" + nombresNivel[nivelEducativo] + "] --");
                            System.out.println("  1. Papelería:");
                            System.out.println("  2. Uniformes:" );
                            System.out.println("  0. Volver");
                            int opc = Main.verificarInt(sc, ">> ");

                            switch (opc) {
                                case 0:
                                    break;
                                case 1: 
                                        while (true) {
                                            System.out.println("\n  -- Papelería --");
                                            for (int i = 0; i < nombresPapeleria.length; i++) {
                                                System.out.printf("%d.- %s ($%.2f)\n", (i + 1), nombresPapeleria[i], preciosPapeleria[i]);
                                            }
                                            System.out.println("0.- Volver al menú anterior");
                                            int opcionpap = Main.verificarInt(sc, ">> ");

                                            if (opcionpap == 0) break;

                                            if (opcionpap < 1 || opcionpap > nombresPapeleria.length) {
                                                System.out.println(">> Opción inválida");
                                                continue;
                                            }

                                            int indice = opcionpap - 1;
                                            double montoPap = preciosPapeleria[indice];

                                            sc.nextLine();
                                            
                                            if (Main.confirmarPago(sc)) {
                                                if (Main.procesarCobro(montoPap, saldo_disponible, nombresPapeleria[indice])) {
                                                    acumuladosPap[nivelEducativo][0] += montoPap;  
                                                    saldo_disponible -= montoPap;
                                                    System.out.printf("  Compraste: %s por $%.2f\n", nombresPapeleria[indice], montoPap);
                                                }
                                            }
                                        }
                                        break;
                                case 2: 
                                    while (true) {
                                        System.out.println("\n  -- Elija un uniforme --  [" + nombresNivel[nivelEducativo] + "]");
                                        for (int i = 0; i < nombreUniformes.length; i++) {
                                            System.out.printf("%d.- %s ($%.2f)\n", (i + 1), nombreUniformes[i], TarifaUnif[nivelEducativo][i]);
                                        }
                                        System.out.println("0.- para volver al menú anterior");
                                        int opcionUnif = Main.verificarInt(sc, ">> ");

                                        if (opcionUnif == 0) break;

                                        if (opcionUnif < 1 || opcionUnif > nombreUniformes.length) {
                                            System.out.println(">> Opción inválida");
                                            continue;
                                        }

                                        int indice = opcionUnif - 1;
                                        double montoUniformes = TarifaUnif[nivelEducativo][indice];

                                        sc.nextLine();

                                        if (Main.confirmarPago(sc)){
                                            if (Main.procesarCobro(montoUniformes, saldo_disponible, nombreUniformes[indice])) {
                                                acumuladosUnif[nivelEducativo][indice] += montoUniformes;
                                                saldo_disponible -= montoUniformes;
                                                System.out.printf("Has comprado: %s por $%.2f\n", nombreUniformes[indice], montoUniformes);
                                            }
                                        }    
                                    }   
                                break;
                                default:
                                    System.out.println(">> Opción inválida");
                            }

                                if (opc == 0) break; 
                            }
                            break;

                    default:
                        System.out.println(">> Opción inválida");
                }
            }
        }
    System.out.println("\n=== RECIBO GENERAL ===");
        for (int i = 0; i < 3; i++) {
            if (!checarCobros(acumulados, i) && 
                !checarCobros(acumuladosPap, i) &&
                !checarCobros(acumuladosUnif, i)) continue;

            System.out.println("Nivel educativo: " + nombresNivel[i]);

            
            for (int j = 0; j < acumulados[i].length; j++) {
                if (acumulados[i][j] > 0) {
                    System.out.printf("  %-14s $%.2f\n", nombresOpciones[j] + ":", acumulados[i][j]);
                    deuda_total += acumulados[i][j];
                }
            }

            if (acumuladosPap[i][0] > 0) {
                System.out.printf("  %-14s $%.2f\n", "Papelería:", acumuladosPap[i][0]);
                deuda_total += acumuladosPap[i][0];
            }

            for (int j = 0; j < acumuladosUnif[i].length; j++) {
                if (acumuladosUnif[i][j] > 0) {
                    System.out.printf("  %-14s $%.2f\n", "Uniformes:", acumuladosUnif[i][j]);
                    deuda_total += acumuladosUnif[i][j];
                }
            }
}


        System.out.printf("Saldo inicial:    $%.2f\n", saldo_inicial);
        System.out.printf("Total a pagar:    $%.2f\n", deuda_total);
        System.out.printf("Saldo restante:   $%.2f\n", saldo_disponible);

        
        DataManager.saldos[DataManager.usuarioActual] = saldo_disponible;
        Main.Esperar(2);
        Main.mostrarMenu();

    }
        // funcion para verificar si hay cupo en la escuela (en la matriz)
        public static boolean registrarAlumno(int nivel, String nombre) {
            for (int i = 0; i < DataManager.alumnosInscritos[nivel].length; i++) {
                if (DataManager.alumnosInscritos[nivel][i] == null) {
                    DataManager.alumnosInscritos[nivel][i] = nombre;
                    System.out.println("Alumno " + DataManager.alumnosInscritos[nivel][i] + " ha sido inscrito correctamente.");
                    return true; 
                }
            }
            System.out.println("No hay cupo para más alumnos.");
            return false;
}

        // es simplemente para saber si hay cobros o no, cuando recorremos todas las matrices/arreglos
        public static boolean checarCobros(double[][] matriz, int fila) {
            for (int j = 0; j < matriz[fila].length; j++) {
                if (matriz[fila][j] > 0) {
                    return true;
                }
            }
            return false;
        }
}
