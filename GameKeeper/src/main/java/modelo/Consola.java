package modelo;

import java.sql.SQLException;
import dao.DaoConsolas;


/**
 * <h2>Clase Consola</h2> 
 * <ul><li>Permite almacenar, editar y elminar datos de la Consola.</li>
 * <li>Genera listas con los nombres de las Consolas para los formularios y buscadores.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class Consola {
	
	/**
	 * Id de la Consola.
	 */
	private int id;
	
	/**
	 * Nombre de la Consola.
	 */
	private String nombre;
	
	/**
	 * Desarrollador de la Consola.
	 */
	private String desarrollador;
	
	/**
	 * Edicion de la Consola.
	 */
	private String edicion;
	
	/**
	 * Fecha de lanzamiento de la Consola.
	 */
	private String fecha;
	
	/**
	 * Ruta de la foto de la consola almacenada en la base de datos.
	 */
	private String foto;

	
	/**
	 * Retorna el id de la consola.
	 * @return id de la consola.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el id de la consola.
	 * @param id - <i>id de la consola.</i>
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre de la consola.
	 * @return nombre de la consola 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre de la consola.
	 * @param nombre - <i>nombre de la consola.</i>
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el desarrollador de la consola.
	 * @return desarrollador de la consola
	 */
	public String getDesarrollador() {
		return desarrollador;
	}

	/**
	 * Establece el desarrollador de la consola.
	 * @param desarrollador - <i> desarrollador de la consola.</i>
	 */
	public void setDesarrollador(String desarrollador) {
		this.desarrollador = desarrollador;
	}

	/**
	 * Retorna la edición de la consola.
	 * @return edición de la consola
	 */
	public String getEdicion() {
		return edicion;
	}

	/**
	 * Establece la edición de la consola.
	 * @param edicion - <i>edición de la consola.</i>
	 */
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	/**
	 * Retorna la fecha de lanzamiento de la consola.
	 * @return fecha de lanzamiento de la consola
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de lanzamiento de la consola.
	 * @param fecha  - <i>fecha de lanzamiento de la consola.</i>
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna la ruta de la foto de la consola.
	 * @return la ruta de la foto de la consola.
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * Establece la ruta de la foto de la consola.
	 * @param foto - <i>ruta de la foto de la consola.</i>
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	/**
	 * Constructor vacio.
	 */
	public Consola() {
	}
	
	/**
	 * Constructor con 1 parametro.
	 * @param id <i>Toma el id de la consola servlet para ser usado en el DAO.</i>
	 * @see controlador.ConsolaServlet
	 */
	public Consola(int id) {
		this.id = id;
	}
	
	/**
	 * Constructor con 2 parametros 
	 * <i>Dentro de DAO crea objetos para enviarlos por el Servlet y generar un listado dinamico</i>
	 * @param id <i>Id de la consola.</i>
	 * @param nombre <i>Nombre de la consola.</i>
	 * @see dao.DaoConsolas
	 * @see controlador.ListarConsolas
	 */
	public Consola(int id, String nombre) { // Implementar lista dinamica de consolas
		super();
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * Constructor con 6 parametros.
	 * <ul><li>Se genera en el Servler para insertar o actualizar los datos de la consola.</li>
	 * <li>Se genera en el DAO para enviar por servlet los datos de la consola.</li></ul>
	 * @param id <i>Id de la consola.</i>
	 * @param nombre <i>Nombre de la consola.</i>
	 * @param desarrollador <i>Desarrollador de la consola.</i>
	 * @param edicion <i>Edicion de la consola.</i>
	 * @param fecha <i>Fecha de la consola.</i>
	 * @param foto <i>Ruta de la foto de la consola.</i>
	 * @see controlador.ConsolaServlet
	 * @see dao.DaoBiblioteca
	 */
	public Consola(int id, String nombre, String desarrollador, String edicion, String fecha, String foto) {
		this.id = id;
		this.nombre = nombre;
		this.desarrollador = desarrollador;
		this.edicion = edicion;
		this.fecha = fecha;
		this.foto = foto;
	}
	
	/**
	 * Metodo para insertar los datos de la consola proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoConsolas
	 * @see controlador.ConsolaServlet
	 */
	public void insertar() throws SQLException {
		DaoConsolas.getInstance().insertar(this);
	}
	
	/**
	 * Metodo para eliminar los datos de la consola proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoConsolas
	 * @see controlador.ConsolaServlet
	 */
	public void eliminar() throws SQLException {
		DaoConsolas.getInstance().eliminar(this);
	}
	
	/**
	 * Metodo para actualizar los datos de la consola proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoConsolas
	 * @see controlador.ConsolaServlet
	 */
	public void update() throws SQLException{
		DaoConsolas.getInstance().update(this);
	}
	
	/**
	 * Metodo para listar los nombres de la consola y hacer listas dinamicas.
	 * @return String <i>Retorna un String en formato JSON que representa la lista de consolas.</i>
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoConsolas
	 * @see controlador.ListarConsolas
	 */
	public String listaNomConsolas() throws SQLException{
		return DaoConsolas.getInstance().listaNomConsolas(this);
	}

	// Metodo toString
	
	@Override
	public String toString() {
		return "Consola [id=" + id + ", nombre=" + nombre + ", desarrollador=" + desarrollador + ", edicion=" + edicion
				+ ", fecha=" + fecha + ", foto=" + foto + "]";
	} 		
}
