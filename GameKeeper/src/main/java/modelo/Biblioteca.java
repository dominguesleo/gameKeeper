package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.DaoBiblioteca;

/**
 * <h2>Clase Biblioteca</h2> 
 * <ul><li>Genera estadisticas de Juegos y Consolas para ser copiadas en la Pagina Principal.</li>
 * <li>Genera listas de los objetos Juego y Consola</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 * @see Juego
 * @see Consola
 */
public class Biblioteca {

	/**
	 * Id de la biblioteca 
	 */
	private int id;
	
	/**
	 * Numero de juegos que posee el usuario.
	 */
	private int juegos;
	
	/**
	 * Numero de consolas que posee el usuario.
	 */
	private int consolas;
	
	/**
	 * Numero de juegos deseados por el usuario.
	 */
	private int deseados;
	
	/**
	 * Numero de juegos en los que actualmente está jugando el usuario.
	 */
	private int jugando;
	
	/**
	 * Numero de juegos que ha obtenido el usuario recientemente.
	 */
	private int obtenido;
	
	/**
	 * Numero de juegos que ha terminado de jugar el usuario.
	 */
	private int terminado;
	
	/**
	 * Lista de los ultimos juegos que ha agregado el usuario.
	 */
	private ArrayList<Juego> ultimosJuegos;
	
	/**
	 * Lista de las ultimas consolas que ha agregado el usuario.
	 */
	private ArrayList<Consola> ultimasConsolas;


	/**
	 * Obtiene el id de la biblioteca.
	 * @return id de la biblioteca.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el id de la biblioteca.
	 * @param id - <i>el id de la biblioteca a establecer.</i>
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Obtiene la cantidad de juegos en la biblioteca.
	 * @return cantidad de juegos en la biblioteca.
	 */
	public int getJuegos() {
		return juegos;
	}

	/**
	 * Establece la cantidad de juegos en la biblioteca.
	 * @param juegos - <i>cantidad de juegos a establecer.</i>
	 */
	public void setJuegos(int juegos) {
		this.juegos = juegos;
	}

	/**
	 * Obtiene la cantidad de consolas en la biblioteca.
	 * @return cantidad de consolas en la biblioteca.
	 */
	public int getConsolas() {
		return consolas;
	}

	/**
	 * Establece la cantidad de consolas en la biblioteca.
	 * @param consolas - <i>cantidad de consolas a establecer.</i>
	 */
	public void setConsolas(int consolas) {
		this.consolas = consolas;
	}

	/**
	 * Obtiene la cantidad de juegos deseados en la biblioteca.
	 * @return cantidad de juegos deseados en la biblioteca.
	 */
	public int getDeseados() {
		return deseados;
	}

	/**
	 * Establece la cantidad de juegos deseados en la biblioteca.
	 * @param deseados - <i>cantidad de juegos deseados a establecer.</i>
	 */
	public void setDeseados(int deseados) {
		this.deseados = deseados;
	}

	/**
	 * Obtiene la cantidad de juegos en proceso de juego en la biblioteca.
	 * @return cantidad de juegos en proceso de juego en la biblioteca.
	 */
	public int getJugando() {
		return jugando;
	}

	/**
	 * Establece la cantidad de juegos en proceso de juego en la biblioteca.
	 * @param jugando - <i>cantidad de juegos en proceso de juego a establecer.</i>
	 */
	public void setJugando(int jugando) {
		this.jugando = jugando;
	}

	/**
	 * Obtiene la cantidad de juegos obtenidos en la biblioteca.
	 * @return cantidad de juegos obtenidos en la biblioteca.
	 */
	public int getObtenido() {
		return obtenido;
	}

	/**
	 * Establece la cantidad de juegos obtenidos en la biblioteca.
	 * @param obtenido - <i>cantidad de juegos obtenidos a establecer.</i>
	 */
	public void setObtenido(int obtenido) {
		this.obtenido = obtenido;
	}

