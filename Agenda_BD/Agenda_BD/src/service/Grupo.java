package service;

import dao.GrupoDAO;
import dto.ContactoDTO;
import dto.GrupoDTO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import utils.Validar;

public class Grupo {

    private static final Scanner sc = new Scanner(System.in);
    private static final GrupoDAO grupoDAO = new GrupoDAO();

    public static void main(String[] args) throws Exception {

    }

    public static void menuGrupo() {

        int opcion = 0;

        // Si se mete una letra en vez de un número, no cerrará el programa
        while (opcion != 6) {
            try {
                mostrarMenuGrupo();
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 ->
                        crearGrupo();
                    case 2 ->
                        listarGrupos();
                    case 3 ->
                        modificarGrupo();
                    case 4 ->
                        eliminarGrupo();
                    case 5 ->
                        verContactosGrupo(); 
                    case 6 ->
                        System.out.println("Volviendo al menú principal...");
                    default ->
                        System.out.println("Opción no válida.Por favor, elige una opción del 1 al 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, elige una opción del 1 al 6.");
                sc.nextLine();
            }
        }
    }

    public static void mostrarMenuGrupo() {
        System.out.println("\nMENÚ DE GRUPO");
        System.out.println("================");
        System.out.println("1. Crear grupo.");
        System.out.println("2. Listar grupos.");
        System.out.println("3. Modificar grupo.");
        System.out.println("4. Eliminar grupo.");
        System.out.println("5. Ver contactos de un grupo.");
        System.out.println("6. Volver al menú principal.");
        System.out.print("Elige una opción: ");
    }

    public static void listarGrupos() {
        List<GrupoDTO> grupos = grupoDAO.findAll();

        //Comprobar si la lista está vacía
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos en la agenda. Por favor, inserta uno primero.");
            return;
        }

        //Mostrar los grupos
        System.out.println("GRUPOS DE LA AGENDA");
        System.out.println("===================");
        //Recorrer la lista e imprimir cada grupo
        for (GrupoDTO grupo : grupos) {
            System.out.println(grupo);
        }
    }

    public static void crearGrupo() {

        String nombre;

        while (true) {
            System.out.print("Introduce un nombre para el nuevo grupo: ");
            nombre = sc.nextLine();

            //Comprobar validez del nombre con la función de Validar.validarNombreGrupo
            if (Validar.validarNombreGrupo(nombre)) {
                break;
            }

        }

        GrupoDTO nuevo = new GrupoDTO();
        nuevo.setNombre(nombre);
        grupoDAO.create(nuevo);
        System.out.println("Grupo creado con ID: " + nuevo.getID_Grupo());
    }

    public static void modificarGrupo() {
        //Que muestre los grupos primero, para que el usuario vea los IDs
        listarGrupos();
        System.out.print("\nIntroduce ID del grupo a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();

        //Comprobar si el grupo existe
        GrupoDTO grupo = grupoDAO.findById(id);
        if (grupo == null) {
            System.out.println("El grupo con ID = " + id + " no existe.");
            return;
        }

        String nombre;

        while (true) {
            System.out.print("Introduce nombre nuevo (Para mantener nombre, no introducir nada): ");
            nombre = sc.nextLine();

            //Si no se introduce nada, mantener el nombre actual
            if (nombre.isEmpty()) {
                nombre = grupo.getNombre();
                break;
            }

            //Comprobar validez del nombre con la función de Validar.validarNombreGrupo
            if (Validar.validarNombreGrupo(nombre)) {
                break;
            }
        }

        grupo.setNombre(nombre);

        
    }

    public static void eliminarGrupo() {
        //Que muestre los grupos primero, para que el usuario vea los IDs
        listarGrupos();
        System.out.print("\nIntroduce ID del grupo a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();

        GrupoDAO GrupoDAO = new GrupoDAO();
        dto.GrupoDTO Grupo = GrupoDAO.findById(id);

        //Comprobar si el grupo existe
        if (Grupo == null) {
            System.out.println("No existe ningún contacto con ID = " + id + ".");
        } else {
            GrupoDAO.delete(id);
            System.out.println("Grupo con ID = " + id + " eliminado correctamente.");
        }
    }

    public static void verContactosGrupo() {
        //Que muestre los grupos primero, para que el usuario vea los IDs
        listarGrupos();
        System.out.print("\nIntroduce ID de un grupo: ");
        int id = sc.nextInt();
        sc.nextLine();

        //Comprobar si el grupo existe
        GrupoDTO grupo = grupoDAO.findById(id);
        if (grupo == null) {
            System.out.println("El grupo con ID = " + id + " no existe.");
            return;
        }

        List<ContactoDTO> contactos = grupoDAO.findContactsInGroup(id);

        //Comprobar si la lista está vacía
        if (contactos.isEmpty()) {
            System.out.println("El grupo no tiene contactos.");
            return;
        }

        //Mostrar los contactos del grupo
        System.out.println("Contactos en el grupo " + id + ":");
        for (ContactoDTO c : contactos) {
            System.out.println(c);
        }
    }
}