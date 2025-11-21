import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ejercicio2 {

public static void main(String[] args) throws Exception {

        //Facer un programa que permita buscar peliculas por duracion minima e unha duracion maxima, que a duracion cumpla esos dous parametros

        String url = "jdbc:mysql://localhost:3306/sakila";
        String user ="root";
        String password = "";
        Scanner teclado = new Scanner(System.in);
        Connection conn = DriverManager.getConnection(url, user, password);

        String query = "SELECT * FROM film WHERE length > ? AND length < ?;";

        PreparedStatement pstmt = conn.prepareStatement(query);

        System.out.println("Introduce la duracion minima: ");
        String duracion1 = teclado.nextLine();
        System.out.println("Introduce la duracion maxima: ");
        String duracion2 = teclado.nextLine();

        pstmt.setString(1, duracion1);
        pstmt.setString(2, duracion2);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()){
            System.out.println(rs.getInt("film_id") + " " + rs.getString("title") + " " + rs.getInt("length") + " ");
        }

        rs.close();
        
        pstmt.close();

        conn.close();
    }
}
