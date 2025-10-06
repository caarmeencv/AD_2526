

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio1Ficheros {
    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 14.1");

        //Path path = Path.of("D:\\carmen\\AD\\Ficheros\\archivo.txt");

        //BufferedReader bf = new BufferedReader(new InputStreamReader(new InputStream("archivo")));

        System.out.println("Escribir en un archivo txt una linea con ñ y otra linea");

        try (BufferedWriter bu = Files.newBufferedWriter(Path.of("D:\\carmen\\AD\\Ficheros\\archivo.txt"), StandardCharsets.UTF_8)){
            
            bu.write("Linea con la letra ñ");
            bu.flush();
            
            bu.newLine();
            bu.write("Otra línea");

            Scanner teclado = new Scanner(System.in);
            System.out.println("Dale a enter para escribir");
            teclado.nextLine();
            

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