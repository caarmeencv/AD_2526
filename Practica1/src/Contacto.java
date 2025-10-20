import java.io.Serializable;


public class Contacto implements Serializable{

    private String nombre;
    private String correoelect;
    private int telefono;

    public Contacto(String nombre, String correoelect, int telefono){
        this.nombre = nombre;
        this.correoelect = correoelect;
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElect() {
        return correoelect;
    }

    public void setCorreoElect(String correoelect) {
        this.correoelect = correoelect;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void  mostrarAgenda() {
        System.out.println("Contacto {" +
                "Nombre='" + nombre + '\'' +
                ", Correo Electrónico='" + correoelect + '\'' +
                ", Teléfono=" + telefono +
                '}');
    }

}