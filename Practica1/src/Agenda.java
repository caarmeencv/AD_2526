
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;

public class Agenda {

    Scanner teclado = new Scanner(System.in);

    private ArrayList<Contacto> Agenda;

    private String archivoAgenda = "agenda.dat";

    private String archivoSeguridad = "seguridad.dat";

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
            Agenda.add(new Contacto("Juan", "juan@gmail.com", "654892317"));
            Agenda.add(new Contacto("Alejandro", "alejandro@gmail.com", "759846321"));
            Agenda.add(new Contacto("María", "maria@gmail.com", "632145789"));
            guardarEnArchivo();
            System.out.println("Agenda creada con contactos de ejemplo.");
        } else {
            System.out.println("¡¡¡ Respuesta no válida !!!");
            crear();
        }
    }

    //Eliminar los contactos ya creados en la Agenda
    public void vaciar() {
        if (Agenda.isEmpty()) {
            System.out.println("La agenda no contiene ningún contacto");
        } else {
            Agenda.clear();
            guardarEnArchivo();
            System.out.println("La Agenda ha sido vaciada con éxito.");
        }
    }

    //Añadir un nuevo contacto
    public void anadir() {
        System.out.println("Para añadir un nuevo contacto, se necesitan los datos del mismo.");
        System.out.println("Nombre: ");
        String nombre = teclado.nextLine();

        for (Contacto c : Agenda) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("Ya existe un contacto con ese nombre.");
                anadir();
            }
        }

        String correo;
        while (true) {
            System.out.println("Correo electrónico: ");
            correo = teclado.nextLine();
            if (validarCorreo(correo)) {
                break;
            } else {
                System.out.println("Correo inválido!!!!!");
            }
        }

        String telefono;
        while (true) {
            System.out.println("Teléfono: ");
            telefono = teclado.nextLine();
            if (validarTelefono(telefono)) {
                break;
            } else {
                System.out.println("Telefono inválido!!!!!");
            }
        }

        Agenda.add(new Contacto(nombre, correo, telefono));
        guardarEnArchivo();
        System.out.println("El contacto se ha añadido con éxito.");
    }

    //Mostrar la agenda
    public void mostrarContactos() {
        //asumimos que todos los contactos están borrados
        boolean todosBorrados = true;
        //recorro todos los contactos de agenda
        for (Contacto c : Agenda) {

            //si no esta borrado, cambiamos el estado a false para indicar que hay algun contacto no borrado
            if (!c.isBorrado()) {
                todosBorrados = false;
                break; // sabemos  que por lo menos uno esta borrado, asi que no nos hace falta mas
            }
        }

        if (Agenda.isEmpty() || todosBorrados == true) {
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
                    if (c.isBorrado()) {
                        System.out.println("El contacto ha sido borrado");
                    } else {
                        System.out.print(" - ");
                        c.mostrarAgenda();
                    }
                    return;
                }
            }
            System.out.println("El contacto no existe.");
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
                    if (c.isBorrado()) {
                        System.out.println("El contacto está borrado.");
                        return;
                    }
                    System.out.println("Introduce los nuevos datos: ");
                    System.out.println("Nombre: ");
                    String nombre2 = teclado.nextLine();
                    c.setNombre(nombre2);
                    String correo;
                    while (true) {
                        System.out.println("Correo electrónico: ");
                        correo = teclado.nextLine();
                        if (validarCorreo(correo)) {
                            break;
                        } else {
                            System.out.println("Correo inválido!!!!!");
                        }
                    }

                    String telefono;
                    while (true) {
                        System.out.println("Teléfono: ");
                        telefono = teclado.nextLine();
                        if (validarTelefono(telefono)) {
                            break;
                        } else {
                            System.out.println("Telefono inválido!!!!!");
                        }
                    }
                    c.setTelefono(telefono);
                    guardarEnArchivo();

                    System.out.println("Contacto añadido correctamente.");
                    System.out.print(" - ");
                    c.mostrarAgenda();
                    return;
                }
            }
            System.out.println("El contacto no existe.");
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
                if (c.isBorrado()) {
                    System.out.println("Ese contacto ya está borrado.");
                    return;
                }
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    c.setBorrado(true);
                    guardarEnArchivo();
                    System.out.println("Contacto eliminado.");
                    return;
                }
            }

            System.out.println("No hay ningún contacto con ese nombre.");

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
                hayBorrados = true;
                break; // sabemos  que por lo menos uno esta borrado, asi que no nos hace falta mas
            }
        }

        //si la agenda esta vacia o si no hay algun borrado, no podremos recuperar ningun contacto
        if (Agenda.isEmpty() || hayBorrados == false) {
            System.out.println("No hay contactos por recuperar");
        } else {
            System.out.println("Para recuperar un contacto, se necesita el nombre del mismo.");
            System.out.println("Nombre: ");
            String nombre = teclado.nextLine();

            for (Contacto c : Agenda) {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    if(!c.isBorrado()){
                        System.out.println("Este contacto no ha sido borrado o ya se ha recuperado.");
                        return;
                    }
                    c.setBorrado(false);
                    guardarEnArchivo();
                    System.out.println("Contacto recuperado.");
                    System.out.print(" - ");
                    c.mostrarAgenda();
                    return;
                }
            }

            System.out.println("No hay ningún contacto con ese nombre.");

        }
    }

    //mostrar informacion del archivo
    public void mostrarArchivo() {
        try {
            File archivo = new File(archivoAgenda);
            System.out.println("Información del archivo agenda.dat: ");
            System.out.println(" - Ubicación del archivo: " + archivo.getAbsolutePath());
            System.out.println(" - Tamaño del archivo: " + archivo.length() + " bytes");
            System.out.println(" - Permiso de lectura del archivo: " + archivo.canRead());
            System.out.println(" - Permiso de escritura del archivo: " + archivo.canWrite());
            System.out.println(" - Permiso de ejecución del archivo: " + archivo.canExecute());
            long ultimaMod = archivo.lastModified();
            Date fecha = new Date(ultimaMod);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            System.out.println(" - Fecha y hora de la última modificación del archivo: " + sdf.format(fecha));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al mostrar la información del archivo: " + e.getMessage());
        }
    }

    //hacer una copia de seguridad
    public void copiaSeguridad() {
        Path origen = Paths.get(archivoAgenda);
        Path destino = Paths.get(archivoSeguridad);

        try {
            // Copia el archivo (si ya existe, lo sobreescribe)
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Copia de seguridad creada correctamente en: " + destino.toAbsolutePath());
        } catch (NoSuchFileException e) {
            System.out.println("No existe el archivo de agenda, no se puede hacer copia de seguridad.");
        } catch (IOException e) {
            System.out.println("Error al crear la copia de seguridad: " + e.getMessage());
        }
    }

    //restaurar copia de seguridad
    public void restaurarCopia() {
        Path origen = Paths.get(archivoSeguridad);
        Path destino = Paths.get(archivoAgenda);

        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Se ha restaurado la agenda desde la copia de seguridad.");

            // Recargar contactos desde el archivo restaurado
            Agenda.clear();
            try (BufferedReader br = new BufferedReader(new FileReader(archivoAgenda))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    String[] partes = linea.split(";");
                    if (partes.length == 4) {
                        String nombre = partes[0];
                        String correo = partes[1];
                        String telefono = partes[2];
                        boolean borrado = Boolean.parseBoolean(partes[3]);
                        Contacto c = new Contacto(nombre, correo, telefono);
                        c.setBorrado(borrado);
                        Agenda.add(c);
                    }
                }
            }

            System.out.println("Contactos cargados correctamente tras la restauración.");

        } catch (NoSuchFileException e) {
            System.out.println("No existe la copia de seguridad, no se puede restaurar.");
        } catch (IOException e) {
            System.out.println("Error al restaurar la agenda: " + e.getMessage());
        }
    }

    //ver mas opciones
    public void masOpciones() {
        int opcion2;
        do {
            opcion2 = dameOpcion2();
            switch (opcion2) {
                case 1:
                    mostrarArchivo();
                    break;
                case 2:
                    copiaSeguridad();
                    break;
                case 3:
                    restaurarCopia();
                    break;
                case 4:
                    System.out.println("Submenu cerrado");
                    break;
                default:
                    System.out.println("OPCION Erronea");
                    break;
            }
        } while (opcion2 != 4);

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

    //Validar correo electrónico
    private boolean validarCorreo(String correo) {
        String compCorreo = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return correo.matches(compCorreo);
    }

    //Validar número de teléfono
    private boolean validarTelefono(String telefono) {
        String compTelefono = "\\d{9}"; // solo 9 dígitos
        return telefono.matches(compTelefono);
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
