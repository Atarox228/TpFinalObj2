package tpfinal.copy;

public class TicketEst implements Ticket{

	private String patente;
	private String Fecha;
	private int horaInicio;
	private int HorasTotales;
	private PuntoDeVenta puntoVenta;
	
	public TicketEst(String patente,  int horasTotales, int horaInicio, String fecha, PuntoDeVenta puntoVenta) {
		super();
		this.patente = patente;
		Fecha = fecha;
		this.horaInicio = horaInicio;
		HorasTotales = horasTotales;
		this.puntoVenta = puntoVenta;
	}
	
	public int getTelefono() {
		return 0;
	}
	
}
