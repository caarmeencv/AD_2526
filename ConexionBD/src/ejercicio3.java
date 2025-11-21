
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ejercicio3 {

    public static void main(String[] args) throws Exception {

        /* Proba a:
         *
         * Usa try with resources para a conexion e o statement
         * 
         * Con un solo statement: onter 2 ResultSET (todos os actores e todas as peliculas, con LIMIT 5)
         * 
         * Despois imprime ambas tablas
         */
        String url = "jdbc:mysql://localhost:3306/sakila";
        String user = "root";
        String password = "";

        Scanner teclado = new Scanner(System.in);
        try (Connection conn = DriverManager.getConnection(url, user, password); Statement stmt = conn.createStatement()) {

            String query1 = "SELECT * FROM actor LIMIT 5";
            String query2 = "SELECT * FROM film LIMIT 5";

            try (ResultSet rs1 = stmt.executeQuery(query1)) {
                while (rs1.next()) {
                    System.out.println(rs1.getString("first_name"));
                }
            }

            try (ResultSet rs2 = stmt.executeQuery(query2)) {
                while (rs2.next()) {
                    System.out.println(rs2.getString("title"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
