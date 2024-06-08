package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppUsuarioTest {
	
	private AppUsuario app;
	private SEM sistemaSEM;
	private Celular cel;
	
	@BeforeEach
	void setUp() {
		
		sistemaSEM = mock(SEM.class);
		cel = mock(Celular.class);
		app = new AppUsuario("AA-000-AA",sistemaSEM,cel);
		when(cel.getNumero()).thenReturn(12345);
	}
	
	@Test
	void inicioEstacionamiento() {
		
		app.inicioDeEstacionamiento(app.getPatentePredeterminada());
		verify(sistemaSEM,times(1)).registrarEstacionamientoApp("AA-000-AA",app);
		assertTrue(app.getVigente());
	}
	
	@Test
	void finEstacionamiento() {
		
		app.inicioDeEstacionamiento(app.getPatentePredeterminada());
		app.finDeEstacionamiento();
		verify(sistemaSEM,times(1)).removerEstacionamientoDe_(app.getNTelefono());
		assertFalse(app.getVigente());
	}
	
	@Test
	void cambiarModo() {
		
		app.cambiarModoApp();
	}
}
