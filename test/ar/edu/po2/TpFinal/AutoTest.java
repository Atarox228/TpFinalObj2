package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AutoTest {

	@Test
	void unAutoSabeDecirSuPatenteTest() {
		Auto auto =  new Auto("AA-000-AA");
		
		assertEquals(auto.getPatente(),"AA-000-AA");
		
	}

}
