package ar.edu.po2.TpFinal;

public class Caminando extends SensorMovimiento {


	@Override
	public void estaManejando(AppUsuario app) {
		
		SensorMovimiento manejando = new Manejando();
		app.setSensorMovimiento(manejando);
		if(app.estaEnLaZona())
			app.finDeEstacionamiento();
	}
	@Override
	public void estaCaminando(AppUsuario app) {
		
	}
}
