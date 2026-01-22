package com.practica1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.practica1.entity.Curso;
import com.practica1.repository.CursoRepository;
;

public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso crearCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso actualizarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
}
