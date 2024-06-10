package ar.edu.po2.TpFinal;

public class TicketEst implements Ticket{
	
	private String patente;
	private String fecha;
	private int horaInicio;
	private int idTicket;
	private PuntoVenta puntoVenta;
	private int horaMaxima;
	
	
	public TicketEst(int id,String patente, int horaInicio, int horaMaxima, String fecha, PuntoVenta puntoVenta) {
		this.idTicket = id;
		this.patente = patente;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.puntoVenta = puntoVenta;
		this.horaMaxima = horaMaxima;
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
	public int getHoraMaxima() {
		return horaMaxima;
	}
	
}
