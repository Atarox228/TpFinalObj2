package ar.edu.po2.TpFinal;

public class TicketEst implements Ticket{
	
	//Varaibles de Instancia
	private int idTicket;
	private String patente;
	private String fecha;
	private int horaInicio;
	private int horaMaxima;
	private PuntoVenta puntoVenta;
	
	//Constructores
	public TicketEst(int id,String patente, String fecha, int horaInicio, int horaMaxima,  PuntoVenta puntoVenta) {
		this.idTicket = id;
		this.patente = patente;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.puntoVenta = puntoVenta;
		this.horaMaxima = horaMaxima;
	}
	
	//Metodos
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
