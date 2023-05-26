package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Login;
import modelo.Usuario;

/**
 * <h2>DaoLogin</h2> 
 * Representa un DAO para la clase Login.
 * <ul><li>Implementa el patron DAO para la validacion del login de un usuario en la base de datos.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see controlador.LoginServlet
 */
public class DaoLogin {
	
	/**
	 * Conexion a la base de datos.
	 */
	private Connection con = null;
	
	/**
	 * Instancia del objeto DaoLogin.
	 */
	private static DaoLogin instance = null;
	
	
	/**
	 * Constructor que establece la conexion con la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public DaoLogin() throws SQLException {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Metodo que devuelve la instancia del DaoLogin para el uso del patron singleton.
	 * @return Instancia del objeto DaoUsuario.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static DaoLogin getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoLogin();
		}
		return instance;
	}
	
	/**
	 * Metodo que valida el login de un usuario en la base de datos.
	 * @param login <i>Objeto Login con las credenciales del usuario (mail-password).</i>
	 * @return Usuario que contiene los datos del usuario en la base de datos si el login es correcto.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public Usuario validarLogin(Login login) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT id, nombre, permiso FROM usuario WHERE mail = ? AND password = ?");
		ps.setString(1, login.getMail());
		ps.setString(2, login.getPassword());
		ResultSet rs = ps.executeQuery();
		
		Usuario usuario = null;
		
		if(rs.next()) {
			usuario = new Usuario(rs.getInt(1), rs.getString(2), rs.getInt(3));
		}
		rs.close();
		ps.close();

		return usuario;	// Si no existe devuelve usuario nulo
	}
}
