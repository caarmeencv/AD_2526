package dao;

import dto.ContactoDTO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    public void create(ContactoDTO contacto) {
        String sql = "INSERT INTO Contacto(Nombre, Email, Telefono) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getEmail());
            ps.setInt(3, contacto.getTelefono());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    // generated key available: handle assignment to ContactoDTO here if DTO exposes an ID setter
                    // e.g. contacto.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ContactoDTO findById(int id) {
        String sql = "SELECT ID_Contacto, Nombre, Email, Telefono FROM Contacto WHERE ID_Contacto = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ContactoDTO contacto = new ContactoDTO();
                    contacto.setID_Contacto(rs.getInt("ID_Contacto"));
                    contacto.setNombre(rs.getString("Nombre"));
                    contacto.setEmail(rs.getString("Email"));
                    contacto.setTelefono(rs.getInt("Telefono"));
                    return contacto;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ContactoDTO> findAll() {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT ID_Contacto, Nombre, Email, Telefono FROM Contacto";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ContactoDTO contacto = new ContactoDTO();
                contacto.setID_Contacto(rs.getInt("ID_Contacto"));
                contacto.setNombre(rs.getString("Nombre"));
                contacto.setEmail(rs.getString("Email"));
                contacto.setTelefono(rs.getInt("Telefono"));
                contactos.add(contacto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    public void update(ContactoDTO contacto) {
        String sql = "UPDATE Contacto SET Nombre = ?, Email = ? , Telefono= ? WHERE ID_Contacto = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getEmail());
            ps.setInt(3, contacto.getTelefono());
            ps.setInt(4, contacto.getID_Contacto());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sqlContacto = "DELETE FROM Contacto WHERE ID_Contacto = ?";
        String sqlContactoGrupo = "DELETE FROM Contacto_Grupo WHERE ID_Contacto = ?";


        try (Connection conn = ConnectionFactory.getConnection()) {

            //Desactivar autocommit para controlar la transaccion
            conn.setAutoCommit(false);

            try(PreparedStatement psCG = conn.prepareStatement(sqlContactoGrupo);
            PreparedStatement psC = conn.prepareStatement(sqlContacto)){

                //1. Borrar relaciones en film_actor
                psCG.setInt(1, id);
                psCG.executeUpdate();

                //2. borrar el actor
                psC.setInt(1, id);
                psC.executeUpdate();

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

    public List<ContactoDTO> findByNameOrEmail(String NombreEmail) {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT ID_Contacto, Nombre, Email, Telefono FROM Contacto "
                + "WHERE Nombre LIKE ? OR Email LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + NombreEmail + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ContactoDTO contacto = new ContactoDTO();
                    contacto.setID_Contacto(rs.getInt("ID_Contacto"));
                    contacto.setNombre(rs.getString("Nombre"));
                    contacto.setEmail(rs.getString("Email"));
                    contacto.setTelefono(rs.getInt("Telefono"));
                    contactos.add(contacto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactos;
    }

}
