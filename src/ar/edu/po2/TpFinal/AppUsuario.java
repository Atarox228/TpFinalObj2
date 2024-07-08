package ar.edu.po2.TpFinal;

import java.util.Random;

public class AppUsuario {

	//Variables de Instancia
	private String patentePredeterminada;
	private SEM sistemaSEM;
	private Modo modoApp;
	private SensorMovimiento sensorMovimiento;
	private Celular celular;
	private boolean vigente; //Es una bandera para corroborar que automatico no inicie otro estacionamiento
	private ZonaEstacionamiento zona;
	private int puntoGeograficoActual; // Un punto greogracico de cualquier zona, representado con un simple entero.
	
	
	//Constructores
	
	public AppUsuario(String patentePredeterminada, SEM sistemaSEM, Celular celular) {
		super();
		this.patentePredeterminada = patentePredeterminada;
		this.sistemaSEM = sistemaSEM;
		this.modoApp = new ModoManual(this);
		this.celular = celular;
		this.vigente = false;
		this.sensorMovimiento = new Apagado();
		this.puntoGeograficoActual = 0; // por defecto arranca en cero.
	
	}
	
	//Getters Setters
	
	public String getPatentePredeterminada() {
		return patentePredeterminada;
	}
	public void setPatentePredeterminada(String patente) {
		this.patentePredeterminada = patente;
	}
	
	protected int getNTelefono() {
		return celular.getNumero();
	}
	
	public boolean getVigente() {
		
		return this.vigente;
	}
	protected void cambiarVigente() {
		this.vigente = !this.vigente;
	}
	public int getPuntoGeograficoActual() {
		
		return this.puntoGeograficoActual;
	}
	
	public void cambiarModoApp(){
	    
		modoApp.cambiarModo(this);
	  }  
	protected void setModoApp(Modo modo) {
		
		this.modoApp= modo;
	}
	public void setSensorMovimiento(SensorMovimiento sensor) {
		
		this.sensorMovimiento = sensor;
		
	}
	public ZonaEstacionamiento getZona() {
		return this.zona;
	}

	//Metodos 
	
	public void inicioDeEstacionamiento(String patente){
		modoApp.inicioDeEstacionamiento(patente);
	}
	
	public void finDeEstacionamiento(){
		modoApp.finDeEstacionamiento();
	}
	
	protected void inicioDeEstacionamientoApp(String patente){
		
		if (!(this.getVigente())) {
	    sistemaSEM.registrarEstacionamientoApp(patente,this);
	    this.cambiarVigente();
	  }
	}
	protected void finDeEstacionamientoApp(){
		  
		if(this.getVigente()) {
		  sistemaSEM.removerEstacionamientoDe_(this);
		  this.cambiarVigente();
	  }
	} 
	public Double consultarSaldo(){
		  
		  return sistemaSEM.consultarSaldoDe_(this.getNTelefono());
	  }

	public void driving() {
		
		this.sensorMovimiento.estaManejando(this);
	}
	
	public void walking() {
		
		this.sensorMovimiento.estaCaminando(this);
	}

	public void encenderSensor() {
		
		this.sensorMovimiento.encender(this);
		
	}
	public void apagarSensor() {
		this.sensorMovimiento.apagarSensor(this);
	}

	public void notificar(String notificacion) {
		
		this.celular.notificar(notificacion);
		
	}

	public ZonaEstacionamiento obtenerZonaCercana() {
		
		ZonaEstacionamiento z = this.sistemaSEM.obtenerZonaCercana();
		this.zona = z;
		return z;
	}

	public void notificarSensorApagado() {
		
		this.modoApp.notificarSensorApagado(this);
		
	}
	public void obtenerPuntoGeografico() {
		
		// Retorna un numero random, dando supuestamente la ubicacion
		Random random = new Random();
		int puntoRandom = random.nextInt(100); 
		this.puntoGeograficoActual = puntoRandom;
		
	}
	public boolean estaEnLaZona() {
		
		this.obtenerZonaCercana();
		int rangoZona = this.zona.getPuntoGeografico();
		return this.puntoGeograficoActual > 0 && this.puntoGeograficoActual < rangoZona; 
	}
}
