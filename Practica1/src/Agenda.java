
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Agenda {

    Scanner teclado = new Scanner(System.in);

    private ArrayList<Contacto> Agenda;

    private final String archivoAgenda = "agenda.dat";

    //private final String archivoAgenda = "agenda.dat";
    public Agenda() {
        Agenda = new ArrayList<Contacto>();
    }

    //Crear la agenda y que pregunte si la queremos vacia o con contactos de ejemplo
    public void crear() {
        System.out.println("¿Deseas crear una agenda con contactos o sin contactos (c/s)?");
        String respuesta = teclado.nextLine();
        if (respuesta.equalsIgnoreCase("s")) {
            Agenda.clear();
            guardarEnArchivo();
            System.out.println("Agenda vacía creada correctamente.");
        } else if (respuesta.equalsIgnoreCase("c")) {
            Agenda.clear();
            Agenda.add(new Contacto("Juan", "juan@gmail.com", 654892317));
            Agenda.add(new Contacto("Alejandro", "alejandro@gmail.com", 759846321));
            Agenda.add(new Contacto("María", "maria@gmail.com", 632145789));
            guardarEnArchivo();
            System.out.println("Agenda creada con contactos de ejemplo.");
        } else {
            System.out.println("¡¡¡ Respuesta no válida !!!");
            crear();
        }
    }

    //Eliminar los contactos ya creados en la Agenda
    public void vaciar() {
        Agenda.clear();
        guardarEnArchivo();
        System.out.println("La Agenda ha sido vaciada con éxito.");
    }

    //Añadir un nuevo contacto
    public void anadir() {
        System.out.println("Para añadir un nuevo contacto, se necesitan los datos del mismo.");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();
        System.out.println("Correo electrónico: ");
        String correo = teclado.nextLine();
        System.out.println("Teléfono: ");
        int telefono = teclado.nextInt();
        teclado.nextLine();

        for (Contacto c : Agenda) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ya existe un contacto con ese nombre.");
                anadir();
            }
        }

        Agenda.add(new Contacto(nombre, correo, telefono));
        guardarEnArchivo();
        System.out.println("El contacto se ha añadido con éxito.");
    }

    //Mostrar la agenda
    public void mostrarContactos() {
        if (Agenda.isEmpty()) {
            System.out.println("La agenda no contiene ningún contacto");
        } else {
            int contador = 0;
            System.out.println("Contactos:");
            for (Contacto c : Agenda) {
                if (!c.isBorrado()) {
                    System.out.print(" - ");
                    c.mostrarAgenda();
                    contador++;
                }
            }
            System.out.println("Total de contactos en la agenda: " + contador);
        }
    }

    //Consultar contacto
    public void consultarContacto() {
        if (Agenda.isEmpty()) {
            System.out.println("La agenda no contiene ningún contacto");
        } else {
            System.out.println("Para consultar un contacto, se necesita el nombre del mismo.");
            System.out.println("Nombre: ");
            String nombre = teclado.nextLine();

            for (Contacto c : Agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    c.mostrarAgenda();
                }
            }
        }
    }

    //modificar contacto
    public void modificarContacto() {
        if (Agenda.isEmpty()) {
            System.out.println("La agenda no contiene ningún contacto");
        } else {
            System.out.println("Para modificar un contacto, se necesita el nombre del mismo.");
            System.out.println("Nombre: ");
            String nombre = teclado.nextLine();

            for (Contacto c : Agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    System.out.println("Nombre: ");
                    String nombre2 = teclado.nextLine();
                    c.setNombre(nombre2);
                    System.out.println("Correo electrónico: ");
                    String correo = teclado.nextLine();
                    c.setCorreo(correo);
                    System.out.println("Teléfono: ");
                    int telefono = teclado.nextInt();
                    teclado.nextLine();
                    c.setTelefono(telefono);
                    guardarEnArchivo();

                    System.out.println("Contacto añadido correctamente.");
                    c.mostrarAgenda();
                }
            }
        }
    }

    //Borrar contacto
    public void borrar() {
        if (Agenda.isEmpty()) {
            System.out.println("La agenda no contiene ningún contacto");
        } else {
            System.out.println("Para borrar un contacto, se necesita el nombre del mismo");
            System.out.println("Nombre: ");
            String nombre = teclado.nextLine();

            for (Contacto c : Agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    c.setBorrado(true);
                    guardarEnArchivo();
                }
            }

            System.out.println("Contacto eliminado.");

        }
    }

    //recuperar contacto de borrados
    public void recuperarContacto() {
        //asumimos que no hay ningún contacto borrado en la agenda
        boolean hayBorrados = false; 

        //recorro todos los contactos de agenda
        for (Contacto c : Agenda) {

            //si esta borrado, cambiamos el estado a true para indicar que hay algun borrado
            if (c.isBorrado()) {
                hayBorrados = false;
                break; // sabemos  que por lo menos uno esta borrado, asi que no nos hace falta mas
            }
        }

        //si la agenda esta vacia o si estan todos borrados, no podremos recuperar ningun contacto
        if (Agenda.isEmpty() || hayBorrados) {
            System.out.println("No hay contactos por recuperar");
        } else {
            System.out.println("Para recuperar un contacto, se necesita el nombre del mismo.");
            System.out.println("Nombre: ");
            String nombre = teclado.nextLine();

            for (Contacto c : Agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    c.setBorrado(false);
                    guardarEnArchivo();
                }
            }

            System.out.println("Contacto recuperado.");

        }
    }

    //ver mas opciones
    public void masOpciones() {

    }

    //Primer menu
    public static void Menu() {
        System.out.println("\n    MENU    ");
        System.out.println("===============");
        System.out.println("1- Crear agenda.");
        System.out.println("2- Añadir contacto.");
        System.out.println("3- Consultar contacto.");
        System.out.println("4- Modificar contacto.");
        System.out.println("5- Borrar contacto.");
        System.out.println("6- Restaurar contacto.");
        System.out.println("7- Ver contactos.");
        System.out.println("8- Vaciar agenda.");
        System.out.println("9- Ver más opciones.");
        System.out.println("10- Salir.\n");
    }

    //Submenu
    public static void Menu2() {
        System.out.println("\n    SUBMENU    ");
        System.out.println("===============");
        System.out.println("1- Mostrar información del archivo.");
        System.out.println("2- Hacer copia de seguridad.");
        System.out.println("3- Restaurar copia de seguridad.");
        System.out.println("4- Volver al anterior menú.\n");
    }

    //Para que nos deje seleccionar opcion después de ambos menús
    public int dameOpcion() {
        int opcion;
        Menu();
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    public int dameOpcion2() {
        int opcion;
        Menu2();
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    //Para que nos guarde la agenda y los borrados en un archivo
    // Guardar la agenda en el archivo
    public void guardarEnArchivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoAgenda))) {
            for (Contacto c : Agenda) {
                //añadimos el c.isActivo() para que en el .dat refleje si está borrado o no (para poder recuperarlo)
                pw.println(c.getNombre() + ";" + c.getCorreo() + ";" + c.getTelefono() + ";" + c.isBorrado());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda: " + e.getMessage());
        }
    }

}
