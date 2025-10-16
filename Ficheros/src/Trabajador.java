
import java.io.Serializable;


public class Trabajador implements Serializable{

    private String nombre;
    private Double salario;
    private String puesto;
    private int edad;

    public Trabajador(String nombre, Double salario, String puesto, int edad){
        this.nombre = nombre;
        this.edad = edad;
        this.puesto = puesto;
        this.salario = salario;
    }

    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

     @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", puesto='" + puesto + '\'' +
                ", edad=" + edad +
                '}';
    }


}
