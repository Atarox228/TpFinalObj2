package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfraccionTest {
	
	//setUp
	Infraccion infraccion;
	ZonaEstacionamiento estacionamiento;
	
	
	@BeforeEach
	void setUp() {
		estacionamiento = mock(ZonaEstacionamiento.class);
		infraccion = new Infraccion("AA-000-AA","20/02/2020",10,0546,estacionamiento);
		
	}
	
	
	@Test
	void devolverPatente() {
		
		//Exercise
		String patente = infraccion.getPatente();
		
		//verify
		assertEquals(patente,"AA-000-AA");
	}
	
	@Test
	void devolverDia() {
		
		//Exercise
		String dia = infraccion.getFecha();

		//verify
		assertEquals(dia,"20/02/2020");
	}
	
	@Test
	void devolverHora() {
		
		//Exercise
		int hora = infraccion.getHora();
		
		//verify
		assertEquals(hora,10);
	}
	
	@Test
	void devolverIdInspector() {
		
		//Exercise
		int ins = infraccion.getIdInspector();
		
		//verify
		assertEquals(ins,0546);		
	}
	
	@Test
	void devolverZonaEstacionamiento() {
		
		//Exercise
		ZonaEstacionamiento est = infraccion.getZonaEstacionamiento();
		
		//verify
		assertEquals(est,estacionamiento);
	}
	
	
}
