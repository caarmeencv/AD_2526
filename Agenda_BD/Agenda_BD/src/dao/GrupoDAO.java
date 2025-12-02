package dao;

import dto.GrupoDTO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    public void create(GrupoDTO Grupo) {
        String sql = "INSERT INTO Grupo(Nombre) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, Grupo.getNombre());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Grupo.setID_Grupo(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public GrupoDTO findById(int id) {
        String sql = "SELECT ID_Grupo, Nombre FROM Grupo WHERE ID_Grupo = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GrupoDTO Grupo = new GrupoDTO();
                    Grupo.setID_Grupo(rs.getInt("actor_id"));
                    Grupo.setNombre(rs.getString("first_name"));
                    return Grupo;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<GrupoDTO> findAll() {
        List<GrupoDTO> Grupos = new ArrayList<>();
        String sql = "SELECT ID_Grupo, Nombre FROM Grupo";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GrupoDTO Grupo = new GrupoDTO();
                Grupo.setID_Grupo(rs.getInt("ID_Grupo"));
                Grupo.setNombre(rs.getString("Nombre"));
                Grupos.add(Grupo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Grupos;
    }

    public void update(GrupoDTO Grupo) {
        String sql = "UPDATE Grupo SET Nombre = ? WHERE ID_Grupo = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Grupo.getID_Grupo());
            ps.setString(2, Grupo.getNombre());


            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sqlGrupo = "DELETE FROM Grupo WHERE ID_Grupo = ?";
        String sqlGrupoContacto = "DELETE FROM Contacto_Grupo WHERE ID_Grupo = ?";


        try (Connection conn = ConnectionFactory.getConnection()) {

            //Desactivar autocommit para controlar la transaccion
            conn.setAutoCommit(false);

            try(PreparedStatement psGC = conn.prepareStatement(sqlGrupoContacto);
            PreparedStatement psG = conn.prepareStatement(sqlGrupo)){

                //1. Borrar relaciones en film_actor
                psGC.setInt(1, id);
                psGC.executeUpdate();

                //2. borrar el actor
                psG.setInt(1, id);
                psG.executeUpdate();

                //Confirmar todo
                conn.commit();
            
            
            } catch (SQLException e) {
            conn.rollback(); //deshacer si hago falla
            throw e;
        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<GrupoDTO> findByName(String nombre) {
        List<GrupoDTO> Grupos = new ArrayList<>();
        String sql = "SELECT ID_Grupo, Nombre FROM Grupo WHERE Nombre LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + nombre + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GrupoDTO Grupo = new GrupoDTO();
                    Grupo.setID_Grupo(rs.getInt("actor_id"));
                    Grupo.setNombre(rs.getString("first_name"));
                    Grupos.add(Grupo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Grupos;
    }

}
