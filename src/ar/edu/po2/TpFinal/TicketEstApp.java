package ar.edu.po2.TpFinal;

public class TicketEstApp extends TicketEst{

	private int nTelefono;
	private int horaMaxima;
	
	
	public TicketEstApp(int id, String patente, int nTelefono, int horaInicio, String fecha, int saldo) {
			// el sistema ya se fijo que saldo es mayor o igual a 40
		super(id,patente,fecha, horaInicio);
		int horasPosibles = (int) Math.floor(saldo/40); 
		this.nTelefono = nTelefono;
		if (horaInicio + horasPosibles >= 20 ) {
			this.horaMaxima = 20;
		} else {
			this.horaMaxima = horaInicio + horasPosibles;
		}
		
	}	
	
	public int getHoraMaxima() {
		return horaMaxima;
	}
	
	@Override
	public int getNTelefono() {
		return this.nTelefono;
	}

	

	
}
