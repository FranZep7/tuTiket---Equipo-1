/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidad.Boleto;
import entidad.Evento;
import java.sql.SQLException;
import persistencia.boletoDAO.IBoletoDAO;
import persistencia.clienteDAO.IClienteDAO;
import persistencia.cuentaClienteDAO.ICuentaClienteDAO;
import persistencia.eventoDAO.IEventoDAO;
import persistencia.operacionesDAO.IOperacionesDAO;

/**
 *
 * @author tolan
 */
public class EventoNegocio {
    private final IEventoDAO eventoDAO;
    private final IBoletoDAO boletoDAO;

    public EventoNegocio(IEventoDAO eventoDAO, IBoletoDAO boletoDAO) {
        this.eventoDAO = eventoDAO;
        this.boletoDAO = boletoDAO;
    }
    public void confirmarEvento(Evento evento, double costoBoleto) throws SQLException { {
        // 1. Insertar el evento
        eventoDAO.insertar(evento);

        // 2. Generar inventario automáticamente
        for (int i = 1; i <= evento.getCantidadBoletos(); i++) {
            Boleto b = new Boleto(
                    evento.getId(),
                     "DISPONIBLE",
                    costoBoleto);
            boletoDAO.insertar(b);
        }
    }
}
}
