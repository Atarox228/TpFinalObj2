package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppInspectorTest {

	private AppInspector app;
	private SEM s;
	
	@BeforeEach
	void setUp() {
		s = mock(SEM.class);
		app = new AppInspector(2546,s);
	}
	
	@Test
	void gettersTest() {

		assertEquals(app.getId(),2546);
		assertEquals(app.getSistemaSEM(), s);
	}
	
	@Test
	void altaInfraccionTest() {
		app.altaDeInfraccion("AA-000-AA");
		
		verify(s, atLeast(1)).altaDeInfraccion("AA-000-AA",2546);
	}
	
	@Test
	void consultaInfraccionTest() {
		when(s.estaEnInfraccion("AA-000-AA",app)).thenReturn(false);
		app.consultarEstacionamientoDe("AA-000-AA");
		
		verify(s, atMost(0)).altaDeInfraccion("AA-000-AA",2546);
	}
	
	@Test
	void consultaInfraccion2Test() {
		when(s.estaEnInfraccion("AA-000-AA",app)).thenReturn(true);
		app.consultarEstacionamientoDe("AA-000-AA");
		
		verify(s, atLeast(1)).altaDeInfraccion("AA-000-AA",2546);
	}


}
