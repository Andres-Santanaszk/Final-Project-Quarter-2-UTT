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

    public static int [][] costoEventos(){

        int[][] costos = {
            {300, 250, 1200, 500},
            {400, 500, 1500, 350},
            {5000, 2100, 3000, 700}
        };
        return costos;
    }
}  