package dto;

public class GrupoDTO {
    private int ID_Grupo;
    private String Nombre;

    public GrupoDTO() {}
    
    public GrupoDTO(int ID_Grupo, String Nombre) {
        this.ID_Grupo = ID_Grupo;
        this.Nombre = Nombre;
    }

    public int getID_Grupo() {
        return ID_Grupo;
    }

    public void setID_Grupo(int ID_Grupo) {
        this.ID_Grupo = ID_Grupo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    @Override
    public String toString() {
        return "GrupoDTO [ID_Grupo=" + ID_Grupo + ", Nombre=" + Nombre + "]";
    }
    
}
