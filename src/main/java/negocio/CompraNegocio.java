/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidad.Boleto;
import entidad.Cliente;
import entidad.Compra;
import entidad.CuentaCliente;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import persistencia.boletoDAO.IBoletoDAO;
import persistencia.clienteDAO.IClienteDAO;
import persistencia.compraDAO.ICompraDAO;
import persistencia.cuentaClienteDAO.ICuentaClienteDAO;

/**
 *
 * @author tolan
 */
public class CompraNegocio {
    private final ICompraDAO compraDAO;
    private final IBoletoDAO boletoDAO;
    private final ICuentaClienteDAO cuentaClienteDAO;

    public CompraNegocio(ICompraDAO compraDAO,
                         IBoletoDAO boletoDAO,
                         ICuentaClienteDAO cuentaClienteDAO) {
        this.compraDAO = compraDAO;
        this.boletoDAO = boletoDAO;
        this.cuentaClienteDAO = cuentaClienteDAO;
    }

    public void comprar(int clienteId, int boletoId) throws SQLException, ValidacionException {
        // 1. El boleto debe existir y estar disponible
        Boleto boleto = boletoDAO.consultarPorId(boletoId);
        if (boleto == null || !"DISPONIBLE".equals(boleto.getEstatus())) {
            throw new ValidacionException("El boleto no está disponible.");
        }

        // 2. Buscar una cuenta del cliente con saldo suficiente
        CuentaCliente cuentaCobro = null;
        for (CuentaCliente cuenta : cuentaClienteDAO.consultarPorCliente(clienteId)) {
            if (cuenta.getSaldo() >= boleto.getCosto()) {
                cuentaCobro = cuenta;
                break;
            }
        }
        if (cuentaCobro == null) {
            throw new ValidacionException("Saldo insuficiente en las cuentas del cliente.");
        }

        // 3. Descontar el costo del boleto
        cuentaCobro.setSaldo(cuentaCobro.getSaldo() - boleto.getCosto());
        cuentaClienteDAO.actualizar(cuentaCobro);

        // 4. Registrar la compra
        Compra compra = new Compra(clienteId, boletoId, LocalDateTime.now(),
                                   boleto.getCosto(), "COMPRADO");
        compraDAO.insertar(compra);

        // 5. Marcar el boleto como vendido
        boleto.setEstatus("COMPRADO");
        boletoDAO.actualizar(boleto);
    }

    public void cancelar(int compraId) throws SQLException, ValidacionException {
        // 1. La compra debe existir y no estar cancelada
        Compra compra = compraDAO.consultarPorId(compraId);
        if (compra == null) {
            throw new ValidacionException("La compra no existe.");
        }
        if ("CANCELADO".equals(compra.getEstado())) {
            throw new ValidacionException("La compra ya fue cancelada.");
        }

        // 2. Regla de las 24 horas exactas
        if (compra.getFechaHora().plusHours(24).isBefore(LocalDateTime.now())) {
            throw new ValidacionException("Solo puedes cancelar dentro de las 24 horas posteriores a la compra.");
        }

        // 3. Reembolso íntegro a la primera cuenta del cliente
        List<CuentaCliente> cuentas = cuentaClienteDAO.consultarPorCliente(compra.getClienteId());
        if (cuentas.isEmpty()) {
            throw new ValidacionException("El cliente no tiene cuentas.");
        }
        CuentaCliente cuenta = cuentas.get(0);
        cuenta.setSaldo(cuenta.getSaldo() + compra.getTotal());
        cuentaClienteDAO.actualizar(cuenta);

        // 4. El boleto vuelve a estar disponible
        Boleto boleto = boletoDAO.consultarPorId(compra.getBoletoId());
        boleto.setEstatus("DISPONIBLE");
        boletoDAO.actualizar(boleto);

        // 5. Marcar la compra como cancelada
        compra.setEstado("CANCELADO");
        compraDAO.actualizar(compra);
    }
}