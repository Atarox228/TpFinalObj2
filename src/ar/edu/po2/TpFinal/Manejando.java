package ar.edu.po2.TpFinal;

public class Manejando extends SensorMovimiento {

	@Override
	public void estaCaminando(AppUsuario app) {
		
		SensorMovimiento caminando = new Caminando();
		app.setSensorMovimiento(caminando);
		String patente = app.getPatentePredeterminada();
		app.inicioDeEstacionamiento(patente);
	}
	@Override
	public void estaManejando(AppUsuario app) {
		
	}
	
}
