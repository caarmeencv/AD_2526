
public class Jugador {

    private String nombre;
    private double salario;
    private int edad;

    public Jugador(String nombre, double salario, int edad) {
        this.nombre = nombre;

        if (salario < 0) {
            this.salario = 0.0;
        } else {
            this.salario = salario;
        }

        if (edad < 0 || edad > 100) {
            edad = 18;
            System.out.println(" La edad de " + nombre + "se ha cambiado a 18 años.");
        } else {
            this.edad = edad;
        }
    }

    public Jugador() {
        nombre = "Jugador vacio";
        salario = 0.0;
        edad = 18;
    }

    public String getNombre() {
        return this.nombre;
    }

    public double getSalario() {
        return this.salario;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
