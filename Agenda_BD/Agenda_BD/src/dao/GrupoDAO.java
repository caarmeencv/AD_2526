package dao;

import dto.ContactoDTO;
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

    // Crear un nuevo grupo
    public void create(GrupoDTO grupo) {
        String sql = "INSERT INTO Grupo(Nombre) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, grupo.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    grupo.setID_Grupo(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar grupo por ID
    public GrupoDTO findById(int id) {
        String sql = "SELECT ID_Grupo, Nombre FROM Grupo WHERE ID_Grupo = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GrupoDTO grupo = new GrupoDTO();
                    grupo.setID_Grupo(rs.getInt("ID_Grupo"));
                    grupo.setNombre(rs.getString("Nombre"));
                    return grupo;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Obtener todos los grupos
    public List<GrupoDTO> findAll() {
        List<GrupoDTO> grupos = new ArrayList<>();
        String sql = "SELECT ID_Grupo, Nombre FROM Grupo";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GrupoDTO grupo = new GrupoDTO();
                grupo.setID_Grupo(rs.getInt("ID_Grupo"));
                grupo.setNombre(rs.getString("Nombre"));
                grupos.add(grupo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return grupos;
    }

    // Actualizar un grupo existente
    public void update(GrupoDTO grupo) {
        String sql = "UPDATE Grupo SET Nombre = ? WHERE ID_Grupo = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, grupo.getNombre());
            ps.setInt(2, grupo.getID_Grupo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Eliminar un grupo por ID
    public void delete(int id) {
        String sqlGrupo = "DELETE FROM Grupo WHERE ID_Grupo = ?";
        String sqlGrupoContacto = "DELETE FROM Contacto_Grupo WHERE ID_Grupo = ?";

        try (Connection conn = ConnectionFactory.getConnection()) {

            // Desactivar autocommit para controlar la transaccion
            conn.setAutoCommit(false);

            try (PreparedStatement psGC = conn.prepareStatement(sqlGrupoContacto);
                 PreparedStatement psG = conn.prepareStatement(sqlGrupo)) {

                // 1. Borrar relaciones en Contacto_Grupo
                psGC.setInt(1, id);
                psGC.executeUpdate();

                // 2. borrar el grupo
                psG.setInt(1, id);
                psG.executeUpdate();

                // Confirmar todo
                conn.commit();

            } catch (SQLException e) {
                conn.rollback(); //Deshacer si algo falla
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Buscar grupos por nombre 
    public List<GrupoDTO> findByName(String nombre) {
        List<GrupoDTO> grupos = new ArrayList<>();
        String sql = "SELECT ID_Grupo, Nombre FROM Grupo WHERE Nombre LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + nombre + "%";
            ps.setString(1, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    GrupoDTO grupo = new GrupoDTO();
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

    // Obtener los contactos de un grupo
    public List<ContactoDTO> findContactsInGroup(int idGrupo) {
        List<ContactoDTO> contactos = new ArrayList<>();
        String sql = "SELECT c.ID_Contacto, c.Nombre, c.Telefono, c.Email FROM Contacto c " +
                     "JOIN Contacto_Grupo cg ON c.ID_Contacto = cg.ID_Contacto " +
                     "WHERE cg.ID_Grupo = ? ORDER BY c.Nombre";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idGrupo);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ContactoDTO contacto = new ContactoDTO();
                    contacto.setID_Contacto(rs.getInt("ID_Contacto"));
                    contacto.setNombre(rs.getString("Nombre"));
                    try {
                        contacto.setTelefono(rs.getString("Telefono"));
                    } catch (SQLException ignored) {}
                    try {
                        contacto.setEmail(rs.getString("Email"));
                    } catch (SQLException ignored) {}
                    contactos.add(contacto);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactos;
    }

    //Comprobar si existe un nombre
    public static boolean existeNombre(String nombre) {
        String sql = "SELECT 1 FROM Grupo WHERE Nombre = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay algún registro, el nombre ya existe
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
