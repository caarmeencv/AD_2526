
import java.util.Scanner;

public class Ejercicio5 {

    public static void main(String[] args) throws Exception{
        
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce la cadena: ");
        String cadena = teclado.nextLine();

        System.out.println("==========");
        System.out.println("APARTADO A");

        System.out.println("¿La cadena es una matricula?");

        if(cadena.matches("\\d{4} ?[A-Z]{3}")){
            System.out.println("Si, la cadena es una matricula!");
        }
        else{
            System.out.println("No, la cadena no es una matricula");
        }

        System.out.println("==========");
        System.out.println("APARTADO B");

        System.out.println("¿La cadena es un numero binario de una o mas posiciones?");

        if(cadena.matches("[0||1]+")){
            System.out.println(" - Si, la cadena es un binario de una o mas posiciones");
        }
        else{
            System.out.println(" - No, la cadena no es un binario de una o mas posiciones");
        }

        System.out.println("==========");
        System.out.println("APARTADO C");

        System.out.println("¿La cadena es un numero hexadecimal de entre 5 y 8 posiciones?");

        if(cadena.matches("[0-9A-Fa-f]{5,8}")){
            System.out.println("Si, la cadena es un numero hexadecimal entre 5 y 8 posiciones");
        }
        else{
            System.out.println("No, la cadena no es un numero hexadecimal entre 5 y 8 posiciones");
        }

        System.out.println("==========");
        System.out.println("APARTADO D");

        System.out.println("¿La cadena es una fecha con formato YYYY-MM-DD?");

        if(cadena.matches("\\d{4}\\d{2}\\d{2}")){
            System.out.println("Si, la cadena es una fecha con formato YYYY-MM-DD");
        }
        else{
            System.out.println("No, la cadena no es una fecha con formato YYYY-MM-DD");
        }

        System.out.println("==========");
        System.out.println("APARTADO E");

        System.out.println("¿La cadena (si es que si en el anterior apartado) tiene el mes o el dia con solo un digito?");
        
        if(cadena.matches("\\d{4}\\d{1,2}\\d{1,2}")){
            System.out.println("Si, la cadena es una fecha con formato YYYY-MM-DD");
        }
        else{
            System.out.println("No, la cadena no es una fecha con formato YYYY-MM-DD");
        }

        System.out.println("==========");
        System.out.println("APARTADO F");

        System.out.println("¿La cadena podria ser un nombre de usuario en Twitter?");

        if(cadena.matches("^[@\\w]{2,15}")){
            System.out.println("Si, la cadena seria un usuario de Twitter valido");
        }
        else{
            System.out.println("No, la cadena no seria un usuario de Twitter valido");
        }
    }

}
