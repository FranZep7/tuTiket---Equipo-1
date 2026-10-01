/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.operacionesDAO;

import entidad.Operaciones;
import java.util.List;

public interface IOperacionesDAO {
    void insertar(Operaciones registro);
    
    List<Operaciones> consultarPorCuenta(int cuentaClienteId);
    
    List<Operaciones> consultarTodos();
}