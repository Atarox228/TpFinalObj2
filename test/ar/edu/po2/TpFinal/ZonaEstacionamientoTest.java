package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZonaEstacionamientoTest {

	private ZonaEstacionamiento zona;
	
	@BeforeEach
	void setUp() {
		zona = new ZonaEstacionamiento(2546,200);
	}
	
	@Test
	void getterIdInspectorTest() {
		
		assertEquals(zona.getIdInspector(),2546);
	}
	@Test
	void getterPuntoGeografico() {
		
		assertEquals(zona.getPuntoGeografico(),200);
	}
	
	@Test
	void agregarPatenteTest() {
		
		zona.estacionar("AA-000-AA");
		
		assertEquals(zona.getPatentesSize(),1);
	}

	@Test
	void agregarPuntoVentaTest() {
		
		PuntoVenta puntoVenta = mock(PuntoVenta.class);
		zona.agregarPuntoVenta(puntoVenta);
		
		assertEquals(zona.getPuntosDeVentasSize(),1);
		
	}
	
	@Test
	void eliminarPatenteTest() {
		PuntoVenta p = mock(PuntoVenta.class);
		zona.agregarPuntoVenta(p);
		zona.estacionar("AA-000-AA");
		zona.desEstacionar("AA-000-AA");
		
		assertEquals(zona.getPatentesSize(),0);
	}
	@Test
	void eliminarPatenteInexistenteTest() {
		
		PuntoVenta p = mock(PuntoVenta.class);
		zona.agregarPuntoVenta(p);
		zona.desEstacionar("AA-000-AA");
		
		assertEquals(zona.getPatentesSize(),0);
	}
	
	@Test
	void eliminarPuntoVentaTest() {
		
		PuntoVenta puntoVenta = mock(PuntoVenta.class);
		zona.agregarPuntoVenta(puntoVenta);
		zona.quitarPuntoVenta(puntoVenta);
		
		assertEquals(zona.getPuntosDeVentasSize(),0);
		
	}
	@Test
	void eliminarPuntoVentaInexistenteTest() {
		
		PuntoVenta puntoVenta = mock(PuntoVenta.class);
		zona.quitarPuntoVenta(puntoVenta);
		
		assertEquals(zona.getPuntosDeVentasSize(),0);
	}
	
}
