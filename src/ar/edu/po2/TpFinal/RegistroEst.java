package ar.edu.po2.TpFinal;

public abstract class RegistroEst {
	
	//Variables de Instancia
	private String patente;
	private int horaInicio;
	private int horaFinal;
	private int nTelefono;
	
	//Constructores
	public RegistroEst() {
		//Constructor para heredar.
	}
	public RegistroEst(String patente, int horaInicio) {
		super();
		this.patente = patente;
		this.horaInicio = horaInicio;
	}

	//Getters y Setters
	
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
	public void setHoraFinal(int hora) {
		
		this.horaFinal = hora;
	}
	public void setNTelefono(int nTelefono) {
		
		this.nTelefono = nTelefono;
	}
	
	
	
}
