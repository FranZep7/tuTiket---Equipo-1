/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidad.Compra;
import java.sql.SQLException;
import persistencia.compraDAO.ICompraDAO;

/**
 *
 * @author tolan
 */
public class TableroNegocio {
    private final ICompraDAO compraDAO;

    public TableroNegocio(ICompraDAO compraDAO) {
        this.compraDAO = compraDAO;
    }

    public double ventasPorEvento(int eventoId) throws SQLException {
        double total = 0;
        for (Compra c : compraDAO.consultarPorEvento(eventoId)) {
            if ("COMPRADO".equals(c.getEstado())) {
                total += c.getTotal();
            }
        }
        return total;
    }

    public int boletosVendidosPorEvento(int eventoId) throws SQLException {
        int contador = 0;
        for (Compra c : compraDAO.consultarPorEvento(eventoId)) {
            if ("COMPRADO".equals(c.getEstado())) {
                contador++;
            }
        }
        return contador;
    }

    public double ventasTotales() throws SQLException {
        double total = 0;
        for (Compra c : compraDAO.consultarTodas()) {
            if ("COMPRADO".equals(c.getEstado())) {
                total += c.getTotal();
            }
        }
        return total;
    }
}
