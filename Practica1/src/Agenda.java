import java.util.ArrayList;

public class Agenda {

    public static void main(String[] args) throws Exception {
    
        ArrayList<Contacto> lista = new ArrayList<>();

        Menu();

    }

    public static void Menu(){
        System.out.println("    MENU    ");
        System.out.println("============");
        System.out.println("1-. Crear agenda.");
        System.out.println("2-. Añadir contacto.");
        System.out.println("3-. Consultar contacto.");
        System.out.println("4-. Modificar contacto.");
        System.out.println("5-. Borrar contacto.");
        System.out.println("6-. Restaurar contacto.");
        System.out.println("7-. Ver contactos.");
        System.out.println("8-. Vaciar agenda.");
        System.out.println("9-. Ver más opciones.");
        System.out.println("10-. Salir.");
    }

    public static void Menu2(){
        System.out.println("    MENU    ");
        System.out.println("============");
        System.out.println("1-. Mostrar información del archivo.");
        System.out.println("2-. Añadir contacto.");
        System.out.println("3-. Consultar contacto.");
        System.out.println("4-. Modificar contacto.");
        System.out.println("5-. Borrar contacto.");
        System.out.println("6-. Restaurar contacto.");
        System.out.println("7-. Ver contactos.");
        System.out.println("8-. Vaciar agenda.");
        System.out.println("9-. Ver más opciones.");
        System.out.println("10-. Salir.");
    }
}
