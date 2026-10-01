/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.administradorDAO;

import entidad.Administrador;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;

public class AdministradorDAO implements IAdministradorDAO {
    private IConexion conexion;

    public AdministradorDAO (IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(Administrador admin) {
        String sql = "INSERT INTO administrador (promotora_id, usuario, contrasena, nombres, "
                + "apellido_paterno, apellido_materno) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, admin.getPromotoraId());
            ps.setString(2, admin.getUsuario());
            ps.setString(3, admin.getContrasena());
            ps.setString(4, admin.getNombres());
            ps.setString(5, admin.getApellidoPaterno());
            ps.setString(6, admin.getApellidoMaterno());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) admin.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Administrador consultarPorId(int id) {
        String sql = "SELECT * FROM administrador WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Administrador consultarPorUsuario(String usuario) {
        String sql = "SELECT * FROM administrador WHERE usuario = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, usuario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Administrador> consultarPorPromotora(int promotoraId) {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM administrador WHERE promotora_id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, promotoraId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Administrador> consultarTodos() {
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM administrador";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Administrador admin) {
        String sql = "UPDATE administrador SET promotora_id = ?, usuario = ?, contrasena = ?, "
                + "nombres = ?, apellido_paterno = ?, apellido_materno = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, admin.getPromotoraId());
            ps.setString(2, admin.getUsuario());
            ps.setString(3, admin.getContrasena());
            ps.setString(4, admin.getNombres());
            ps.setString(5, admin.getApellidoPaterno());
            ps.setString(6, admin.getApellidoMaterno());
            ps.setInt(7, admin.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM administrador WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Administrador mapear(ResultSet rs) throws SQLException {
        Administrador a = new Administrador();
        a.setId(rs.getInt("id"));
        a.setPromotoraId(rs.getInt("promotora_id"));
        a.setUsuario(rs.getString("usuario"));
        a.setContrasena(rs.getString("contrasena"));
        a.setNombres(rs.getString("nombres"));
        a.setApellidoPaterno(rs.getString("apellido_paterno"));
        a.setApellidoMaterno(rs.getString("apellido_materno"));
        return a;
    }
}