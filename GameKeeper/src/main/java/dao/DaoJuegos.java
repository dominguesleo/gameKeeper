package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Juego;

/**
 * <h2>DaoJuegos</h2> 
 * Representa un DAO para la clase Juego.
 * <ul><li>Implementa el patron DAO para insertar, actualizar y eliminar juegos de la base de datos.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see controlador.JuegoServlet
 */
public class DaoJuegos {
	
	/**
	 * Conexion a la base de datos.
	 */
	private Connection con = null;
	
	/**
	 * Instancia del objeto DaoJuegos.
	 */
	private static DaoJuegos instance = null;
	
	
	/**
	 * Constructor que establece la conexion con la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public DaoJuegos() throws SQLException {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Metodo que devuelve la instancia del DaoJuegos para el uso del patron singleton.
	 * @return Instancia del objeto DaoJuegos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static DaoJuegos getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoJuegos();
		}
		return instance;
	}
	
	/**
	 * Metodo para inserta un juego en la base de datos.
	 * @param juego <i>Juego a insertar en la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void insertar(Juego juego) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO juego (nombre, formato, estado, edicion, genero, fecha, valoracion, foto, usuario_id, consola_id) VALUES (?,?,?,?,?,?,?,?,?,?)");
		
		ps.setString(1, juego.getNombre());
		ps.setString(2, juego.getFormato());
		ps.setString(3, juego.getEstado());

		if (juego.getEdicion().equals("")) { // Validamos los datos opcionales para que sean nulos en la base de datos
			ps.setNull(4, java.sql.Types.VARCHAR);
		}else {
			ps.setString(4, juego.getEdicion());
		}
		
		if (juego.getGenero().equals("")) {
			ps.setNull(5, java.sql.Types.VARCHAR);		
		}else {
			ps.setString(5, juego.getGenero());
		}
		
		if (juego.getFecha().equals("")) {
			ps.setNull(6, java.sql.Types.DATE);		
		}else {
			ps.setDate(6, java.sql.Date.valueOf(juego.getFecha()));
		}
		
		if (juego.getValoracion() == 0) {
			ps.setNull(7, java.sql.Types.INTEGER);
		}else {
			ps.setInt(7, juego.getValoracion());		
		}
		
		if(juego.getFoto().equals("")) {
			ps.setNull(8, java.sql.Types.VARCHAR);
		}else {
			ps.setString(8, juego.getFoto());
		}		
		ps.setInt(9, juego.getId());
		ps.setInt(10, juego.getConsola());		
		ps.executeUpdate();		
		ps.close();	
	}
	
	/**
	 * Metodo para eliminar un juego en la base de datos.
	 * @param juego <i>Juego a eliminar en la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void eliminar(Juego juego) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("DELETE FROM juego WHERE id=?");
		ps.setInt(1, juego.getId());	
		ps.executeUpdate();		
		ps.close();	
	}
	
	/**
	 * Metodo para actualizar un juego en la base de datos.
	 * @param juego <i>Juego a actualizar en la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void update(Juego juego) throws SQLException {
		
		PreparedStatement ps = null;
		
		if(juego.getFoto().equals("")) { // Si el usuario NO actualiza la imagen a la hora de editar el juego
			ps = con.prepareStatement("UPDATE juego SET nombre=?, formato=?, estado=?, edicion=?, genero=?, fecha=?, valoracion=?, consola_id=? WHERE id=?");
			ps.setInt(8, juego.getConsola());
			ps.setInt(9, juego.getId());
		}else { // Si el usuario SI actualiza la imagen a la hora de editar el juego
			ps = con.prepareStatement("UPDATE juego SET nombre=?, formato=?, estado=?, edicion=?, genero=?, fecha=?, valoracion=?, foto=?, consola_id=? WHERE id=?");
			ps.setString(8, juego.getFoto());
			ps.setInt(9, juego.getConsola());
	        ps.setInt(10, juego.getId());
		}
			
		ps.setString(1, juego.getNombre());
		ps.setString(2, juego.getFormato());
		ps.setString(3, juego.getEstado());

		if (juego.getEdicion().equals("")) { // Validamos los datos opcionales para que sean nulos en la base de datos
			ps.setNull(4, java.sql.Types.VARCHAR);
		}else {
			ps.setString(4, juego.getEdicion());
		}
		
		if (juego.getGenero().equals("")) {
			ps.setNull(5, java.sql.Types.VARCHAR);		
		}else {
			ps.setString(5, juego.getGenero());
		}
		
		if (juego.getFecha().equals("")) {
			ps.setNull(6, java.sql.Types.DATE);		
		}else {
			ps.setDate(6, java.sql.Date.valueOf(juego.getFecha()));
		}
		
		if (juego.getValoracion() == 0) {
			ps.setNull(7, java.sql.Types.INTEGER);
		}else {
			ps.setInt(7, juego.getValoracion());		
		}
	
		ps.executeUpdate();		
		ps.close();		
	}	
}
