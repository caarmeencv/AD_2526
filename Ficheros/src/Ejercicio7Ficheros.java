import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio7Ficheros {

        public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.6");

        try (BufferedWriter bw = Files.newBufferedWriter(Path.of("D:\\carmen\\AD\\Ficheros\\alumnos.csv"), StandardCharsets.UTF_8)){
         
            Scanner teclado = new Scanner(System.in);

            int contador = 0;

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

                Double notafinal = 0.0;
                double nota1 = 0;
                double nota2 = 0;
                double nota3 = 0;

                for (int i = 1; i <= 3; i++) {
                    bw.write(";");
                    System.out.println("Introduce la nota de la " + i + "º evaluacion");
                    String nota = teclado.nextLine();
                    bw.write(nota);
                    switch (i) {
                        case 1:
                            nota1 = Double.parseDouble(nota) * 0.2;
                            break;
                        case 2:
                            nota2 = Double.parseDouble(nota) * 0.3;
                            break;
                        case 3:
                            nota3 = Double.parseDouble(nota) * 0.5;
                            notafinal = nota1 + nota2 + nota3;
                            bw.write(";");
                            bw.write(notafinal.toString());
                            if (notafinal >= 5) {

                              contador++;

                            }
                            break;
                    }
                }

                bw.newLine();
            }

            bw.write("Hay " + contador + " alumnos con un 5 o mas en la nota final");

        } catch (Exception e) {
            
        }

        System.out.println("Fin del programa");
    }


}