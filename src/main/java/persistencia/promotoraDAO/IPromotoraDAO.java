/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.promotoraDAO;

import entidad.Promotora;
import java.util.List;

public interface IPromotoraDAO {
    void insertar(Promotora promotora);
    
    Promotora consultarPorId(int id);
    
    List<Promotora> consultarTodas();
    
    void actualizar(Promotora promotora);
    
    void eliminar(int id);
}
