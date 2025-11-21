
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class gestionActores {

    //HACER UN PROGRAMA QUE PERMITA
    // - BUSCAR ACTORES
    // - BORRAR ACTORES POR ID
    // - ACTUALIZAR ACTORES POR ID
    // - INSERTAR NUEVOS ACTORES
    //USA STATEMENT Y PREPAREDSTATEMENT CUANDO CORRESPONDA


        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "";

        Scanner teclado = new Scanner(System.in);

        //Submenu
    public static void menu() {
        System.out.println("\n    MENU    ");
        System.out.println("===============");
        System.out.println("1- Buscar actor por nombre.");
        System.out.println("2- Borrar actor por id.");
        System.out.println("3- Actualizar actor por id.");
        System.out.println("4- Insertar nuevo actor.");
        System.out.println("5- Salir.\n");
    }

    public int dameOpcion() {
        int opcion;
        menu();
        opcion = teclado.nextInt();
        teclado.nextLine();
        return opcion;
    }

    public void consultarActor(){
        System.out.println("Dime el nombre del actor que quieras buscar:");
        String nombre = teclado.nextLine();

        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String query = "SELECT * FROM actor WHERE first_name LIKE \"%" + nombre + "%\";";

            System.out.println("RESULTADO:");

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("actor_id") + "\t" + rs.getString("first_name") + "\t" + rs.getString("last_name") + "\t" + rs.getString("last_update"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarActor(){
        System.out.println("Dime el id del actor al que borrar:");
        int id = teclado.nextInt();


        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String query = "DELETE FROM actor WHERE actor_id LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();

            if (rows == 0) {
                System.out.println("No existe ningún actor con ese id.");
            }

            System.out.println("Se ha eliminado " + rows + " fila");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarActor(){
        System.out.println("Dime el id del actor que quieres modificar:");
        int id = teclado.nextInt();
        teclado.nextLine();
        System.out.println("¿Qué nombre le quieres poner?");
        String nombre = teclado.nextLine();
        System.out.println("¿Qué apellido le quieres poner?");
        String apellido = teclado.nextLine();

        try (Connection conn = DriverManager.getConnection(url, user, password)){

            String query = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, nombre.toUpperCase());
            pstmt.setString(2, apellido.toUpperCase());
            pstmt.setInt(3, id);
            int rows = pstmt.executeUpdate();

            if (rows == 0) {
                System.out.println("No existe ningún actor con ese id.");
            }

            System.out.println("Se ha modificado " + rows + " fila");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarActor(){

        System.out.println("¿Qué nombre le quieres poner?");
        String nombre = teclado.nextLine();
        System.out.println("¿Qué apellido le quieres poner?");
        String apellido = teclado.nextLine();

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String query = "INSERT INTO actor(first_name, last_name) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            int rows = pstmt.executeUpdate();

            System.out.println("Se ha añadido " + rows + " fila");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
}