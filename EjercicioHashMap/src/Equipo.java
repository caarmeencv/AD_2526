
import java.util.HashMap;

public class Equipo {

    private static final int LIMITE_JUGADORES = 25;
    private HashMap<String, Jugador> plantilla = new HashMap<>();

    public Equipo() {

    }

    public void anadirJugador(Jugador jugador) {
        if (plantilla.size() >= LIMITE_JUGADORES) {
            System.out.println("El equipo ya está lleno!!");
        } else {
            plantilla.put(jugador.getNombre(), jugador);
        }
    }

    public void eliminarJugador(Jugador jugador) {
        if (plantilla.containsKey(jugador.getNombre())) {
            plantilla.remove(jugador.getNombre());
        } else {
            System.out.println("El jugador no está en el equipo");
        }
    }

    public void listarJugadores() {
        if (plantilla.isEmpty()) {
            System.out.println("No hay jugadores");
        } else {
            for (Jugador jugador : plantilla.values()) {
                System.out.println(jugador);
            }
        }
    }

    public void listarPorClave() {
        for (String clave : plantilla.keySet()) {
            System.out.println(clave);
        }
    }

    public void buscaPorSalario(double salario) {
        double salario_max = salario + 6000;
        double salario_min = salario - 6000;

        boolean encontrado = false;

        for (Jugador jugador : plantilla.values()) {
            if (jugador.getSalario() >= salario_min && jugador.getSalario() <= salario_max) {
                System.out.println(jugador);
            }
            encontrado = true;
        }

        if (!encontrado) {
            System.out.println("No hay jugadores con ese salario");
        }
    }
}
