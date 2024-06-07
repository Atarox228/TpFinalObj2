package tpfinal.copy;

public class TicketEstApp {

	private String patente;
	private int telefono;
	private int horaInicio;
	private int horaMaxima;
	
	
	public TicketEstApp(String patente, int telefono, int horaInicio, int saldo) {
			int horasPosibles = (int) Math.floor(saldo/40); 
			this.patente = patente;
			this.telefono = telefono;
			this.horaInicio = horaInicio;
			if (horaInicio + horasPosibles >= 20 ) {
				this.horaMaxima = 20;
			} else {
				this.horaMaxima = horaInicio + horasPosibles;
			}
		
	}	
	
	public int getTelefono() {
		return this.telefono;
	}
	
	
	
	
}
