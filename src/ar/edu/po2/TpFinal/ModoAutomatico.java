package ar.edu.po2.TpFinal;

public class ModoAutomatico implements Modo{
	
	
	private AppUsuario app;
	private boolean estadoCaminando;
	
	public ModoAutomatico(AppUsuario app) {
		
		this.app = app;
		this.estadoCaminando = true;
	}
	
	
	@Override
	public void cambiarModo(AppUsuario app) {
		
		Modo m = new ModoManual();
		this.app.setModoApp(m);
		
	}
	
	//hay que avisar que se hizo automaticamente
	public void iniciarEstacionamiento(){
		
		if (!(app.getVigente()) ) 
			this.app.inicioDeEstacionamiento(app.getPatentePredeterminada());
		
		
	}
	
	//hay que avisar que se hizo automaticamente
	public void finEstacionamiento() {
		
		if(app.getVigente()) 
			this.app.finDeEstacionamiento();
	}
	
	// como hay dos estados del movement, entonces los tomamos como boleanos

	@Override
	public void estaManejando() {
		if (this.estadoCaminando) {
			this.finEstacionamiento();
			this.estadoCaminando = !estadoCaminando;
		}
		
		
	}


	@Override
	public void estaCaminando() {
		if (!this.estadoCaminando) {
			this.iniciarEstacionamiento();
			this.estadoCaminando = !estadoCaminando;
		}
		
	}


	@Override
	public void cambiarModoSensorApagado() {
		this.cambiarModo(app);
		
	}

}
