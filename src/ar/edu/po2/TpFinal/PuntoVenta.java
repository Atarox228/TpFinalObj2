package ar.edu.po2.TpFinal;

public class PuntoVenta {

	//Intancia de Variables
	private ZonaEstacionamiento zona;
	private SEM sistemaSEM;
	
	//Constructores 
	public PuntoVenta(ZonaEstacionamiento zona, SEM sistema) {
		
		this.sistemaSEM = sistema;
		this.zona = zona;
		zona.agregarPuntoVenta(this);
	}
	
	//Metodos
	
	/*
	 * Recibe una patente y una hora, y llama al SEM con el metodo registrarCompraPuntual.
	 * Ademas crear un tikcet. 
	 */
	public void acreditarEst(String patente, int horas) {
		 
		sistemaSEM.registrarCompraPuntual(patente, horas,this);
		zona.estacionar(patente);
	}
	
	/*
	 * Recibe un monto y un telefono, y llama al Sem con el metodo cargarCredito.
	 * Ademas crear un tikcet.
	 */
	public void cargarSaldo(Double monto, int nTelefono) {
		
		sistemaSEM.cargarCredito(monto, nTelefono);
	}
}
