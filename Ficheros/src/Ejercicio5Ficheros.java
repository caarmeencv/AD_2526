import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio5Ficheros {

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.5");

        Path path = Path.of("D:\\carmen\\AD\\Ficheros\\archivo3.txt");

        int num_lineas = 0;
        int num_caracteres = 0;
        int num_palabras = 0;

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)){
            String linea;

            while((linea = br.readLine()) != null){

                num_lineas++;
                num_caracteres+= linea.length();

                //contar letras solo sin espacios
                //num_caracteres+=linea.replace(" ", " ").length();

                num_palabras += linea.trim().split(" ").length;

                //expresion regular para coller 1 ou mais espacios
                // num_palabras *= linea.trim().split("\\s+").length;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("- Numero de lineas: " + num_lineas);
        System.out.println("- Numero de caracteres: " + num_caracteres);
        System.out.println("- Numero de palabras: " + num_palabras);

        System.out.println("Fin del programa");
    }

}
