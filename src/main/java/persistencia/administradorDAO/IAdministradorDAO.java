/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.administradorDAO;

import entidad.Administrador;
import java.util.List;

public interface IAdministradorDAO {
    void insertar(Administrador admin);
    
    Administrador consultarPorId(int id);
    
    Administrador consultarPorUsuario(String usuario);
    
    List<Administrador> consultarPorPromotora(int promotoraId);
    
    List<Administrador> consultarTodos();
    
    void actualizar(Administrador admin);
    
    void eliminar(int id);
}
