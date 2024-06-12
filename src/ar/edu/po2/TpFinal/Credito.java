package ar.edu.po2.TpFinal;

public class Credito {

	//Variables de Instancia
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
	// Observacion : el monto recibido por parametro es mayor a 0.
	public void decrementarCredito(double monto) {
		double montoARemover = Math.min(monto, this.credito);
		this.credito -= montoARemover;
	}
}
