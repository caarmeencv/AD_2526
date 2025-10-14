import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio7Leer {

     public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.7");

        int aprobados = 0;
        String mejorAlumno = "";
        double mejorNota = 0;

        try (BufferedReader br = Files.newBufferedReader(Path.of("D:\\carmen\\AD\\Ficheros\\alumnos.csv"), StandardCharsets.UTF_8)){

            String linea;
            

            while((linea = br.readLine()) != null) { 
                String[] datos = linea.split(";");
                String nombre = datos[0];
                double nota1 = Double.parseDouble(datos[2]);
                double nota2 = Double.parseDouble(datos[3]);
                double nota3 = Double.parseDouble(datos[4]);

                double notafinal = nota1 * 0.2 + nota2 * 0.3 + nota3 * 0.5;

                if (notafinal >= 5) {
                    aprobados++ ;
                }

                if(notafinal >= 5){
                    mejorNota = notafinal;
                    mejorAlumno = nombre;
                }
            }
            System.out.println("Cantidad de alumnos con nota final mayor de 5: " + aprobados);
            System.out.println("Alumno con mejor nota: " + mejorAlumno + " -> " + mejorNota);

        } catch (Exception e) {
            
        }

        System.out.println("Fin del programa");
    }


}