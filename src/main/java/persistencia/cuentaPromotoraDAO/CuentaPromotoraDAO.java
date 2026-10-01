/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.cuentaPromotoraDAO;

import entidad.CuentaPromotora;
import persistencia.IConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaPromotoraDAO implements ICuentaPromotoraDAO {
    private IConexion conexion;

    public CuentaPromotoraDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(CuentaPromotora c) {
        String sql = "INSERT INTO cuenta_promotora (promotora_id, banco, numero_cuenta, saldo) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getPromotoraId());
            ps.setString(2, c.getBanco());
            ps.setString(3, c.getNumeroCuenta());
            ps.setDouble(4, c.getSaldo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public CuentaPromotora consultarPorId(int id) {
        String sql = "SELECT * FROM cuenta_promotora WHERE id = ?";
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
    public List<CuentaPromotora> consultarPorPromotora(int promotoraId) {
        List<CuentaPromotora> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_promotora WHERE promotora_id = ?";
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
    public List<CuentaPromotora> consultarTodas() {
        List<CuentaPromotora> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_promotora";
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
    public void actualizar(CuentaPromotora c) {
        String sql = "UPDATE cuenta_promotora SET promotora_id = ?, banco = ?, numero_cuenta = ?, "
                + "saldo = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getPromotoraId());
            ps.setString(2, c.getBanco());
            ps.setString(3, c.getNumeroCuenta());
            ps.setDouble(4, c.getSaldo());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cuenta_promotora WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CuentaPromotora mapear(ResultSet rs) throws SQLException {
        CuentaPromotora c = new CuentaPromotora();
        c.setId(rs.getInt("id"));
        c.setPromotoraId(rs.getInt("promotora_id"));
        c.setBanco(rs.getString("banco"));
        c.setNumeroCuenta(rs.getString("numero_cuenta"));
        c.setSaldo(rs.getDouble("saldo"));
        return c;
    }
}
