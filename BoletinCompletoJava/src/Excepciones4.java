
import java.util.Scanner;

public class Excepciones4 {

    public static void main(String[] args) throws Exception {

        System.out.println("EJERCICIO 4");

        leerEntero(9, 1);

        /*Crear una función leerEntero(int max, int min) que solicite 
        al usuario la entrada de un número entero y que valide que es 
        un valor entero y que está comprendido entre los parámetros 
        max y min (esto es, mayor o igual que min y menor o igual que 
        max). El usuario deberá repetir la entrada de datos hasta que 
        lo haga bien, devolviendo finalmente la función el valor 
        introducido y validado. La función controlará las posibles 
        excepciones (por ejemplo caracteres no numéricos)*/
    }

    public static Integer leerEntero(int max, int min) {
        Scanner teclado = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Introduce un numero entero: ");
                String cadena = teclado.next();
                int num = Integer.parseInt(cadena);
                //int num = teclado.nextInt()
                if (num > min && num < max) {
                    teclado.close();
                    System.out.println("Numero valido");
                    return num;
                } else {
                    System.out.println("El numero no esta entre el minimo y el maximo");
                }
            } catch (NumberFormatException e) {
                System.out.println("No has introducido un entero");
            }
        }
    }
}
