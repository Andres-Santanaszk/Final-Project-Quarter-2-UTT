import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    private static final String ENV_HOST = "SERVIDOR_HOST";
    private static final String ENV_PORT = "SERVIDOR_PORT";

    public static void main(String[] args) {

        String host = System.getProperty("host", "");          
        int    port = parseOrDefault(System.getProperty("port"), 0);

        if (args.length >= 1) host = args[0];          
        if (args.length >= 2) port = parseOrDefault(args[1], port);

        if (host.isBlank())
            host = System.getenv().getOrDefault(ENV_HOST, "localhost");
        if (port == 0)
            port = parseOrDefault(System.getenv(ENV_PORT), 5000);

        Scanner sc = new Scanner(System.in);
        System.out.printf("Ingrese la IP del servidor al que desea conectarse: ", host);
        String inHost = sc.nextLine().trim();
        if (!inHost.isEmpty()) host = inHost;       

        System.out.printf("Ingrese el puerto: ", port);
        String inPort = sc.nextLine().trim();
        if (!inPort.isEmpty())
            port = parseOrDefault(inPort, port);


        System.out.printf("Conectando a %s:%d…%n", host, port);

        try (Socket socket = new Socket(host, port);
             BufferedReader in  = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
             PrintWriter    out = new PrintWriter(
                 socket.getOutputStream(), true)) {

            int intentos = 0;
            while (intentos < 3) {
                System.out.print("Usuario: ");
                String user = sc.nextLine().trim();
                System.out.print("Contraseña: ");
                String pass = sc.nextLine().trim();

                out.printf("LOGIN %s %s%n", user, pass);
                String rsp = in.readLine();

                if ("OK".equals(rsp)) {
                    System.out.println("\n Autenticación correcta\n");
                    Main.mostrarMenu();
                    out.println("EXIT");
                    return;
                }
                System.out.println("Credenciales incorrectas\n");
                intentos++;
            }

            System.out.println("Demasiados intentos. Cerrando.");
            sc.close();
        } catch (IOException ex) {
            System.err.println("No se pudo conectar: " + ex.getMessage());
        }
    }
    
    private static int parseOrDefault(String val, int def) {
        if (val == null || val.isBlank()) return def;
        try { return Integer.parseInt(val.trim()); }
        catch (NumberFormatException e) { return def; }
    }
}
