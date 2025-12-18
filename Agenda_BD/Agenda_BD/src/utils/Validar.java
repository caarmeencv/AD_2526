package utils;

import dao.ContactoDAO;
import dao.GrupoDAO;

public class Validar {

    //Validar correo electrónico
    public static boolean validarEmail(String email) {

        //Comprobar que el email no existe ya en otro contacto de la agenda
        if (ContactoDAO.existeEmail(email)) {
            System.out.println("Ya existe un contacto con ese email.");
            return false;
        }

        //Que tenga algo, despues una @, algo más, un punto y algo más
        if(!email.matches(".+@.+\\..+")){
            System.out.println("El correo debe tener el siguiente formato: usuario@dominio.extension.");
            return false;
        }

        return true;
    }

    // Validar número de teléfono
    public static boolean validarTelefono(String telefono) {

        //Comprobar que el teléfono no existe ya en otro contacto de la agenda
        if (ContactoDAO.existeTelefono(telefono)) {
            System.out.println("Ya existe un contacto con ese teléfono.");
            return false;
        }

        //Que tenga 9 dígitos
        if(!telefono.matches("\\d{9}")){
            System.out.println("El teléfono debe contener 9 dígitos.");
            return false;
        }
        return true;
    }

    // Validar nombre de contacto
    public static boolean validarNombreContacto(String nombre) {

        //Comprobar que el nombre no existe ya en otro contacto de la agenda
        if (ContactoDAO.existeNombre(nombre)) {
            System.out.println("Ya existe un contacto con ese nombre.");
            return false;
        }

        //Comprobar que no es nulo ni vacío
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }

        //Comprobar que no tiene mas de 25 caracteres
        if (nombre.length() > 25) {
            System.out.println("El nombre no puede tener más de 25 caracteres.");
            return false;
        }

        //Comprobar que solo tiene letras (mayúsculas, minúsculas y espacios)
        //La primera letra tiene ser mayúscula si o si
        //Los espacios se permiten para nombres compuestos como "Juan Carlos"

        if(!nombre.matches("^[A-Z][a-zA-Z ]*$")) {
            System.out.println("El nombre solo puede contener letras y espacios, y debe empezar por mayúscula.");
            return false;
        }

        return true;

    }

    // Validar nombre de grupo
    public static boolean validarNombreGrupo(String nombre) {

        //Comprobar que no es nulo ni vacío
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }

        //Comprobar que el nombre no existe ya en otro grupo de la agenda
        if (GrupoDAO.existeNombre(nombre)) {
            System.out.println("Ya existe un grupo con ese nombre.");
            return false;
        }

        //En WhatsApp los nombres de grupo pueden tener letras, números, espacios y algunos caracteres especiales
        //Los grupos de WhatsApp permiten hasta 100 caracteres en su nombre, esa es la guía que seguí para poner por lo menos alguna validación
        if (nombre.length() > 100) {
            System.out.println("El nombre no puede tener más de 100 caracteres.");
            return false;
        }

        return true;
    }
}