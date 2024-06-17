package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ManejandoTest {

	private SensorMovimiento manejando;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() {
		
		manejando = new Manejando();
		app = mock(AppUsuario.class);
	}
	
	@Test
	void apagarSensorEnEstadoManejando() {
		
		manejando.apagarSensor(app);
		
		verify(app,times(1)).setSensorMovimiento(any(Apagado.class));
		verify(app,times(1)).notificarSensorApagado();
	}
	@Test
	void encenderSensorEnEstadoManejando() {
		
		manejando.encender(app);
		
		verify(app,times(0)).setSensorMovimiento(any(Encendido.class));
	}
	@Test
	void mensajeCaminarEnEstadoManejando() {
		
		when(app.getPatentePredeterminada()).thenReturn("AA-0000-AA");
		manejando.estaCaminando(app);
		
		
		verify(app,times(1)).setSensorMovimiento(any(Caminando.class));
		verify(app,times(1)).inicioDeEstacionamiento("AA-0000-AA");
	}
	@Test
	void mensajeManejandoEnEstadoManejando() {
		
		manejando.estaManejando(app);
		
		verify(app,times(0)).setSensorMovimiento(any(Manejando.class));
	}

}
