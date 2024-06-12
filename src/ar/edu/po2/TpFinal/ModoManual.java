package ar.edu.po2.TpFinal;

public class ModoManual implements Modo {

	//Variables de Instancia
	private AppUsuario app;
	
	//Constructores
	public ModoManual(AppUsuario app) {
		
		this.app = app;
	}
	
	//Metodos
	@Override
	public void cambiarModo(AppUsuario app) {
		
		Modo m = new ModoAutomatico(app);
		
		app.setModoApp(m);
		app.encenderSensor();
	}

	@Override
	public void estaManejando() {
		
	}

	@Override
	public void estaCaminando() {
	
	}

	@Override
	public void cambiarModoSensorApagado() {
		
	}

	@Override
	public void finDeEstacionamiento() {
		
		app.finDeEstacionamientoApp();
	}

	@Override
	public void inicioDeEstacionamiento(String patente) {
		
		app.inicioDeEstacionamientoApp(patente);
	}

}
