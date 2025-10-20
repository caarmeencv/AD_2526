import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Agenda {

    private ArrayList<Contacto> Agenda;

    public Agenda() {
        Agenda = new ArrayList<Contacto>();
    }

    public void llenar() {
        Agenda.removeAll(Agenda);
        Agenda.add(new Contacto("Juan", "juan@gmail.com", 654892317));
        Agenda.add(new Contacto("Alejandro", "alejandro@gmail.com", 759846321));
        Agenda.add(new Contacto("María", "maria@gmail.com", 632145789));
    }

    public void vaciar() {
        Agenda.removeAll(Agenda);
    }

    public void mostrar() {
        Iterator<Contacto> iterator = Agenda.iterator();
        int contador = 0;

        if (Agenda.isEmpty())
            System.out.println("Agenda vacía");
        else {
            while (iterator.hasNext()){
                contador++;
                iterator.next().mostrarAgenda();
            }
        }

        System.out.println("Contactos en la agenda: " + contador);
    }

    public static void Menu(){
        System.out.println("    MENU    ");
        System.out.println("============");
        System.out.println("1- Crear agenda.");
        System.out.println("2- Añadir contacto.");
        System.out.println("3- Consultar contacto.");
        System.out.println("4- Modificar contacto.");
        System.out.println("5- Borrar contacto.");
        System.out.println("6- Restaurar contacto.");
        System.out.println("7- Ver contactos.");
        System.out.println("8- Vaciar agenda.");
        System.out.println("9- Ver más opciones.");
        System.out.println("10- Salir.");
    }

    public static void Menu2(){
        System.out.println("    MENU 2    ");
        System.out.println("============");
        System.out.println("1- Mostrar información del archivo.");
        System.out.println("2- Hacer copia de seguridad.");
        System.out.println("3- Restaurar copia de seguridad.");
        System.out.println("4- Volver al anterior menú.");
    }

    public int dameOpcion() {
        int opcion;

        Menu();
        opcion = Leer.datoInt();
        System.out.println();
        return opcion;
    }

    public int dameOpcion2() {
        int opcion;

        Menu2();
        opcion = Leer.datoInt();
        System.out.println();
        return opcion;
    }
}
