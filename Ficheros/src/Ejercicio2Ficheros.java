
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio2Ficheros {

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.2");

        System.out.println("Escribir en un archivo txt lineas escritas con el teclado");

        try (BufferedWriter bu = Files.newBufferedWriter(Path.of("D:\\carmen\\AD\\Ficheros\\archivo2.txt"), StandardCharsets.UTF_8)) {

            Scanner teclado = new Scanner(System.in);

            String frase = " ";

            while (true) { 
                System.out.println("Escribe linea: ");
                frase = teclado.nextLine();
                if (frase.equals("fin")) {
                    break;
                }
                bu.write(frase);
                bu.newLine();
            }


/*          MI SOLUCION: 
            frase = " EJERCICIO 14.2"
            while (!frase.equals("fin")) {
                System.out.println("Escribe algo para poner en el archivo");
                frase = teclado.nextLine();
                if (!frase.equals("fin")) {
                    bu.write(frase);
                    bu.flush();
                    bu.newLine();
                }
            }
*/

        } catch (Exception e) {

        }

        System.out.println("Fin del programa");
    }
}
