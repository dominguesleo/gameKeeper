package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;


class UsuarioTest {

	@Test
	public void testValidarDuplicado() throws SQLException {

		Usuario usuario = new Usuario();
		usuario.setMail("noexiste@correo.com"); // No existe en la base de datos
		boolean resultado = usuario.validarDuplicado();	
		boolean resultadoEsperado = false;
		assertEquals(resultadoEsperado, resultado);	
	}
	
	@Test
	public void testObtenerDatos() throws SQLException {

		Usuario usuario = new Usuario();
		usuario.setId(4);
		String resultado = usuario.obtenerDatos();
		
		Usuario esperado = new Usuario("user", "user", "user@user.com", "123456789", "1996-09-20", "España"); // Existe en la base de datos
		Gson gson = new Gson();
		String resultadoEsperado = gson.toJson(esperado);
		
		assertEquals(resultadoEsperado, resultado);	
	}

}
