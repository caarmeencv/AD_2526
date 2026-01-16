package service;

import dao.ContactoDAO;
import dao.GrupoDAO;
import dto.ContactoDTO;
import dto.GrupoDTO;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import utils.Validar;

public class Contacto {

    private static final Scanner sc = new Scanner(System.in);
    private static final ContactoDAO ContactoDAO = new ContactoDAO();
    private static final GrupoDAO GrupoDAO = new GrupoDAO();

    public static void main(String[] args) throws Exception {

    }

    public static void menuContacto() {
        int opcion = 0;

        // Si se mete una letra en vez de un número, no cerrará el programa
        while (opcion != 9) {
            try {
                mostrarMenuContacto();
                opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1 ->
                        crearContacto();
                    case 2 ->
                        listarContacto();
                    case 3 ->
                        buscarContacto();
                    case 4 ->
                        modificarContacto();
                    case 5 ->
                        eliminarContacto();
                    case 6 ->
                        verGruposContacto();
                    case 7 ->
                        cargarCSV();
                    case 8 ->
                        anadirContactoAGrupo();
                    case 9 ->
                        System.out.println("Volviendo al menú principal...");
                    default ->
                        System.out.println("Opción no válida. Por favor, elige una opción del 1 al 9.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                sc.nextLine();
            }
        }

    }

    public static void mostrarMenuContacto() {
        System.out.println("\nMENÚ DE CONTACTO");
        System.out.println("====================");
        System.out.println("1. Crear contacto.");
        System.out.println("2. Listar contactos.");
        System.out.println("3. Buscar contacto.");
        System.out.println("4. Modificar contacto.");
        System.out.println("5. Eliminar contacto.");
        System.out.println("6. Ver grupos de un contacto.");
        System.out.println("7. Cargar un CSV de contactos.");
        System.out.println("8. Añadir contacto a grupo.");
        System.out.println("9. Volver al menú principal.");
        System.out.print("Elige una opción: ");
    }

    public static void listarContacto() {
        List<ContactoDTO> contactos = ContactoDAO.findAll();

        //Comprobar si la lista está vacía
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la Agenda. Por favor, inserta uno primero.");
            return;
        }

        //Mostrar los contactos
        System.out.println("CONTACTOS DE LA AGENDA");
        System.out.println("======================");
        //Recorrer la lista e imprimir cada contacto
        for (ContactoDTO contacto : contactos) {
            System.out.println(contacto);
        }
    }

    public static void buscarContacto() {
        //Pedir al usuario un campo o pista para buscar, deben aparecer en cualquier campo y si hay varios resultados mostrarlos todos.
        System.out.print("Introduce un campo o una pista del contacto: ");
        String hint = sc.nextLine();

        //Buscar en la base de datos con el método findBySomething de ContactoDAO
        List<ContactoDTO> resultados = ContactoDAO.findBySomething(hint);

        if (resultados.isEmpty()) {
            System.out.println("Contacto no encontrado.");
        } else {
            System.out.println("Encontrados " + resultados.size() + " contacto(s):");
            for (ContactoDTO contacto : resultados) {
                System.out.println(contacto); 
            }
        }
    }


