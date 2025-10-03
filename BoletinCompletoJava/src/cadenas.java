
import java.util.Scanner;

public class cadenas {

    public static void main(String[] args) throws Exception {

        String cadena;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Dame una cadena: ");
        cadena = teclado.nextLine();
        System.out.println("==============================");

        //  APARTADO A
        System.out.println("EJERCICIO 4.1.a");
        System.out.println("Pasar la cadena a mayúsculas y a minúsculas.");
        System.out.println("La cadena en minúsculas es: " + cadena.toLowerCase());
        System.out.println("La cadena en mayúsculas es: " + cadena.toUpperCase());

        //APARTADO B
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.b");

        System.out.println("¿La cadena contiene el caracter 'x'?");

        if (cadena.contains("x") || cadena.contains("X")) {
            System.out.println("Si, la cadena contiene 'x'");
        } else {
            System.out.println("No, La cadena no contiene 'x'");
        }

        //APARTADO C
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.c");

        System.out.println("¿Cuántas posiciones tiene la cadena?");

        if (cadena.length() > 10) {
            System.out.println("La cadena tiene más de 10 posiciones");
        } else if (cadena.length() == 10) {
            System.out.println("La cadena tiene 10 posiciones");
        } else {
            System.out.println("La cadena tiene menos de 10 posiciones");
        }

        System.out.println("La cadena tiene " + cadena.length() + " posiciones");

        //APARTADO D
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.d");

        System.out.println("¿La cadena contiene el caracter 'x' a partir de la cuarta posición?");

        if (cadena.length() < 4) {
            System.out.println("La cadena tiene menos de 4 caracteres");
        } else if (cadena.substring(3).contains("x") || cadena.substring(3).contains("X")) {
            System.out.println("Si, la cadena contiene el caracter 'x' a partir de la cuarta posición");
        } else {
            System.out.println("No, la cadena no contiene el caracter 'x' apartir de la cuarta posicion");
        }

        //APARTADO E
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.e");

        System.out.println("Crear una cadena formada por las 5 primeras posiciones de la cadena.");

        if (cadena.length() < 5) {
            System.out.println("La cadena tiene menos de 5 caracteres");
        } else {
            String primerasletras = cadena.substring(0, 5);
            System.out.println("La nueva cadena es: " + primerasletras);
        }

        //APARTADO F
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.f");

        System.out.println("Crear una cadena formada por las 5 primeras posiciones de la cadena.");

        if (cadena.length() < 5) {
            System.out.println("La cadena tiene menos de 5 caracteres");
        } else {
            String ultimasletras = cadena.substring(cadena.length() - 5);
            System.out.println("La nueva cadena es: " + ultimasletras);
        }

        //APARTADO G
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.g");

        System.out.println("¿La cadena es'hola'?");

        if (cadena.equals("hola")) {
            System.out.println("La cadena es 'hola'");
        } else {
            System.out.println("La cadena no es 'hola'");
        }

        //APARTADO H
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.h");

        System.out.println("Convertir la cadena de entrada a una variable de tipo int, suponiendo que dicha cadena es un número, esto es, contiene solo dígitos decimales (del 0 a 9)");

        Integer numero;

        try {
            numero = Integer.parseInt(cadena);
            System.out.println(numero);
        } catch (NumberFormatException exception) {
            System.out.println("La cadena no es un entero");
        } catch (Exception e) {
            System.out.println("Ha ocurrido otro tipo de error");
            System.out.println(e.getMessage());
        }

        //APARTADO I

        /*
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.i");

        System.out.println("Convertir la cadena de entrada a una variable de tipo int, suponiendo que dicha cadena es un número hexadecimal, esto es, contiene solo dígitos decimales (del 0 a 9) y letras de la A a F.");

        int decimal = Integer.parseInt(cadena, 16);
        System.out.println(decimal);

        String valoresHex = "0123456789ABCDEF";

        decimal=0;

        for(char c : cadena.toCharArray()){
            int valor = valoresHex.indexOf(c);
            decimal = valor + 16 + decimal;    
        }

        System.out.println(decimal);
         */
        //APARTADO J
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.j");

        System.out.println("Si se encuentra con en su interior con 'prueva' sustituir por 'prueba'.");

        if (cadena.contains("prueva")) {
            cadena = cadena.replace("prueva", "prueba");
            System.out.println(cadena);
        }

        //APARTADO K
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.k");

        System.out.println("¿La primera posición de la cadena es igual a la ultima?");

        Character primera = cadena.charAt(0);
        Character ultima = cadena.charAt(cadena.length() - 1);

        if (primera.equals(ultima)) {
            System.out.println("Si, el primer caracter es igual al ultimo");
        } else {
            System.out.println("No, el primer caracter y el ultimo no son iguales");
        }

        //APARTADO L
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.l");

        System.out.println("¿Cuantos digitos numericos hay en la cadena?");

        int contador;
        contador = 0;

        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            if (Character.isDigit(letra)) {
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay digitos en la cadena");
        } else if (contador == 1) {
            System.out.println("En la cadena hay 1 digito");
        } else {
            System.out.println("En la cadena hay " + contador + " digitos");
        }

        contador = 0;

        //con un for each
        for (char caracterCadena : cadena.toCharArray()) {
            if (Character.isDigit(caracterCadena)) {
                contador++;
            }
        }

        if (contador == 0) {
            System.out.println("No hay digitos en la cadena");
        } else if (contador == 1) {
            System.out.println("En la cadena hay 1 digito");
        } else {
            System.out.println("En la cadena hay " + contador + " digitos");
        }

        //APARTADO M
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.m");

        System.out.println("¿La cadena es un palindromo?");

        //con un metodo
        String reverso = new StringBuilder(cadena).reverse().toString();

        if (cadena.equals(reverso)) {
            System.out.println("Si, es un palindromo");
        } else {
            System.out.println("No, no es un palindromo");
        }

        //con i y j
        boolean esPalindromo = true;
        for (int i = 0, j = cadena.length() - 1; i < j; i++, j--) {
            if (cadena.charAt(i) != cadena.charAt(j)) {
                esPalindromo = false;
                break;
            }
        }
        System.out.println("Es palindromo: " + esPalindromo);

        //dividiendo entre dos
        esPalindromo = true;
        for (int i = 0; i < cadena.length() / 2; i++) {
            if (cadena.charAt(i) != cadena.charAt(cadena.length() - 1 - i)) {
                esPalindromo = false;
                break;
            }
        }
        System.out.println("Es palindromo: " + esPalindromo);

        //APARTADO N
        System.out.println("==============================");
        System.out.println("EJERCICIO 4.1.n");

        System.out.println("Crear una cadena que sea igual a la introducida, pero con la primera y última posiciones intercambiadas.");

        char ultimo = cadena.charAt(cadena.length() - 1);

        char primero = cadena.charAt(0);

        String cadena2;

        cadena2 = cadena.substring(1, cadena.length() - 1);

        cadena2 = ultimo + cadena2 + primero;

        System.out.println("La nueva cadena es: " + cadena2);

        teclado.close();
    }
}
