
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio6FicherosExpresionesRegulares {

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.6 con expresiones regulares");

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("D:\\carmen\\AD\\Ficheros\\alumnos.csv"), StandardCharsets.UTF_8)) {

            Scanner teclado = new Scanner(System.in);

            while (true) {

                System.out.println("Introduce alumno: ");
                String nombre = teclado.nextLine();
                if (nombre.matches("[A-Ya-z]+")) {
                    bw.write(nombre);
                    bw.write(";");
                } else if (nombre.matches("Z")) {
                    System.out.println("Programa finalizado");
                    break;
                }

                System.out.println("Introduce la fecha: ");
                String fecha = teclado.nextLine();

                if(fecha.matches("\\d{1,2}-\\d{1,2}-\\d{4}")){
                    bw.write(fecha);
                }
                else{
                    System.out.println("El formato de la fecha no es valido");
                }

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
