import java.util.InputMismatchException;
import java.util.Scanner;

public class Excepciones {

    public static void main(String[] args) throws Exception{

        Scanner teclado = new Scanner(System.in);
        
        System.out.println("EJERCICIO 1");

        /*Haz un programa que solicite al usuario dos números enteros 
        y los intente dividir (división entera, sin decimales). Si se 
        produce algún error el programa no finalizará de forma abrupta 
        si no que capturará la excepción ocurrida: o bien entrada de 
        valores incorrectos o bien por división por cero, informando al
        usuario de lo que ha acontecido. Finalmente, mostrará el resultado 
        de la división, que será cero en caso de cualquiera de los dos 
        errores posibles. 
        Nota: para provocar la excepción, hacemos la división de enteros,
        sin casting: double res=num1/num2; con num2=0. Si hiciésemos el 
        casting: double res = (double) num1 / num2; no se produce la excepción, 
        informa que el resultado es ‘Infinity’.*/

        int div;

        System.out.println("Dame numero A: ");
        int a=teclado.nextInt();

        System.out.println("Dame numero B: ");
        int b=teclado.nextInt();

        try{

            div = a / b;

        } catch (InputMismatchException e) {

            div = 0;

        }catch(ArithmeticException e){

            div = 0;

        }

        System.out.println("El resultado de la division entre " + a + " y " + b + " es " + div);

        System.out.println("==========");
        System.out.println("EJERCICIO 2");

        /*Haz un programa que tenga definido un Array de 7 posiciones de
        double que representa la temperatura media en una ciudad durante 
        una semana (puedes generar valores al azar entre 0 y 40 para llenarlo). 
        Se le solicitará al usuario que introduzca dos posiciones (entre 0 y 6), 
        y calculará la temperatura media entre esos días de la semana, ambos 
        inclusive. Si los valores introducidos no son válidos, por estar
        fuera de los límites del array se capturará la excepción y la media será cero.*/

        double[] array = new double[7];

        for(int i = 0; i < 8; i++){
            array[i] = Math.random()*40;
            System.out.println("Temperatura dia " + i + " : " + array[i]);
        }

        System.out.println("Introduce numero 1: ");
        a=teclado.nextInt();

        System.out.println("Introduce numero 2: ");
        b=teclado.nextInt();

        if(a>b){
            int aux = a;
            a = b;
            b = aux;
        }

        double suma = 0;

        for(int i = a ; i <= b ; i++){
            suma = array[i];
        }

        double media = suma/(b-a+1);
        System.out.println("La media es: " + media);
    }
}
