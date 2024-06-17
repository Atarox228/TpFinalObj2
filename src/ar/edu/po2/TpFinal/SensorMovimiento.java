package ar.edu.po2.TpFinal;

public abstract class SensorMovimiento {

	public void encender(AppUsuario app) {
		
	}
	public void apagarSensor(AppUsuario app) {
		
		SensorMovimiento apagado = new Apagado();
		app.setSensorMovimiento(apagado);
		app.notificarSensorApagado();
	
	}
	public void estaCaminando(AppUsuario app) {
		
	}
	public void estaManejando(AppUsuario app) {
		
	}
		
}
