import java.io.*;
import java.net.*;

public class Servidor {

    private static final int    PUERTO_POR_DEFECTO = 5000;
    private static final String ENV_VAR           = "SERVIDOR_PORT";

    public static void main(String[] args) {

        int puerto = PUERTO_POR_DEFECTO;

        puerto = parseOrDefault(System.getProperty("port"), puerto);

        if (args.length > 0)
            puerto = parseOrDefault(args[0], puerto);

        puerto = parseOrDefault(System.getenv(ENV_VAR), puerto);

        try (ServerSocket ss = new ServerSocket(puerto)) {
            System.out.printf("🟢 Servidor escuchando en 0.0.0.0:%d%n", puerto);

            Runtime.getRuntime().addShutdownHook(
                new Thread(() -> System.out.println("\nApagando servidor…")));

            while (true) {
                Socket s = ss.accept();
                new Thread(new ManejadorCliente(s)).start();
            }
        } catch (IOException ex) {
            System.err.printf("⚠️  Error al iniciar el servidor (%d): %s%n",
                              puerto, ex.getMessage());
        }
    }

    private static int parseOrDefault(String valor, int actual) {
        if (valor == null || valor.isBlank()) return actual;
        try {
            return Integer.parseInt(valor.trim());
        } catch (NumberFormatException e) {
            System.err.printf("⚠️  Puerto inválido «%s», usando %d%n", valor, actual);
            return actual;
        }
    }
}

class ManejadorCliente implements Runnable {

    private final Socket socket;
    ManejadorCliente(Socket socket) { this.socket = socket; }

    @Override public void run() {
        String remoto = socket.getRemoteSocketAddress().toString();
        System.out.println("➕ Cliente conectado: " + remoto);

        try (BufferedReader in  = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
             PrintWriter    out = new PrintWriter(
                 socket.getOutputStream(), true)) {

            String linea;
            while ((linea = in.readLine()) != null) {
                String[] p = linea.trim().split("\\s+");
                if (p.length == 0) continue;

                switch (p[0].toUpperCase()) {
                    case "LOGIN":
                        if (p.length == 3) {
                            boolean ok = Login.validar(p[1], p[2], DataManager.usuarios);
                            out.println(ok ? "OK" : "FAIL");
                        } else out.println("ERROR");
                        break;

                    case "EXIT":
                        out.println("BYE");
                        return;

                    default:
                        out.println("COMANDO_DESCONOCIDO");
                }
            }
        } catch (IOException ex) {
            System.err.printf("❌ Cliente %s desconectado: %s%n", remoto, ex.getMessage());
        }
    }
}
