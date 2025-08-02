import java.util.Scanner;

public class DataManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);












       

        // Mostrar la matriz
        DataManager.hh();
    }

















    public static int [][] costoEventos(){
    
        int[][] costos = {
            {120, 304, 300},
            {150, 500, 200},
            {40, 432, 400}
        };
        return costos;
    }
    public static Object[][] costosOtros = new Object[3][3];

    public static void setDato(int fila, int columna, Object valor){
        costosOtros[fila][columna] = valor;
    }
    public static void agregarDatos(String nombre, int monto){
        int filaDisponible = -1;
            for (int i = 0; i < DataManager.costosOtros.length; i++) {
                if (DataManager.costosOtros[i][0] == null) {
                    filaDisponible = i;
                    break;
                }
            }

            if (filaDisponible != -1) {
                setDato(filaDisponible, 0, nombre);
                setDato(filaDisponible, 1, monto);
            }    
        
    }

    public static void hh(){
            System.out.println("== Eventos registrados ==");
            for (int i = 0; i < costosOtros.length; i++) {
                System.out.println("Fila " + i + ": " + costosOtros[i][0] + " | " + costosOtros[i][1]);
            }
            
        }


}
