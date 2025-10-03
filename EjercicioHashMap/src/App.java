
public class App {

    public static void main(String[] args) throws Exception {
        Jugador j1 = new Jugador("Alberto", 10000d, 21);
        Jugador j2 = new Jugador("Pepe", 20000d, 40);
        Jugador j3 = new Jugador("Manolo", 30000d, 51);

        Equipo equipo = new Equipo();

        equipo.anadirJugador(j1);
        equipo.anadirJugador(j2);
        equipo.anadirJugador(j3);

        equipo.eliminarJugador(j2);
        equipo.listarJugadores();

    }
}
