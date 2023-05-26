package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Biblioteca;
import modelo.Consola;
import modelo.Juego;

/**
 * <h2>DaoBiblioteca</h2> 
 * Representa un DAO para la clase Biblioteca.
 * <ul><li>Proporciona un metodo para obtener las estadisticas y listas Juegos/Consolas de la pagina principal.</li>
 * <li>Proporciona un metodo para obtener el listado de todos los juegos y consolas pudiendo aplicar filtros de busqueda.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see controlador.PaginaPrincipal
 * @see controlador.MisConsolas
 * @see controlador.MisConsolas
 */
public class DaoBiblioteca {
	
	/**
	 * Conexion a la base de datos.
	 */
	private Connection con = null;
	
	/**
	 * Instancia del objeto DaoUsuario.
	 */
	private static DaoBiblioteca instance = null;
	
	/**
	 * Contructor que establece una conexion a la base de datos.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public DaoBiblioteca() throws SQLException {
		con = DBConnection.getConnection();
	}
	
	/**
	 * Metodo que devuelve la instancia del DaoBiblioteca para el uso del patron singleton.
	 * @return Instancia del objeto DaoBiblioteca.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public static DaoBiblioteca getInstance() throws SQLException {
		if(instance == null) {
			instance = new DaoBiblioteca();
		}
		return instance;
	}
	
	/**
	 * Metodo que las estadisticas de biblioteca.
	 * @param biblioteca <i>Toma el id del usuario.</i>
	 * @return Biblioteca - <i>Bilbioteca que contiene los valores  de las estadisticas.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public Biblioteca listarEstadisticas(Biblioteca biblioteca) throws SQLException {
		
		int totalConsolas = 0;
		int totalJuegos = 0;
	    int deseados = 0;
	    int jugando = 0;
	    int obtenido = 0;
	    int terminado = 0;

	    PreparedStatement ps1 = con.prepareStatement( // Lista el numero total de Juegos y el numero total de Consolas en la Biblioteca 
	            "SELECT " +
	            "(SELECT COUNT(*) FROM consola WHERE usuario_id=?) AS totalConsolas, " +
	            "(SELECT COUNT(*) FROM juego WHERE usuario_id=?) AS totalJuegos");
	    
	    ps1.setInt(1, biblioteca.getId());
	    ps1.setInt(2, biblioteca.getId());
	    ResultSet rs1 = ps1.executeQuery();
		
	    if (rs1.next()) {
	        totalConsolas = rs1.getInt("totalConsolas");
	        totalJuegos = rs1.getInt("totalJuegos");
	    }
	    rs1.close();
	    ps1.close();

		PreparedStatement ps2 = con.prepareStatement("SELECT estado, count(*) as listaEStados FROM juego WHERE usuario_id=? GROUP BY estado"); // Lista el numero total de cada estado al que pertenece el juego
		ps2.setInt(1, biblioteca.getId());
		ResultSet rs2 = ps2.executeQuery();
		
	    while(rs2.next()) {
	        String estado = rs2.getString("estado");
	        int cantidad = rs2.getInt("listaEStados");
	        
	        switch(estado) {
            case "Deseado":
                deseados = cantidad;
                break;
            case "Jugando":
                jugando = cantidad;
                break;
            case "Obtenido":
                obtenido = cantidad;
                break;
            case "Terminado":
                terminado = cantidad;
                break;
	        }
	    }
	    rs2.close();
	    ps2.close();
		
	    Biblioteca datos = new Biblioteca(totalJuegos, totalConsolas, deseados, jugando, obtenido, terminado);
	    return datos;    
	}
	
	/**
	 * Metodo que devuelve el listado de los juegos del usuarrio.
	 * <ul><li>Habilita opcion y valor para aplicar filtros a la busqueda.</li></ul>
	 * @param biblioteca <i>Toma el id del usuario.</i>
	 * @param opcion <i>Opcion segun la cual se quiere realizar la busqueda.</i>
	 * @param valor <i>Valor que se quiere buscar.</i>
	 * @return ArrayList - <i>Lista de juegos que coincidan con la opcion y valor especificados.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public ArrayList<Juego> listaJuegos(Biblioteca biblioteca, int opcion, String valor) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;

		switch (opcion) {
		case 0: // Opcion usada solo para listar los ultimos 6 juegos de la pantalla principal
			ps = con.prepareStatement("SELECT * FROM juego WHERE usuario_id=? ORDER BY id DESC LIMIT 6");
			break;
		case 1: // Buscar por nombre
			ps = con.prepareStatement("SELECT * FROM juego WHERE usuario_id=? AND nombre LIKE ? ORDER BY id DESC");
			break;		
		case 2: // Buscar por formato
			ps = con.prepareStatement("SELECT * FROM juego WHERE usuario_id=? AND formato LIKE ? ORDER BY id DESC");
			break;
		case 3: // Buscar por consola
			ps = con.prepareStatement("SELECT * FROM juego WHERE usuario_id=? AND consola_id LIKE ? ORDER BY id DESC");
			break;
		case 4: // Buscar por estado
			ps = con.prepareStatement("SELECT * FROM juego WHERE usuario_id=? AND estado LIKE ? ORDER BY id DESC");
			break;
		case 5: // Buscar por valoracion
			ps = con.prepareStatement("SELECT * FROM juego WHERE usuario_id=? AND valoracion LIKE ? ORDER BY id DESC");
			break;
		}	
		
		ps.setInt(1, biblioteca.getId());
		
		if(opcion != 0 && opcion < 5) { // Se utiliza si la opcion es diferente a 0 y mayor a 5. La opcion 0 no tiene un 2do valor de entrada.
			ps.setString(2, "%" + valor + "%");
		}else if(opcion == 5){ // Evita que se junten los juegos valorados en 1 y 10
			ps.setString(2, valor); 
		}
		
		rs = ps.executeQuery();		
		ArrayList<Juego> lista = null;
		
		while(rs.next()) {
			if(lista == null) {
				lista = new ArrayList<>();
			}					
			lista.add(new Juego(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9), rs.getInt(11)));
		}
		rs.close();
		ps.close();
		return lista;
	}
	
	/**
	 * Metodo que devuelve el listado de las consolas del usuarrio.
	 * <ul><li>Habilita opcion y valor para aplicar filtros a la busqueda.</li></ul>
	 * @param biblioteca <i>Toma el id del usuario.</i>
	 * @param opcion <i>Opcion segun la cual se quiere realizar la busqueda.</i>
	 * @param valor <i>Valor que se quiere buscar.</i>
	 * @return ArrayList - <i>Lista de juegos que coincidan con la opcion y valor especificados.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 */
	public ArrayList<Consola> listaConsolas(Biblioteca biblioteca, int opcion, String valor) throws SQLException {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		switch (opcion) {
		case 0: // Opcion usada solo para listar las ultimas 6 consolas de la pantalla principal
			ps = con.prepareStatement("SELECT * FROM consola WHERE usuario_id=? ORDER BY id DESC LIMIT 6");
			break;	
		case 1: // Buscar por nombre
			ps = con.prepareStatement("SELECT * FROM consola WHERE usuario_id=? AND nombre LIKE ? ORDER BY id DESC");
			break;		
		case 2: // Buscar por desarrollador
			ps = con.prepareStatement("SELECT * FROM consola WHERE usuario_id=? AND desarrollador LIKE ? ORDER BY id DESC");
			break;
		}
		
		ps.setInt(1, biblioteca.getId());
		
		if(opcion != 0) { // No se utiliza si esta marcada la opcion 0
			ps.setString(2, "%" + valor + "%");
		}

		rs = ps.executeQuery();
		ArrayList<Consola> lista = null;
		
		while(rs.next()) {
			if(lista == null) {
				lista = new ArrayList<>();
			}	
			lista.add(new Consola(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
		}
		rs.close();
		ps.close();
		return lista;		
	}
}
