package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ModoAutomaticoTest {

	private AppUsuario app;
	private ModoAutomatico mAuto;
	
	@BeforeEach
	void setUp() {
		app = mock(AppUsuario.class);
		mAuto = new ModoAutomatico(app);
	}
	
	@Test
	void testCambiarModo() {
		mAuto.cambiarModo(app);
		verify(app,times(1)).setModoApp(any(ModoManual.class));
	}
	
	@Test
	void testIniciarEstacionamiento() {
		
		when(app.getPatentePredeterminada()).thenReturn("AA-000-AA");
		
		mAuto.iniciarEstacionamiento();
		
		verify(app,times(1)).inicioDeEstacionamiento("AA-000-AA");
	}
	
	@Test
	void testFinEstacionamiento() {
		
		mAuto.finEstacionamiento();
		
		verify(app,times(1)).finDeEstacionamiento();
	}
	
	@Test
	void testDeCaminarAManejar() {
		
		when(app.getVigente()).thenReturn(true);
		
		mAuto.estaManejando();
		
		verify(app,times(1)).finDeEstacionamiento();
	}
	
	@Test
	void testDeManejarAManejar() {
		
		mAuto.cambiarEstado();
		
		mAuto.estaManejando();
		
		verify(app,times(0)).finDeEstacionamiento();
	}
	
	@Test
	void testDeManejarACaminar() {
		
		mAuto.estaManejando();
		when(app.getPatentePredeterminada()).thenReturn("AA-000-AA");
		when(app.getVigente()).thenReturn(true);
		
		mAuto.estaCaminando();
		
		verify(app,times(1)).inicioDeEstacionamiento("AA-000-AA");
	}
	
	@Test
	void testDeCaminarACaminar() {
		
		mAuto.estaCaminando();
		
		verify(app,times(0)).inicioDeEstacionamiento("AA-000-AA");
	}
	
	@Test
	void testCambiarModoSensorApagado() {
		mAuto.cambiarModoSensorApagado();
		verify(app,times(1)).setModoApp(any(ModoManual.class));
	}
}
