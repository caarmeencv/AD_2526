
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:sqlite:database/prueba.dt";

        Connection conn = DriverManager.getConnection(url);

        Statement stmt = conn.createStatement();

        Integer i = stmt.executeUpdate("INSERT INTO actor(nombre) VALUES ('Piferrer')");

        stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM actor");

        while(rs.next()){
            System.out.println(rs.getInt("actor_id") + " " + rs.getString("nombre"));
        }
    }
}
