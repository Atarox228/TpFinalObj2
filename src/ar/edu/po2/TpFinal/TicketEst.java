package ar.edu.po2.TpFinal;

public abstract class TicketEst implements Ticket{
	
	private String patente;
	private String fecha;
	private int horaInicio;
	private int idTicket;
	
	public TicketEst(int id, String patente, String fecha, int horaInicio) {
		super();
		this.idTicket = id;
		this.patente = patente;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
	}
	public TicketEst() {
	}

	public abstract int getHoraMaxima();
	
	public int getIdTicket() {
		return idTicket;
	}
	public String getFecha() {
		return fecha;
	}
	public  int getNTelefono() {
		return 0;
	}
	public String getPatente() {
		return this.patente;
	}
	public int getHoraInicio() {
		return horaInicio;
	}
		
	
}
