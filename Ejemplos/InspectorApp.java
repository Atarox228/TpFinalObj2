package tpfinal.copy;

public class InspectorApp {

	private int id;
	private SEM sistSem;
	
	public Inspector(SEM s, Int id) {
		this.id = id;
		this.sistSem = s
	}
	
	
	public void consultarEstacionamientoDe(String patente) {
		if this.sistSem.estaEnInfraccion(patente, this){
			this.altaDeInfraccion(patente, this);
		}
	}
	
	public void altaDeInfraccion(patente, this) {
		this.sistSem.altaDeInfraccion(patente,this);
	}

}
