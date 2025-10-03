
import java.io.File;
import java.util.Calendar;
import java.util.Scanner;

public class ejerciciosclase {

    public static void main(String[] args) throws Exception {

        //ejercicio 1

        System.out.println("EJERCICIO 1");

        File f = new File("D:\\carmen\\AD\\FICHERO.txt");
        
        System.out.println("Nombre: " + f.getName());
        System.out.println("Existe: " + f.exists());
        System.out.println("Directorio: " + f.isDirectory());
        System.out.println("Fichero: " + f.isFile());
        System.out.println("Ruta absoluta: " + f.getAbsolutePath());
        System.out.println("Ruta canonica: " + f.getCanonicalPath());
        System.out.println("Tamaño en bytes: " + f.length());

        //ejercicio 2

        System.out.println("============");
        System.out.println("EJERCICIO 2");
        System.out.println("¿Que permisos tiene?");

        System.out.println("Permisos de Lectura: " + f.canRead());
        System.out.println("Permisos de Escritura: " + f.canWrite());
        System.out.println("Permisos de Ejecución: " + f.canExecute());

        //ejercicio 3

        System.out.println("============");
        System.out.println("EJERCICIO 3");
        
        System.out.println("Crear unha carpeta probas con un subdirectorio");

        File subdir = new File("D:\\carmen\\AD\\probas\\subdirectorio");

        if(subdir.mkdirs()){
            System.out.println("Probas creado");
            System.out.println("Subdirectorio creado");
            
        }
        else{
            System.out.println("Probas ya creado!!");
            if(subdir.mkdir()){
                System.out.println("Subdirectorio creado");
            }
            else{
                System.out.println("Subdirectorio ya creado!!");
            }
        }

        //ejercicio 4

        System.out.println("============");
        System.out.println("EJERCICIO 4");
        
        System.out.println("Crear datos.txt vacio dentro de la carpeta probas");

        Scanner teclado = new Scanner(System.in);
        String respuesta;

        File datos = new File("D:\\carmen\\AD\\probas\\datos.txt");

        if (datos.createNewFile()) {
            System.out.println("El fichero datos.txt creado correctamente.");
        } else {
            System.out.println("El fichero datos.txt ya existe.");
        }

        System.out.println("¿Quieres borrar el fichero datos.txt? (s/n)");
        respuesta = teclado.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            datos.delete();
            System.out.println("El fichero datos.txt ha sido eliminado");
        }
        else if(respuesta.equalsIgnoreCase("n")){
            System.out.println("El fichero datos.txt no se eliminó");
        }
        else{
            System.out.println("Respuesta incorrecta, no se eliminó el fichero");
        }

        //ejercicio 5

        System.out.println("============");
        System.out.println("EJERCICIO 5");

        String ruta = "D:\\carmen\\AD";

        File carpeta = new File(ruta);

        String[] nombres = carpeta.list();
        File[] rutas = carpeta.listFiles();

        System.out.println("Nombres:");

        for(String nombre : nombres){
            System.out.println("- " + nombre);
        }

        System.out.println("----------");
        System.out.println("Rutas absolutas:");
        
        for(File ruta2:rutas){
            System.out.println("- " + ruta2.getAbsolutePath());
        }

        System.out.println("----------");
        System.out.println("Archivos:");
        
        for(File ruta2 : rutas){
            if(ruta2.isFile()){
                System.out.println("- " + ruta2.getAbsolutePath());
            }
        }

        //ejercicio 6

        System.out.println("============");
        System.out.println("EJERCICIO 6");

        System.out.println("Archivos .txt:");

        for(File ruta2 : rutas){
            if(ruta2.isFile()){
                if(ruta2.getAbsolutePath().endsWith(".txt")){
                    System.out.println("- " + ruta2.getAbsolutePath());
                }
            }
        }

        //ejercicio 7

        System.out.println("============");
        System.out.println("EJERCICIO 7");

        File copia = new File("D:\\carmen\\AD\\probas\\copia.txt");

        if(datos.exists()){
            datos.renameTo(copia);
            System.out.println("Se cambió el nombre del archivo datos.txt a copia.txt");
        }
        else{
            System.out.println("El fichero datos.txt no existe o ya se ha cambiado el nombre previamente");
        }

        //ejercicio 8

        System.out.println("============");
        System.out.println("EJERCICIO 8");

        System.out.println("¿Cual es la ultima modificacion del fichero copia.txt?");

        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(copia.lastModified());

        System.out.println("Ultima modificación: " + c.getTime());
        
        teclado.close();

        //ejercicio 9

        System.out.println("============");
        System.out.println("EJERCICIO 9");

        File dir = new File("D:\\carmen\\AD\\EjercicioFichero");

        System.out.println("Imprimiendo contenido de: " + dir.getAbsolutePath());

        recursivo(dir, 0);
    }

    public static void recursivo(File dir, int level) {
        File[] lista = dir.listFiles();
        for(File file : lista){
            for(int i = 0; i < level ; i++){
                System.out.print("-");
            }
            System.out.println(file.getName());
            if(file.isDirectory()){
                recursivo(file, level + 1);
            }
        }
    }
     
}
