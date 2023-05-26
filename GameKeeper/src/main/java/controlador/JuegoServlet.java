package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import modelo.Juego;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * <h2>JuegoServlet</h2> 
 * Servlet implementation class JuegoServlet
 * <ul><li>Representa un Servlet para el manejo de las solicitudes GET y POST de los juegos.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
@MultipartConfig
public class JuegoServlet extends HttpServlet {
	
	/**
	 * Identificador de version para la serializacion de objetos de la clase JuegoServlet.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Ruta de directorio en el servidor donde se guardaran las fotos de los juegos.
	 */
	private String pathFiles = "D:\\8domi\\Documents\\FP DAM\\Modulo 1\\Programación (DAM) - Distancia\\Eclipse\\GameKeeper\\src\\main\\webapp\\fotosJuegos";
	
	/**
	 * Archivo que representa el directorio donde se guardaran las fotos de los juegos.
	 */
	private File uploads = new File(pathFiles);
       
    /**
     * Constructor predefinido.
     * @see HttpServlet#HttpServlet()
     */
    public JuegoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo que maneja las peticios GET.
	 * <ul><li>Metodo que se encarga de eliminar un juego.</li></ul>
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));
		Juego juego = new Juego(id);

		try {
			juego.eliminar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			PrintWriter error = response.getWriter();
			error.print("<h4>Error al eliminar el juego a la biblioteca. Ponte en contacto con el administrador.</h4>");
		}
	}

	/**
	 * Metodo que maneja las peticios POST.
	 * <ul><li>Metodo que se encarga de insertar o actualizar los datos de un juego.</li>
	 * <li>Si el id es 0, agrega el juego y lo redirige al formulario.</li>
	 * <li>Si la id es diferente a 0, actualiza los datos del juego y lo redirige a la pagina misJuegos.</li></ul>
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		int usuarioId = (Integer) sesion.getAttribute("usuarioId");	
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String formato = request.getParameter("formato");
		String estado = request.getParameter("estado");
		String edicion = request.getParameter("edicion");
		String genero = request.getParameter("genero");		
		String fecha = request.getParameter("fecha");
		int consola = Integer.parseInt(request.getParameter("consola"));
		int valoracion = (request.getParameter("valoracion") != null) ? Integer.parseInt(request.getParameter("valoracion")) : 0;	
		
		Part part = request.getPart("foto"); // Datos binarios
		Path path = Paths.get(part.getSubmittedFileName()); // Da el nombre de archivo
		String foto = path.getFileName().toString();
		
		if(foto != "") { //Evita error si el usuario no ingresa usa foto
			InputStream input = part.getInputStream();
			File file = new File(uploads, foto);
			try {
				Files.copy(input, file.toPath());
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error en la copia del archivo");
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al intentar copiar la foto</h4>");
			}			
		}
		
		Juego juego = null;
		
		if(id == 0) { // Añadir Juego -- Formulario front configurado para enviar id = 0
			juego = new Juego(usuarioId, nombre, formato, estado, edicion, genero, fecha, valoracion, foto, consola);
			try {
				juego.insertar();
				response.sendRedirect("agregarjuego.html?validado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al añadir el juego a la biblioteca. Ponte en contacto con el administrador.</h4>");
			}	
		}else { // Editar Juego
			juego = new Juego(id, nombre, formato, estado, edicion, genero, fecha, valoracion, foto, consola);			
			try {
				juego.update();
				response.sendRedirect("misjuegos.html");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al alctualizar el juego en la biblioteca. Ponte en contacto con el administrador.</h4>");
			}
		}
	}
}
