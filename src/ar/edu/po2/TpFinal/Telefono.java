package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.ArrayList;

public class Telefono {

	//Instacia de Variables
	private int numero; // Numero de celular que es unico.
	private int hora; 
	private ZonaEstacionamiento zona; // Si no me equivoco un auto ya conoce la zona. 
	private List<String> patentes; 
	private AppUsuario app;
	
	//Constructores
	public Telefono(int numero, ZonaEstacionamiento zona) {
		
		this.numero = numero;
		this.zona = zona;
		this.patentes = new ArrayList<String>();
		this.app = new AppUsuario(); // Se le podria pasar una patente no?
	
	}
	
	//Metodos
	
	/*
	 * Retorna el numero del telefono/celular
	 */
	public int getNumero() {
		
		return this.numero;
	}
	
	
}
