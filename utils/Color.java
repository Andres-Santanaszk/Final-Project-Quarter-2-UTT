package utils;

public class Color {
    public static final String RESET  = "\u001B[0m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";

    public static final String[] COLORES = {RED, YELLOW, GREEN, CYAN, BLUE, PURPLE};

    /** Imprime un array de líneas ASCII con colores cíclicos */
    public static void mostrarAscii(String[] asciiLines) {
        System.out.println();
        for (int i = 0; i < asciiLines.length; i++) {
            String color = COLORES[i % COLORES.length];
            System.out.println(color + asciiLines[i] + RESET);
        }
        System.out.println();
    }

    /** Coloriza un texto plano */
    public static String pintar(String color, String texto) {
        return color + texto + RESET;
    }

    /** Imprime texto plano en color */
    public static void println(String color, String texto) {
        System.out.println(pintar(color, texto));
    }
}
