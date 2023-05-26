package modelo;

import java.sql.SQLException;

import dao.DaoLogin;

/**
 * <h2>Clase Login</h2>
 * Realiza la autentificacion de usuario para poder acceder al area privada.  
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 *
 */
public class Login {
	
	/**
	 * Correo electronico del usuario.
	 */
	private String mail;
	
	/**
	 * Password del usuario.
	 */
	private String password;
	
	/**
	 * Retorna el correo electronico del usuario.
	 * @return correo electronico del usuario.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Establece el correo electronico del usuario.
	 * @param mail - <i>correo electrónico del usuario.</i>
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Retorna el password del usuario.
	 * @return password del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece el password del usuario.
	 * @param password - <i>password del usuario.</i>
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Constructor vacio.
	 */
	public Login() {
	}

	/**
	 * Constructor con 2 parametros.
	 * <i>Se genera para almacenar el correo electronico y password del usuario para realizar el login.</i>
	 * @param mail <i>Correo electronico del usuario.</i>
	 * @param password <i>Password del usuario.</i>
	 */
	public Login(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}

	/**
	 * Metodo valida valida las credenciales de un usuario al hacer login.
	 * @return Usuario - <i>Retorna un objeto usuario con sus datos a existir en la base de datos. De lo contrario retorna un objeto usuario nulo.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoLogin 
	 */
	public Usuario validarLogin() throws SQLException {
		return DaoLogin.getInstance().validarLogin(this);
	}

	// Metodo toString
	
	@Override
	public String toString() {
		return "Login [mail=" + mail + ", password=" + password + "]";
	}
}
