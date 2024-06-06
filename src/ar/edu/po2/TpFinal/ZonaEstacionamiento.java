package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.ArrayList;

public class ZonaEstacionamiento {

	//Instancia de Variables
	private boolean vigente; // Hay que aclarar para que sirve este bool.
	private int puntoGeografico; // El punto donde se localiza el estacionamiento
	private List<String> patentes;
	private List<PuntoVenta> puntosDeVentas;
	
	//Constructores
	public ZonaEstacionamiento() {
		
		this.vigente = false;
		// Aca habria que agregar el punto de la zona.
		this.patentes = new ArrayList<String>();
		this.puntosDeVentas = new ArrayList<PuntoVenta>();
		
	}
	
	//Metodos
	
	/*
	 * Agrega la patente pasada por parametro a la lista de patentes.
	 * Este metodo guarda aquellos 'autos' que se estacionan.
	 */
	public void estacionar(String patente) {
		
		this.patentes.add(patente);
	}
	
	/*
	 * Remueve de la lista patente, la patente pasada por parametro en caso de que exista.
	 * Es decir, cuando un auto deja la zona de estacionamiento se llama este metodo.
	 */
	public void desEstacionar(String patente) {
		
		this.patentes.remove(patente);
	}
	
	/*
	 * En algun punto de venta de la zona de estacionamiento, se acredita el estacionamiento de un
	 * auto, pasando por parametro la patente y la hora.
	 */
	public void acreditarEst(String patente, int horas) {
		
		// Me parece que este metodo esta de mas ya que podria ser cualquier punto de venta, y ademas
		// se deberia distinguir entre acreditar y cargar.
		
	}
}

