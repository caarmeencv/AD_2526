
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio1Ficheros {
    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.1");

        //Path path = Path.of("D:\\carmen\\AD\\Ficheros\\archivo.txt");

        //BufferedReader bf = new BufferedReader(new InputStreamReader(new InputStream("archivo")));

        System.out.println("Escribir en un archivo txt aaaññññaa");

        try (BufferedWriter bu = Files.newBufferedWriter(Path.of("D:\\carmen\\AD\\Ficheros\\archivo.txt"), StandardCharsets.UTF_8)){
            
            bu.write("Línea con la letra ñ");

        } catch (Exception e) {

        }

        System.out.println("Fin del programa");

        /*
        try (BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String linea;
            while ((linea = bufferedReader.readLine()) != null) { 
                System.out.println(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }
}