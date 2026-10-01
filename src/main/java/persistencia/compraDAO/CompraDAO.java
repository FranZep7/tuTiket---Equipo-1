/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.compraDAO;

import entidad.Compra;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;

public class CompraDAO implements ICompraDAO {
    private IConexion conexion;

    public CompraDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(Compra c) {
        String sql = "INSERT INTO compra (cliente_id, boleto_id, fecha_compra, total, estatus) "
                + "VALUES (?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getClienteId());
            ps.setInt(2, c.getBoletoId());
            ps.setTimestamp(3, Timestamp.valueOf(c.getFechaHora()));
            ps.setDouble(4, c.getTotal());
            ps.setString(5, c.getEstado());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Compra consultarPorId(int id) {
        String sql = "SELECT * FROM compra WHERE id = ?";
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
    public List<Compra> consultarPorCliente(int clienteId) {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM compra WHERE cliente_id = ? ORDER BY fecha_compra DESC";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Compra> consultarPorEvento(int eventoId) {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT c.* FROM compra c "
                + "JOIN boleto b ON b.id = c.boleto_id "
                + "WHERE b.evento_id = ? ORDER BY c.fecha_compra DESC";
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
    public List<Compra> consultarTodas() {
        List<Compra> lista = new ArrayList<>();
        String sql = "SELECT * FROM compra";
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
    public void actualizar(Compra c) {
        String sql = "UPDATE compra SET cliente_id = ?, boleto_id = ?, fecha_compra = ?, "
                + "total = ?, estatus = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getClienteId());
            ps.setInt(2, c.getBoletoId());
            ps.setTimestamp(3, Timestamp.valueOf(c.getFechaHora()));
            ps.setDouble(4, c.getTotal());
            ps.setString(5, c.getEstado());
            ps.setInt(6, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM compra WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Compra mapear(ResultSet rs) throws SQLException {
        Compra c = new Compra();
        c.setId(rs.getInt("id"));
        c.setClienteId(rs.getInt("cliente_id"));
        c.setBoletoId(rs.getInt("boleto_id"));
        c.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
        c.setTotal(rs.getDouble("total"));
        c.setEstado(rs.getString("estatus"));
        return c;
    }
}