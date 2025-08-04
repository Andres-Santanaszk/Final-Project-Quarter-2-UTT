import java.util.Scanner;
public class CobrosAnualesOpcionales {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
            String opcion;
            
            while (true) {
                System.out.println("=== Menu opcional===");
                System.out.println("- Papeleria");
                System.out.println("- Uniformes ");  
                System.out.println("- Salir");  
                
                opcion = EventosEspeciales.normalize(sc.nextLine());

                if (opcion.equals("papeleria")) {
                    PapeleriaOpcional(sc);
                    break;
                }else if (opcion.equals("uniformes")) {
                    UniformesOpcional(sc);
                    break;
                    
                }else if (opcion.equals("salir")) {
                    System.out.println("Saliendo de Menu opcional...");
                        CobrosAnuales.main(args);
                    break;
                }else {
                    System.out.println("Opcion inválida. Intenta de nuevo.");
                }
            }
    }

    public static void PapeleriaOpcional(Scanner sc){
        String opcion;
        System.out.println("=== Menu de la Papeleria ===");

        System.out.println("hola");
        System.out.println("hola");
        System.out.println("hola");
        System.out.println("hola");
    }

    public static void UniformesOpcional(Scanner sc){
        System.out.println("=== Menu de Uniformes ===");

        System.out.println("hola");
        System.out.println("hola");
        System.out.println("hola");
        System.out.println("hola");
    }

}
