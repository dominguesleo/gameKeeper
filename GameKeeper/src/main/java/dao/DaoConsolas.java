package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.google.gson.Gson;

import modelo.Consola;

/**
 * <h2>DaoConsolas</h2> 
 * Representa un DAO para la clase Consola.
 * <ul><li>Implementa el patron DAO para insertar, actualizar y eliminar juegos de la base de datos.</li>
 * <li>Implementa un metodo para listar los nombres de las consolas para generar listas dinamicas.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see controlador.JuegoServlet
 */
public class DaoConsolas {
	
	/**
	 * Conexion a la base de datos.
	 */
	private Connection con = null;
	
	/**
	 * Instancia del objeto DaoJuegos.
	 */
	private static DaoConsolas instance = null;
	
	/**
	 * Constructor que establece la conexion con la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public DaoConsolas() throws SQLException {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Metodo que devuelve la instancia del DaoConsolas para el uso del patron singleton.
	 * @return Instancia del objeto DaoConsolas.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static DaoConsolas getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoConsolas();
		}
		return instance;
	}
	
	/**
	 * Metodo para inserta una consola en la base de datos.
	 * @param consola <i>Consola a insertar en la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void insertar(Consola consola) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO consola (nombre, desarrollador, edicion, fecha, foto, usuario_id) VALUES (?,?,?,?,?,?)");
		
		ps.setString(1, consola.getNombre());
		ps.setString(2, consola.getDesarrollador());

		if(consola.getEdicion().equals("")) { // Validamos los datos opcionales para que sean nulos en la base de datos
			ps.setNull(3, java.sql.Types.VARCHAR);
		}else {
			ps.setString(3, consola.getEdicion());
		}	
		
		if (consola.getFecha().equals("")) {
			ps.setNull(4, java.sql.Types.DATE);		
		}else {
			ps.setDate(4, java.sql.Date.valueOf(consola.getFecha()));
		}
		
		if(consola.getFoto().equals("")) {
			ps.setNull(5, java.sql.Types.VARCHAR);
		}else {
			ps.setString(5, consola.getFoto());
		}
	
		ps.setInt(6, consola.getId());
		ps.executeUpdate();
		ps.close();	
	}

	/**
	 * Metodo para eliminar una consola en la base de datos justo a los juegos relacionados.
	 * @param consola <i>Consola a eliminar en la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void eliminar(Consola consola) throws SQLException {
		
		PreparedStatement ps1 = con.prepareStatement("DELETE FROM juego WHERE consola_id=?");
		ps1.setInt(1, consola.getId());
		ps1.executeUpdate();	
		ps1.close();	
		
		PreparedStatement ps2 = con.prepareStatement("DELETE FROM consola WHERE id=?");
		ps2.setInt(1, consola.getId());	
		ps2.executeUpdate();		
		ps2.close();
	}
	
	/**
	 * Metodo para actualizar una consola en la base de datos.
	 * @param consola <i>Consola a actualizar en la base de datos.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public void update(Consola consola) throws SQLException {
		
		PreparedStatement ps = null;
		
		if(consola.getFoto().equals("")) { // Si el usuario NO actualiza la imagen a la hora de editar la consola
			ps = con.prepareStatement("UPDATE consola SET nombre=?, desarrollador=?, edicion=?, fecha=? WHERE id=?");
	        ps.setInt(5, consola.getId());
		}else { // Si el usuario SI actualiza la imagen a la hora de editar la consola
			ps = con.prepareStatement("UPDATE consola SET nombre=?, desarrollador=?, edicion=?, fecha=?, foto=? WHERE id=?");
	        ps.setString(5, consola.getFoto());
	        ps.setInt(6, consola.getId());
		}

		ps.setString(1, consola.getNombre());
		ps.setString(2, consola.getDesarrollador());
		
		if(consola.getEdicion().equals("")) { // Validamos los datos opcionales para que sean nulos en la base de datos
			ps.setNull(3, java.sql.Types.VARCHAR);
		}else {
			ps.setString(3, consola.getEdicion());
		}
		
		if (consola.getFecha().equals("")) {
			ps.setNull(4, java.sql.Types.DATE);		
		}else {
			ps.setDate(4, java.sql.Date.valueOf(consola.getFecha()));
		}
		
		ps.executeUpdate();		
		ps.close();
	}
	
	/**
	 * Metodo que devuelve una lista de consolas (solo nombre e id) pertenecientes a un usuario en formato JSON.
	 * @param consola <i>Consola con el id del usuario.</i>
	 * @return String - <i>String en formato JSON con los datos de la lista de consolas.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see controlador.ListarConsolas
	 */
	public String listaNomConsolas(Consola consola) throws SQLException {

		PreparedStatement ps = con.prepareStatement("SELECT id, nombre FROM consola WHERE usuario_id=? ORDER BY nombre");
		ps.setInt(1, consola.getId());
		ResultSet rs = ps.executeQuery();

		ArrayList<Consola> lista = null;
		while(rs.next()) {
			if(lista == null) {
				lista = new ArrayList<>();
			}
			lista.add(new Consola(rs.getInt(1), rs.getString(2)));
		}
		rs.close();
		ps.close();

		Gson gson = new Gson();
		return gson.toJson(lista);
	}
	



}
