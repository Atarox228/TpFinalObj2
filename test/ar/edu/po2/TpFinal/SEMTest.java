package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SEMTest {

	private SEM SEM;

	@BeforeEach
	void setUp() {
		this.SEM = new SEM("20/02/2020");
	}
	
	@Test
	void creacionTest() {
		assertEquals(SEM.getHora(),7);
		assertEquals(SEM.getContadorIdTickets(),0);
		assertEquals(SEM.getPrecioEstacionamiento(),40d);
		assertEquals(SEM.getCantTickets(),0);
		assertEquals(SEM.getCantTicketsEst(),0);
		assertEquals(SEM.getCantZonasEst(),0);
		assertEquals(SEM.getCantInfracciones(),0);
		assertEquals(SEM.getCantCreditos(),0);
		assertEquals(SEM.getFecha(),"20/02/2020");
	}

	@Test
	void cambniarHoraTest() {
		SEM.setHora(9);
		assertEquals(SEM.getHora(),9);
	}
	
	@Test
	void CambiarFechaTest(){
		SEM.setFecha("21/02/2020");
		assertEquals(SEM.getFecha(),"21/02/2020");
	}
	
	@Test
	void infraccionTrueTest() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		ZonaEstacionamiento z2 = mock(ZonaEstacionamiento.class);
		TicketEst t1 = mock(TicketEst.class);
		TicketEst t2 = mock(TicketEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(true);
		when(t2.getHoraMaxima()).thenReturn(10);
		
		SEM.addTicketDeEstacionamiento(t1);
		SEM.addTicketDeEstacionamiento(t2);
		SEM.addZonaEstacionamiento(z1);
		SEM.addZonaEstacionamiento(z2);
		SEM.setHora(17);
		
		assertTrue(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
	
	@Test
	void infraccionFalseTest() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		ZonaEstacionamiento z2 = mock(ZonaEstacionamiento.class);
		TicketEst t1 = mock(TicketEst.class);
		TicketEst t2 = mock(TicketEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(true);
		when(t2.getHoraMaxima()).thenReturn(11);
		
		SEM.addTicketDeEstacionamiento(t1);
		SEM.addTicketDeEstacionamiento(t2);
		SEM.addZonaEstacionamiento(z1);
		SEM.addZonaEstacionamiento(z2);
		SEM.setHora(10);
		
		assertFalse(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
	
	@Test
	void infraccionFalse2Test() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		ZonaEstacionamiento z2 = mock(ZonaEstacionamiento.class);
		TicketEst t1 = mock(TicketEst.class);
		TicketEst t2 = mock(TicketEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		// cambia que ahora no esta estacionado
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(false);
		when(t2.getHoraMaxima()).thenReturn(11);
		
		SEM.addTicketDeEstacionamiento(t1);
		SEM.addTicketDeEstacionamiento(t2);
		SEM.addZonaEstacionamiento(z1);
		SEM.addZonaEstacionamiento(z2);
		SEM.setHora(19);
		
		assertFalse(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
	
	@Test
	void infraccionDePatenteNoExistenteTest() {
		SEM.setHora(19);
		
		assertFalse(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
	
	@Test
	void infraccionTest() {
		int infraccionesPasadas = SEM.getCantInfracciones();
		SEM.altaDeInfraccion("AA-000-AA", 2546);
		
		assertEquals(SEM.getCantInfracciones(),infraccionesPasadas + 1);
	}
	
	@Test
	void agregarCompraPuntualTest() {
		
		PuntoVenta pv = mock(PuntoVenta.class);
		int ticketEstAnteriores = SEM.getCantTicketsEst();
		int ticketsAnteriores = SEM.getCantTickets();
		int contadorAnterior = SEM.getContadorIdTickets();
		
		SEM.registrarCompraPuntual("AA-000-AA", 4,pv);
		assertEquals(SEM.getCantTicketsEst(), ticketEstAnteriores + 1);
		assertEquals(SEM.getCantTickets(), ticketsAnteriores + 1);
		assertEquals(SEM.getContadorIdTickets(), contadorAnterior + 1);
	}
	
	@Test
	void agregarCompraAppSinSaldoTest() {
		AppUsuario app = mock(AppUsuario.class);
		when(app.getNTelefono()).thenReturn(23047067);
		int ticketEstAnteriores = SEM.getCantTicketsEst();
		int ticketsAnteriores = SEM.getCantTickets();
		int contadorAnterior = SEM.getContadorIdTickets();
		
		SEM.registrarEstacionamientoApp("AA-000-AA",app);
		assertEquals(SEM.getCantTicketsEst(), ticketEstAnteriores);
		assertEquals(SEM.getCantTickets(), ticketsAnteriores);
		assertEquals(SEM.getContadorIdTickets(), contadorAnterior);
		
	}
}
