package ar.edu.po2.TpFinal;


import java.time.LocalDate;

public class Infraccion {
	
	String patente;
	LocalDate fecha;
	int hora;
	Inspector inspector;
	Estacionamiento estacionamiento;
	
	
	public Infraccion(String patente, LocalDate fecha, int hora, Inspector inspector, Estacionamiento estacionamiento) {
		super();
		this.patente = patente;
		this.fecha = fecha;
		this.hora = hora;
		this.inspector = inspector;
		this.estacionamiento = estacionamiento;
	}


	public String getPatente() {
		return patente;
	}


	public LocalDate getFecha() {
		return fecha;
	}


	public int getHora() {
		return hora;
	}


	public Inspector getInspector() {
		return inspector;
	}


	public Estacionamiento getEstacionamiento() {
		return estacionamiento;
	}


	
	
	
	
}
