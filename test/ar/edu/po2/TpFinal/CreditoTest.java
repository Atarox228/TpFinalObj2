package ar.edu.po2.TpFinal;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreditoTest {

	private Credito credito;
	
	@BeforeEach
	void setUp() {
		credito = new Credito(41770166);
	}
	
	@Test
	void getterCreditoTest() {
		
		assertEquals(credito.getNTelefono(),41770166);
		assertEquals(credito.getCredito(),0d);
	}
	@Test
	void aumentarCreditoTest() {
		
		credito.aumentarCredito(40d);
		
		assertEquals(credito.getCredito(),40d);
	}
	@Test
	void decremenarCreditoTest() {
		
		credito.aumentarCredito(80d);
		credito.decrementarCredito(40d);
		
		assertEquals(credito.getCredito(),40d);
	}
}
