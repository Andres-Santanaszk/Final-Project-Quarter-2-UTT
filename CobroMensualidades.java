import java.util.Scanner;

public class CobroMensualidades {
    static final int NIVELES = 3; 
    static final int MESES   = 12; // Ciclo escolar de Marzo (0) a Febrero (11)

    static final String[] NIVEL_NOMBRE = {
        "Preescolar", "Primaria", "Secundaria"
    };

    static final String[] MES_NOMBRE = {
        "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre",
        "Noviembre", "Diciembre", "Enero", "Febrero"
    };

    
    static double tarifaBase(int nivelIdx) {
        return (nivelIdx == 0) ? 2500
             : (nivelIdx == 1) ? 2700
             : 3000; // nivel 2
    }

    //cobranza por mes:
     double[][] generarMatMensualidades() {
        double[][] mat = new double[NIVELES][MESES];
        for (int n = 0; n < NIVELES; n++) {
            double base = tarifaBase(n);
            for (int m = 0; m < MESES; m++) {
                mat[n][m] = base;
            }
            mat[n][0] += base; // pago extra 
        }
        return mat;
    }

    
    static int pedirNivel(Scanner sc) {
        int nivel;
        while (true) {
            System.out.print("\nElija nivel escolar (0 Preescolar, 1 Primaria, 2 Secundaria): ");
            if (sc.hasNextInt()) {
                nivel = sc.nextInt(); sc.nextLine(); // limpia buf
                if (nivel >= 0 && nivel < NIVELES)
                    return nivel;
            } else {
                sc.nextLine(); // entrada inválida
            }
            System.out.println("Entrada no válida. Intente de nuevo.");
        }
    }

    
    static int pedirMes(Scanner sc) {
        while (true) {
            System.out.print("Ingresa un mes: ");
            String linea = sc.nextLine().trim();
            for (int i = 0; i < MESES; i++) {
                if (MES_NOMBRE[i].equalsIgnoreCase(linea)) { 
                    return i;
                }
            }
            System.out.println("Mes inválido o fuera del ciclo. Ej. Marzo, Abril... Intenta otra vez.");
        }
    }

   
    static void mostrarMonto(double[][] mat, int nivel, int mes) {
        System.out.printf("\nPara %s en %s: $%.2f%n",
            NIVEL_NOMBRE[nivel], MES_NOMBRE[mes], mat[nivel][mes]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double[][] mat = generarMatMensualidades();

        System.out.println("Consulta de monto mensual");
        int nivel = pedirNivel(sc);
        int mes   = pedirMes(sc);
        mostrarMonto(mat, nivel, mes);

        sc.close();
    }
}
