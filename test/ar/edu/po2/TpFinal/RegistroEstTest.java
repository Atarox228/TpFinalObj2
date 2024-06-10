package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegistroEstTest {
	
	@Test
	void registroPuntoVentaTest() {
		RegistroEst registro = new RegistroEst("AA-000-AA",8,8);
		
		assertEquals(registro.getPatente(),"AA-000-AA");
		assertEquals(registro.getHoraInicio(),8);
		assertEquals(registro.getHoraFinal(),16);
		assertEquals(registro.getNTelefono(),0);
	}
	
	@Test
	void registrarAppTest() {
		RegistroEst registro = new RegistroEst("AA-000-AA",8,23047067,41d);
		
		assertEquals(registro.getPatente(),"AA-000-AA");
		assertEquals(registro.getNTelefono(),23047067);
	}
	
	@Test
	void registrarAppHoraFinalTest() {
		RegistroEst registro = new RegistroEst("AA-000-AA",8,23047067,40d);
		RegistroEst registro2 = new RegistroEst("AA-000-AA",8,23047067,41d);
		RegistroEst registro3 = new RegistroEst("AA-000-AA",8,23047067,79d);
		RegistroEst registro4 = new RegistroEst("AA-000-AA",15,23047067,80d);
		RegistroEst registro5 = new RegistroEst("AA-000-AA",8,23047067,4160d);
		
		
		assertEquals(registro.getHoraFinal(),9);
		assertEquals(registro2.getHoraFinal(),9);
		assertEquals(registro3.getHoraFinal(),9);
		assertEquals(registro4.getHoraFinal(),17);
		assertEquals(registro5.getHoraFinal(),20);
	}

}
