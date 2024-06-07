package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.ArrayList;

public class ZonaEstacionamiento {

	//Instancia de Variables
	private AppInspector inspector;
	private List<String> patentes;
	private List<PuntoVenta> puntosDeVentas;
	
	//Constructores
	public ZonaEstacionamiento(AppInspector inspector) {
		
		this.inspector = inspector;
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
	
	public void agregarPuntoVenta(PuntoVenta puntoVenta) {
		
		this.puntosDeVentas.add(puntoVenta);
	}
	public void quitarPuntoVenta(PuntoVenta puntoVenta) {
		
		this.puntosDeVentas.remove(puntoVenta);
	}
	
	public int getIdInspector() {
		
		return this.inspector.getId();
	}

	public int getPatentesSize() {
		
		return this.patentes.size();
	}

	public int getPuntosDeVentasSize() {
		
		return this.puntosDeVentas.size();
	}
}

