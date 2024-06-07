package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketSaldoTest {

	private TicketSaldo ticket;
	private PuntoVenta punto;
	
	@BeforeEach
	void setUp() {
		punto = mock(PuntoVenta.class);
		ticket = new TicketSaldo(1,punto,"02/02/2020",8,41770166, 45d);
	}
	@Test
	void getterTickeTest() {
		
		assertEquals(ticket.getFecha(),"02/02/2020");
		assertEquals(ticket.getIdTicket(),1);
	}

}
