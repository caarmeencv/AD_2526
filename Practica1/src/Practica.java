
public class Practica {

    public static void main(String[] args) {
        Agenda Agenda;
        Agenda = new Agenda();
        int opcion;

        do {
            opcion = Agenda.dameOpcion();
            switch (opcion) {
                case 1:
                    Agenda.llenar();
                    System.out.println("Agenda LLENA");
                    break;
                case 2:
                    
                    break;
                case 3:
                    //Agenda.mostrar();
                    break;
                case 4:
                    // System.out.print("Dame clave de aspirante: ");
                    // clave = Leer.datoInt();
                    //Agenda.mostrarAspirante(clave);
                    break;
                case 5:
                    //System.out.print("Dame clave de aspirante: ");
                    // clave = Leer.datoInt();
                    //Agenda.eliminarAspirante(clave);
                    break;
                case 6:
                    
                    break;
                case 7:
                    Agenda.mostrar();
                    break;
                case 8:
                    Agenda.vaciar();
                    System.out.println("Agenda VACIA");
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
