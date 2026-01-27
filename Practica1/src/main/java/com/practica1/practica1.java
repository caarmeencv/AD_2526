package com.practica1;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.practica1.entity.Alumno;
import com.practica1.entity.Curso;
import com.practica1.service.AlumnoService;
import com.practica1.service.CursoService;

@SpringBootApplication
public class practica1 {
    
    public static void main(String[] args) {
        SpringApplication.run(practica1.class, args);
    }

    @Bean
    public CommandLineRunner run(AlumnoService alumnoService, CursoService cursoService) {
        return args -> {
            System.out.println("\n========== PRUEBAS DE RELACIÓN MANY-TO-ONE / ONE-TO-MANY ==========\n");

            // 1. Crear cursos
            System.out.println("1. Creando cursos...");
            Curso curso1 = cursoService.crearCurso("Programación en Java");
            Curso curso2 = cursoService.crearCurso("Desarrollo Web");
            System.out.println("   ✓ " + curso1);
            System.out.println("   ✓ " + curso2);
            System.out.println();


            // 2. Crear alumnos SIN curso
            System.out.println("2. Creando alumnos sin asignar a curso...");
            Alumno alumno1 = alumnoService.crearAlumno("Juan García", "juan@example.com");
            Alumno alumno2 = alumnoService.crearAlumno("María López", "maria@example.com");
            Alumno alumno3 = alumnoService.crearAlumno("Carlos Pérez", "carlos@example.com");
            System.out.println("   ✓ " + alumno1);
            System.out.println("   ✓ " + alumno2);
            System.out.println("   ✓ " + alumno3);
            System.out.println();

            // 3. Asignar alumnos a curso usando el helper de Alumno
            System.out.println("3. Asignando alumnos al Curso 1...");
            alumno1.asignarCurso(curso1);
            alumno2.asignarCurso(curso1);
            alumnoService.actualizarAlumno(alumno1.getId(), alumno1.getNombre(), alumno1.getEmail());
            alumnoService.actualizarAlumno(alumno2.getId(), alumno2.getNombre(), alumno2.getEmail());
            System.out.println("   ✓ Alumnos asignados");
            System.out.println();


            // 4. Asignar alumno3 a curso2
            System.out.println("4. Asignando alumno 3 al Curso 2...");
            alumno3.asignarCurso(curso2);
            alumnoService.actualizarAlumno(alumno3.getId(), alumno3.getNombre(), alumno3.getEmail());
            System.out.println("   ✓ Alumno asignado");
            System.out.println();

            
            // 5. Verificar la relación - Obtener curso y ver sus alumnos
            System.out.println("5. Verificando relación OneToMany:");
            Optional<Curso> cursoOpt1 = cursoService.obtenerPorId(curso1.getId());
            if (cursoOpt1.isPresent()) {
                Curso cursoRecuperado = cursoOpt1.get();
                System.out.println("   Curso: " + cursoRecuperado.getNombre());
                System.out.println("   Cantidad de alumnos: " + cursoRecuperado.getAlumnos().size());
                System.out.println("   Alumnos del curso:");
                cursoRecuperado.getAlumnos().forEach(a -> 
                    System.out.println("      - " + a.getNombre() + " (" + a.getEmail() + ")")
                );
            }
            System.out.println();

            // 6. Verificar la relación inversa - Obtener alumno y ver su curso
            System.out.println("6. Verificando relación ManyToOne:");
            Optional<Alumno> alumnoOpt = alumnoService.obtenerPorId(alumno1.getId());
            if (alumnoOpt.isPresent()) {
                Alumno alumnoRecuperado = alumnoOpt.get();
                System.out.println("   Alumno: " + alumnoRecuperado.getNombre());
                if (alumnoRecuperado.getCurso() != null) {
                    System.out.println("   Curso asignado: " + alumnoRecuperado.getCurso().getNombre());
                } else {
                    System.out.println("   Sin curso asignado");
                }
            }
            System.out.println();

            // 7. Listar todos los alumnos con sus cursos
            System.out.println("7. Listando todos los alumnos con sus cursos...");
            List<Alumno> todosAlumnos = alumnoService.listarTodos();
            todosAlumnos.forEach(System.out::println);
            System.out.println();

            // 8. Listar todos los cursos
            System.out.println("8. Listando todos los cursos...");
            List<Curso> todosCursos = cursoService.listarTodos();
            todosCursos.forEach(System.out::println);

            System.out.println("\n========== FIN DE LAS PRUEBAS ==========\n");
        };
    }
}


