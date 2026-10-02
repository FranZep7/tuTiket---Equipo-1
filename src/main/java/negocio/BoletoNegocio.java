/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidad.Boleto;
import java.util.List;
import persistencia.boletoDAO.IBoletoDAO;

/**
 *
 * @author tolan
 */
public class BoletoNegocio {
    private final IBoletoDAO boletoDAO;

    public BoletoNegocio(IBoletoDAO boletoDAO) {
        this.boletoDAO = boletoDAO;
    }

    public List<Boleto> disponiblesPorEvento(int eventoId) {
        return boletoDAO.consultarDisponiblesPorEvento(eventoId);
    }

    public List<Boleto> todosPorEvento(int eventoId) {
        return boletoDAO.consultarPorEvento(eventoId);
    }
}
