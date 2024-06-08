package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuntoVentaTest {

	private PuntoVenta punto;
	private ZonaEstacionamiento zona;
	private SEM sistemaSEM;
	
	@BeforeEach
	void setUp() {
		sistemaSEM = mock(SEM.class);
		zona = mock(ZonaEstacionamiento.class);
		punto = new PuntoVenta(zona, sistemaSEM);
	}
	
	@Test
	void acreditarEstacionamientoTest() {
		
		punto.acreditarEst("AA-000-AA", 2);
		
		verify(sistemaSEM,times(1)).registrarCompraPuntual("AA-000-AA", 2,punto);
		verify(zona, times(1)).estacionar("AA-000-AA");
	}
	
	@Test
	void cargarSaldoPuntoVentaTest() {
		
		punto.cargarSaldo(120d, 41770166);
		
		verify(sistemaSEM, times(1)).cargarCredito(120d,41770166);
	}

}
