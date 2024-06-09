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
	private Modo mAuto;
	private Modo mManual;
	
	@BeforeEach
	void setUp() {
		
		sistemaSEM = mock(SEM.class);
		cel = mock(Celular.class);
		app = new AppUsuario("AA-000-AA",sistemaSEM,cel);
		when(cel.getNumero()).thenReturn(12345);
		
		mManual = mock(ModoManual.class);
		mAuto = mock(ModoAutomatico.class);
		app.setModoApp(mManual);
	}
	
	@Test
	void settersTest() {
		
		app.setPatentePredeterminada("AB-123-CD");
		assertEquals("AB-123-CD",app.getPatentePredeterminada());
	}
	
	@Test
	void testInicioEstacionamiento() {
		
		app.inicioDeEstacionamiento(app.getPatentePredeterminada());
		verify(sistemaSEM,times(1)).registrarEstacionamientoApp("AA-000-AA",app);
		assertTrue(app.getVigente());
	}
	
	@Test
	void testTinEstacionamiento() {
		
		app.inicioDeEstacionamiento(app.getPatentePredeterminada());
		app.finDeEstacionamiento();
		verify(sistemaSEM,times(1)).removerEstacionamientoDe_(app.getNTelefono());
		assertFalse(app.getVigente());
	}
	
	@Test
	void testConsultarSaldo() {
		
		Double saldo = app.consultarSaldo();
		verify(sistemaSEM).consultarSaldoDe_(app.getNTelefono());
		assertEquals(saldo,0);
		
	}
	
	@Test
	void testEncenderSensor() {
		
		app.encenderSensor();
		assertTrue(app.sensorPrendido());
		
	}
	
	@Test
	void testApagarSensor() {
		
		app.apagarSensor();
		assertFalse(app.sensorPrendido());
		verify(mManual).cambiarModoSensorApagado();
		
	}
	
	@Test
	void testApagarSensorEnAutomaticoCambiaModo() {
		
		app.setModoApp(mAuto);
		app.apagarSensor();
		
		assertFalse(app.sensorPrendido());
		verify(mAuto).cambiarModoSensorApagado();
		
	}
	
	@Test
	void testCambiarAModoAutomatico() {
		
		
		app.cambiarModoApp();
		verify(mManual).cambiarModo(app);
		//assertTrue(app.sensorPrendido());
	}
	
	@Test
	void testCambiarAModoManual() {
		
		app.setModoApp(mAuto);
		app.cambiarModoApp();
		verify(mAuto).cambiarModo(app);
	}
	
	
	@Test
	void testAppEnviaMensajesConSensorPrendido() {
		
		app.encenderSensor();
		app.driving();
		app.walking();
		
		verify(mManual).estaManejando();
		verify(mManual).estaCaminando();;
		
	}
	
	@Test
	void testAppNoEnviaMensajesConSensorApagado() {
		
		app.driving();
		app.walking();
		
		verify(mManual,times(0)).estaManejando();
		verify(mManual,times(0)).estaCaminando();;
		
	}
}
