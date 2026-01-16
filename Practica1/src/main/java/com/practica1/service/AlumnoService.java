package com.practica1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica1.entity.Alumno;
import com.practica1.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public Alumno crearAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public List<Alumno> listarAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }

    public void eliminarAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }
}