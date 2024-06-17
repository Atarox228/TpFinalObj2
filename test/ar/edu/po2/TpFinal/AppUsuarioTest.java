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
	private SEM sem;
	private Celular cel;
	private Modo mAuto;
	private Modo mManual;
	private SensorMovimiento sApagado;
	private SensorMovimiento sEncendido;
	
	@BeforeEach
	void setUp() {
		
		sem = mock(SEM.class);
		cel = mock(Celular.class);
		app = new AppUsuario("AA-000-AA",sem,cel);
		when(cel.getNumero()).thenReturn(12345);
	
		
		mManual = mock(ModoManual.class);
		mAuto = mock(ModoAutomatico.class);
		sApagado = mock(Apagado.class);
		sEncendido = mock(Encendido.class);
		app.setModoApp(mManual);
		app.setSensorMovimiento(sApagado);
	}
	
	@Test
	void settersTest() {
		
		app.setPatentePredeterminada("AB-123-CD");
		assertEquals("AB-123-CD",app.getPatentePredeterminada());
	}
	
	@Test
	void gettersTest() {
		assertEquals(app.getPatentePredeterminada(),"AA-000-AA");
		assertFalse(app.getVigente());
		assertEquals(app.getZona(),null);
	}
	
	@Test
	void testInicioEstacionamientoApp() {
		
		app.inicioDeEstacionamientoApp(app.getPatentePredeterminada());
		verify(sem,times(1)).registrarEstacionamientoApp("AA-000-AA",app);
		assertTrue(app.getVigente());
	}
	
	@Test
	void testInicioDeEstacionamientoManual() {
		app.setModoApp(mManual);
		app.inicioDeEstacionamiento("AA-000-AA");
		
		verify(mManual,times(1)).inicioDeEstacionamiento("AA-000-AA");
	}
	
	@Test
	void testFinEstacionamientoApp() {
		
		app.inicioDeEstacionamientoApp(app.getPatentePredeterminada());
		app.finDeEstacionamientoApp();
		verify(sem,times(1)).removerEstacionamientoDe_(app);
		assertFalse(app.getVigente());
	}
	
	@Test
	void testFinDeEstacionamientoManual() {
		app.setModoApp(mManual);
		app.inicioDeEstacionamiento("AA-000-AA");
		app.finDeEstacionamiento();
		
		verify(mManual,times(1)).finDeEstacionamiento();
	}
	
	@Test
	void testFinDeEstacionamientoAutomatico() {
		app.setModoApp(mAuto);
		app.inicioDeEstacionamiento("AA-000-AA");
		app.finDeEstacionamiento();
		
		verify(mAuto,times(1)).finDeEstacionamiento();
	}
	
	@Test
	void testConsultarSaldo() {
		
		Double saldo = app.consultarSaldo();
		verify(sem).consultarSaldoDe_(app.getNTelefono());
		assertEquals(saldo,0);
		
	}
	
	@Test
	void testEncenderSensor() {
		
		app.encenderSensor();
		verify(sApagado, times(1)).encender(app);;
		
	}
	
	@Test
	void testApagarSensor() {
		
		app.setSensorMovimiento(sEncendido);
		app.apagarSensor();
		
		verify(sEncendido, times(1)).apagarSensor(app);
		
	}
	
	@Test
	void testCambiarAModoAutomatico() {
		
		
		app.cambiarModoApp();
		verify(mManual).cambiarModo(app);
	}
	
	@Test
	void testCambiarAModoManual() {
		
		app.setModoApp(mAuto);
		app.cambiarModoApp();
		verify(mAuto).cambiarModo(app);
	}
	
	
	@Test
	void testAppNoEnviaMensajesConSensorApagado() {
		
		app.driving();
		app.walking();
		
		verify(mManual,times(0)).estaManejando();
		verify(mManual,times(0)).estaCaminando();;
		
	}
	
	@Test
	void testObtenerZonaCerca() {
		
		ZonaEstacionamiento zona = mock(ZonaEstacionamiento.class);
		when(sem.obtenerZonaCercana()).thenReturn(zona);
		
		ZonaEstacionamiento zona2 = app.obtenerZonaCercana();
		
		assertEquals(zona,zona2);
	}
	
	@Test
	void testNotificar() {
		
		String mensaje = "Saldo insuficiente";
		app.notificar(mensaje);
		
		verify(cel,times(1)).notificar(mensaje);
	}
	@Test
	void testNotificarSensorApagado() {
		
		app.setModoApp(mAuto);
		app.notificarSensorApagado();
		
		verify(mAuto,times(1)).notificarSensorApagado(app);
	}
}
