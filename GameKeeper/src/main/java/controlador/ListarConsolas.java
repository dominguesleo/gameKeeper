package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Consola;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;



/**
 * <h2>ListarConsolas</h2> 
 * Servlet implementation class ListarConsolas
 * <ul><li>Representa un Servlet para el listado de las consolas.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class ListarConsolas extends HttpServlet {
	
	/**
	 * Identificador de version para la serializacion de objetos de la clase ListarConsolas.
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor predefinido.
     * @see HttpServlet#HttpServlet()
     */
    public ListarConsolas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo que maneja las peticios GET.
	 * <ul><li>Se encarga de enviar la lista de consolas del usuario.</li></ul>
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		int usuarioId = (Integer) sesion.getAttribute("usuarioId");
		Consola consola = new Consola(usuarioId);
	
		try {
			PrintWriter respuesta = response.getWriter();
			respuesta.print(consola.listaNomConsolas());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			PrintWriter error = response.getWriter();
			error.print("<h4>Error al tomar el listado de las consolas. Ponte en contacto con el administrador.</h4>");
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
