// Clase que simula ser una base de datos con matrices y arrays.
public class DataManager {

    public static String[][] usuarios = {
        {"Andres", "*****"}, 
        {"Angel", "******"},
        {"Julissa", "*******"},
    };
    
    public static String[][] alumnos = {
        {"Enrique", "Orlando" },
        {"Enrique", "Orlando" },
        {"Enrique", "Orlando" }
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