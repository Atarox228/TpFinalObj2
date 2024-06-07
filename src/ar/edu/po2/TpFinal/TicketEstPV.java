package ar.edu.po2.TpFinal;

public class TicketEstPV extends TicketEst {

	private int horasTotales;
	private PuntoVenta puntoVenta;
	
	public TicketEstPV(int id,String patente,  int horasTotales, int horaInicio, String fecha, PuntoVenta puntoVenta) {
		super(id,patente, fecha, horaInicio);
		this.horasTotales = horasTotales;
		this.puntoVenta = puntoVenta;
	}
	
	public int getHoraMaxima() {
		return horasTotales + this.getHoraInicio();
	}

}
