package ar.edu.po2.TpFinal;

public class TicketEst implements Ticket{
	
	private String patente;
	private String fecha;
	private int horaInicio;
	private int idTicket;
	private PuntoVenta puntoVenta;
	
	
	public TicketEst(int id,String patente, int horaInicio, String fecha, PuntoVenta puntoVenta) {
		this.idTicket = id;
		this.patente = patente;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.puntoVenta = puntoVenta;
	}
	
	public int getIdTicket() {
		return idTicket;
	}
	public String getFecha() {
		return fecha;
	}
	public String getPatente() {
		return this.patente;
	}
	public int getHoraInicio() {
		return horaInicio;
	}
	public PuntoVenta getPuntoVenta() {
		return this.puntoVenta;
	}
	
}
