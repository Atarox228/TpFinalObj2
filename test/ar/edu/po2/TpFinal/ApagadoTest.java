package ar.edu.po2.TpFinal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ApagadoTest {

	private SensorMovimiento apagado;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() {
		
		apagado = new Apagado();
		app = mock(AppUsuario.class);
	}
	
	@Test
	void apagarElSensorYaApagadoTest() {
		
		apagado.apagarSensor(app);
		
		verify(app, times(0)).notificarSensorApagado();
	}
	@Test
	void prenderElSensorEstandoApagadoTest() {
		
		apagado.encender(app);
		
		verify(app,times(1)).setSensorMovimiento(any(Encendido.class));
		verify(app,times(1)).setModoApp(any(ModoAutomatico.class));
	}
	@Test
	void caminarConElSensorApagadoTest() {
		
		apagado.estaCaminando(app);
		
		verify(app,times(0)).setSensorMovimiento(any(Caminando.class));
	}
	@Test
	void manejandoConElSensorApagadoTest() {
		
		apagado.estaManejando(app);
		
		verify(app,times(0)).setSensorMovimiento(any(Manejando.class));
	}
}
