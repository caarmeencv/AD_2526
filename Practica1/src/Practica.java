
public class Practica {

    public static void main(String[] args) {
        Agenda Agenda;
        Agenda = new Agenda();
        int opcion;

        do {
            opcion = Agenda.dameOpcion();
            switch (opcion) {
                case 1:
                    Agenda.crear();
                    break;
                case 2:
                    Agenda.anadir();
                    break;
                case 3:
                    Agenda.consultarContacto();
                    break;
                case 4:
                    Agenda.modificarContacto();
                    break;
                case 5:
                    Agenda.borrar();
                    break;
                case 6:
                    Agenda.recuperarContacto();
                    break;
                case 7:
                    Agenda.mostrarContactos();
                    break;
                case 8:
                    Agenda.vaciar();
                    break;
                case 9:
                    Agenda.masOpciones();
                    break;
                case 10:
                    System.out.println("FINAL DEL PROGRAMA");
                    break;
                default:
                    System.out.println("OPCION Erronea");
                    break;
            }
        } while (opcion != 10);
    }
}