	/**
	 * Obtiene la cantidad de juegos terminados en la biblioteca.
	 * @return cantidad de juegos terminados en la biblioteca.
	 */
	public int getTerminado() {
		return terminado;
	}

	/**
	 * Establece la cantidad de juegos terminados en la biblioteca.
	 * @param terminado - <i>cantidad de juegos terminados a establecer.</i>
	 */
	public void setTerminado(int terminado) {
		this.terminado = terminado;
	}
	
	/**
	 * Obtiene la lista de los ultimos juegos agregados a la biblioteca.
	 * @return lista de los ultimos juegos agregados a la biblioteca.
	 */
	public ArrayList<Juego> getUltimosJuegos() {
		return ultimosJuegos;
	}

	/**
	 * Establece la lista de los ultimos juegos agregados a la biblioteca.
	 * @param ultimosJuegos - <i>lista de los ultimos juegos agregados a la biblioteca a establecer.</i>
	 */
	public void setUltimosJuegos(ArrayList<Juego> ultimosJuegos) {
		this.ultimosJuegos = ultimosJuegos;
	}

	/**
	 * Obtiene la lista de las ultimas consolas agregadas a la biblioteca.
	 * @return lista de las ultimas consolas agregadas a la biblioteca.
	 */
	public ArrayList<Consola> getUltimasConsolas() {
		return ultimasConsolas;
	}

	/**
	 * Establece la lista de las ultimas consolas agregadas a la biblioteca.
	 * @param ultimasConsolas - <i>lista de las ultimas consolas agregadas a la biblioteca a establecer.</i>
	 */
	public void setUltimasConsolas(ArrayList<Consola> ultimasConsolas) {
		this.ultimasConsolas = ultimasConsolas;
	}

	
	/**
	 * Constructor vacio.
	 */
	public Biblioteca() {
	}
	
	/**
	 * Constructor con 1 parametro.
	 * @param id <i>Toma el id de la sesion en el servlet para ser usado en el DAO.</i>
	 * @see controlador.MisJuegos
	 * @see controlador.MisConsolas
	 * @see controlador.PaginaPrincipal
	 * @see dao.DaoBiblioteca
	 */
	public Biblioteca(int id) {
		this.id = id;
	}
	
	
	/**
	 * Constructor con 6 parametros.
	 * <i>Se utiliza en el DaoBiblioteca para almacenar las estadisticas del usuario</i>
	 * @param juegos <i>Numero total Juegos del Usuario.</i>
	 * @param consolas <i>Numero total Consolas del Usuario.</i>
	 * @param deseados <i>Numero total juegos deseados del Usuario.</i>
	 * @param jugando <i>Numero total juegos jugando del Usuario.</i>
	 * @param obtenido <i>Numero total juegos obtenidos del Usuario.</i>
	 * @param terminado <i>Numero total juegos terminados del Usuario.</i>
	 * @see controlador.PaginaPrincipal
	 * @see dao.DaoBiblioteca 
	 */
	public Biblioteca(int juegos, int consolas, int deseados, int jugando, int obtenido, int terminado) {
		super();
		this.juegos = juegos;
		this.consolas = consolas;
		this.deseados = deseados;
		this.jugando = jugando;
		this.obtenido = obtenido;
		this.terminado = terminado;
	}

