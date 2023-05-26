package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.UsuarioAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * <h2>AdminServlet</h2> 
 * Servlet implementation class AdminServlet
 * <ul><li>Representa un Servlet para las acciones del Administrador.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class AdminServlet extends HttpServlet {
	
	/**
	 * Identificador de version para la serializacion de objetos de la clase AdminServlet.
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor predefinido.
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo que maneja las peticios GET.
	 * <ul><li>No representa ningun uso para este servlet.</li></ul>
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
			
	}

	/**
	 * Metodo que maneja las peticios POST.
	 * <ul><li>Obtiene el permiso del Administrador de la sesion, la opcion seleccionada y el Id del usuario donde se ejecutara la accion</li>
	 * <li>Si la opcion es 1, bloquea al usuario con el id indicado.</li>
	 * <li>Si la opcion es 2, desbloquea al usuario con el id indicado.</li></ul>
	 * <li>Si la opcion es 3, da permisos de administrador al usuario con el id indicado.</li></ul>
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		int idadmin = (Integer) sesion.getAttribute("usuarioId");
		String nombre = (String) sesion.getAttribute("nombre");
		int permiso = (Integer) sesion.getAttribute("permiso");
		UsuarioAdmin admin = new UsuarioAdmin(idadmin, nombre, permiso);

		int opcion = Integer.parseInt(request.getParameter("opcion"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		if(admin.getPermiso() == 10) { //Si no tiene permisos se le envia a perfil.html
			switch (opcion) {
			case 1: //Bloquear Usuario
				try {
					admin.bloquearUsuario(id);
					response.sendRedirect("perfiladmin.html?bloqueado");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					PrintWriter error = response.getWriter();
					error.print("<h4>Error al bloquear el Usuario.</h4>");
				}
				break;
			case 2: // Desbloquear Usuario
				try {
					admin.desbloquearUsuario(id);
					response.sendRedirect("perfiladmin.html?desbloqueado");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					PrintWriter error = response.getWriter();
					error.print("<h4>Error al desbloquear el Usuario.</h4>");
				}
				break;
			case 3: // Hacer Administrador
				try {
					admin.hacerAdmin(id);
					response.sendRedirect("perfiladmin.html?admin"); 
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					PrintWriter error = response.getWriter();
					error.print("<h4>Error al dar permisos de Administrador.</h4>");
				}
				break;
			default:
				break;
			}
		}else {
			response.sendRedirect("perfil.html?permiso"); // Genera una notificacion con js
		}
	}
}
