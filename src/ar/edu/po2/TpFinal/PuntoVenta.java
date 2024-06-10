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
	 * Ademas crear un tikcet. lo devuelve para hacer la corroboracion en el test, cuando se llama no se pide el debe de guardar en una variable 
	 */
	public TicketEst acreditarEst(String patente, int cantHoras) {
		 // no manejo concurrencia asi que si dos entidades piden el contador a la vez esto se rompre 
		// ya que gerena dos tickes con el mismo id
		int contador = sistemaSEM.getContadorIdTickets();
		int hora = sistemaSEM.getHora();
		String fecha = sistemaSEM.getFecha();
		TicketEst ticket = new TicketEst(contador ,patente, hora,cantHoras,fecha,this);
		sistemaSEM.addTicket(ticket);
		sistemaSEM.addEstacionamientoPV(patente,cantHoras);
		zona.estacionar(patente);
		return ticket;
	}
	
	/*
	 * Recibe un monto y un telefono, y llama al Sem con el metodo cargarCredito.
	 * Ademas crear un tikcet.
	 */
	public TicketSaldo cargarSaldo(Double monto, int nTelefono) {
         // no manejo concurrencia asi que si dos entidades piden el contador a la vez esto se rompre 
		// ya que gerena dos tickes con el mismo id
		int contador = sistemaSEM.getContadorIdTickets();
		int hora = sistemaSEM.getHora();
		String fecha = sistemaSEM.getFecha();
		TicketSaldo ticket = new TicketSaldo(contador,this,fecha,hora, nTelefono,monto);
		sistemaSEM.addTicket(ticket);
		sistemaSEM.cargarCredito(monto, nTelefono);
		return ticket;
	}
}
