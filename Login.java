import java.util.Scanner;
public class Login {

    public static void main(String[] args) {
        mostrarBanner();

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
            System.out.println(
                "\n¡Inicio de sesión exitoso, bienvenido, "
                + usuarioNombre + "!\n"
            );
            main.iniciarPrograma();
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


    private static void mostrarBanner() {
        System.out.println();
        System.out.println("______ _                           _     _                   ");
        System.out.println("| ___ (_)                         (_)   | |                  ");
        System.out.println("| |_/ /_  ___ _ ____   _____ _ __  _  __| | ___              ");
        System.out.println("| ___ \\ |/ _ \\ '_ \\ \\ / / _ \\ '_ \\| |/ _` |/ _ \\             ");
        System.out.println("| |_/ / |  __/ | | \\ V /  __/ | | | | (_| | (_) |            ");
        System.out.println("\\____/|_|\\___|_| |_|\\_/ \\___|_| |_|_|\\__,_|\\___/             ");
        System.out.println(" _____      _      _                            _            ");
        System.out.println("|_   _|    (_)    (_)                          (_)           ");
        System.out.println("  | | _ __  _  ___ _  __ _ _ __   ___  ___  ___ _  ___  _ __ ");
        System.out.println("  | || '_ \\| |/ __| |/ _` | '__| / __|/ _ \\/ __| |/ _ \\| '_ \\");
        System.out.println(" _| || | | | | (__| | (_| | |    \\__ \\  __/\\__ \\ | (_) | | | |");
        System.out.println(" \\___/_| |_|_|\\___|_|\\__,_|_|    |___/\\___||___/_|\\___/|_| |_|");
        System.out.println();
    }
}
