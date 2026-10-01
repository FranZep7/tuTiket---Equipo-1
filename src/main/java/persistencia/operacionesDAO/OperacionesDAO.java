/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.operacionesDAO;

import entidad.Operaciones;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;

public class OperacionesDAO implements IOperacionesDAO {
    private IConexion conexion;

    public OperacionesDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(Operaciones r) {
        String sql = "INSERT INTO registro_operacion (cuenta_cliente_id, tipo_operacion, monto, fecha_hora) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, r.getCuentaClienteId());
            ps.setString(2, r.getTipoOperacion());
            ps.setDouble(3, r.getMonto());
            ps.setTimestamp(4, Timestamp.valueOf(r.getFechaHora()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Operaciones> consultarPorCuenta(int cuentaClienteId) {
        List<Operaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM registro_operacion WHERE cuenta_cliente_id = ? "
                + "ORDER BY fecha_hora DESC";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, cuentaClienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Operaciones> consultarTodos() {
        List<Operaciones> lista = new ArrayList<>();
        String sql = "SELECT * FROM registro_operacion ORDER BY fecha_hora DESC";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) lista.add(mapear(rs));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    private Operaciones mapear(ResultSet rs) throws SQLException {
        Operaciones r = new Operaciones();
        r.setId(rs.getInt("id"));
        r.setCuentaClienteId(rs.getInt("cuenta_cliente_id"));
        r.setTipoOperacion(rs.getString("tipo_operacion"));
        r.setMonto(rs.getDouble("monto"));
        r.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
        return r;
    }
}
