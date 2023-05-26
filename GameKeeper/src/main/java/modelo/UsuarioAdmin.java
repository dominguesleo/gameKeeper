package modelo;

import java.sql.SQLException;
import dao.DaoUsuarioAdmin;

/**
 * <h2>Clase UsuarioAdmin</h2> 
 * <ul><li>Permite bloquear, desbloquear usuarios y hacerlos administradores.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class UsuarioAdmin extends Usuario {

	/**
	 * Constructor vacio.
	 */
	public UsuarioAdmin() {
	}
	
	/**
	 * Constructor con 3 parametro.
	 * Permite crear UsuarioAdmin en el servlet "AdminServlet"
	 * @param id <i>Toma el id de la sesion en el servlet para verificar permisos</i>
	 * @param nombre <i>Toma el nombre de la sesion en el servlet</i>
	 * @param permiso <i>Toma el permiso de la sesion en el servlet</i>
	 * @see controlador.AdminServlet
	 */
	public UsuarioAdmin(int id, String nombre, int permiso) {
		super(id, nombre, permiso);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Metodo para bloquear a un Usuario.
	 * @param id <i>Id del usuario que el Administrador enviar hacia el servlet.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void bloquearUsuario(int id) throws SQLException {
		DaoUsuarioAdmin.getInstance().bloquearDesbloquear(1, id);
	}
	
	/**
	 * Metodo para desbloquear a un Usuario.
	 * @param id <i>Id del usuario que el Administrador enviar hacia el servlet.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void desbloquearUsuario(int id) throws SQLException {
		DaoUsuarioAdmin.getInstance().bloquearDesbloquear(2, id);
	}
	
	/**
	 * Metodo para subir los permisos de Administrador a un Usuario
	 * @param id <i>Id del usuario que el Administrador enviar hacia el servlet.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void hacerAdmin(int id) throws SQLException {
		DaoUsuarioAdmin.getInstance().hacerAdmin(id);
	}

}
