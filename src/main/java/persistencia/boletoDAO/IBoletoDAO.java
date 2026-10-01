/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.boletoDAO;

import entidad.Boleto;
import java.util.List;

public interface IBoletoDAO {
    void insertar(Boleto boleto);
    
    Boleto consultarPorId(int id);
    
    List<Boleto> consultarPorEvento(int eventoId);
    
    List<Boleto> consultarDisponiblesPorEvento(int eventoId);
    
    List<Boleto> consultarTodos();
    
    void actualizar(Boleto boleto);
    
    void eliminar(int id);
}