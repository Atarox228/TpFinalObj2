package ar.edu.po2.TpFinal;

public class AppUsuario {

	//Instancia de Variables
	private String patentePredeterminada;
	private SEM sistemaSEM;
	private Modo modo;
	
	//Constructores
	
	public AppUsuario() {
		
		//Lo del SeM aun se tiene q ver.
		// Capaz aca se le puede pasar la patente.
		this.modo = new ModoManual();
	}
	
	//Metodos
	
	/*
	 * Deberia retorna un String indicado cada dato que pide el modelo.
	 * Registra en el SEM que se inicio el estacionamiento. Ademas de mostrar los datos 
	 */
	public void inicioDeEstacionamiento(String patente, telefono) {
		
	}
}
