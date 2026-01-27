package com.practica1.repository;

import com.practica1.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    // JpaRepository proporciona automáticamente:
    // - save(Alumno alumno)
    // - findById(Long id)
    // - findAll()
    // - delete(Alumno alumno)
    // - deleteById(Long id)
    // - count()
    // - exists(Long id)
    // ... y muchos más métodos CRUD
}
