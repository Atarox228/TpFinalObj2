package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicketEstAppTest {


	
	@Test
	void gettersNoVariablestest() {
		TicketEstApp ticket = new TicketEstApp("AA-000-AA",23047067,8,"20/02/2020",40);
		assertEquals(ticket.getPatente(),"AA-000-AA");
		assertEquals(ticket.getNTelefono(), 23047067);
		assertEquals(ticket.getHoraInicio(), 8);
		assertEquals(ticket.getFecha(),"20/02/2020");
		
	}
	
	@Test
	void gettersVariablesTest() {
		TicketEstApp ticket1 = new TicketEstApp("AA-000-AA",23047067,8,"20/02/2020",40);
		TicketEstApp ticket2 = new TicketEstApp("AA-000-AA",23047067,8,"20/02/2020",41);
		TicketEstApp ticket3 = new TicketEstApp("AA-000-AA",23047067,8,"20/02/2020",79);
		TicketEstApp ticket4 = new TicketEstApp("AA-000-AA",23047067,16,"20/02/2020",240);
		TicketEstApp ticket5 = new TicketEstApp("AA-000-AA",23047067,16,"20/02/2020",80);
		
		assertEquals(ticket1.getHoraMaxima(),9);
		assertEquals(ticket2.getHoraMaxima(),9);
		assertEquals(ticket3.getHoraMaxima(),9);
		assertEquals(ticket4.getHoraMaxima(),20);
		assertEquals(ticket5.getHoraMaxima(),18);
		
	}

}
