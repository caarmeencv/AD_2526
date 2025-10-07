import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ejercicio4Ficheros {

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.3");

        System.out.println("Leer el contenido de un archivo txt");

        try {

            System.out.println("=====================");
            System.out.println("Leyendo con List");
            System.out.println("=====================");

            List<String> lineas = Files.readAllLines(Path.of("D:\\carmen\\AD\\Ficheros\\archivo2.txt"), StandardCharsets.UTF_8);

            for(String linea : lineas){
                System.out.println(linea.toUpperCase());
            }

            System.out.println("=====================");
            System.out.println("Leyendo con ReadString");
            System.out.println("=====================");

            String ficheroCompleto = Files.readString(Path.of("D:\\carmen\\AD\\Ficheros\\archivo2.txt"), StandardCharsets.UTF_8);
            System.out.println(ficheroCompleto);

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Fin del programa");
    }

}
