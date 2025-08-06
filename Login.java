import java.util.Scanner;

import utils.AsciiArt;
import utils.Color;

public class Login {

    public static void main(String[] args) {
        
        Color.mostrarAscii(AsciiArt.LOGIN);
        try {
        Thread.sleep(1000); 
        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        }

        String[][] usuarios = DataManager.usuarios;
        Scanner sc = new Scanner(System.in);
        int intentos = 0;
        boolean acceso = false;
        String usuarioNombre = "";

        while (intentos < 3 && !acceso) {
            System.out.println();
            System.out.print(Color.BLUE + "Usuario: " + Color.RESET);
            String user = sc.nextLine().trim();

            System.out.println();
            System.out.print(Color.BLUE + "Constraseña: " + Color.RESET);
            String pass = sc.nextLine().trim();

            if (validar(user, pass, usuarios)) {
                acceso = true;
                usuarioNombre = user;
            } 
            else {
                intentos++;
                System.out.println(Color.RED + "Usuario o contraseña incorrecta. Intento "+ intentos + " de 3.\n" + Color.RESET);
            }
        }

        if (acceso) {
            Color.mostrarAscii(AsciiArt.BIENVENIDA);
        } else {
            System.out.println(Color.RED + "\nHas excedido el Andnúmero de intentos. Saliendo del sistema." + Color.RESET);
        }
        
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0].equals(usuarioNombre)) {
            DataManager.usuarioActual = i;
            break;
        }
    }
    }

        public static boolean validar(String user, String password, String[][] usuarios) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0].equals(user) && usuarios[i][1].equals(password)) {
                return true;
            }
        }
        return false;
    }
}
