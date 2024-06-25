package ar.edu.po2.TpFinal;

public class RegistroEstApp extends RegistroEst{

	public RegistroEstApp(String patente, int horaInicio, int nTelefono, double saldo) {
		// estacionamiento por app
		super(patente, horaInicio);
		int horasPosibles = (int) Math.floor(saldo/40); 
		this.setNTelefono(nTelefono);
		if (horaInicio + horasPosibles >= 20 ) {
			this.setHoraFinal(20);
		} else {
			this.setHoraFinal(horaInicio + horasPosibles);
		}
	}
}
