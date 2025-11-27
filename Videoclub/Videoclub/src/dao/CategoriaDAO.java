package dao;

import dto.CategoriaDTO;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void create(CategoriaDTO categoria) {
        String sql = "INSERT INTO category(name) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, categoria.getName());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setCategory_id(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CategoriaDTO findById(int id) {
        String sql = "SELECT category_id, name, last_update FROM category WHERE category_id = ?";
        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setCategory_id(rs.getInt("actor_id"));
                    categoria.setName(rs.getString("first_name"));
                    categoria.setLast_update(rs.getTimestamp("last_name"));
                    return categoria;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CategoriaDTO> findAll() {
        List<CategoriaDTO> categorias = new ArrayList<>();
        String sql = "SELECT category_id, name, last_update FROM category";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoriaDTO categoria = new CategoriaDTO();
                categoria.setCategory_id(rs.getInt("actor_id"));
                categoria.setName(rs.getString("first_name"));
                categoria.setLast_update(rs.getTimestamp("last_name"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public void update(CategoriaDTO categoria) {
        String sql = "UPDATE category SET name = ? WHERE category_id = ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, categoria.getCategory_id());
            ps.setString(2, categoria.getName());
            ps.setTimestamp(3, categoria.getLast_update());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sqlCategory = "DELETE FROM category WHERE category_id = ?";
        String sqlFilmCategory = "DELETE FROM film_category WHERE category_id = ?";


        try (Connection conn = ConnectionFactory.getConnection()) {

            //Desactivar autocommit para controlar la transaccion
            conn.setAutoCommit(false);

            try(PreparedStatement psFC = conn.prepareStatement(sqlFilmCategory);
            PreparedStatement psC = conn.prepareStatement(sqlCategory)){

                //1. Borrar relaciones en film_actor
                psFC.setInt(1, id);
                psFC.executeUpdate();

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

    public List<CategoriaDTO> findByName(String nombre) {
        List<CategoriaDTO> categorias = new ArrayList<>();
        String sql = "SELECT category_id, name, last_update FROM category WHERE name LIKE ?";

        try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String filtro = "%" + nombre + "%";
            ps.setString(1, filtro);
            ps.setString(2, filtro);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CategoriaDTO categoria = new CategoriaDTO();
                    categoria.setCategory_id(rs.getInt("actor_id"));
                    categoria.setName(rs.getString("first_name"));
                    categoria.setLast_update(rs.getTimestamp("last_name"));
                    categorias.add(categoria);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

}
