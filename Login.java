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
            System.out.print("Usuario: ");
            String user = sc.nextLine().trim();

            System.out.print("Contraseña: ");
            String pass = sc.nextLine().trim();

            if (validar(user, pass, usuarios)) {
                acceso = true;
                usuarioNombre = user;
            } else {
                intentos++;
                System.out.println(
                    "Usuario o contraseña incorrecta. Intento "
                    + intentos + " de 3.\n"
                );
            }
        }

        if (acceso) {
            Color.mostrarAscii(AsciiArt.BIENVENIDA);
            Main.iniciarPrograma();
        } else {
            System.out.println(
                "\nHas excedido el número de intentos. Saliendo del sistema."
            );
        }

        sc.close();
    }

        // la dejé en private para que solamente sea accesible desde dentro de la misma clase
        public static boolean validar(String user,String password,String[][] usuarios) {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i][0].equals(user) && usuarios[i][1].equals(password)) {
                return true;
            }
        }
        return false;
    }


    
}
