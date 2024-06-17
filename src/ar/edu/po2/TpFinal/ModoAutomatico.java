package ar.edu.po2.TpFinal;

public class ModoAutomatico implements Modo{
	
	
	//Variables de Instancia
	
	private AppUsuario app;
	private boolean estadoCaminando;
	
	//Constructor
	public ModoAutomatico(AppUsuario app) {
		
		this.app = app;
		this.estadoCaminando = true;
	}
	
	//Metodos
	@Override
	public void cambiarModo(AppUsuario app) {
		
		Modo m = new ModoManual(app);
		this.app.setModoApp(m);
		
	}
	
	//hay que avisar que se hizo automaticamente
	public void iniciarEstacionamiento(){
		
		this.app.notificar("Inicio de Estacionamiento Automatico");
		this.app.inicioDeEstacionamientoApp(app.getPatentePredeterminada());
	}
	
	//hay que avisar que se hizo automaticamente
	public void finalizarEstacionamiento() {
		
		this.app.notificar("Fin de estacionamiento Automatico");
		this.app.finDeEstacionamientoApp();
	}
	
	// como hay dos estados del movement, entonces los tomamos como boleanos

	@Override
	public void estaManejando() {
		if (this.estadoCaminando) {
			this.finalizarEstacionamiento();
			cambiarEstado();
		}
	}


	@Override
	public void estaCaminando() {
		if (!this.estadoCaminando) {
			this.iniciarEstacionamiento();
			cambiarEstado();
		}
	}
	//protected para los tests
	protected void cambiarEstado() {
		
		this.estadoCaminando = !estadoCaminando;
	}

	@Override
	public void finDeEstacionamiento() {
		
		
	}


	@Override
	public void inicioDeEstacionamiento(String patente) {
		
		
	}
	@Override 
	public void notificarSensorApagado(AppUsuario app) {
		
		Modo manual = new ModoManual(app);
		app.setModoApp(manual);
	}
}
