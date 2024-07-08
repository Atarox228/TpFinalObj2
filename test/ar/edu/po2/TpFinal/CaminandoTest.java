package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CaminandoTest {

	private SensorMovimiento caminando;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() {
		
		caminando = new Caminando();
		app = mock(AppUsuario.class);
	}
	
	@Test
	void apagarSensorEnEstadoCaminando() {

		caminando.apagarSensor(app);
		
		verify(app,times(1)).setSensorMovimiento(any(Apagado.class));
		verify(app,times(1)).notificarSensorApagado();
	}
	@Test
	void encenderSensorEnEstadoCaminando() {
		
		caminando.encender(app);
		
		verify(app,times(0)).setSensorMovimiento(any(Encendido.class));
	}
	@Test
	void mensajeCaminarEnEstadoCaminando() {
		
		caminando.estaCaminando(app);
		
		verify(app,times(0)).setSensorMovimiento(any(Caminando.class));
	}
	@Test
	void mensajeManejandoEnEstadoCaminando() {
		
		when(app.estaEnLaZona()).thenReturn(true);
		caminando.estaManejando(app);
		
		verify(app,times(1)).setSensorMovimiento(any(Manejando.class));
		verify(app,times(1)).finDeEstacionamiento();
	}
	

}
