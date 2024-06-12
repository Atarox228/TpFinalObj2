package ar.edu.po2.TpFinal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModoManualTest {

	private ModoManual mm;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() {
		app = mock(AppUsuario.class);
		mm = new ModoManual(app);
		
	}
	
	@Test
	void testCambiarModo() {
		mm.cambiarModo(app);
		verify(app,times(1)).setModoApp(any(ModoAutomatico.class));
		verify(app,times(1)).encenderSensor();
	}
	
	@Test
	void testEstaManejando() {
		mm.estaManejando();
	}
	
	@Test
	void testEstaCaminando() {
		mm.estaCaminando();
	}
	
	@Test
	void testCambiarModoSensorApagado() {
		mm.cambiarModoSensorApagado();
	}
	
	@Test
	void testInicioDeEstacionamiento() {
		mm.inicioDeEstacionamiento("AA-000-AA");
		verify(app,times(1)).inicioDeEstacionamientoApp("AA-000-AA");
		
	}
	
	@Test
	void testFinDeEstacionamiento() {
		mm.finDeEstacionamiento();
		verify(app,times(1)).finDeEstacionamientoApp();
		
	}
}
