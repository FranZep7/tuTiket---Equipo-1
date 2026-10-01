/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.cuentaPromotoraDAO;

import entidad.CuentaPromotora;
import java.util.List;

/**
 *
 * @author tolan
 */
public interface ICuentaPromotoraDAO {
    void insertar(CuentaPromotora cuenta);
    
    CuentaPromotora consultarPorId(int id);
    
    List<CuentaPromotora> consultarPorPromotora(int promotoraId);
    
    List<CuentaPromotora> consultarTodas();
    
    void actualizar(CuentaPromotora cuenta);
    
    void eliminar(int id);
}
