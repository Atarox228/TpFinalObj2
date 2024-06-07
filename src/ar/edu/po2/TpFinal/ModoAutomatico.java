package ar.edu.po2.TpFinal;

public class ModoAutomatico implements Modo{
	
	
	private AppUsuario app;
	
	public ModoAutomatico(AppUsuario app) {
		
		this.app = app;
	}
	
	
	@Override
	public void cambiarModo() {
		
		this.app.setModoApp(new ModoManual(app));
		
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

}
