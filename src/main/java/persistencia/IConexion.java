package persistencia;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConexion {

    Connection crearConexion() throws SQLException;

}
