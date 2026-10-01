/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.clienteDAO;

import entidad.Cliente;
import persistencia.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import persistencia.IConexion;
/**
 *
 * @author tolan
 */
public class ClienteDAO implements IClienteDAO{
    private IConexion conexion;

    public ClienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO cliente (usuario, contrasena, nombres, apellido_paterno, "
                + "apellido_materno, fecha_nacimiento, saldo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getUsuario());
            ps.setString(2, cliente.getContrasena());
            ps.setString(3, cliente.getNombres());
            ps.setString(4, cliente.getApellidoPaterno());
            ps.setString(5, cliente.getApellidoMaterno());
            ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente consultarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";
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
    public Cliente consultarPorUsuario(String usuario) {
        String sql = "SELECT * FROM cliente WHERE usuario = ?";
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
    public List<Cliente> consultarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
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
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET usuario = ?, contrasena = ?, nombres = ?, "
                + "apellido_paterno = ?, apellido_materno = ?, fecha_nacimiento = ?, saldo = ? "
                + "WHERE id = ?";
        try (Connection con = conexion.crearConexion();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, cliente.getUsuario());
            ps.setString(2, cliente.getContrasena());
            ps.setString(3, cliente.getNombres());
            ps.setString(4, cliente.getApellidoPaterno());
            ps.setString(5, cliente.getApellidoMaterno());
            ps.setDate(6, Date.valueOf(cliente.getFechaNacimiento()));
            ps.setInt(8, cliente.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Cliente mapear(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setId(rs.getInt("id"));
        c.setUsuario(rs.getString("usuario"));
        c.setContrasena(rs.getString("contrasena"));
        c.setNombres(rs.getString("nombres"));
        c.setApellidoPaterno(rs.getString("apellido_paterno"));
        c.setApellidoMaterno(rs.getString("apellido_materno"));
        c.setFechaNacimiento(rs.getString("fecha_nacimiento"));
        return c;
    }
}