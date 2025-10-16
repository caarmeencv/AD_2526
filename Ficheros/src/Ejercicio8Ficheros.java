import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class Ejercicio8Ficheros {

    public static void main(String[] args) throws Exception {

        ArrayList<Trabajador> lista = new ArrayList<>();

        Trabajador t1 = new Trabajador ("Pepita", 3000.0, "Jefa", 20);
        Trabajador t2 = new Trabajador ("Pepito", 2000.0, "Encargado", 30);
        Trabajador t3 = new Trabajador ("Pepite", 1000.0, "Director", 40);
        Trabajador t4 = new Trabajador ("Pepiti", 2300.0, "Subdirectora", 50);
        Trabajador t5 = new Trabajador ("Pepitu", 1500.0, "Trabajador", 60);
    
        lista.add(t1);
        lista.add(t2);
        lista.add(t3);
        lista.add(t4);
        lista.add(t5);

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("trabajadores.dat"))){
            out.writeObject(lista);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("trabajadores.dat"))){
            ArrayList<Trabajador> listaLeida = (ArrayList<Trabajador>) in.readObject();

            for(Trabajador t : listaLeida){
                System.out.println(t.toString());
            }
        }
    }

}