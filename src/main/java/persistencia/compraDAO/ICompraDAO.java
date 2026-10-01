/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia.compraDAO;

import entidad.Compra;
import java.util.List;

public interface ICompraDAO {
    void insertar(Compra compra);
    
    Compra consultarPorId(int id);
    
    List<Compra> consultarPorCliente(int clienteId);
    
    List<Compra> consultarPorEvento(int eventoId);
    
    List<Compra> consultarTodas();
    
    void actualizar(Compra compra);
    
    void eliminar(int id);
}