    public static void crearContacto() {

        String nombre, email, telefono;

        //Comprobar validez del nombre con Validar.validarNombreContacto
        while (true) {
            System.out.print("Introduce nombre: ");
            nombre = sc.nextLine();
            if (Validar.validarNombreContacto(nombre)) {
                break;
            }
        }

        //Comprobar validez del email con Validar.validarEmail
        while (true) {
            System.out.print("Introduce email: ");
            email = sc.nextLine();
            if (Validar.validarEmail(email)) {
                break;
            }
        }

        //Comprobar validez del teléfono con Validar.validarTelefono
        while (true) {
            System.out.print("Introduce telefono: ");
            telefono = sc.nextLine();
            if (Validar.validarTelefono(telefono)) {
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

    public static void modificarContacto() {
        //Que muestre los contactos primero, para que el usuario vea los IDs
        listarContacto();
        System.out.print("\nIntroduce ID del contacto a modificar: ");
        int id = sc.nextInt();
        sc.nextLine();
        ContactoDTO contacto = ContactoDAO.findById(id);
        if (contacto != null) {
            String nombre, email, telefono;
            while (true) {
                System.out.print("Introduce nombre (Para mantener nombre, no introducir nada): ");
                nombre = sc.nextLine();

                //Si el usuario no introduce nada, mantener el nombre actual
                if (nombre.isEmpty()) {
                    nombre = contacto.getNombre();
                    break;
                }

                //Comprobar validez del nombre con Validar.validarNombreContacto
                if (Validar.validarNombreContacto(nombre)) {
                    break;
                }
            }

            while (true) {
                System.out.print("Introduce email (Para mantener email, no introducir nada): ");
                email = sc.nextLine();
                
                //Si el usuario no introduce nada, mantener el email actual
                if (email.isEmpty()) {
                    email = contacto.getEmail();
                    break;
                }

                //Comprobar validez del email con Validar.validarEmail
                if (Validar.validarEmail(email)) {
                    break;
                }
            }

            while (true) {
                System.out.print("Introduce telefono (Para mantener nombre, no introducir nada): ");
                telefono = sc.nextLine();

                //Si el usuario no introduce nada, mantener el teléfono actual
                if (telefono.isEmpty()) {
                    telefono = contacto.getTelefono();
                    break;
                }

                //Comprobar validez del teléfono con Validar.validarTelefono
                if (Validar.validarTelefono(telefono)) {
                    break;
                }
            }

            contacto.setNombre(nombre);
            contacto.setEmail(email);
            contacto.setTelefono(telefono);
            ContactoDAO.update(contacto);
            System.out.println("Contacto actualizado.");
        } else {
            System.out.println("No existe ningún contacto con ID " + id + ".");
        }
    }

    public static void eliminarContacto() {
        //Que muestre los contactos primero, para que el usuario vea los IDs
        listarContacto();
        System.out.print("\nIntroduce ID del contacto a borrar: ");
        int id = sc.nextInt();
        sc.nextLine();

        ContactoDAO contactoDAO = new ContactoDAO();
        dto.ContactoDTO Contacto = contactoDAO.findById(id);

        //Comprobar si el contacto existe
        if (Contacto == null) {
            System.out.println("No existe ningún contacto con ID " + id + ".");
        } else {
            contactoDAO.delete(id);
            System.out.println("Contacto '" + Contacto.getNombre() + "' (ID " + id + ") borrado correctamente.");
        }
    }

    public static void anadirContactoAGrupo() {
        //Que muestre los contactos primero, para que el usuario vea los IDs
        listarContacto();
        
        int contactoId;
        
        while (true) {
            System.out.print("\nIntroduce ID del contacto: ");
            contactoId = sc.nextInt();
            sc.nextLine();

            //Comprobar si el contacto existe
            if (ContactoDAO.findById(contactoId) != null) {
                break;
            } else {
                System.out.println("No existe un contacto con ID = " + contactoId + ".");
            }
        }

        //Que muestre los grupos primero, para que el usuario vea los IDs
        Grupo.listarGrupos();

        int grupoId;
        
        while (true) {
            System.out.print("\nIntroduce ID del grupo: ");
            grupoId = sc.nextInt();
            sc.nextLine();

            //Comprobar si el grupo existe
            if (GrupoDAO.findById(grupoId) != null) {
                break;
            } else {
                System.out.println("No existe un grupo con ID = " + grupoId + ".");
            }
        }
       
        //Insertar en la tabla Contacto_Grupo para añadir la relacion
        try {
            ContactoDAO.addContactoToGrupo(contactoId, grupoId);
            System.out.println("Contacto añadido al grupo correctamente.");
        } catch (RuntimeException e) {
            System.out.println("Error al añadir al grupo: " + e.getMessage());
        }
    }

    public static void verGruposContacto() {
        //Que muestre los contactos primero, para que el usuario vea los IDs
        listarContacto();
        System.out.print("\nIntroduce ID del contacto: ");
        int id = sc.nextInt();
        sc.nextLine();
        List<GrupoDTO> grupos = ContactoDAO.findGruposOfContacto(id);
        //Comprobar si la lista está vacía
        if (grupos == null || grupos.isEmpty()) {
            System.out.println("No hay grupos para este contacto o el contacto no existe.");
        } else {
            for (GrupoDTO grupo : grupos) {
                System.out.println(grupo);
            }
        }
    }

    public static void cargarCSV() {
        List<ContactoDTO> contactos = utils.LeerCSV.loadContactosFromCsv();
        ContactoDAO contactoDAO = new ContactoDAO();

        //Comprobar si la lista está vacía
        if (contactos.isEmpty()) {
            System.out.println("No se han encontrado contactos en el CSV.");
            return;
        }

        //El contador es para saber cuántos contactos se han cargado correctamente e imprimirlo al final
        int contador = 0;

        for (ContactoDTO contacto : contactos) {
            try {
                contactoDAO.create(contacto);
                contador ++;
            } catch (Exception e) {
                System.out.println("Error al cargar el contacto: " + e.getMessage());
            }
        }

        System.out.println("Numero total de contactos cargados: " + contador);
    }
}
