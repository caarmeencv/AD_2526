import java.io.Serializable;


public class Contacto implements Serializable{

    private String nombre;
    private String correo;
    private String telefono;
    private boolean borrado; //si es true, significa que está borrado

    public Contacto(String nombre, String correo, String telefono){
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isBorrado(){
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public void  mostrarAgenda() {
        System.out.println("Nombre='" + nombre + '\'' +
                ", Correo Electrónico='" + correo + '\'' +
                ", Teléfono=" + telefono);
    }

}