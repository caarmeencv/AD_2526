import java.io.Serializable;


public class Contacto implements Serializable{

    private String nombre;
    private String correo;
    private int telefono;

    public Contacto(String nombre, String correo, int telefono){
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void  mostrarAgenda() {
        System.out.println("Nombre='" + nombre + '\'' +
                ", Correo Electrónico='" + correo + '\'' +
                ", Teléfono=" + telefono);
    }

}