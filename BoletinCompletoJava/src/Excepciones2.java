
import java.util.Scanner;

public class Excepciones2 {

    public static void main(String[] args) throws Exception {
        System.out.println("EJERCICIO 2");

        Scanner teclado = new Scanner(System.in);

        /*Haz un programa que tenga definido un Array de 7 posiciones de
        double que representa la temperatura media en una ciudad durante 
        una semana (puedes generar valores al azar entre 0 y 40 para llenarlo). 
        Se le solicitará al usuario que introduzca dos posiciones (entre 0 y 6), 
        y calculará la temperatura media entre esos días de la semana, ambos 
        inclusive. Si los valores introducidos no son válidos, por estar
        fuera de los límites del array se capturará la excepción y la media será cero.*/
        double[] array = new double[7];

        for (int i = 0; i < 7; i++) {
            array[i] = Math.random() * 40;
            System.out.println("Temperatura dia " + i + " : " + array[i]);
        }

        System.out.println("Introduce numero 1: ");
        int a = teclado.nextInt();

        System.out.println("Introduce numero 2: ");
        int b = teclado.nextInt();

        /*Intercambiar a y b si a es mayor que b para acceder al array
         de menor a mayor */
        if (a > b) {
            int aux = a;
            a = b;
            b = aux;
        }

        double media;
        double suma = 0;

        try {
            for (int i = a; i <= b; i++) {
                suma = array[i];
            }
            media = suma / (b - a + 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Numeros incorrectos");
            media = 0;
        }

        System.out.println("La media es: " + media);
    }

}
