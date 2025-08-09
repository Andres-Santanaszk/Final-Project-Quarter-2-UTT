// Clase que simula ser una base de datos con matrices y arrays.
public class DataManager {

    public static String[][] usuarios = {
        {"Andres", "*****"}, // las contraseñas son el numero de asteriscos
        {"Angel", "******"},
        {"Julissa", "*******"},
    };

    public static String[][] alumnosInscritos = {
        {"luis", "ana", "mario", "claudia", "diego", null, null, null, null, null}, // kinder   
        {"karen", "hugo", "teresa", "humberto", "patricia", null, null, null, null, null}, // primaria 
        {"carlos", "valeria", "jose", "camila", "esteban", null, null, null, null, null} // secundaria
    };

    public static double[] saldos = {20000, 20000, 20000}; // saldos asignados por indice con respecto a [i][0] en usuarios

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