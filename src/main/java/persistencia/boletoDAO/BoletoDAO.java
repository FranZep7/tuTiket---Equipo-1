/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.boletoDAO;


import entidad.Boleto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;

public class BoletoDAO implements IBoletoDAO {
    private IConexion conexion;

    public BoletoDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    @Override
    public void insertar(Boleto b) {
        String sql = "INSERT INTO boleto (evento_id, id, costo, estatus) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, b.getEventoId());
            ps.setInt(2, b.getId());
            ps.setDouble(3, b.getCosto());
            ps.setString(4, b.getEstatus());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) b.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Boleto consultarPorId(int id) {
        String sql = "SELECT * FROM boleto WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Boleto> consultarPorEvento(int eventoId) {
        List<Boleto> lista = new ArrayList<>();
        String sql = "SELECT * FROM boleto WHERE evento_id = ? ORDER BY numero_boleto";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Boleto> consultarDisponiblesPorEvento(int eventoId) {
        List<Boleto> lista = new ArrayList<>();
        String sql = "SELECT * FROM boleto WHERE evento_id = ? AND estatus = 'DISPONIBLE' "
                + "ORDER BY numero_boleto";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, eventoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Boleto> consultarTodos() {
        List<Boleto> lista = new ArrayList<>();
        String sql = "SELECT * FROM boleto";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public void actualizar(Boleto b) {
        String sql = "UPDATE boleto SET evento_id = ?, numero_boleto = ?, costo = ?, "
                + "estatus = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, b.getEventoId());
            ps.setInt(2, b.getId());
            ps.setDouble(3, b.getCosto());
            ps.setString(4, b.getEstatus());
            ps.setInt(5, b.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM boleto WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Boleto mapear(ResultSet rs) throws SQLException {
        Boleto b = new Boleto();
        b.setId(rs.getInt("id"));
        b.setEventoId(rs.getInt("evento_id"));
        b.setCosto(rs.getDouble("costo"));
        b.setEstatus(rs.getString("estatus"));
        return b;
    }
}
