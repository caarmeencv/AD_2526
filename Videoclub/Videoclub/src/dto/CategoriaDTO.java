package dto;

import java.sql.Timestamp;

public class CategoriaDTO {
    private int category_id;
    private String name;
    private Timestamp last_update;
    
    public CategoriaDTO() {}
    
    public CategoriaDTO(int category_id, String name, Timestamp last_update) {
        this.category_id = category_id;
        this.name = name;
        this.last_update = last_update;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    @Override
    public String toString() {
        return "ActorDTO [actorId=" + category_id + ", firstName=" + name + ", lastName=" + last_update + "]";
    }
    
}