
public class Excepciones6 {

    public static void main(String[] args) throws Exception{
        
        System.out.println("EJERCICIO 6");

        /*13.6. Realiza una función/método que se le pase por parámetro 
        una cadena que representa una dirección de correo electrónico.
        En caso de que la cadena no cumpla las condiciones sintácticas 
        de un email, se generará una excepción llamada EmailInvalidoException 
        con mensaje: “Formato email inválido”. Si no se produce la excepción, e
        l método devolverá la parte del email previa a la arroba. Los 
        requisitos que tiene que tener una dirección de email podrían ser: 
        número mínimo de caracteres=5, una arroba y al menos un punto después 
        de la arroba. Hacer también un programa que llame a la función anterior 
        y capture la excepción generada. */
        //Scanner teclado = new Scanner(System.in);
        //System.out.println("Introduce un email: ");
        //String email = teclado.next();

    }

    public static String validarEmail(String email) throws EmailInvalidoException{


        if(email.length() < 5){
            throw new EmailInvalidoException();
        }
        if(!email.contains("@")){
            throw new EmailInvalidoException();
        }
        int posicion = email.indexOf("@");
       // int posicion2 = email.indexOf(".");
        String substring = email.substring(posicion);

        if(!substring.contains(".")){
            throw new EmailInvalidoException();
        }

        return email.substring(0, posicion);

    }

}
