import java.util.Scanner;

public class CobroMensualidades {
    public static void main(String[] args) {
        final int NIVELES = 3;
        final int MESES = 12;
        final String[] NIVEL_NOMBRE = {"Preescolar", "Primaria", "Secundaria"};
        final String[] MES_NOMBRE = {
            "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre",
            "Noviembre", "Diciembre", "Enero", "Febrero"
        };
        double[][] mat = new double[NIVELES][MESES];
        for (int n = 0; n < NIVELES; n++) {
            double base = (n == 0) ? 2500 : (n == 1) ? 2700 : 3000;
            for (int m = 0; m < MESES; m++) {
                mat[n][m] = base;
            }
            mat[n][0] += base; // pago extra en marzo
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Consulta de monto mensual");

        int nivel = -1;
        while (true) {
            System.out.print("\nElija nivel escolar (0 Preescolar, 1 Primaria, 2 Secundaria): ");
            if (sc.hasNextInt()) {
                nivel = sc.nextInt(); sc.nextLine();
                if (nivel >= 0 && nivel < NIVELES) break;
            } else {
                sc.nextLine();
            }
            System.out.println("Entrada no válida. Intente de nuevo.");
        }

        int mes = -1;
        while (true) {
            System.out.print("Ingresa un mes: ");
            String linea = sc.nextLine().trim();
            for (int i = 0; i < MESES; i++) {
                if (MES_NOMBRE[i].equalsIgnoreCase(linea)) {
                    mes = i;
                    break;
                }
            }
            if (mes != -1) break;
            System.out.println("Mes inválido o fuera del ciclo. Ej. Marzo, Abril... Intenta otra vez.");
        }

        System.out.printf("\nPara %s en %s: $%.2f%n",
            NIVEL_NOMBRE[nivel], MES_NOMBRE[mes], mat[nivel][mes]);

        sc.close();
    }
}
