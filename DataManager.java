// Clase que simula ser una base de datos con matrices y arrays.
public class DataManager {

    public static String[][] usuarios = {
        {"Andres", "*****"}, 
        {"Angel", "******"},
        {"Julissa", "*******"},
    };

    public static String[][] alumnosInscritos = {
        {"Luis", "Ana", "Mario", "Claudia", "Diego", null, null, null, null, null}, // kinder   
        {"Karen", "Hugo", "Teresa", "Rafael", "Patricia", null, null, null, null, null}, // primaria 
        {"Carlos", "Valeria", "José", "Camila", "Esteban", null, null, null, null, null} // secundaria
    };

    public static double[] saldos = {9000, 13289.21, 12978.90}; // saldos asignados por indice con respecto a [i][0] en usuarios

    public static int usuarioActual = 0;

    public static double [][] costoEventos(){

        double[][] costos = {
            {300.43, 250.64, 1200.00, 500.14},
            {400.34, 500.45, 1500.54, 350.23},
            {5000.54, 2100.43, 3000.43, 700.23}
        };
        return costos;
    }
}  