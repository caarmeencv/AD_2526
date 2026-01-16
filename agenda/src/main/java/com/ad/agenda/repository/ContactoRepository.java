package com.ad.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ad.agenda.entity.Contacto;

public interface ContactoRepository extends JpaRepository <Contacto, Long>{

    List <Contacto> findByNombre(String nombre);
    List <Contacto> findByNombreContaining(String nombre);
    List <Contacto> findByNombreContainingIgnoreCase(String nombre);    

}
