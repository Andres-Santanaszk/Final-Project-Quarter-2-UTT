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
        System.out.print("Ingrese la IP del servidor al que desea conectarse: ");
        String inHost = sc.nextLine().trim();
        if (!inHost.isEmpty()) host = inHost;

        System.out.print("Ingrese el puerto: ");
        String inPort = sc.nextLine().trim();
        if (!inPort.isEmpty())
            port = parseOrDefault(inPort, port);

        System.out.printf("Conectando a %s:%d…%n", host, port);

        try (Socket socket = new Socket(host, port);
             BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
             PrintWriter    out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true)) {

            // Handshake tolerante: si llega READY, respondemos; si no, seguimos con lo recibido
            String first = in.readLine(); // puede ser "READY" o ya "OK"/"ERR"
            String rsp;

            if ("READY".equals(first)) {
                // Enviamos un LOGIN genérico, ya que el login real lo hace tu Main/Login.java
                out.println("LOGIN demo demo");
                rsp = in.readLine(); // debería ser "OK"
            } else {
                // Servidor que no manda READY: tomamos la primera respuesta como válida
                rsp = first;
            }

            if ("OK".equals(rsp)) {
                System.out.println("\n✅ ¡Conexión exitosa!\n");
                // Ejecuta tu flujo real (Main -> Login.java -> mostrarMenu)
                // Capturamos el saldo ANTES de que el usuario haga operaciones
                double saldoInicial = DataManager.saldos[DataManager.usuarioActual];

                Main.main(new String[0]); // El usuario realiza todas sus operaciones aquí

                // Capturamos el saldo DESPUÉS de las operaciones
                double saldoFinal = DataManager.saldos[DataManager.usuarioActual];
                double totalGastado = saldoInicial - saldoFinal;

                // Enviamos el total real para generar el recibo
                out.println("TOTAL " + String.format("%.2f", totalGastado));
                String reciboRsp = in.readLine();
                if ("RECIBO_GENERADO".equals(reciboRsp)) {
                    System.out.println("🧾 Recibo generado en el servidor.");
                } else {
                    System.err.println("⚠️  No se pudo generar el recibo: " + reciboRsp);
                }

                // Notificamos salida limpia (opcional)
                try { out.println("EXIT"); } catch (Exception ignore) {}
            } else {
                System.err.println("❌ Handshake fallido: " + rsp);
            }

        } catch (IOException ex) {
            System.err.println("No se pudo conectar: " + ex.getMessage());
        } finally {
            try { sc.close(); } catch (Exception ignore) {}
        }
    }

    private static int parseOrDefault(String val, int def) {
        if (val == null || val.isBlank()) return def;
        try { return Integer.parseInt(val.trim()); }
        catch (NumberFormatException e) { return def; }
    }
}
