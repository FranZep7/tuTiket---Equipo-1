/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.cuentaClienteDAO;

import entidad.CuentaCliente;
import java.util.List;

public interface ICuentaClienteDAO {
    void insertar(CuentaCliente cuenta);
    
    CuentaCliente consultarPorId(int id);
    
    List<CuentaCliente> consultarPorCliente(int clienteId);
    
    List<CuentaCliente> consultarTodas();
    
    void actualizar(CuentaCliente cuenta);
    
    void eliminar(int id);
}
