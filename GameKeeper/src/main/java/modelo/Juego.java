package modelo;

import java.sql.SQLException;
import dao.DaoJuegos;

/**
 * <h2>Clase Juego</h2> 
 * Permite almacenar, editar y elminar datos del Juego.
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class Juego {
	
	// Atributos
	
	/**
	 * Id del Juego.
	 */
	private int id;
	
	/**
	 * Nombre del Juego.
	 */
	private String nombre;
	
	/**
	 * Formato del Juego.
	 */
	private String formato;
	
	
	/**
	 * Estado del Juego.
	 */
	private String estado;
	
	/**
	 * Edicion del Juego.
	 */
	private String edicion;
	
	/**
	 * Genero del Juego.
	 */
	private String genero;
	
	/**
	 * Fecha del Juego.
	 */
	private String fecha;
	
	/**
	 * Valoracion del Juego.
	 */
	private int valoracion;
	
	/**
	 * Foto del Juego.
	 */
	private String foto;
	
	/**
	 * Consola asociada al Juego
	 */
	private int consola;

	
	// Get and Set 
	
	/**
	 * Retorna el id del Juego.
	 * @return id del juego.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Establece el id del juego.
	 * @param id - <i>id del juego.</i>
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retorna el nombre del juego.
	 * @return nombre del juego.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del juego.
	 * @param nombre - <i>nombre del juego.</i>
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Retorna el formato del juego.
	 * @return formato del juego.
	 */
	public String getFormato() {
		return formato;
	}

	/**
	 * Establece el formato del juego.
	 * @param formato - <i>formato del juego.</i>
	 */
	public void setFormato(String formato) {
		this.formato = formato;
	}

	/**
	 * Retorna el estado del juego.
	 * @return estado del juego.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el estado del juego.
	 * @param estado - <i>estado del juego.</i>
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna la edicion del juego.
	 * @return edicion del juego.
	 */
	public String getEdicion() {
		return edicion;
	}

	/**
	 * Establece la edicion del juego.
	 * @param edicion - <i>edicion del juego.</i>
	 */
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}

	/**
	 * Retorna el genero del juego.
	 * @return genero del juego.
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Establece el genero del juego.
	 * @param genero - <i>genero del juego.</i>
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * Retorna la fecha de lanzamiento del juego.
	 * @return fecha de lanzamiento del juego.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Establece la fecha de lanzamiento del juego.
	 * @param fecha - <i>fecha de lanzamiento del juego.</i>
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna la valoracion del juego.
	 * @return valoracion del juego.
	 */
	public int getValoracion() {
		return valoracion;
	}

	/**
	 * Establece la valoracion del juego.
	 * @param valoracion - <i>valoracion del juego.</i>
	 */
	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	/**
	 * Retorna la ruta de la foto dej juego.
	 * @return ruta de la foto dej juego.
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * Establece la ruta de la foto del juego.
	 * @param foto - <i>ruta de la foto del juego.</i>
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}

	/**
	 * Retorna la consola asociada al juego, siendo el id de la consola.
	 * @return consola asociada al juego
	 */
	public int getConsola() {
		return consola;
	}

	/**
	 * Establece la consola asociada al juego por medio del id de la consola.
	 * @param consola <i>id de la consola.</i>
	 */
	public void setConsola(int consola) {
		this.consola = consola;
	}
	
	// Constructores
	
	/**
	 * Constructor vacio.
	 */
	public Juego() {
	}
	
	/**
	 * Constructor con 1 parametro.
	 * @param id <i>Toma el id de la consola servlet para ser usado en el DAO.</i>
	 * @see controlador.JuegoServlet
	 */
	public Juego(int id) {
		this.id = id;
	}

	/**
	 * Constructor con 10 parametros.
	 * <ul><li>Se genera en el Servler para insertar o actualizar los datos del juego.</li>
	 * <li>Se genera en el DAO para enviar por servlet los datos del juego.</li></ul>
	 * @param id <i>Id del juego.</i>
	 * @param nombre <i>Nombre del juego.</i>
	 * @param formato <i>Formato del juego.</i>
	 * @param estado <i>Estado del juego.</i>
	 * @param edicion <i>Edicion del juego.</i>
	 * @param genero <i>Genero del juego.</i>
	 * @param fecha <i>Fecha del juego.</i>
	 * @param valoracion <i>Valoracion del juego.</i>
	 * @param foto <i>Ruta de la foto del juego.</i>
	 * @param consola <i>Consola del juego.</i>
	 * @see controlador.JuegoServlet
	 * @see dao.DaoBiblioteca
	 */
	public Juego(int id, String nombre, String formato, String estado, String edicion, String genero, String fecha,
			int valoracion, String foto, int consola) {
		this.id = id;
		this.nombre = nombre;
		this.formato = formato;
		this.estado = estado;
		this.edicion = edicion;
		this.genero = genero;
		this.fecha = fecha;
		this.valoracion = valoracion;
		this.foto = foto;
		this.consola = consola;
	}
	
	/**
	 * Metodo para insertar los datos del juego proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoJuegos
	 * @see controlador.JuegoServlet
	 */
	public void insertar() throws SQLException {			
		DaoJuegos.getInstance().insertar(this);
	}
	
	/**
	 * Metodo para eliminar los datos del juego proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoJuegos
	 * @see controlador.JuegoServlet
	 */
	public void eliminar() throws SQLException {
		DaoJuegos.getInstance().eliminar(this);
	}
	
	/**
	 * Metodo para actualizar los datos de la consola proveniente del Servlet.
	 * @throws SQLException <i>Propaga la exepcion hasta el servlet.</i>
	 * @see dao.DaoJuegos
	 * @see controlador.JuegoServlet
	 */
	public void update() throws SQLException {			
		DaoJuegos.getInstance().update(this);
	}

	// Metodo toString
	
	@Override
	public String toString() {
		return "Juego [id=" + id + ", nombre=" + nombre + ", formato=" + formato + ", estado=" + estado + ", edicion="
				+ edicion + ", genero=" + genero + ", fecha=" + fecha + ", valoracion=" + valoracion + ", foto=" + foto
				+ ", consola=" + consola + "]";
	}
}
