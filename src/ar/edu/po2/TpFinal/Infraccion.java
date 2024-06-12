package ar.edu.po2.TpFinal;



public class Infraccion {

	//Variables de Instancia
	
	String patente;
	String fecha;
	int hora;
	int idInspector;
	ZonaEstacionamiento estacionamiento;
	
	
	//Constructores
	
	public Infraccion(String patente, String fecha, int hora, int idInspector, ZonaEstacionamiento estacionamiento) {
		super();
		this.patente = patente;
		this.fecha = fecha;
		this.hora = hora;
		this.idInspector = idInspector;
		this.estacionamiento = estacionamiento;
	}

	//Metodos
	
	public String getPatente() {
		return patente;
	}


	public String getFecha() {
		return fecha;
	}


	public int getHora() {
		return hora;
	}


	public int getIdInspector() {
		return idInspector;
	}


	public ZonaEstacionamiento getZonaEstacionamiento() {
		return estacionamiento;
	}


	
	
	
	
}
