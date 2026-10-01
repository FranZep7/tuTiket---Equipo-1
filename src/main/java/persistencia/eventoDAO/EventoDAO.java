/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.eventoDAO;

import entidad.Evento;
import persistencia.IConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO implements IEventoDAO {
    private IConexion conexion;

    public EventoDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public void insertar(Evento e) {
        String sql = "INSERT INTO evento (promotora_id, nombre, tipo_evento, edad_minima, "
                + "imagen_promocional, cantidad_boletos) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = conexion.crearConexion();
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getPromotoraId());
            ps.setString(2, e.getNombreEvento());
            ps.setString(3, e.getTipoEvento());
            ps.setInt(4, e.getEdadMinima());
            ps.setString(5, e.getImagenPromocional());
            ps.setInt(6, e.getCantidadBoletos());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Evento consultarPorId(int id) {
        String sql = "SELECT * FROM evento WHERE id = ?";
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
    public List<Evento> consultarPorPromotora(int promotoraId) {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM evento WHERE promotora_id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, promotoraId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) lista.add(mapear(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Evento> consultarTodos() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM evento";
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
    public void actualizar(Evento e) {
        String sql = "UPDATE evento SET promotora_id = ?, nombre = ?, tipo_evento = ?, "
                + "edad_minima = ?, imagen_promocional = ?, cantidad_boletos = ? WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getPromotoraId());
            ps.setString(2, e.getNombreEvento());
            ps.setString(3, e.getTipoEvento());
            ps.setInt(4, e.getEdadMinima());
            ps.setString(5, e.getImagenPromocional());
            ps.setInt(6, e.getCantidadBoletos());
            ps.setInt(7, e.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM evento WHERE id = ?";
        try (Connection con = conexion.crearConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private Evento mapear(ResultSet rs) throws SQLException {
        Evento e = new Evento();
        e.setId(rs.getInt("id"));
        e.setPromotoraId(rs.getInt("promotora_id"));
        e.setNombre(rs.getString("nombre"));
        e.setTipoEvento(rs.getString("tipo_evento"));
        e.setEdadMinima(rs.getInt("edad_minima"));
        e.setImagenPromocional(rs.getString("imagen_promocional"));
        e.setCantidadBoletos(rs.getInt("cantidad_boletos"));
        return e;
    }
}