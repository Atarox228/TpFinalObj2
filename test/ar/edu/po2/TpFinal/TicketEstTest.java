package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketEstTest {

	private TicketEst t1;

	
	@BeforeEach
	void setUp() {
		PuntoVenta p = mock(PuntoVenta.class);
		t1 = new TicketEstPV("AA-000-AA",4,8,"20/02/2020",p);
		
	}
	
	@Test
	void comportaminetoGeneralTest() {
		assertEquals(t1.getHoraMaxima(),12);
		assertEquals(t1.getPatente(),"AA-000-AA");
		assertEquals(t1.getFecha(),"20/02/2020");
		assertEquals(t1.getNTelefono(),0);
		assertEquals(t1.getHoraInicio(),8);
	}
	
	@Test
	void polimorfismoTest(){
		t1 = new TicketEstApp("AA-000-BA",23047067,8,"20/02/2020",40);
		assertEquals(t1.getHoraMaxima(),9);
		assertEquals(t1.getPatente(),"AA-000-BA");
		assertEquals(t1.getFecha(),"20/02/2020");
		assertEquals(t1.getNTelefono(),23047067);
		assertEquals(t1.getHoraInicio(),8);
	}

}
