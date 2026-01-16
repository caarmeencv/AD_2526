package com.ad.agenda.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @ManyToMany
    private List<Contacto> contactos = new ArrayList<>();

    public void addContacto(Contacto contacto) {
        this.contactos.add(contacto);
        contacto.getGrupos().add(this);
    }

    // Constructor vacío
    public Grupo() {
    }

    // Constructor con parámetros
    public Grupo(String nombre) {
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

}
