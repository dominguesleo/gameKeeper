package modelo;

import java.sql.SQLException;
import dao.DaoUsuario;

/**
 * <h2>Clase Usuario</h2> 
 * <ul><li>Permite almacenar, editar y elminar datos del usuario.</li>
 * <li>Permite enviar los datos del usuario</li>
 * <li>Permite validar datos duplicados a la hora del registro de usuarios.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class Usuario {
	
	/**
	 * Id del usuario.
	 */
	private int id;
	
	/**
	 * Nombre del usuario.
	 */
	private String nombre;
	
	/**
	 * Apellido(s) del usuario.
	 */
	private String apellido;
	
	/**
	 * Correo electronico del usuario.
	 */
	private String mail;
	
	/**
	 * Password del usuario.
	 */
	private String password;
	
	/**
	 * Fecha de nacimiento del usuario.
	 */
	private String fecha;
	
	/**
	 * Pais de residencia del usuario.
	 */
	private String pais;
	
	/**
	 * Nivel de permiso del usuario.
	 */
	private int permiso;
	
	
	/**
	 * Retorna el id del usuario.
	 * @return id del usuario.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el identificador del usuario.
	 * @param id - <i>id del usuario.</i>
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre del usuario.
	 * @return nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 * @param nombre - <i>nombre del usuario.</i>
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el apellido del usuario.
	 * @return apellido del usuario.
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * Establece el apellido del usuario.
	 * @param apellido - <i>apellido del usuario.</i>
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Retorna el correo electronico del usuario.
	 * @return correo electronico del usuario.
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Establece el correo electronico del usuario.
	 * @param mail - <i>correo electronico del usuario.</i>
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
	 * Retorna la fecha de nacimiento del usuario.
	 * @return fecha de nacimiento del usuario.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de nacimiento del usuario.
	 * @param fecha - <i>fecha de nacimiento del usuario.</i>
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna el pais de residencia del usuario.
	 * @return pais del usuario.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Establece el pais de residencia del usuario.
	 * @param pais  - <i>pais de residencia del usuario.</i>
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}

	/**
	 * Retorna el nivel de permisos del usuario.
	 * @return nivel de permisos del usuario.
	 */
	public int getPermiso() {
		return permiso;
	}

	/**
	 * Establece el nivel de permisos del usuario.
	 * @param permiso - <i>nivel de permisos del usuario.</i>
	 */
	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}
	
	/**
	 * Constructor vacio.
	 */
	public Usuario() {
		
	}
	
	/**
	 * Constructor con 1 parametro.
	 * @param id <i>Toma el id de la sesion en el servlet para ser usado en el DAO.</i>
	 * @see controlador.UsuarioServlet
	 */
	public Usuario(int id) {
		this.id = id;
	}
	
	/**
	 * Contructor con 3 parametros. 
	 * <i>Toma los valores almacenados en BBDD para ser almacenados en la sesion.</i>
	 * @param id <i>Id del usuario.</i>
	 * @param nombre <i>Nombre del usuario.</i>
	 * @param permiso <i>Nivel de permisos del usuario.</i>
	 * @see dao.DaoLogin
	 */
	public Usuario(int id, String nombre, int permiso) {
		super(); // DaoLogin validar
		this.id = id;
		this.nombre = nombre;
		this.permiso = permiso;
	}
	
	
	/**
	 * Contructor con 2 parametros. 
	 * <i>Constructor para que el usuario recupere su password</i>
	 * @param mail <i>Correo del usuario.</i>
	 * @param password <i>Password del usuario.</i>
	 * @see controlador.UsuarioServlet
	 */
	public Usuario(String mail, String password) {
		super();
		this.mail = mail;
		this.password = password;
	}

	/**
 	 * Constructor con 7 parametro.
 	 * <i>Constructor utilizado para actualizar datos del Usuario</i>
	 * @param id <i>Toma el id de la sesion en el servlet para ser usado en el DAO.</i>
	 * @param nombre <i>Nombre del usuario.</i>
	 * @param apellido <i>Apellido(s) del usuario.</i>
	 * @param mail <i>Correo electronico del usuario.</i>
	 * @param password <i>Password del usuario.</i>
	 * @param fecha <i>Fecha de nacimiento del usuario.</i>
	 * @param pais <i>pais de residencia usuario.</i>
	 * @see controlador.UsuarioServlet
	 */
	public Usuario(int id, String nombre, String apellido, String mail, String password, String fecha, String pais) {
		super(); //UsuarioServlet actualizat
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.password = password;
		this.fecha = fecha;
		this.pais = pais;
	}
	
	/**
	 * Constructor con 6 parametro.
	 * <i>Constructor utilizado para insertar Usuario en el registro</i>
	 * @param nombre <i>Nombre del usuario.</i>
	 * @param apellido <i>Apellido(s) del usuario.</i>
	 * @param mail <i>Correo electronico del usuario.</i>
	 * @param password <i>Password del usuario.</i>
	 * @param fecha <i>Fecha de nacimiento del usuario.</i>
	 * @param pais <i>pais de residencia usuario.</i>
	 * @see controlador.UsuarioServlet
	 * @see dao.DaoUsuario
	 */
	public Usuario(String nombre, String apellido, String mail, String password, String fecha, String pais) {
		super(); // UsuarioServlet insertar  // DaoUsuario Obtener datos
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.password = password;
		this.fecha = fecha;
		this.pais = pais;
	}
	
	/**
	 * Constructor con 7 parametro.
	 * <i>Constructor utilizado para pintar datos en su perfil.</i>
	 * @param nombre <i>Nombre del usuario.</i>
	 * @param apellido <i>Apellido(s) del usuario.</i>
	 * @param mail <i>Correo electronico del usuario.</i>
	 * @param password <i>Password del usuario.</i>
	 * @param fecha <i>Fecha de nacimiento del usuario.</i>
	 * @param pais <i>Pais de residencia usuario.</i>
	 * @param permiso <i>Permiso del usuario.</i>
	 * see controlador.UsuarioServlet
	 * @see dao.DaoUsuario
	 */
	public Usuario(String nombre, String apellido, String mail, String password, String fecha, String pais,
			int permiso) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.password = password;
		this.fecha = fecha;
		this.pais = pais;
		this.permiso = permiso;
	}

	/**
	 * Metodo para insertar los datos del usuario proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoUsuario
	 * @see controlador.UsuarioServlet
	 */
	public void insertar() throws SQLException {
		DaoUsuario.getInstance().insertarUpdate(this, 1);
	}
	
	/**
	 * Metodo para actualizar los datos del usuario proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoUsuario
	 * @see controlador.UsuarioServlet
	 */
	public void update() throws SQLException {
		DaoUsuario.getInstance().insertarUpdate(this, 2);
	}
	
	/**
	 * Metodo para eliminar los datos del usuario proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoUsuario
	 * @see controlador.UsuarioServlet
	 */
	public void eliminar() throws SQLException {
		DaoUsuario.getInstance().eliminar(this);
	}
	
	/**
	 * Metodo para enviar los datos del usuario por Servlet.
	 * @return String - <i>Retorna un String en formato JSON que representa los datos del usuario.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoUsuario
	 * @see controlador.UsuarioServlet
	 */
	public String obtenerDatos() throws SQLException {
		return DaoUsuario.getInstance().obtenerDatos(this);
	}
	
	/**
	 * Metodo que valida si un usuario ya existe en la base de datos.
	 * @return boolean - <i>Retorna un booleano para validar que no existan usuarios duplicados.</i>
	 * <ul><li>Devuelve True: Si existe un usuario.</li>
	 * <li>Devuelve False: Si no existe un usuario.</li></ul>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see controlador.UsuarioServlet
	 */
	public boolean validarDuplicado() throws SQLException {
		
		int duplicado = DaoUsuario.getInstance().validarDuplicado(this);
		
		if(duplicado == 0) {
			return false;
		}else {
			return true;
		}		 
	}
	
	/**
	 * Metodo que permite cambiar el password cuando el usuario no la recuerda.
	 * @return boolean - <i>Retorna un booleano para validar si realizo o no el cambio al comprobar que el usuario esta de alta.</i>
	 * <i>Primero se consulta si el usuario existe con el metodo validarDuplicado.</i>
	 * <ul><li>Devuelve True: Si existe el usuario y se realizo el cambio de password.</li>
	 * <li>Devuelve False: Si no existe un usuario.</li></ul>
	 * @throws SQLException
	 */
	public boolean passwordUpdate() throws SQLException {
		if(this.validarDuplicado()) {
			DaoUsuario.getInstance().passwordUpdate(this);
			return true;
		}else {
			return false;
		}		
	}
	
	// Metodo toString
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail + ", password="
				+ password + ", fecha=" + fecha + ", pais=" + pais + ", permiso=" + permiso + "]";
	}
}
