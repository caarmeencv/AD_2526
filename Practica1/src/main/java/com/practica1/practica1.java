package com.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practica1.entity.Alumno;
import com.practica1.entity.Curso;
import com.practica1.service.AlumnoService;
import com.practica1.service.CursoService;

@SpringBootApplication
public class practica1 implements CommandLineRunner {

    @Autowired
    private AlumnoService alumnoService;
    @Autowired
    private CursoService cursoService;

    public static void main(String[] args) {
        SpringApplication.run(practica1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Crear alumnos
        Alumno a1 = alumnoService.crearAlumno(new Alumno("Daniel", "daniel@correo.com"));
        Alumno a2 = alumnoService.crearAlumno(new Alumno("Elena", "elena@correo.com"));
        Alumno a3 = alumnoService.crearAlumno(new Alumno("Alberto", "alberto@correo.com"));

        System.out.println(" - Listar Alumnos - ");
        alumnoService.listarAlumnos().forEach(System.out::println);

        System.out.println(" - Obtener Alumno por ID (" + a2.getId() + ") - ");
        System.out.println(alumnoService.obtenerAlumnoPorId(a2.getId()));

        System.out.println(" - Eliminar Alumno (" + a1.getId() + ") - ");
        alumnoService.eliminarAlumno(a1.getId());

        System.out.println(" - Listar Alumnos - ");
        alumnoService.listarAlumnos().forEach(System.out::println);

        // Crear un curso y asignar alumnos
        Curso curso = new Curso("Matemáticas");
        curso.getAlumnos().add(a2);
        curso.getAlumnos().add(a3);
        // Guardar el curso (implementa el método en el servicio correspondiente)
        cursoService.crearCurso(curso);
    }
}