import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class Servidor {

    private static final int    DEFAULT_PORT = 5000;
    private static final String ENV_VAR_PORT = "SERVIDOR_PORT";

    public static void main(String[] args) {
        int port = resolvePort(args);
        System.out.println("📡 Servidor iniciando en puerto " + port + "...");

        // Pool simple para manejar clientes concurrentes
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket server = new ServerSocket()) {
            server.setReuseAddress(true);
            server.bind(new InetSocketAddress(port));
            System.out.println("✅ Servidor listo. Esperando clientes...");

            while (true) {
                try {
                    Socket s = server.accept();
                    pool.submit(new ManejadorCliente(s));
                } catch (IOException acceptErr) {
                    System.err.println("❌ Error aceptando cliente: " + acceptErr.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("❌ No se pudo iniciar el servidor: " + e.getMessage());
        } finally {
            pool.shutdownNow();
        }
    }

    private static int resolvePort(String[] args) {
        // Prioridad: args[0] > -Dport= > ENV > DEFAULT
        int port = DEFAULT_PORT;

        // Propiedad del sistema
        port = parseOrDefault(System.getProperty("port"), port);

        // Arg[0]
        if (args != null && args.length > 0) {
            port = parseOrDefault(args[0], port);
        }

        // ENV
        String env = System.getenv(ENV_VAR_PORT);
        port = parseOrDefault(env, port);

        return port;
    }

    private static int parseOrDefault(String v, int def) {
        if (v == null || v.isBlank()) return def;
        try {
            return Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return def;
        }
    }
}

class ManejadorCliente implements Runnable {
    private static final int READ_TIMEOUT_MS = 15000;

    private final Socket socket;

    ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        String remoto = socket.getRemoteSocketAddress().toString();
        System.out.println("➕ Cliente conectado: " + remoto);

        try {
            socket.setSoTimeout(READ_TIMEOUT_MS);

            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                 PrintWriter out   = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {

                // 1) Señal de listo
                out.println("READY");

                // 2) Espera una línea de tipo LOGIN ...
                String line = in.readLine();
                if (line == null) {
                    out.println("ERR no-input");
                    return;
                }

                line = line.trim();
                if (!line.toUpperCase().startsWith("LOGIN")) {
                    // Para mantener el mini-protocolo, solo aceptamos LOGIN;
                    // si quisieras aceptar cualquier texto y pasar, cambia a out.println("OK") aquí.
                    out.println("ERR bad-command");
                    System.out.printf("⚠️  [%s] comando no soportado: %s%n", remoto, line);
                    return;
                }

                // ACEPTAMOS SIN VALIDAR CREDENCIALES
                // Partimos pero sin exigir cantidad de tokens
                String[] parts = line.split("\\s+", 3); // LOGIN [user] [pass]
                String user = (parts.length >= 2) ? parts[1] : "";
                // String pass = (parts.length >= 3) ? parts[2] : ""; // no se usa

                out.println("OK");
                System.out.printf("🔓 [%s] handshake OK (usuario recibido='%s')%n", remoto, user);
                // Para este flujo "rápido y funcional", cerramos aquí.
                return;
            }
        } catch (SocketTimeoutException te) {
            System.err.printf("⌛ [%s] timeout de lectura: %s%n", remoto, te.getMessage());
        } catch (IOException ex) {
            System.err.printf("❌ [%s] error E/S: %s%n", remoto, ex.getMessage());
        } finally {
            try { socket.close(); } catch (IOException ignore) {}
            System.out.println("➖ Cliente desconectado: " + remoto);
        }
    }
}
