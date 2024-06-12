package ar.edu.po2.TpFinal;

public class AppInspector {

	//Variables de Instancia
	
	private int id;
	private SEM sistemaSEM;
	
	//Constructor 
	public AppInspector(int id, SEM s) {
		
		this.id = id;
		this.sistemaSEM = s;
	}
	
	public int getId() {
		return id;
	}
	public SEM getSistemaSEM() {
		return sistemaSEM;
	}

	//Metodos
	
	public void altaDeInfraccion(String patente) {
		this.sistemaSEM.altaDeInfraccion(patente,this.getId());
	}
	
	public void consultarEstacionamientoDe(String patente) {
		if (this.sistemaSEM.estaEnInfraccion(patente, this.getId())){
			this.altaDeInfraccion(patente);
		}
	}
}
