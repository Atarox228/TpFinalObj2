package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class ZonaEstacionamiento {

	//Instancia de Variables
	private int idInspector;
	private List<String> patentes;
	private List<PuntoVenta> puntosDeVentas;
	
	//Constructores
	public ZonaEstacionamiento(int idInspector) {
		
		this.idInspector = idInspector;
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
		Random random = new Random();
		int indexRandom = random.nextInt(this.getCantPuntoDeVenta());
		PuntoVenta p =  this.puntosDeVentas.get(indexRandom);
		p.finEstacionamiento();
	}
	private int getCantPuntoDeVenta() {
		return this.puntosDeVentas.size();
	}

	public void agregarPuntoVenta(PuntoVenta puntoVenta) {
		
		this.puntosDeVentas.add(puntoVenta);
	}
	public void quitarPuntoVenta(PuntoVenta puntoVenta) {
		
		this.puntosDeVentas.remove(puntoVenta);
	}
	
	public int getIdInspector() {
		
		return this.idInspector;
	}
	public int getPatentesSize() {
		
		return this.patentes.size();
	}
	public int getPuntosDeVentasSize() {
		
		return this.puntosDeVentas.size();
	}

	public boolean tieneEstacionadoA(String patente) {
		return this.patentes.contains(patente);
	}
}

