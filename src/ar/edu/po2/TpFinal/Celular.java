package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.ArrayList;

public class Celular {

	//Variables de Instancia
	private int numero; // Numero de celular que es unico.
	private ZonaEstacionamiento zona; // Si no me equivoco un auto ya conoce la zona. 
	private List<String> patentes; 
	private AppUsuario app;
	
	//Constructores
	public Celular(int numero) {
		
		this.numero = numero;
		this.zona = null;
		this.patentes = new ArrayList<String>();
		this.app = null;

	}
	
	public void obtenerZonaCercana() {
		// se mi ubicacion pero no se si estoy en una zonaEstacionamiento valida
		if(this.appInstalada())
		this.zona = app.obtenerZonaCercana();
	}
	public ZonaEstacionamiento getZona() {
		return this.zona;
	}
	public void descargarApp(AppUsuario app) {
		
		if(this.app == null)
		this.app = app;
	}
	public AppUsuario getApp() {
		return this.app;
	}
	
	public int getNumero() {
		
		return this.numero;
	}
	
	//Metodos
	
	protected void addPatente(String patente) {
		patentes.add(patente);
	}
	
	protected void removePatente(String patente) {
		patentes.remove(patente);
	}
	
	protected int getPatentesSize() {
		return patentes.size();
	}
	
	protected boolean appInstalada() {
		
		return this.app != null;
	}
	
	// solo funciona con una app descargada
	public void setPatentePrincipal(String patente) {
		if(patentes.contains(patente) && this.appInstalada())
		app.setPatentePredeterminada(patente);
	}
	
	public void iniciarEstacionamiento(String patente) {
		
		if(this.appInstalada()) {
			this.obtenerZonaCercana();
			app.inicioDeEstacionamiento(patente);
		}
	}
	
	public void finalizarEstacionamiento() {
		
		if(this.appInstalada())
		app.finDeEstacionamiento();
	}
	
	public void consultarSaldo() {
		
		if(this.appInstalada())
		app.consultarSaldo();
	}
	
	public void cambiarModo() {
		
		if(this.appInstalada())
			app.cambiarModoApp();;
	}
	
	public void encenderSensor() {
		
		if(this.appInstalada())
			app.encenderSensor();
	}
	public void apagarSensor() {
		
		if(this.appInstalada())
			app.apagarSensor();
	}
	public String notificar(String notificacion) {
		
		return notificacion;
	}
	
}
