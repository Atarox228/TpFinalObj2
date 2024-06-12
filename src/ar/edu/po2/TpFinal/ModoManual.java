package ar.edu.po2.TpFinal;

public class ModoManual implements Modo {

	private AppUsuario app;
	
	public ModoManual(AppUsuario app) {
		
		this.app = app;
	}
	
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
