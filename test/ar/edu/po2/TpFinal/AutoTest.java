package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutoTest {
	
	//setup
	Auto auto =  new Auto("AA-000-AA");
	
	@Test
	void unAutoSabeDecirSuPatenteTest() {
		
		//exercise
		String patente = auto.getPatente();
		//verify
		assertEquals(patente,"AA-000-AA");
		
	}

}
