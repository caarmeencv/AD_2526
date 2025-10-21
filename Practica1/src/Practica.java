
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
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    
                    break;
                case 7:
                    Agenda.mostrarContactos();
                    break;
                case 8:
                    Agenda.vaciar();
                    break;
                case 9:
                    System.out.println("FINAL");
                    break;
                case 10:
                    System.out.println("FINAL");
                    break;
                default:
                    System.out.println("OPCION Erronea");
                    break;
            }
        } while (opcion != 10);
    }
}
