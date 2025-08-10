import java.io.*;
import java.net.*;

public class Cliente {

    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 5000;
    private static final String ENV_VAR_HOST = "CLIENTE_HOST";
    private static final String ENV_VAR_PORT = "CLIENTE_PORT";

    public static void main(String[] args) {
        String host = resolveHost(args);
        int port = resolvePort(args);

        boolean ok = false;

        // 1) Conectar y autenticar rápido
        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(15000);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {

                String ready = in.readLine(); // debe ser "READY"
                if (!"READY".equals(ready)) {
                    System.err.println("❌ Servidor no respondió READY: " + ready);
                } else {
                    // Como la verificación real está en Main/Login, mandamos credenciales genéricas
                    out.println("LOGIN demo demo");
                    String resp = in.readLine(); // "OK" o "ERR ..."
                    if ("OK".equals(resp)) {
                        ok = true;
                        System.out.println("✅ Conexión y handshake con servidor exitoso");
                    } else {
                        System.err.println("❌ Handshake fallido: " + resp);
                    }
                }
            }
        } catch (SocketTimeoutException te) {
            System.err.println("⌛ Timeout esperando respuesta del servidor: " + te.getMessage());
        } catch (IOException e) {
            System.err.println("❌ Error de conexión: " + e.getMessage());
        }

        // 2) Si autenticó, ejecutar tu Main (que hace login real)
        if (ok) {
            Main.main(null);
        } else {
            System.err.println("Saliendo...");
        }
    }

    private static String resolveHost(String[] args) {
        String host = DEFAULT_HOST;
        String sysProp = System.getProperty("host");
        if (sysProp != null && !sysProp.isBlank()) host = sysProp.trim();
        if (args != null && args.length >= 1 && !args[0].isBlank()) host = args[0].trim();
        String env = System.getenv(ENV_VAR_HOST);
        if (env != null && !env.isBlank()) host = env.trim();
        return host;
    }

    private static int resolvePort(String[] args) {
        int port = DEFAULT_PORT;
        port = parseOrDefault(System.getProperty("port"), port);
        if (args != null && args.length >= 2) port = parseOrDefault(args[1], port);
        String env = System.getenv(ENV_VAR_PORT);
        port = parseOrDefault(env, port);
        return port;
    }

    private static int parseOrDefault(String val, int def) {
        if (val == null || val.isBlank()) return def;
        try { return Integer.parseInt(val.trim()); }
        catch (NumberFormatException e) { return def; }
    }
}
