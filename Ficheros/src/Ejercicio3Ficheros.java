import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio3Ficheros {

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.3");

        System.out.println("Leer el contenido de un archivo txt");

        Path path = Path.of("D:\\carmen\\AD\\Ficheros\\archivo2.txt");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {

            String linea;

            while ((linea = br.readLine()) != null) { 

                System.out.println(linea.toUpperCase());

            }

        } catch (Exception e) {

        }

        System.out.println("Fin del programa");
    }

}
