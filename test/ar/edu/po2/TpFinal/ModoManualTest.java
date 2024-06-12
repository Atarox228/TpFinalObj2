package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
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
		mm = new ModoManual();
		app = mock(AppUsuario.class);
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

}
