package ar.edu.po2.TpFinal;

public interface Modo {

	public void cambiarModo(AppUsuario app);

	public void estaManejando();
	public void estaCaminando();

	public void cambiarModoSensorApagado();

	public void finDeEstacionamiento();

	public void inicioDeEstacionamiento(String patente);
}
