package modelo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class LoginTest {

	@Test
	public void testValidarLogin() throws SQLException {
		
		Login login = new Login("user@user.com", "123456789");
		Usuario resultado = login.validarLogin();
		
		Usuario resultadoEsperado = new Usuario(4, "user", 5); //Existe en la base de datos

		assertEquals(resultadoEsperado.getId(), resultado.getId());	
		assertEquals(resultadoEsperado.getNombre(), resultado.getNombre());	
		assertEquals(resultadoEsperado.getPermiso(), resultado.getPermiso());		
	}

}

