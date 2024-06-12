package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


import org.junit.jupiter.api.Test;

class TicketEstTest {

	private TicketEst t1;
	
	@Test
	void comportaminetoGeneralTest() {
		PuntoVenta p = mock(PuntoVenta.class);
		t1 = new TicketEst(1,"AA-000-AA","20/02/2020",8,2,p);
		assertEquals(t1.getIdTicket(),1);;
		assertEquals(t1.getPatente(),"AA-000-AA");
		assertEquals(t1.getFecha(),"20/02/2020");
		assertEquals(t1.getHoraInicio(),8);
		assertEquals(t1.getHoraMaxima(),2);
		
	}
	
	

}
