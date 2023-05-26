package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * <h2>Clase DBConection - DAO</h2> 
 * <ul><li>Proporciona una conexión a la base de datos utilizando el controlador JDBC.</li>
 * <li>La conexión se establece utilizando las credenciales de usuario y contraseña especificadas en la clase.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class DBConnection {
	
	/**
	 * Objeto Connection para la conexión a la base de datos.
	 */
	public static Connection instance = null;
	
	/**
	 * URL de la base de datos MySQL.
	 */
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/bdgkprueba";
	
	/**
	 * Método que devuelve una conexion a la base de datos.
	 * @return conexion a la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static Connection getConnection() throws SQLException {
		
		if(instance == null) {
			Properties props = new Properties();
			props.put("user", "root");
			props.put("password", "");
					
			instance = DriverManager.getConnection(JDBC_URL, props);
		}
		
		return instance;
	}
}
