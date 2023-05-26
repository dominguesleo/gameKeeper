package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Login;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * <h2>LoginServlet</h2> 
 * Servlet implementation class LoginServlet
 * <ul><li>Representa un Servlet para el inicio y cierre de sesion del usuario.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class LoginServlet extends HttpServlet {
	
	/**
	 * Identificador de version para la serializacion de objetos de la clase LoginServlet.
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor predefinido.
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo que maneja las peticios GET.
	 * <ul><li>Invalida la sesion actual.</li></ul>
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession(); 
		sesion.invalidate(); //Se cierra sesion y mediante js se envia a login.html
	}

	/**
	 * Metodo que maneja las peticios POST.
	 * <ul><li>Valida el inicio de sesion del usuario y redirige a la pagina principal si es valido.</li></ul>
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		Login login = new Login(mail, password);
		Usuario usuario = null;
		
		try {
			usuario = login.validarLogin(); // Devuelve null si no coincide el mail y password en la base de datos 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			PrintWriter error = response.getWriter();
			error.print("<h4>Error al validar las credenciales del Usuario. Ponte en contacto con el administrador.</h4>");
		}
		
		if(usuario != null) { // Si es null los datos son incorrectos o no existe 
			if(usuario.getPermiso() >= 5) { // Si el permiso es menor a 5 el usuario esta bloqueado, se niega el acceso.
				HttpSession sesion = request.getSession();
				sesion.setAttribute("usuarioId", usuario.getId());
				sesion.setAttribute("nombre", usuario.getNombre());
				sesion.setAttribute("permiso", usuario.getPermiso());
				response.sendRedirect("index.html");
			}else {
				response.sendRedirect("login.html?permiso");
			}
		} else {
			response.sendRedirect("login.html?error");
		}
	}
}