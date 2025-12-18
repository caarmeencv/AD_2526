import java.util.InputMismatchException;
import java.util.Scanner;
import service.Contacto;
import service.Grupo;

public class Agenda {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int opcion = 0;

        // Si se mete una letra en vez de un número, no cerrará el programa
        while (opcion != 3) {
            mostrarMenuPrincipal();
            try {
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 -> Contacto.menuContacto();
                    case 2 -> Grupo.menuGrupo();
                    case 3 -> System.out.println("Saliendo de la agenda...");
                    default -> System.out.println("Opción no válida. Por favor, elige una opción del 1 al 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                sc.nextLine();
            }
        }
        sc.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\nMENÚ PRINCIPAL");
        System.out.println("1. Menú de contactos");
        System.out.println("2. Menú de grupos");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }
}
