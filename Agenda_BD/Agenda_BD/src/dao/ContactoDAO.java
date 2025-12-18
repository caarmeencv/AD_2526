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

    // Crear un nuevo contacto
    public void create(ContactoDTO contacto) {
        String sql = "INSERT INTO Contacto(Nombre, Email, Telefono) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getEmail());
            ps.setString(3, contacto.getTelefono());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contacto.setID_Contacto(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Leer un contacto por ID
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
                    contacto.setTelefono(rs.getString("Telefono"));
                    return contacto;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Leer todos los contactos
    public List<ContactoDTO> findAll() {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT ID_Contacto, Nombre, Email, Telefono FROM Contacto";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ContactoDTO contacto = new ContactoDTO();
                contacto.setID_Contacto(rs.getInt("ID_Contacto"));
                contacto.setNombre(rs.getString("Nombre"));
                contacto.setEmail(rs.getString("Email"));
                contacto.setTelefono(rs.getString("Telefono"));
                contactos.add(contacto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    // Buscar contactos por lo que sea, nombre, telefono, email o id
    public static List<ContactoDTO> findBySomething(String hint) {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT ID_Contacto, Nombre, Email, Telefono " +
                    "FROM Contacto " +
                    "WHERE Nombre LIKE ? OR Telefono LIKE ? OR Email LIKE ? OR ID_Contacto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            String like = "%" + hint + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            ps.setString(3, like);

            int id;
            try {
                id = Integer.parseInt(hint);
            } catch (NumberFormatException e) {
                //un valor que no existe en los IDs, por lo cual no devolverá resultados por ID
                id = -1; 
            }
            ps.setInt(4, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ContactoDTO contacto = new ContactoDTO();
                    contacto.setID_Contacto(rs.getInt("ID_Contacto"));
                    contacto.setNombre(rs.getString("Nombre"));
                    contacto.setEmail(rs.getString("Email"));
                    contacto.setTelefono(rs.getString("Telefono"));
                    contactos.add(contacto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contactos;
    }

    // Actualizar un contacto
    public void update(ContactoDTO contacto) {
        String sql = "UPDATE Contacto SET Nombre = ?, Email = ? , Telefono= ? WHERE ID_Contacto = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contacto.getNombre());
            ps.setString(2, contacto.getEmail());
            ps.setString(3, contacto.getTelefono());
            ps.setInt(4, contacto.getID_Contacto());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Borrar un contacto
    public void delete(int id) {
        String sqlContacto = "DELETE FROM Contacto WHERE ID_Contacto = ?";
        String sqlContactoGrupo = "DELETE FROM Contacto_Grupo WHERE ID_Contacto = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Desactivar autocommit para controlar la transaccion
            conn.setAutoCommit(false);

            try (PreparedStatement psCG = conn.prepareStatement(sqlContactoGrupo);
                    PreparedStatement psC = conn.prepareStatement(sqlContacto)) {

                // 1. Borrar relaciones en Contacto_Grupo
                psCG.setInt(1, id);
                psCG.executeUpdate();

                // 2. borrar el contacto
                psC.setInt(1, id);
                psC.executeUpdate();

                // Confirmar todo
                conn.commit();

            } catch (SQLException e) {
                conn.rollback(); // deshacer si hago falla
                throw e;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Añadir un contacto a un grupo
    public void addContactoToGrupo(int contactoId, int grupoId) {
        String sqlInsert = "INSERT INTO Contacto_Grupo (ID_Contacto, ID_Grupo) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
                PreparedStatement ps = conn.prepareStatement(sqlInsert)) {

            ps.setInt(1, contactoId);
            ps.setInt(2, grupoId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Obtener los grupos de un contacto
    public static List<dto.GrupoDTO> findGruposOfContacto(int contactoId) {
        List<dto.GrupoDTO> grupos = new ArrayList<>();
        String sql = "SELECT g.ID_Grupo, g.Nombre " +
                     "FROM Grupo g " +
                     "JOIN Contacto_Grupo cg ON g.ID_Grupo = cg.ID_Grupo " +
                     "WHERE cg.ID_Contacto = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, contactoId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    dto.GrupoDTO grupo = new dto.GrupoDTO();
                    grupo.setID_Grupo(rs.getInt("ID_Grupo"));
                    grupo.setNombre(rs.getString("Nombre"));
                    grupos.add(grupo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    //Comprobar si existe un email
    public static boolean existeEmail(String email) {
        String sql = "SELECT Email FROM Contacto WHERE Email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Comprobar si existe un nombre
    public static boolean existeNombre(String nombre) {
        String sql = "SELECT Nombre FROM Contacto WHERE Nombre = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Comprobar si existe un telefono
    public static boolean existeTelefono(String telefono) {
        String sql = "SELECT Telefono FROM Contacto WHERE Telefono = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, telefono);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
