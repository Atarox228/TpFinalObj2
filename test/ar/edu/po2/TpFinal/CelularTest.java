package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CelularTest {

	private Celular cel;
	private AppUsuario app;
	
	@BeforeEach
	void setUp() {
		cel = new Celular(1159045262);
		app = mock(AppUsuario.class);
		
	}
	
	@Test
	void gettersTest() {
		
		//Exercise
		int num = cel.getNumero();
		ZonaEstacionamiento zona1 = cel.getZona();
		//Verify
		assertEquals(num, 1159045262);
		assertEquals(zona1, null);
	}
	
	@Test
	void settersTest() {
		
		//SetUp
		ZonaEstacionamiento zona1 = mock(ZonaEstacionamiento.class);
		when(app.obtenerZonaCercana()).thenReturn(zona1);
		
		//Exercise
		cel.descargarApp(app);
		cel.obtenerZonaCercana();
		
		//Verify
		assertEquals(cel.getZona(), zona1);
		assertEquals(cel.getApp(), app);
	}
	
	@Test
	void agregarPatenteTest() {
		
		cel.addPatente("AA-000-AA");
		
		assertEquals(cel.getPatentesSize(),1);
	}
	
	@Test
	void eliminarPatenteTest() {
		
		cel.addPatente("AA-000-AA");
		cel.removePatente("AA-000-AA");
		
		assertEquals(cel.getPatentesSize(),0);
	}

	@Test
	void cambiarPatentePrincipalSiLaTiene() {
		
		cel.addPatente("AA-000-AA");
		cel.descargarApp(app);
		cel.setPatentePrincipal("AA-000-AA");
		
		verify(app, times(1)).setPatentePredeterminada("AA-000-AA");
	}
	
	@Test
	void cambiarPatentePrincipalSiNoLaTiene() {
		
		cel.descargarApp(app);
		cel.setPatentePrincipal("AA-000-AA");
		
		verify(app, times(0)).setPatentePredeterminada("AA-000-AA");
	}
	
	@Test
	void cambiarPatentePrincipalSiNoTieneApp() {
		
		cel.setPatentePrincipal("AA-000-AA");
		
		verify(app, times(0)).setPatentePredeterminada("AA-000-AA");
	}
	
	@Test
	void usarAplicacionDescargada() {
		
		cel.descargarApp(app);
		cel.iniciarEstacionamiento("AA-000-AA");
		cel.finalizarEstacionamiento();
		cel.consultarSaldo();
		cel.cambiarModo();
		
		verify(app, times(1)).inicioDeEstacionamiento("AA-000-AA");
		verify(app, times(1)).finDeEstacionamiento();
		verify(app, times(1)).consultarSaldo();
		verify(app, times(1)).cambiarModoApp();
		
	}
	
	@Test
	void intentarUsarAplicacionSinDescargar() {
		
		cel.iniciarEstacionamiento("AA-000-AA");
		cel.finalizarEstacionamiento();
		cel.consultarSaldo();
		cel.cambiarModo();
		
		verify(app, times(0)).inicioDeEstacionamiento("AA-000-AA");
		verify(app, times(0)).finDeEstacionamiento();
		verify(app, times(0)).consultarSaldo();
		verify(app, times(0)).cambiarModoApp();
	}
	
	@Test
	void celularNoPuedeDescargarDosAppsTest() {
		
		AppUsuario app2 = mock(AppUsuario.class);
		cel.descargarApp(app);
		cel.descargarApp(app2);
		
		assertEquals(cel.getApp(),app);
		
	}
	
	@Test
	void notificarTest() {
		
		String mensaje = "Saldo insuficiente. Estacionamiento no permitido";
		app.notificar(mensaje);
		String mensaje1 = cel.notificar(mensaje);
		
		assertEquals(mensaje1, mensaje);
	}
	
	@Test
	void cambiarEstadoSensorTest() {
		cel.descargarApp(app);
		cel.cambiarEstadoSensor();
		verify(app,times(1)).cambiarEstadoSensor();
	}
}
