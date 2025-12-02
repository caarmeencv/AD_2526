package dto;

public class ContactoDTO {
    private int ID_Contacto;
    private String Email;
    private String Nombre;
    private int Telefono;

    public ContactoDTO() {}
    
    public ContactoDTO(int ID_Contacto, String Email, String Nombre, int Telefono) {
        this.ID_Contacto = ID_Contacto;
        this.Email = Email;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
    }

    public int getID_Contacto() {
        return ID_Contacto;
    }

    public void setID_Contacto(int ID_Contacto) {
        this.ID_Contacto = ID_Contacto;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getTelefono() {
        return Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    @Override
    public String toString() {
        return "ContactoDTO [ID_Contacto=" + ID_Contacto + ", Nombre=" + Nombre + ", Email=" + Email + ", Telefono=" + Telefono + "]";
    }
    
}
