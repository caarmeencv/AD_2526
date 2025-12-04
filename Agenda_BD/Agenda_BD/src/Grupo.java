import dao.GrupoDAO;
import dto.GrupoDTO;
import dto.ContactoDTO;
import java.util.List;
import java.util.Scanner;

public class Grupo {

    private static final Scanner sc = new Scanner(System.in);
    private static final GrupoDAO grupoDAO = new GrupoDAO();

    public static void main(String[] args) throws Exception {

        int opcion = 0;

        while (opcion != 6) {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir salto de línea

            switch (opcion) {
                case 1 -> crearGrupo();
                case 2 -> listarGrupos();
                case 3 -> modificarGrupo();
                case 4 -> eliminarGrupo();
                case 5 -> verContactosGrupo(); // ahora muestra contactos del grupo
                case 6 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        }

        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Grupo ---");
        System.out.println("1. Crear grupo.");
        System.out.println("2. Listar grupos.");
        System.out.println("3. Modificar grupo.");
        System.out.println("4. Eliminar grupo.");
        System.out.println("5. Ver contactos de un grupo.");
        System.out.println("6. Salir");
        System.out.print("Elige una opción: ");
    }

    private static void listarGrupos() {
        List<GrupoDTO> grupos = grupoDAO.findAll();
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos.");
            return;
        }
        for (GrupoDTO grupo : grupos) {
            System.out.println(grupo);
        }
    }
/*
    private static void buscarGrupoPorID() {
        System.out.print("Introduce ID del grupo: ");
        int id = sc.nextInt();
        sc.nextLine();
        GrupoDTO grupo = grupoDAO.findById(id);
        if (grupo != null) {
            System.out.println(grupo);
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }
*/
    private static void crearGrupo() {
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        GrupoDTO nuevo = new GrupoDTO();
        nuevo.setNombre(nombre);
        grupoDAO.create(nuevo);
        System.out.println("Grupo creado con ID: " + nuevo.getID_Grupo());
    }

    private static void modificarGrupo() {
        System.out.print("Introduce ID del grupo a actualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        GrupoDTO grupo = grupoDAO.findById(id);
        if (grupo != null) {
            System.out.print("Nuevo nombre (" + grupo.getNombre() + "): ");
            String nombre = sc.nextLine();
            grupo.setNombre(nombre.isEmpty() ? grupo.getNombre() : nombre);
            grupoDAO.update(grupo);
            System.out.println("Grupo actualizado.");
        } else {
            System.out.println("Grupo no encontrado.");
        }
    }

    private static void eliminarGrupo() {
        System.out.print("Introduce ID del grupo a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();
        grupoDAO.delete(id);
        System.out.println("Grupo borrado si existía.");
    }

    private static void verContactosGrupo() {
        System.out.print("Introduce ID del grupo: ");
        int id = sc.nextInt();
        sc.nextLine();
        List<ContactoDTO> contactos = grupoDAO.findContactsInGroup(id);
        if (contactos == null || contactos.isEmpty()) {
            System.out.println("No hay contactos en este grupo o el grupo no existe.");
            return;
        }
        System.out.println("Contactos en el grupo " + id + ":");
        for (ContactoDTO c : contactos) {
            System.out.println(c);
        }
    }
}
