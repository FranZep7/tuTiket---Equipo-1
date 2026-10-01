/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.promotoraDAO;

import entidad.Promotora;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;

public class PromotoraDAO implements IPromotoraDAO {
    private IConexion conexion;

    public PromotoraDAO (IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(Promotora p) {
        String sql = "INSERT INTO promotora (nombre_comercial, calle, numero, colonia, "
                + "ciudad, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombreComercial());
            ps.setString(2, p.getCalle());
            ps.setString(3, p.getNumero());
            ps.setString(4, p.getColonia());
            ps.setString(5, p.getCiudad());
            ps.setString(6, p.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Promotora consultarPorId(int id) {
        String sql = "SELECT * FROM promotora WHERE id = ?";
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
    public List<Promotora> consultarTodas() {
        List<Promotora> lista = new ArrayList<>();
        String sql = "SELECT * FROM promotora";
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
    public void actualizar(Promotora p) {
        String sql = "UPDATE promotora SET nombre_comercial = ?, calle = ?, numero = ?, "
                + "colonia = ?, ciudad = ?, estado = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombreComercial());
            ps.setString(2, p.getCalle());
            ps.setString(3, p.getNumero());
            ps.setString(4, p.getColonia());
            ps.setString(5, p.getCiudad());
            ps.setString(6, p.getEstado());
            ps.setInt(7, p.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM promotora WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Promotora mapear(ResultSet rs) throws SQLException {
        Promotora p = new Promotora();
        p.setId(rs.getInt("id"));
        p.setNombreComercial(rs.getString("nombre_comercial"));
        p.setCalle(rs.getString("calle"));
        p.setNumero(rs.getString("numero"));
        p.setColonia(rs.getString("colonia"));
        p.setCiudad(rs.getString("ciudad"));
        p.setEstado(rs.getString("estado"));
        return p;
    }
}