	/**
	 * Constructor con 8 parametros.
	 * <i>Toma los valores almacenados en BBDD mediante el DAO para ser enviados por el servlet.</i>
	 * @param juegos <i>Numero total Juegos del Usuario.</i>
	 * @param consolas <i>Numero total Consolas del Usuario.</i>
	 * @param deseados <i>Numero total juegos deseados del Usuario.</i>
	 * @param jugando <i>Numero total juegos jugando del Usuario.</i>
	 * @param obtenido <i>Numero total juegos obtenidos del Usuario.</i>
	 * @param terminado <i>Numero total juegos terminados del Usuario.</i>
	 * @param ultimosJuegos <i>Almacena los ultimos 6 juegos agregados del Usuario.</i>
	 * @param ultimasConsolas <i>Almacena las ultimas 6 consolas agregados del Usuario.</i>
	 * @see controlador.PaginaPrincipal
	 * @see dao.DaoBiblioteca 
	 */
	public Biblioteca(int juegos, int consolas, int deseados, int jugando, int obtenido, int terminado,
			ArrayList<Juego> ultimosJuegos, ArrayList<Consola> ultimasConsolas) {
		this.juegos = juegos;
		this.consolas = consolas;
		this.deseados = deseados;
		this.jugando = jugando;
		this.obtenido = obtenido;
		this.terminado = terminado;
		this.ultimosJuegos = ultimosJuegos;
		this.ultimasConsolas = ultimasConsolas;
	}
	

	/**
	 * Metodo para listar todos los atributos del objeto Biblioteca en formato Json.
	 * @return String - <i>Retorna un String en formato JSON que representa los datos de las estadisticas y los ultimos juegos/consolas agregados</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see controlador.PaginaPrincipal
	 * @see dao.DaoBiblioteca 
	 */
	public String listarBiblioteca() throws SQLException {
		
		Biblioteca e = DaoBiblioteca.getInstance().listarEstadisticas(this); // Obtiene todas las estadisticas del usuario.
		ArrayList<Juego> ultimosJuegos = DaoBiblioteca.getInstance().listaJuegos(this, 0, ""); // Obtine los ultimos 6 juegos añadidos
		ArrayList<Consola> ultimasConsolas = DaoBiblioteca.getInstance().listaConsolas(this, 0, ""); // Obtiene las ultimas 6 consolas añadidas
		
		Biblioteca biblioteca = new Biblioteca(e.getJuegos(), e.getConsolas(), e.getDeseados(), e.getJugando(), e.getObtenido(), e.getTerminado(), ultimosJuegos, ultimasConsolas);
		Gson gson = new Gson();
		return gson.toJson(biblioteca);
	}
	
	/**
	 * Metodo que retorna un listado de objetos Juego en formato Json.
	 * Consta de dos parametros para hacer uso del buscador.
	 * @param opcion <i>Toma un valor entero que representa la opcion de busqueda.</i>
	 * @param valor <i>Toma una cadena de texto que representa el valor buscado.</i>
	 * @return String - <i>Retorna un String en formato JSON que representa los datos del objeto Juego, siendo o no aplicados filtros a la busqueda.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoBiblioteca 
	 * @see controlador.MisJuegos
	 */
	public String listarJuegos(int opcion, String valor) throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(DaoBiblioteca.getInstance().listaJuegos(this, opcion, valor));
	}
	
	/**
	 * Metodo que retorna un listado de objetos Consola en formato Json.
	 * Consta de dos parametros para hacer uso del buscador.
	 * @param opcion <i>Toma un valor entero que representa la opcion de busqueda.</i>
	 * @param valor <i>Toma una cadena de texto que representa el valor buscado.</i>
	 * @return String - <i>Retorna un String en formato JSON que representa los datos del objeto Consola, siendo o no aplicados filtros a la busqueda.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoBiblioteca
	 * @see controlador.MisConsolas
	 */
	public String listaConsolas(int opcion, String valor) throws SQLException {
		Gson gson = new Gson();
		return gson.toJson(DaoBiblioteca.getInstance().listaConsolas(this, opcion, valor));
	}
	
	@Override
	public String toString() {
		return "Biblioteca [id=" + id + ", juegos=" + juegos + ", consolas=" + consolas + ", deseados=" + deseados
				+ ", jugando=" + jugando + ", obtenido=" + obtenido + ", terminado=" + terminado + ", ultimosJuegos="
				+ ultimosJuegos + ", ultimasConsolas=" + ultimasConsolas + "]";
	}

}
