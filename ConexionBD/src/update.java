import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class update {

public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/sakila";
        String user ="root";
        String password = "";

         try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String query = "UPDATE actor SET first_name = 'CARMEN' WHERE first_name = 'PENELOPE'";

            int rows = stmt.executeUpdate(query);

            System.out.println("Se han modificado " + rows + " filas");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}