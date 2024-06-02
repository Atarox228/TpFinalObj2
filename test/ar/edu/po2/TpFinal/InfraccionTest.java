package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InfraccionTest {
	
	//setUp
	Infraccion infraccion;
	LocalDate hoy = LocalDate.now();
	Inspector inspector;
	Estacionamiento estacionamiento;
	
	
	@BeforeEach
	void setUp() {
		inspector = new Inspector("Raul");
		estacionamiento = new Estacionamiento("Lomas");
		infraccion = new Infraccion("AA-000-AA",hoy,10,inspector,estacionamiento);
		
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
		LocalDate dia = infraccion.getFecha();

		//verify
		assertEquals(dia,hoy);
	}
	
	@Test
	void devolverHora() {
		
		//Exercise
		int hora = infraccion.getHora();
		
		//verify
		assertEquals(hora,10);
	}
	
	@Test
	void devolverInspector() {
		
		//Exercise
		Inspector ins = infraccion.getInspector();
		
		//verify
		assertEquals(ins,inspector);		
	}
	
	@Test
	void devolverEstacionamiento() {
		
		//Exercise
		Estacionamiento est = infraccion.getEstacionamiento();
		
		//verify
		assertEquals(est,estacionamiento);
	}
	
	
}
