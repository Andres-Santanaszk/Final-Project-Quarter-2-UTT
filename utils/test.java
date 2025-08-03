package utils;
public class test {
    public static void main(String[] args) {
    // Parte superior (rojo)
    System.out.println(Color.RED + "╔════════════════════════════════════════════╗" + Color.RESET);
    System.out.println(Color.RED + "║         Colegio Independencia              ║" + Color.RESET);
    System.out.println(Color.RED + "╠════════════════════════════════════════════╣" + Color.RESET);

    // Opciones (azul)
    System.out.print(Color.BLUE);
    System.out.printf("║  %-2s %-38s ║\n", "1.", "Cobros anuales");
    System.out.printf("║  %-2s %-38s ║\n", "2.", "Pago de mensualidades");
    System.out.printf("║  %-2s %-38s ║\n", "3.", "Eventos especiales");
    System.out.printf("║  %-2s %-38s ║\n", "4.", "Configuración del usuario");
    System.out.printf("║  %-2s %-38s ║\n", "5.", "Cambiar de usuario");
    System.out.printf("║  %-2s %-38s ║\n", "6.", "Cerrar sesión");
    System.out.print(Color.RESET);  // importante para no seguir en azul

    // Parte inferior (rojo)
    System.out.println(Color.RED + "╚════════════════════════════════════════════╝" + Color.RESET);
    }   
}
