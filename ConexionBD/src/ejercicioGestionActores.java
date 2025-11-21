public class ejercicioGestionActores {
public static void main(String[] args) {
        gestionActores gestionActores;
        gestionActores = new gestionActores();
        int opcion;
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "";

        do {
            opcion = gestionActores.dameOpcion();
            switch (opcion) {
                case 1:
                    gestionActores.consultarActor();
                    break;
                case 2:
                    gestionActores.borrarActor();
                    break;
                case 3:
                    gestionActores.actualizarActor();
                    break;
                case 4:
                    gestionActores.insertarActor();
                    break;
                case 5:
                    System.out.println("FIN DEL PROGRAMA");
                    break;
                default:
                    System.out.println("Opción érronea");
                    break;
            }
        } while (opcion != 5);
    }
}
