package ar.edu.po2.TpFinal;

public class Apagado extends SensorMovimiento{

	@Override
	public void encender(AppUsuario app) {
		
		SensorMovimiento prendido = new Encendido();
		app.setSensorMovimiento(prendido);
		Modo automatico = new ModoAutomatico(app);
		app.setModoApp(automatico);
	}
	@Override
	public void apagarSensor(AppUsuario app) {
		
	}

}
