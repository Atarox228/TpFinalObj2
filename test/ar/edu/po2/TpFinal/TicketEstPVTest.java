package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class TicketEstPVTest {

	@Test
	void gettersTest() {
		PuntoVenta p = mock(PuntoVenta.class);
		TicketEstPV ticket = new TicketEstPV(1,"AA-000-AA",4,8,"20/02/2020",p);
		
		assertEquals(ticket.getIdTicket(),1);
		assertEquals(ticket.getFecha(),"20/02/2020");
		assertEquals(ticket.getHoraInicio(),8);
		assertEquals(ticket.getPatente(), "AA-000-AA");
		assertEquals(ticket.getHoraMaxima(),12);
	}
	

}
