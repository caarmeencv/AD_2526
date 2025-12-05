import dao.ContactoDAO;
import dto.ContactoDTO;
import java.util.List;
import java.util.Scanner;

public class Contacto {

    private static final Scanner sc = new Scanner(System.in);
    private static final ContactoDAO ContactoDAO = new ContactoDAO();

    public static void main(String[] args) throws Exception {


        int opcion = 0;

        while (opcion != 8) {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> crearContacto();
                case 2 -> listarContacto();
                case 3 -> buscarContacto();
                case 4 -> modificarContacto();
                case 5 -> eliminarContacto();
                case 6 -> eliminarContacto();
                case 7 -> eliminarContacto();
                case 8 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Contactos ---");
        System.out.println("1. Crear contacto.");
        System.out.println("2. Listar contactos.");
        System.out.println("3. Buscar contactos.");
        System.out.println("4. Modificar contacto.");
        System.out.println("5. Eliminar contacto.");
        System.out.println("6. Cargar un CSV de contactos.");
        System.out.println("7. Añadir contacto a grupo.");
        System.out.println("8. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void listarContacto() {
        List<ContactoDTO> contactos = ContactoDAO.findAll();
        for(ContactoDTO contacto : contactos){
            System.out.println(contacto);
        }
    }

    private static void buscarContacto() {
        System.out.print("Introduce ID del actor: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO contacto = ContactoDAO.findById(id);
        if (contacto != null) {
            System.out.println(contacto);
        } else {
            System.out.println("Actor no encontrado.");
        }
    }

    private static void crearContacto() {
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce apellido: ");
        String email = sc.nextLine();
        ContactoDTO nuevo = new ContactoDTO();
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        ContactoDAO.create(nuevo);
        System.out.println("Actor creado con ID: " + nuevo.getID_Contacto());
    }

    private static void modificarContacto() {
        System.out.print("Introduce ID del actor a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO contacto = ContactoDAO.findById(id);
        if (contacto != null) {
            System.out.print("Nuevo nombre (" + contacto.getNombre() + "): ");
            String nombre = sc.nextLine();
            System.out.print("Nuevo apellido (" + contacto.getEmail() + "): ");
            String email = sc.nextLine();
            contacto.setNombre(nombre.isEmpty() ? contacto.getNombre() : nombre);
            contacto.setEmail(email.isEmpty() ? contacto.getEmail() : email);
            ContactoDAO.update(contacto);
            System.out.println("Actor actualizado.");
        } else {
            System.out.println("Actor no encontrado.");
        }
    }

    private static void eliminarContacto() {
        System.out.print("Introduce ID del actor a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDAO.delete(id);
        System.out.println("Actor borrado si existía.");
    }
}
