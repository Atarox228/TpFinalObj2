package ar.edu.po2.TpFinal;

public class Credito {

	//Instancia de variables
	private int nTelefono;
	private double credito;
	
	//Constructor
	public Credito(int numTelefono) {
		
		this.nTelefono = numTelefono;
		this.credito = 0d;
	}
	//Metodos

	public int getNTelefono() {
		return nTelefono;
	}

	public double getCredito() {
		return credito;
	}
	// Observacion : el monto recibido por parametro es mayor a 0.
	public void aumentarCredito(double monto) {
		
		this.credito += monto;
		
	}
	// Observacion : el monto recibido por parametro es mayor a 0. Ademas el sistema del SEM jamas dejara 
	// saldo en un numero menor que 0.
	public void decrementarCredito(double monto) {
		
		this.credito -= monto;
	}
}
