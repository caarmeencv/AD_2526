import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class delete {

public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/sakila";
        String user ="root";
        String password = "";

         try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String query = "DELETE FROM film_actor WHERE actor_id = 2 AND film_id = 3";

            int rows = stmt.executeUpdate(query);

            System.out.println("Se han modificado " + rows + " filas");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}