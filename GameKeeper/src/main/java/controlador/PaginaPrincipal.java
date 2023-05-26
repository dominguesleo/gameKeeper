package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Biblioteca;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * <h2>PaginaPrincipal</h2> 
 * Servlet implementation class PaginaPrincipal
 * <ul><li>Representa un Servlet para enviar las estadisticas y los ultimos 6 juegos/consolas añadidos.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class PaginaPrincipal extends HttpServlet {
	
	/**
	 * Identificador de version para la serializacion de objetos de la clase PaginaPrincipal.
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor predefinido.
     * @see HttpServlet#HttpServlet()
     */
    public PaginaPrincipal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo que maneja las peticios GET.
	 * <ul><li>Metodo que se encarga de enviar los datos de la biblioteca al usuario.</li>
	 * <li>Envia las estadisticas y los ultimos 6 juegos/consolas.</li></ul>
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		int usuarioID = (Integer) sesion.getAttribute("usuarioId");
		
		Biblioteca biblioteca = new Biblioteca(usuarioID);

		try {
			PrintWriter respuesta = response.getWriter();
			respuesta.print(biblioteca.listarBiblioteca());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			PrintWriter error = response.getWriter();
			error.print("<h4>Error al entregar los datos y listados en la Pagina principal. Ponte en contacto con el administrador.</h4>");
		}
	}

	/**
	 * Metodo que maneja las peticios POST.
	 * <ul><li>No representa ningun uso para este servlet.</li></ul>
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
