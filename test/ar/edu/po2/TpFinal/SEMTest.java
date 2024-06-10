package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
		assertEquals(SEM.getCantRegistroDeEst(),0);
		assertEquals(SEM.getCantZonasEst(),0);
		assertEquals(SEM.getCantInfracciones(),0);
		assertEquals(SEM.getCantCreditos(),0);
		assertEquals(SEM.getFecha(),"20/02/2020");
	}

	@Test
	void cambiarHoraTest() {
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
		RegistroEst t1 = mock(RegistroEst.class);
		RegistroEst t2 = mock(RegistroEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(true);
		when(t2.getHoraFinal()).thenReturn(10);
		
		SEM.addRegistroDeEst(t1);
		SEM.addRegistroDeEst(t2);
		SEM.addZonaEstacionamiento(z1);
		SEM.addZonaEstacionamiento(z2);
		SEM.setHora(17);
		
		assertTrue(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
	
	@Test
	void infraccionFalseTest() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		ZonaEstacionamiento z2 = mock(ZonaEstacionamiento.class);
		RegistroEst t1 = mock(RegistroEst.class);
		RegistroEst t2 = mock(RegistroEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(true);
		when(t2.getHoraFinal()).thenReturn(11);
		
		SEM.addRegistroDeEst(t1);
		SEM.addRegistroDeEst(t2);
		SEM.addZonaEstacionamiento(z1);
		SEM.addZonaEstacionamiento(z2);
		SEM.setHora(10);
		
		assertFalse(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
	
	@Test
	void infraccionFalse2Test() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		ZonaEstacionamiento z2 = mock(ZonaEstacionamiento.class);
		RegistroEst t1 = mock(RegistroEst.class);
		RegistroEst t2 = mock(RegistroEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		// cambia que ahora no esta estacionado
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(false);
		when(t2.getHoraFinal()).thenReturn(11);
		
		SEM.addRegistroDeEst(t1);
		SEM.addRegistroDeEst(t2);
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
	void cuandoSeAgregaUnRegistroCamabiaElContadorTest(){
		Ticket t1 = mock(Ticket.class);
		int contadorAnterior = SEM.getContadorIdTickets();
		int cantidadTickesAnterior = SEM.getCantTickets();
	
		SEM.addTicket(t1);
		assertEquals(SEM.getCantTickets(),cantidadTickesAnterior+1);
		assertEquals(SEM.getContadorIdTickets(), contadorAnterior+1);
	}
	
	@Test
	void agregarCompraAppSinSaldoTest() {
		AppUsuario app = mock(AppUsuario.class);
		when(app.getNTelefono()).thenReturn(23047067);
		int RegistroDeEstAnteriores = SEM.getCantRegistroDeEst();
		
		SEM.registrarEstacionamientoApp("AA-000-AA",app);
		assertEquals(SEM.getCantRegistroDeEst(), RegistroDeEstAnteriores);
		verify(app, times(1)).notificar("Saldo insuficiente. Estacionamiento no permitido");

	}
	
	@Test
	void agregarCompraAppConSaldo0Test() {
		AppUsuario app = mock(AppUsuario.class);
		Credito credito = mock(Credito.class);
		when(app.getNTelefono()).thenReturn(23047067);
		when(credito.getNTelefono()).thenReturn(23047067);
		when(credito.getCredito()).thenReturn(0d);
		int RegistroDeEstAnteriores = SEM.getCantRegistroDeEst();
		
		SEM.registrarEstacionamientoApp("AA-000-AA",app);
		assertEquals(SEM.getCantRegistroDeEst(), RegistroDeEstAnteriores);
		verify(app, times(1)).notificar("Saldo insuficiente. Estacionamiento no permitido");

	}
	
	@Test
	void agregarCompraAppConSaldoSuficienteTest() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		AppUsuario app = mock(AppUsuario.class);
		Credito credito = mock(Credito.class);
		when(app.getNTelefono()).thenReturn(23047067);
		when(app.getZona()).thenReturn(z1);
		when(credito.getNTelefono()).thenReturn(23047067);
		when(credito.getCredito()).thenReturn(40d);
		int registroDeEstAnteriores = SEM.getCantRegistroDeEst();
		
		SEM.addCredito(credito);
		SEM.registrarEstacionamientoApp("AA-000-AA",app);
		assertEquals(SEM.getCantRegistroDeEst(), registroDeEstAnteriores+1);
		verify(app,times(1)).notificar("Hora de inicio: 7, Hora de final: 8");

	}
	
	@Test
	void cargarSaldoDeTelefonoNuevoTest() {
		int creditosAnteriores = SEM.getCantCreditos() ;
		SEM.cargarCredito(45, 23047067);

		assertEquals(SEM.getCantCreditos(),creditosAnteriores + 1);
	}
	
	@Test
	void cargarSaldoDeTelefonoExistenteTest() {
		Credito c = mock(Credito.class);
		when(c.getNTelefono()).thenReturn(23047067);
		SEM.addCredito(c);
		
		SEM.cargarCredito(45, 23047067);
		verify(c, times(1)).aumentarCredito(45);
	}
	
	@Test
	void eliminarRegistrosTest() {
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		AppUsuario app = mock(AppUsuario.class);
		RegistroEst r1 = mock(RegistroEst.class);
		RegistroEst r2 = mock(RegistroEst.class);
		Credito c = mock(Credito.class);
		when(r1.getNTelefono()).thenReturn(23047067);
		when(r2.getNTelefono()).thenReturn(40047067);
		when(r2.getHoraInicio()).thenReturn(8);
		when(app.getNTelefono()).thenReturn(40047067);
		when(c.getNTelefono()).thenReturn(40047067);
		when(app.getZona()).thenReturn(z1);
		SEM.addRegistroDeEst(r1);
		SEM.addRegistroDeEst(r2);
		SEM.addCredito(c);
		int cantRegistrosAterior = SEM.getCantRegistroDeEst();		
		SEM.removerEstacionamientoDe_(app);
		assertEquals(SEM.getCantRegistroDeEst(), cantRegistrosAterior-1);
		
	}
	
	@Test
	void notificarEliminacion(){
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		SEM.setHora(10);
		String notificacion = "Hora de Inicio: 8 Hora de Fin: 10 Cantidad Horas Estacionado: 2 Costo: 80.0";
		AppUsuario app = mock(AppUsuario.class);
		RegistroEst r1 = mock(RegistroEst.class);
		RegistroEst r2 = mock(RegistroEst.class);
		Credito c = mock(Credito.class);
		when(r1.getNTelefono()).thenReturn(23047067);
		when(r2.getNTelefono()).thenReturn(40047067);
		when(r2.getHoraInicio()).thenReturn(8);
		when(app.getNTelefono()).thenReturn(40047067);
		when(c.getNTelefono()).thenReturn(40047067);
		when(app.getZona()).thenReturn(z1);
		SEM.addRegistroDeEst(r1);
		SEM.addRegistroDeEst(r2);
		SEM.addCredito(c);
		SEM.removerEstacionamientoDe_(app);
		verify(c,times(1)).decrementarCredito(80d);
		verify(app,times(1)).notificar(notificacion);
		
	}
	
	@Test
	void cuandoFinalizaElHorarioVigenteSeEliminanLosRegistros() {
		RegistroEst r1 = mock(RegistroEst.class);
		SEM.addRegistroDeEst(r1);
		SEM.setHora(20);
		SEM.finalizazHorarioVigente();
		
		assertEquals(SEM.getCantRegistroDeEst(),0);
	}
	
	@Test
	void noSeFinalizaSiNoEsHorario() {
		RegistroEst r1 = mock(RegistroEst.class);
		SEM.addRegistroDeEst(r1);
		SEM.finalizazHorarioVigente();
		
		assertEquals(SEM.getCantRegistroDeEst(),1);
	}
	
	@Test
	void noSeEstaEnInfraccionSiNoEsHorarioVigente(){
		ZonaEstacionamiento z1 = mock(ZonaEstacionamiento.class);
		ZonaEstacionamiento z2 = mock(ZonaEstacionamiento.class);
		RegistroEst t1 = mock(RegistroEst.class);
		RegistroEst t2 = mock(RegistroEst.class);
		when(z1.getIdInspector()).thenReturn(6548);
		when(z2.getIdInspector()).thenReturn(2546);	
		when(t1.getPatente()).thenReturn("AA-000-AB");
		when(t2.getPatente()).thenReturn("AA-000-AA");
		when(z2.tieneEstacionadoA("AA-000-AA")).thenReturn(true);
		when(t2.getHoraFinal()).thenReturn(10);
		
		SEM.addRegistroDeEst(t1);
		SEM.addRegistroDeEst(t2);
		SEM.addZonaEstacionamiento(z1);
		SEM.addZonaEstacionamiento(z2);
		SEM.setHora(20);
		
		assertFalse(SEM.estaEnInfraccion("AA-000-AA", 2546));
	}
}
