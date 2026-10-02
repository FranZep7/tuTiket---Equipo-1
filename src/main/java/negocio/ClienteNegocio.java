/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import entidad.Cliente;
import entidad.CuentaCliente;
import entidad.Operaciones;
import java.sql.SQLException;
import java.time.LocalDateTime;
import persistencia.clienteDAO.ClienteDAO;
import persistencia.clienteDAO.IClienteDAO;
import persistencia.cuentaClienteDAO.CuentaClienteDAO;
import persistencia.cuentaClienteDAO.ICuentaClienteDAO;
import persistencia.operacionesDAO.IOperacionesDAO;
import persistencia.operacionesDAO.OperacionesDAO;

/**
 *
 * @author tolan
 */
public class ClienteNegocio {

    private final IClienteDAO clienteDAO;
    private final ICuentaClienteDAO cuentaClienteDAO;
    private final IOperacionesDAO operacionesDAO;

    public ClienteNegocio(IClienteDAO clienteDAO,
                          ICuentaClienteDAO cuentaClienteDAO,
                          IOperacionesDAO operacionesDAO) {
        this.clienteDAO = clienteDAO;
        this.cuentaClienteDAO = cuentaClienteDAO;
        this.operacionesDAO = operacionesDAO;
    }

    public void registrar(Cliente cliente) throws SQLException {
        clienteDAO.insertar(cliente);

        double saldoInicial = 1000.00;
        for (int i = 0; i < 3; i++) {
            double saldoCuenta = (i == 0) ? saldoInicial : 0.0;
            CuentaCliente cuenta = new CuentaCliente(
                    cliente.getId(),
                    "Banco " + (i + 1),
                    generarNumeroCuenta(),
                    saldoCuenta);
            cuentaClienteDAO.insertar(cuenta);

            if (i == 0) {
                operacionesDAO.insertar(new Operaciones(
                        cuenta.getId(),
                        "DEPOSITO_INICIAL",
                        saldoInicial,
                        LocalDateTime.now()));
            }
        }
    }

    private String generarNumeroCuenta() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append((int) (Math.random() * 10));
        }
        return sb.toString();
    }
}


