package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
import modelo.Usuario;

/**
 * <h2>DaoUsuarios</h2> 
 * Representa un DAO para la clase Usuario.
 * <ul><li>Proporciona metodos para insertar, actualizar, obtener y eliminar registros.</li>
 * <li>Proporciona un metodo para validar registros duplicados.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see controlador.UsuarioServlet
 */
public class DaoUsuario {
	
	/**
	 * Conexion a la base de datos.
	 */
	private Connection con = null;
	
	/**
	 * Instancia del objeto DaoUsuario.
	 */
	private static DaoUsuario instance = null;
	
	/**
	 * Contructor que establece una conexion a la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public DaoUsuario() throws SQLException {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Metodo que devuelve la instancia del DaoUsuario para el uso del patron singleton.
	 * @return Instancia del objeto DaoUsuario.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static DaoUsuario getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoUsuario();
		}
		return instance;
	}
	
	 
	/**
	 * Metodo que inserta o actualiza un registro de usuario en la base de datos.
	 * @param usuario <i>Usuario que se va a insertar o actualizar.</i>
	 * @param opcion <i>Opcion de insercion o actualizacion (1 para insercion, 2 para actualizacion).</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void insertarUpdate(Usuario usuario, int opcion) throws SQLException {
		
		PreparedStatement ps = null;
		
		if(opcion == 1) { // Insertar
			ps = con.prepareStatement("INSERT INTO usuario (nombre, apellido, mail, password, permiso, fecha, pais) VALUES (?,?,?,?,5,?,?)");	
		}else if(opcion == 2) { // Actualizar
			ps = con.prepareStatement("UPDATE usuario SET nombre=?, apellido=?, mail=?, password=?, fecha=?, pais=? WHERE id=?");
	        ps.setInt(7, usuario.getId());
		}
		
		ps.setString(1, usuario.getNombre());
		ps.setString(2, usuario.getApellido());
		ps.setString(3, usuario.getMail());
		ps.setString(4, usuario.getPassword());
	
		if (usuario.getFecha().equals("")) { // Validamos los datos opcionales para que sean nulos en la base de datos
			ps.setNull(5, java.sql.Types.DATE);		
		}else {
			ps.setDate(5, java.sql.Date.valueOf(usuario.getFecha()));
		}
		
		if (usuario.getPais().equals("")) {
			ps.setNull(6, java.sql.Types.VARCHAR);
		}else {
			ps.setString(6, usuario.getPais());
		}

		ps.executeUpdate();		
		ps.close();	
	}
	
	/**
	 * Metodo que limina un usuario de la base de datos, junto con los registros de juegos y consolas asociados a ese usuario.
	 * @param usuario <i>Usuario a eliminar de la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void eliminar(Usuario usuario) throws SQLException {
		
	    PreparedStatement ps1 = con.prepareStatement("DELETE FROM juego WHERE usuario_id = ?");
	    ps1.setInt(1, usuario.getId());
	    ps1.executeUpdate();
	    ps1.close();

	    PreparedStatement ps2 = con.prepareStatement("DELETE FROM consola WHERE usuario_id = ?");
	    ps2.setInt(1, usuario.getId());
	    ps2.executeUpdate();
	    ps2.close();

	    PreparedStatement ps3 = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
	    ps3.setInt(1, usuario.getId());
	    ps3.executeUpdate();
	    ps3.close();	
	}
	
	/**
	 * Metodo que obtiene los datos de un usuario de la base de datos en formato JSON.
	 * @param usuario <i>Usuario del cual se quieren obtener los datos.</i>
	 * @return String - <i>String en formato JSON con los datos del usuario.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public String obtenerDatos(Usuario usuario) throws SQLException {
				
		PreparedStatement ps = con.prepareStatement("SELECT nombre, apellido, mail, password, fecha, pais, permiso FROM usuario WHERE id=?");
		ps.setInt(1, usuario.getId());
		ResultSet rs = ps.executeQuery();
		
		Usuario user = null;
		
		if(rs.next()) {
			if(user == null) {
				user = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		}		
		rs.close();
		ps.close();
		
		Gson gson = new Gson();
		return gson.toJson(user);	
	}
	
	/**
	 * Metodo que valida si un registro de usuario ya existe en la base de datos.
	 * @param usuario <i>Usuario que se va a validar.</i>
	 * @return <ul><li>Devuelve 1: Si existe un usuario.</li>
	 * <li>Devuelve 0: Si no existe un usuario.</li></ul>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public int validarDuplicado(Usuario usuario) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) AS usuarios FROM usuario WHERE mail = ?");
		ps.setString(1, usuario.getMail());
		ResultSet rs = ps.executeQuery();
		
		int duplicados = 0;
		if(rs.next()) {
			duplicados = rs.getInt("usuarios");
			}	
		
		rs.close();
		return duplicados;	
	}
	
	/**
	 * Metodo que modifica el password del usuario cuando no lo recuerda.
	 * @param usuario <i>Usuario que cambia password.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void passwordUpdate (Usuario usuario) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("UPDATE usuario SET password=? WHERE mail = ?");
		ps.setString(1, usuario.getPassword());
		ps.setString(2, usuario.getMail());
		ps.executeUpdate();
		ps.close();		
	}
}
