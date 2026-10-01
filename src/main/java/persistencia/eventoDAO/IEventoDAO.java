/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.eventoDAO;

import entidad.Evento;
import java.util.List;

/**
 *
 * @author tolan
 */
public interface IEventoDAO {
    void insertar(Evento evento);
    
    Evento consultarPorId(int id);
    
    List<Evento> consultarPorPromotora(int promotoraId);
    
    List<Evento> consultarTodos();
    
    void actualizar(Evento evento);
    
    void eliminar(int id);
}