package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.ArrayList;

public class Celular {

	//Instacia de Variables
	private int numero; // Numero de celular que es unico.
	private int hora; 
	private ZonaEstacionamiento zona; // Si no me equivoco un auto ya conoce la zona. 
	private List<String> patentes; 
	private AppUsuario app;
	
	//Constructores
	public Celular(int numero, ZonaEstacionamiento zona) {
		
		this.numero = numero;
		this.zona = zona;
		this.patentes = new ArrayList<String>();
		this.app = null;

	}
	
	//Metodos
	
	/*
	 * Retorna el numero del telefono/celular
	 */
	public int getNumero() {
		
		return this.numero;
	}
	
	// iniciamos al celular sin la app porque para construir la app necesitamos al SEM
	
	public void descargarApp(AppUsuario app) {
		
		this.app = app;
	}
	
	
}
