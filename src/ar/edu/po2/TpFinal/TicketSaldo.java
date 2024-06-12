package ar.edu.po2.TpFinal;

public class TicketSaldo implements Ticket{

	//Variables de Instancia
	private int IdTicket;
	private PuntoVenta puntoDeVenta;
	private String fecha;
	private int hora;
	private int nTelefono;
	private double monto;
	
	//Constructors
	public TicketSaldo(int idTicket, PuntoVenta puntoDeVenta, String fecha, int hora,
			int nTelefono, double monto) {
		super();
		IdTicket = idTicket;
		this.puntoDeVenta = puntoDeVenta;
		this.fecha = fecha;
		this.hora = hora;
		this.nTelefono = nTelefono;
		this.monto = monto;
	}

	//Metodos
	public int getIdTicket() {
		return IdTicket;
	}

	public String getFecha() {
		return fecha;
	}
	
	
	
}
