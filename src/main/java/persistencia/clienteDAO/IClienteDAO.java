
package persistencia.clienteDAO;

import entidad.Cliente;
import java.util.List;

public interface IClienteDAO {
    
    void insertar(Cliente cliente);
    
    Cliente consultarPorId(int id);
    
    Cliente consultarPorUsuario(String usuario);
    
    List<Cliente> consultarTodos();
    
    void actualizar(Cliente cliente);
    
    void eliminar(int id);
}