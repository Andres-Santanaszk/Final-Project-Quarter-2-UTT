
public class DataManager {

    public static String[][] usuarios = {
        {"Andres", "andres123"},
        {"Angel", "angel123"},
        {"Julissa", "julissa123"},
    };
    
    public static String[][] alumnos = {
        {"Enrique", "E", }
    };

    public static double[] saldos = {9000, 13289.21, 12978.90};

    public static int usuarioActual = 0;

    public static int [][] costoEventos(){

        int[][] costos = {
            {300, 250, 1200, 500},
            {400, 500, 1500, 350},
            {5000, 2100, 3000, 700}
        };
        return costos;
    }
}  