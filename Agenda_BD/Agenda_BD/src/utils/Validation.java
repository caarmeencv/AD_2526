package utils;

public class Validation {

    //Validar correo electrónico
    public static boolean validarCorreo(String correo) {
        //Que tenga algo, despues una @, algo más, un punto y algo más
        String comprobarCorreo = ".+@.+\\..+";
        return correo != null && correo.matches(comprobarCorreo);
    }

    // Validar número de teléfono
    public static boolean validarTelefono(String telefono) {
        //Que tenga 9 dígitos
        String comprobarTelefono = "\\d{9}";
        return telefono != null && telefono.matches(comprobarTelefono);
    }

    // Validar nombre de contacto
    public static boolean validarNombreContacto(String nombre) {

        //Comprobar que no es nulo ni vacío
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }

        //Comprobar que no tiene mas de 25 caracteres
        if (nombre.length() > 25) {
            return false;
        }

        //Comprobar que solo tiene letras (mayúsculas, minúsculas, tildes, eñes y espacios)
        //Los espacios se permiten para nombres compuestos como "Juan Carlos"
        String comprobarNombre = "[A-Za-zÁÉÍÓÚáéíóúÑñ ]+";

        return nombre.matches(comprobarNombre);
    }

    // Validar nombre de grupo
    public static boolean validarNombreGrupo(String nombre) {

        //Comprobar que no es nulo ni vacío
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }

        //En WhatsApp los nombres de grupo pueden tener letras, números, espacios y algunos caracteres especiales
        //Los grupos de WhatsApp permiten hasta 100 caracteres en su nombre
        if (nombre.length() > 100) {
            return false;
        }

        return true;
    }
}