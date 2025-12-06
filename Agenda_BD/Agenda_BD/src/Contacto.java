import dao.ContactoDAO;
import dao.GrupoDAO;
import dto.ContactoDTO;
import utils.Validation;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Contacto {

    private static final Scanner sc = new Scanner(System.in);
    private static final ContactoDAO ContactoDAO = new ContactoDAO();
    private static final GrupoDAO GrupoDAO = new GrupoDAO();

    public static void main(String[] args) throws Exception {


        int opcion = 0;

        while (opcion != 8) {
            try{
                mostrarMenu();
                opcion = sc.nextInt();
                sc.nextLine(); // Consumir salto de línea

                switch (opcion) {
                    case 1 -> crearContacto();
                    case 2 -> listarContacto();
                    case 3 -> buscarContacto();
                    case 4 -> modificarContacto();
                    case 5 -> eliminarContacto();
                    case 6 -> crearContacto();
                    case 7 -> anadirContactoAGrupo();
                    case 8 -> System.out.println("Saliendo...");
                    default -> System.out.println("Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                sc.nextLine(); 
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
        System.out.print("Introduce ID del contacto: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO contacto = ContactoDAO.findById(id);
        if (contacto != null) {
            System.out.println(contacto);
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void crearContacto() {

        String nombre, email, telefono;
        
        while (true) { 
            System.out.print("Introduce nombre: ");
            nombre = sc.nextLine();
            if (!Validation.validarNombreContacto(nombre)) {
                System.out.println("Nombre no válido. El nombre no puede estar vacío y solo debe contener letras y espacios.");
                return;
            } else {
                break;
            }
        }
        
        while (true) { 
            System.out.print("Introduce email: ");
            email = sc.nextLine();
            if(!Validation.validarCorreo(email)) {
                System.out.println("Correo no válido. El correo debe tener el siguiente formato: usuario@dominio.extension ");
                return;
            } else {
                break;
            }
        }

        while (true) { 
            System.out.print("Introduce telefono: ");
            telefono = sc.nextLine();
            if(!Validation.validarTelefono(telefono)) {
                System.out.println("Teléfono no válido. El teléfono debe contener 9 dígitos.");
                return;
            } else {
                break;
            }
        }

        ContactoDTO nuevo = new ContactoDTO();
        nuevo.setNombre(nombre);
        nuevo.setEmail(email);
        nuevo.setTelefono(telefono);
        ContactoDAO.create(nuevo);
        System.out.println("Contacto creado con ID: " + nuevo.getID_Contacto());
    }

    private static void modificarContacto() {
        System.out.print("Introduce ID del contacto a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO contacto = ContactoDAO.findById(id);
        if (contacto != null) {
            String nombre, email, telefono;
            while (true) { 
            System.out.print("Introduce nombre: ");
            nombre = sc.nextLine();
            if (!Validation.validarNombreContacto(nombre)) {
                System.out.println("Nombre no válido. El nombre no puede estar vacío y solo debe contener letras y espacios.");
                return;
            } else {
                break;
            }
        }
        
        while (true) { 
            System.out.print("Introduce email: ");
            email = sc.nextLine();
            if(!Validation.validarCorreo(email)) {
                System.out.println("Correo no válido. El correo debe tener el siguiente formato: usuario@dominio.extension ");
                return;
            } else {
                break;
            }
        }

        while (true) { 
            System.out.print("Introduce telefono: ");
            telefono = sc.nextLine();
            if(!Validation.validarTelefono(telefono)) {
                System.out.println("Teléfono no válido. El teléfono debe contener 9 dígitos.");
                return;
            } else {
                break;
            }
        }

            contacto.setNombre(nombre.isEmpty() ? contacto.getNombre() : nombre);
            contacto.setEmail(email.isEmpty() ? contacto.getEmail() : email);
            contacto.setTelefono(telefono.isEmpty() ? contacto.getTelefono() : telefono);
            ContactoDAO.update(contacto);
            System.out.println("Contacto actualizado.");
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    private static void eliminarContacto() {
        System.out.print("Introduce ID del contacto a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDAO.delete(id);
        System.out.println("Contacto borrado si existía.");
    }

    private static void anadirContactoAGrupo() {

        System.out.print("Introduce ID del contacto: ");
        int contactoId = sc.nextInt();
        sc.nextLine();

        System.out.print("Introduce ID del grupo: ");
        int grupoId = sc.nextInt();
        sc.nextLine();

        //Comproar si el contacto existe o no
        if (ContactoDAO.findById(contactoId) == null) {
            System.out.println("No existe un contacto con ese ID.");
            return;
        }

        //Comprobar si el grupo existe o no
        if (GrupoDAO.findById(grupoId) == null) {
            System.out.println("No existe un grupo con ese ID.");
            return;
        }

        //Insertar en la tabla Contacto_Grupo para añadir la relacion
        try {
            ContactoDAO.addContactoToGrupo(contactoId, grupoId);
            System.out.println("Contacto añadido al grupo correctamente.");
        } catch (RuntimeException e) {
            System.out.println("Error al añadir al grupo: " + e.getMessage());
        }
    }
}
