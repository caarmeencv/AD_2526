import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class insert {

public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/sakila";
        String user ="root";
        String password = "";

         try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String query = "INSERT INTO actor(first_name, last_name) VALUES ('DANIEL', 'VILAS')";

            int rows = stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}