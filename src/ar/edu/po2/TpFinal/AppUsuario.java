package ar.edu.po2.TpFinal;

public class AppUsuario {

	//Instancia de Variables
	private String patentePredeterminada;
	private SEM sistemaSEM;
	private Modo modoApp;
	private boolean modoDesplazamiento;
	private Celular celular;
	private boolean vigente;
	
	
	//Constructores
	
	public AppUsuario(String patentePredeterminada, SEM sistemaSEM, Celular celular) {
		super();
		this.patentePredeterminada = patentePredeterminada;
		this.sistemaSEM = sistemaSEM;
		this.modoApp = new ModoManual(this);
		this.modoDesplazamiento = false;
		this.celular = celular;
		this.vigente = false;
	}
	
	
	//Getters Setters
	
	public String getPatentePredeterminada() {
		return patentePredeterminada;
	}
	
	public void setPatentePredeterminada(String patente) {
		this.patentePredeterminada = patente;
	}

	private int getNTelefono() {
		return celular.getNumero();
	}
	
	//Es una bandera 
	public boolean getVigente() {
		
		return this.vigente;
	}
	
	private void changeVigente() {
		this.vigente = !this.vigente;
	}

	//le manda un mensaje al modo actual para hacerlo cambiar el modo
	public void cambiarModoApp(){
	    
		modoApp.cambiarModo();
		if(!(this.modoDesplazamiento))
			this.modoDesplazamiento = !this.modoDesplazamiento;
	  }
	  
	public void setModoApp(Modo modo) {
		
		this.modoApp= modo;
	  }
	
	// deberia estar sincronizado con modo automatico
	private void cambiarModoDesplazamiento() {
		this.modoDesplazamiento = !this.modoDesplazamiento;
		this.cambiarModoApp();
	}

	//Metodos
	/*
	 * Deberia retorna un String indicado cada dato que pide el modelo.
	 * Registra en el SEM que se inicio el estacionamiento. Ademas de mostrar los datos 
	 */

	public void inicioDeEstacionamiento(String patente){

	    sistemaSEM.registrarEstacionamientoApp(patente,this.getNTelefono());
	    this.changeVigente();
	  }
	/*
	 * Deberia retorna un String indicado cada dato que pide el modelo.
	 * Llama al SEM con el metodo removerEstacionamientoDe_(nTelefono). Ademas de mostrar los datos 
	 */
	public void finDeEstacionamiento(){
		  
		  sistemaSEM.removerEstacionamientoDe_(this.getNTelefono());
		  this.changeVigente();
	  }
	  
	  /*
	  * llama al SEM con el metodo consultarSaldoDe(numero) y que devuelva un double
	  */
	public void consultarSaldo(){
		  
		  sistemaSEM.consultarSaldoDe_(this.getNTelefono());
	  }
	
	
	public void driving() {
		
	}
	
	public void walking() {
		
	}
}
