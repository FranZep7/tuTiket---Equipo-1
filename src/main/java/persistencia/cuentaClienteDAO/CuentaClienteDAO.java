/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.cuentaClienteDAO;
import entidad.CuentaCliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;

public class CuentaClienteDAO implements ICuentaClienteDAO {
    private IConexion conexion;

    public CuentaClienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(CuentaCliente c) {
        String sql = "INSERT INTO cuenta_cliente (cliente_id, banco, numero_cuenta, saldo) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, c.getClienteId());
            ps.setString(2, c.getBanco());
            ps.setString(3, c.getNumeroCuenta());
            ps.setDouble(4, c.getSaldo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public CuentaCliente consultarPorId(int id) {
        String sql = "SELECT * FROM cuenta_cliente WHERE id = ?";
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
    public List<CuentaCliente> consultarPorCliente(int clienteId) {
        List<CuentaCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_cliente WHERE cliente_id = ?";
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
    public List<CuentaCliente> consultarTodas() {
        List<CuentaCliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cuenta_cliente";
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
    public void actualizar(CuentaCliente c) {
        String sql = "UPDATE cuenta_cliente SET cliente_id = ?, banco = ?, numero_cuenta = ?, "
                + "saldo = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, c.getClienteId());
            ps.setString(2, c.getBanco());
            ps.setString(3, c.getNumeroCuenta());
            ps.setDouble(4, c.getSaldo());
            ps.setInt(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cuenta_cliente WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private CuentaCliente mapear(ResultSet rs) throws SQLException {
        CuentaCliente c = new CuentaCliente();
        c.setId(rs.getInt("id"));
        c.setClienteId(rs.getInt("cliente_id"));
        c.setBanco(rs.getString("banco"));
        c.setNumeroCuenta(rs.getString("numero_cuenta"));
        c.setSaldo(rs.getDouble("saldo"));
        return c;
    }
}