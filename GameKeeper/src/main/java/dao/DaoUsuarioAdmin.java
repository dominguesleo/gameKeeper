package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * <h2>DaoUsuarioAdmin</h2> 
 * Representa un DAO para la clase UsuarioAdmin.
 * <ul><li>Proporciona metodos para bloquear, desbloquear a usuarios mediante ID.</li>
 * <li>Permite subir los permisos a Administrador.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see controlador.AdminServlet
 */
public class DaoUsuarioAdmin {
	
	/**
	 * Conexion a la base de datos.
	 */
	private Connection con = null;
	
	/**
	 * Instancia del objeto DaoUsuario.
	 */
	private static DaoUsuarioAdmin instance = null;
	
	/**
	 * Contructor que establece una conexion a la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public DaoUsuarioAdmin() throws SQLException {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Metodo que devuelve la instancia del DaoUsuarioAdmin para el uso del patron singleton.
	 * @return Instancia del objeto DaoUsuarioAdmin.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static DaoUsuarioAdmin getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoUsuarioAdmin();
		}
		return instance;
	}
	
	/**
	 * Metodo que bloquea o desbloquea a un usuario mediante cambios en los permisos en la base de datos.
	 * @param opcion <i>Opcion de bloquear o desbloquear (1 para bloquear, 2 para desbloquear).</i>
	 * @param id <i>Id del usuario donde se ejecuta la accion.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void bloquearDesbloquear(int opcion, int id) throws SQLException {
		
		PreparedStatement ps = null;
		
		if(opcion == 1) { // Bloquea usuario colocando permiso=0
			ps = con.prepareStatement("UPDATE usuario SET permiso=0 WHERE id=?");
		}else if(opcion == 2) { // Desbloquea usuario colocando permiso=0
			ps = con.prepareStatement("UPDATE usuario SET permiso=5 WHERE id=?");
		}		
		ps.setInt(1, id);
		ps.executeUpdate();		
		ps.close();	
	}
	
	/**
	 * Metodo que sube los permisos de a Administrador, subiendo a 10 los permisos en la base de datos.
	 * @param id <i>Id del usuario donde se ejecuta la accion.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void hacerAdmin(int id) throws SQLException {
		PreparedStatement ps = con.prepareStatement("UPDATE usuario SET permiso=10 WHERE id=?");
		ps.setInt(1, id);
		ps.executeUpdate();		
		ps.close();	
	}
}
