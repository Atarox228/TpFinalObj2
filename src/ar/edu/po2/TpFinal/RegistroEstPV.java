package ar.edu.po2.TpFinal;

public class RegistroEstPV extends RegistroEst{

	public RegistroEstPV(String patente, int horaInicio, int horas) {
		// estacionamiento por compra puntual
		super(patente, horaInicio);
		this.setNTelefono(0);
		this.setHoraFinal(horaInicio + horas);
	}
}
