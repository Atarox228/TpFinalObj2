package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CelularTest {

	private Celular cel;
	private AppUsuario app;
	private ZonaEstacionamiento zona;
	
	@BeforeEach
	void setUp() {
		zona = mock(ZonaEstacionamiento.class);
		cel = new Celular(1159045262,zona);
		app = mock(AppUsuario.class);
	}
	
	@Test
	void gettersTest() {
		
		//Exercise
		int num = cel.getNumero();
		ZonaEstacionamiento zona1 = cel.getZona();
		//Verify
		assertEquals(num, 1159045262);
		assertEquals(zona1, zona);
	}
	
	@Test
	void settersTest() {
		
		//SetUp
		ZonaEstacionamiento zona1 = mock(ZonaEstacionamiento.class);
		
		//Exercise
		cel.descargarApp(app);
		cel.setZona(zona1);
		
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
}