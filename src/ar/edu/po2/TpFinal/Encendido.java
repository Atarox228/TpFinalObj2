package ar.edu.po2.TpFinal;

public class Encendido extends SensorMovimiento {

	@Override
	public void estaCaminando(AppUsuario app) {
		
		SensorMovimiento caminando = new Caminando();
		app.setSensorMovimiento(caminando);
	}
	@Override
	public void estaManejando(AppUsuario app) {
		
		SensorMovimiento manejando = new Manejando();
		app.setSensorMovimiento(manejando);
	}
}
