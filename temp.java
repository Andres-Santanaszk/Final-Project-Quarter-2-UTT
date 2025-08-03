import utils.AsciiArt;
import utils.Color;

public class Main {
    public static void main(String[] args) {
        iniciarPrograma();
    }

    public static void iniciarPrograma() {
        // Aquí se puede cargar datos, mostrar menú, etc.
        mostrarMenu();
    }

    public static void mostrarMenu() {
        try {
        Thread.sleep(2000); 
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
        Color.mostrarAscii(AsciiArt.COLEGIO_NOMBRE);
        
    }
}