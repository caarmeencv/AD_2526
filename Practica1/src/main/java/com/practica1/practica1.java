package com.practica1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.practica1.entity.Alumno;
import com.practica1.service.AlumnoService;

@SpringBootApplication
public class practica1 implements CommandLineRunner {

    @Autowired
    private AlumnoService alumnoService;

    public static void main(String[] args) {
        SpringApplication.run(practica1.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Aquí pruebas lo que quieras

        // Crear alumnos
        Alumno a1 = alumnoService.crearAlumno(new Alumno("Ana", "ana@correo.com"));
        Alumno a2 = alumnoService.crearAlumno(new Alumno("Luis", "luis@correo.com"));
        Alumno a3 = alumnoService.crearAlumno(new Alumno("Marta", "marta@correo.com"));

        System.out.println("=== LISTAR ===");
        alumnoService.listarAlumnos().forEach(System.out::println);

        System.out.println("=== OBTENER POR ID (" + a2.getId() + ") ===");
        System.out.println(alumnoService.obtenerAlumnoPorId(a2.getId()));

        System.out.println("=== ELIMINAR (" + a1.getId() + ") ===");
        alumnoService.eliminarAlumno(a1.getId());

        System.out.println("=== LISTAR TRAS BORRAR ===");
        alumnoService.listarAlumnos().forEach(System.out::println);
    }
}