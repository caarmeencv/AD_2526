
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

    //archivo donde se guarda la agenda
    private String archivoAgenda = "agenda.dat";

    //archivo donde se hará la copia de seguridad
    private String archivoSeguridad = "seguridad.dat";

    //contructor
    public Agenda() {
        Agenda = new ArrayList<Contacto>();
    }

    //Crear la agenda y que pregunte si la queremos vacía o con contactos de ejemplo
    public void crear() {
        System.out.println("¿Deseas crear una agenda con contactos o sin contactos (c/s)?");
        String respuesta = teclado.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            //crea la agenda vacia y guarda el archivo en agenda.dat
            Agenda.clear();
            guardarEnArchivo();
            System.out.println("Agenda vacía creada correctamente.");

        } else if (respuesta.equalsIgnoreCase("c")) {
            //crea la agenda con 3 contactos y lo guarda en agenda.dat
            Agenda.clear();
            Agenda.add(new Contacto("Juan", "juan@gmail.com", "654892317"));
            Agenda.add(new Contacto("Alejandro", "alejandro@gmail.com", "759846321"));
            Agenda.add(new Contacto("Alba", "alba@gmail.com", "632145789"));
            guardarEnArchivo();
            System.out.println("Agenda creada con contactos de ejemplo.");

        } else {
            //si se añade otra opcion que no sea c o s, se volverá al método recursivamente
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
        //recorre la lista
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                //si el nombre de algún contacto coincide con el nombre dado, volvemos al metodo recursivamente para que nos pida otro nombre
                System.out.println("Ya existe un contacto con ese nombre.");
                anadir();
            }
        }

        String correo;
        while (true) {
            //comprobamos que sea un correo valido, si es valido salimos del bucle, si no es valido, nos quedamos hasta que sea
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
            //comprobamos que sea un telefono valido, si es valido salimos del bucle, si no es valido, nos quedamos hasta que sea
            System.out.println("Teléfono: ");
            telefono = teclado.nextLine();
            if (validarTelefono(telefono)) {
                break;
            } else {
                System.out.println("Telefono inválido!!!!!");
            }
        }

        //cuando tengamos todo, se añade el nuevo contacto y se guarda en agenda.dat
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
            //contador para indicar cuantos contactos hay
            int contador = 0;
            System.out.println("Contactos:");
            for (Contacto c : Agenda) {
                //se recorre toda la lista y si no está borrado, se imprime el contacto en la consola y se suma al contador
                if (!c.isBorrado()) {
                    System.out.print(" - ");
                    c.mostrarAgenda();
                    contador++;
                }
            }
            //al acabar de recorrer la agenda se muestra el total de contactos
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
                        //si el contacto está borrado, no aparecen sus datos por pantalla
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
                        //para comprobar si el correo dado es valido
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
                        //para comprobar si el telefono dado es valido
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

    //recuperar contacto borrado
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
                        //si el contacto no esta borrado
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
            //guarda en ultimaMod el tiempo desde la ultima modificacion en long
            long ultimaMod = archivo.lastModified();
            //representa la fecha y hora de la última modificación en una forma manejable
            Date fecha = new Date(ultimaMod);
            //crea un formato para mostrar la fecha en dia/mes/año y hora:minuto:segundo
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            //format.format(fecha) es como decir "formatea la fecha usando el formato definido en el SimpleDateFormat"
            System.out.println(" - Fecha y hora de la última modificación del archivo: " + format.format(fecha));
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al mostrar la información del archivo: " + e.getMessage());
        }
    }

    //hacer una copia de seguridad
    public void copiaSeguridad() {
        Path origen = Paths.get(archivoAgenda);
        Path destino = Paths.get(archivoSeguridad);

        try {
            // Copia el archivo (si ya existe, lo sobreescribe - StandardCopyOption.REPLACE_EXISTING)
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
            // Copia el archivo (si ya existe, lo sobreescribe - StandardCopyOption.REPLACE_EXISTING)
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Se ha restaurado la agenda desde la copia de seguridad.");

            // Borrar los contactos de la agenda antes de restaurar
            Agenda.clear();

            try (BufferedReader br = new BufferedReader(new FileReader(archivoAgenda))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    //cada linea supone un contacto y cada parte del contacto está separada por ;, con split dividimos por partes separando las mismas por el ;
                    String[] partes = linea.split(";");
                    //en total hay 4 partes: nombre, correo, telefono y si esta borrado o no. El if comprueba que hayan exactamente 4 partes
                    if (partes.length == 4) {
                        //para definir cada parte, no empieza en 1 sino en 0
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
        //método para definir las opciones del submenu
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
    public static void menu() {
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
    public static void menu2() {
        System.out.println("\n    SUBMENU    ");
        System.out.println("===============");
        System.out.println("1- Mostrar información del archivo.");
        System.out.println("2- Hacer copia de seguridad.");
        System.out.println("3- Restaurar copia de seguridad.");
        System.out.println("4- Volver al anterior menú.\n");
    }

    //Para que nos deje seleccionar opcion después de ambos menús. En ambos métodos se mostrarían los menus (menu() y menu2()) y nos pediría que escribamos en el teclado una opción
    public int dameOpcion() {
        int opcion;
        menu();
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    public int dameOpcion2() {
        int opcion;
        menu2();
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    //Validar correo electrónico
    private boolean validarCorreo(String correo) {
        String comprobarCorreo = ".+@.+\\..+";
        /* . -> cualquier caracter
         * + -> una o mas veces
         * @ -> tiene que existir una arroba
         * \\. -> para escribir el punto literal (ya que de por si el . significa cualquier caracter)
         * ".+@.+\\..+" -> cualquier caracter una o mas veces + @ + cualquier caracter una o mas veces + . + cualquier caracter una o mas veces 
         */
        return correo.matches(comprobarCorreo);
    }

    //Validar número de teléfono
    private boolean validarTelefono(String telefono) {
        //Solo 9 dígitos
        String comprobarTelefono = "\\d{9}"; 
        return telefono.matches(comprobarTelefono);
    }


    // Guardar la agenda y los borrados en el archivo
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
