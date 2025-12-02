package dto;

public class Contacto_GrupoDTO {
    private int ID_Contacto;
    private int ID_Grupo;
    
    public Contacto_GrupoDTO() {}
    
    public Contacto_GrupoDTO(int ID_Contacto, int ID_Grupo) {
        this.ID_Contacto = ID_Contacto;
        this.ID_Grupo = ID_Grupo;
    }


    public int getIDContacto() {
        return ID_Contacto;
    }
    public void setIDContacto(int ID_Contacto) {
        this.ID_Contacto = ID_Contacto;
    }
    public int getIDGrupo() {
        return ID_Grupo;
    }
    public void setIDGrupo(int ID_Grupo) {
        this.ID_Grupo = ID_Grupo;
    }

    @Override
    public String toString() {
        return "Contacto_GrupoDTO [ID_Contacto=" + ID_Contacto + ", ID_Grupo=" + ID_Grupo  + "]";
    }
    
}
