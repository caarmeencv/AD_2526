import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ejercicio1 {

public static void main(String[] args) throws Exception {

        //Facer un programa que permita buscar peliculas, sin que o nome sexa unha coincidencia exacta.

        String url = "jdbc:mysql://localhost:3306/sakila";
        String user ="root";
        String password = "";
        Scanner teclado = new Scanner(System.in);
        Connection conn = DriverManager.getConnection(url, user, password);

        String query = "SELECT * FROM film WHERE title LIKE ?;";

        PreparedStatement pstmt = conn.prepareStatement(query);

        System.out.println("Introduce el titulo: ");
        String titulo = teclado.nextLine();

        pstmt.setString(1, "%"+titulo+"%");
        
        //pstmt.setString(1, nombre);
        //pstmt.setString(2, apellido);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt("film_id") + " " + rs.getString("title") + " ");
        }

        rs.close();
        
        pstmt.close();

        conn.close();
    }
}
