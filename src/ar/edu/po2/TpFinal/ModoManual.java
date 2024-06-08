package ar.edu.po2.TpFinal;

public class ModoManual implements Modo {

	
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

}
