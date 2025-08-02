import java.util.Scanner;


public class Login {

    public static void main(String[] args) {

    System.out.println("");
    System.out.println("______ _                           _     _                    ");
    System.out.println("| ___ (_)                         (_)   | |                   ");
    System.out.println("| |_/ /_  ___ _ ____   _____ _ __  _  __| | ___               ");
    System.out.println("| ___ \\ |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\              ");
    System.out.println("| |_/ / |  __/ | | \\ V /  __/ | | | | (_| | (_) |             ");
    System.out.println("\\____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/              ");
    System.out.println(" _____      _      _                            _             ");
    System.out.println("|_   _|    (_)    (_)                          (_)            ");
    System.out.println("  | | _ __  _  ___ _  __ _ _ __   ___  ___  ___ _  ___  _ __  ");
    System.out.println("  | || '_ \\| |/ __| |/ _` | '__| / __|/ _ \\/ __| |/ _ \\| '_ \\ ");
    System.out.println(" _| || | | | | (__| | (_| | |    \\__ \\  __/\\__ \\ | (_) | | | |");
    System.out.println(" \\___/_| |_|_|\\___|_|\\__,_|_|    |___/\\___||___/_|\\___/|_| |_|");
    System.out.println("                                                              ");

        // aqui usamos la matriz que importamos desde data manager
        String[][] usuarios = DataManager.usuarios;

        Scanner sc = new Scanner(System.in);
        int intentos = 0;
        String usuario_nombre = "";
        boolean acceso = false;

        
        while (intentos < 3 && !acceso) {
            System.out.print("Usuario: ");
            String usuario = sc.nextLine();

            System.out.print("Contraseña: ");
            String contrasena = sc.nextLine();

            // recorrer la matriz por indice para verificar usuario y contraseña
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i][0].equals(usuario)
                 && usuarios[i][1].equals(contrasena)) {
                    acceso = true;
                    usuario_nombre = usuarios[i][0];
                    break;
                }
            }

            if (!acceso) {
                intentos++;
                System.out.println("Usuario o contraseña incorrecta. Intento " + intentos + " de 3.\n");
            }
        }

        if (acceso) {
            System.out.println("Inicio de sesión exitoso, bienvenido, " + usuario_nombre);
            main.main(new String[0]);
        } else {
            System.out.println("\nHas excedido el número de intentos. Saliendo del sistema.");
        }


        sc.close();
    }
}
