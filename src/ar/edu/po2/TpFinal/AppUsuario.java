package ar.edu.po2.TpFinal;

public class AppUsuario {

	//Instancia de Variables
	private String patentePredeterminada;
	private SEM sistemaSEM;
	private Modo modoApp;
	private boolean sensorMovimiento;
	private Celular celular;
	private boolean vigente;
	private ZonaEstacionamiento zona;
	
	
	//Constructores
	
	public AppUsuario(String patentePredeterminada, SEM sistemaSEM, Celular celular) {
		super();
		this.patentePredeterminada = patentePredeterminada;
		this.sistemaSEM = sistemaSEM;
		this.modoApp = new ModoManual();
		this.celular = celular;
		this.vigente = false;
		this.sensorMovimiento = false;
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
	
	//Es una bandera para corroborar que automatico no inicie otro estacionamiento
	public boolean getVigente() {
		
		return this.vigente;
	}
	protected void cambiarVigente() {
		this.vigente = !this.vigente;
	}
	
	
	public void cambiarModoApp(){
	    
		modoApp.cambiarModo(this);
	  }  
	protected void setModoApp(Modo modo) {
		
		this.modoApp= modo;
	  }
	public ZonaEstacionamiento getZona() {
		return this.zona;
	}

	//Metodos

	//Deberia retorna un String indicado cada dato que pide el modelo.
	//Registra en el SEM que se inicio el estacionamiento. Ademas de mostrar los datos  
	public void inicioDeEstacionamiento(String patente){
		
		if (!(this.getVigente())) {
	    sistemaSEM.registrarEstacionamientoApp(patente,this);
	    this.cambiarVigente();
	  }
	}
	//Deberia retorna un String indicado cada dato que pide el modelo.
	//Llama al SEM con el metodo removerEstacionamientoDe_(nTelefono). Ademas de mostrar los datos 
	public void finDeEstacionamiento(){
		  
		if(this.getVigente()) {
		  sistemaSEM.removerEstacionamientoDe_(this);
		  this.cambiarVigente();
	  }
	} 
	//llama al SEM con el metodo consultarSaldoDe(numero) y que devuelva un double. Salta excepcion si nunca cargo credito
	public Double consultarSaldo(){
		  
		  return sistemaSEM.consultarSaldoDe_(this.getNTelefono());
	  }
	
	protected boolean sensorPrendido() {
		
		return this.sensorMovimiento;
	}
	
	public void driving() {
		
		if(sensorPrendido())
		this.modoApp.estaManejando();
	}
	
	public void walking() {
		
		if(sensorPrendido())
		this.modoApp.estaCaminando();
	}

	public void encenderSensor() {
		
		this.sensorMovimiento = true;
		
	}
	public void apagarSensor() {
		this.sensorMovimiento = false;
		this.cambiarModoSensorApagado();
	}
	
	//
	protected void cambiarModoSensorApagado() {
		this.modoApp.cambiarModoSensorApagado();
	}

	public void notificar(String notificacion) {
		
		this.celular.notificar(notificacion);
		
	}

	public ZonaEstacionamiento obtenerZonaCercana() {
		
		ZonaEstacionamiento z = this.sistemaSEM.obtenerZonaCercana();
		this.zona = z;
		return z;
	}
}
