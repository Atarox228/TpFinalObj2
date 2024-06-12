package ar.edu.po2.TpFinal;

public class RegistroEst {
	
	//Variables de Instancia
	private String patente;
	private int horaInicio;
	private int horaFinal;
	private int nTelefono;
	
	//Constructores
	public RegistroEst(String patente, int horaInicio, int horas) {
		// estacionamiento por compra puntual
		super();
		this.patente = patente;
		this.horaInicio = horaInicio;
		this.horaFinal = horaInicio + horas;
		this.nTelefono = 0;
	}

	public RegistroEst(String patente, int horaInicio, int nTelefono, double saldo) {
		// estacionamiento por app
		super();
		int horasPosibles = (int) Math.floor(saldo/40); 
		this.nTelefono = nTelefono;
		this.patente = patente;
		this.horaInicio = horaInicio;
		if (horaInicio + horasPosibles >= 20 ) {
			this.horaFinal = 20;
		} else {
			this.horaFinal = horaInicio + horasPosibles;
		}
	}

	//Metodos
	public String getPatente() {
		return patente;
	}
	public int getHoraFinal() {
		return horaFinal;
	}
	public int getNTelefono() {
		return nTelefono;
	}
	public int getHoraInicio() {
		return horaInicio;
	}
	
	
	
	
}
