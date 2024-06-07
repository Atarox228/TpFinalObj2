package ar.edu.po2.TpFinal;

public class ModoManual implements Modo {

	
	private AppUsuario app;
	
	public ModoManual(AppUsuario app) {
		
		this.app = app;
	}
	
	@Override
	public void cambiarModo() {
		
		app.setModoApp(new ModoAutomatico(app));
	}

}
