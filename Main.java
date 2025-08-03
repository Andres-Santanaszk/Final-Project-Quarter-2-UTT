import utils.AsciiArt;
import utils.Color;

public class Main {
    public static void main(String[] args) {
        Login.main(null);
        mostrarMenu();
    }

    public static void mostrarMenu() {
        try {
        Thread.sleep(1000); 
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }
        Color.mostrarAscii(AsciiArt.COLEGIO_NOMBRE);
        
    }
}