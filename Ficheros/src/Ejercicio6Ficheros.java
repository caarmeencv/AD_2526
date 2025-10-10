
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio6Ficheros {

        public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.5");

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("D:\\carmen\\AD\\Ficheros\\alumnos.csv"), StandardCharsets.UTF_8)){
         
            Scanner teclado = new Scanner(System.in);

            while (true) { 
                System.out.println("Introduce alumno: ");
                String nombre = teclado.nextLine();
                if (nombre.equals("Z")) {
                    break;
                }
                bw.write(nombre);
                bw.write(";");

                System.out.println("Introduce la fecha: ");
                String fecha = teclado.nextLine();

                bw.write(fecha);

                for (int i = 1; i <= 3; i++) {
                    bw.write(";");
                    System.out.println("Introduce la nota de la " + i + "º evaluacion");
                    String nota = teclado.nextLine();
                    bw.write(nota);
                }
                bw.newLine();
            }

        } catch (Exception e) {
            
        }

        System.out.println("Fin del programa");
    }


}
