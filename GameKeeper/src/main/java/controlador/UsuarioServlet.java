package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * <h2>UsuarioServlet</h2> 
 * Servlet implementation class UsuarioServlet
 * <ul><li>Representa un Servlet para manejar las peticiones de la clase usuario.</li>
 * <li>Permite insertar, actualizar, eliminar y obtener los datos del usuario.</li></ul>
 * @author Leonardo Domingues Mieres.
 * @version v. 05-2023.
 * @since 03-2023.
 */
public class UsuarioServlet extends HttpServlet {
	
	/**
	 * Identificador de version para la serializacion de objetos de la clase UsuarioServlet.
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * Constructor predefinido.
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * Metodo que maneja las peticios GET.
	 * <ul><li>Obtiene el id del usuario de la sesion y la opcion seleccionada.</li>
	 * <li>Si la opcion es 1, muestra los datos del usuario.</li>
	 * <li>Si la opcion es 2, elimina al usuario y cierra la sesion.</li></ul>
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession sesion = request.getSession();
		int usuarioId = (Integer) sesion.getAttribute("usuarioId");
		int opcion = Integer.parseInt(request.getParameter("opcion"));
		
		Usuario usuario = new Usuario(usuarioId);
			
		if(opcion == 1) { // Devolver datos de usuario
			try {
				PrintWriter respuesta = response.getWriter();
				respuesta.print(usuario.obtenerDatos());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al entregar dos datos del perfil del Usuario. Ponte en contacto con el administrador.</h4>");
			}		
		}else if(opcion == 2) { // Eliminar datos de usuario
			try {
				usuario.eliminar();
				sesion.invalidate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al eliminar el perfil del Usuario. Ponte en contacto con el administrador.</h4>");
			}		
		}	
	}

	/**
	 * Metodo que maneja las peticios POST.
	 * <ul><li>Obtiene el id del usuario de la sesion y la opcion seleccionada.</li>
	 * <li>Si la opcion es 1, registra al usuario y lo redirige a la pagina principal.</li>
	 * <li>Si la opcion es 2, actualiza los datos del usuario y lo redirige a su perfil.</li>
	 * <li>Si la opcion es 3, permite recuperar la contraseña y lo redirige a login.</li></ul>
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession sesion = request.getSession();
		int opcion = Integer.parseInt(request.getParameter("opcion"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		String fecha = request.getParameter("fecha");
		String pais = request.getParameter("pais");
		
		Usuario usuario = null;
			
		switch (opcion) {
		case 1: // Agregar Usuario
			try {
				usuario = new Usuario(nombre, apellido, correo, password, fecha, pais);
				if(!usuario.validarDuplicado()) { //Si existe el mail en la base de datos no permite el registro.
					usuario.insertar();	
					sesion.setAttribute("usuarioId", usuario.getId());
					sesion.setAttribute("nombre", usuario.getNombre());
					sesion.setAttribute("permiso", usuario.getPermiso());
					response.sendRedirect("index.html");	
				}else {
					response.sendRedirect("registrate.html?error");	//
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error en el registro del Usuario. Ponte en contacto con el administrador.</h4>");
			}
			break;
		case 2: // Actualizar datos de Usuario
			int usuarioId = (Integer) sesion.getAttribute("usuarioId");			
			try {
				usuario = new Usuario(usuarioId, nombre, apellido, correo, password, fecha, pais);
				usuario.update();
				response.sendRedirect("perfil.html?validado");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al actualizar los datos del Usuario. Ponte en contacto con el administrador.</h4>");
			}
			break;
		case 3: // Recuperar Contraseña
			try {
				usuario = new Usuario(correo, password);
				if(usuario.passwordUpdate()) { // passwordUpdate() valida el mail, si es correcto realiza el cambio y devuelve true.
					response.sendRedirect("login.html?password");				
				}else {
					response.sendRedirect("recuperarpass.html?error");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				PrintWriter error = response.getWriter();
				error.print("<h4>Error al recuperar la contraseña del Usuario. Ponte en contacto con el administrador.</h4>");
			}		
			break;
		}
	}
}
