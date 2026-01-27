package com.practica1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica1.entity.Alumno;
import com.practica1.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Crear un alumno
    public Alumno crearAlumno(String nombre, String email) {
        Alumno alumno = new Alumno(nombre, email);
        return alumnoRepository.save(alumno);
    }

    // Listar todos los alumnos
    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    // Obtener un alumno por id
    public Optional<Alumno> obtenerPorId(Long id) {
        return alumnoRepository.findById(id);
    }

    // Actualizar un alumno
    public Alumno actualizarAlumno(Long id, String nombre, String email) {
        Optional<Alumno> alumnoOpt = alumnoRepository.findById(id);
        if (alumnoOpt.isPresent()) {
            Alumno alumno = alumnoOpt.get();
            alumno.setNombre(nombre);
            alumno.setEmail(email);
            return alumnoRepository.save(alumno);
        }
        return null;
    }

    // Eliminar un alumno
    public void eliminarAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

    // Contar alumnos
    public long contarAlumnos() {
        return alumnoRepository.count();
    }
}
