package ar.edu.po2.TpFinal;

import java.util.List;
import java.util.Map;
import java.util.Observable;

public class SEM extends Observable{

	//Instancia de Variables
	
	private List<Ticket> compras;
	private Map<String,Integer> credito;
	// Representa la hora actual cuando recibe algun mensaje par finalizar estacionamiento.
	private int hora; 
	// Precio equivale a 40$
	private double precioEstacionamiento;
	
	private List<Infraccion> infracciones;
	
	// Son todos los estacionamientos registrados(tanto compra puntual como app).
	private Map<String,Ticket> estacionamientoVigentes;
	
	private Map<Integer, String> estacionadosApp;
	
	//Constructores
	
	//Metodos
	
	/* 
	 * Recibe un tikcet, lo guarda en la lista compras, y registra ademas en la lista estacionamientoVigentes.
	 * Este metodo lo llama un puntoVenta con el metodo acreditarEst.
	 */
	public void registrarCompraPuntual(String patente, int cantidadHoras, Ticket ticket) {
		
	}
	
	// Eliminia la patente del map estacionamientoVigentes.
	public void removerEstacionamientoDe_(String patente) {
		
	}
	
	// Eliminia del map estacionadosAPP, y ademas elimina la patente de este del map estacionadosVigentes.
	public void removerEstacionamientoDe_(int numeroTelefono) {
		
	}
	//Registra en el map estacionamientoVigentes y estacionadosAPP.
	public void registrarEstacionamientoApp(String patente,int numeroTelefono, Ticket ticket) {
		
	}
	
	// Resgista la carga de un telefono en el map credito, y en caso de que ya exista el telefono en el map, habra que
	// actualizar el saldo del mismo.
	public void cargarCredito(double credito, Telefono telefono) {
		
	}
	
	/*
	 * Revisa, si la app puede o no iniciar el estacionamiento. En caso de que no pueda deberia enviar
	 * el mensaje de error.
	 * Entraria en el map de credito, revisa si existe credito con el telofono por parametro, e indica cuantas horas
	 * cubre su saldo(en caso de que este tubiese).
	 */
	public void enviarMensajeDeInicioEstacionamiento(Telefono telefono) {
		
	}
	
	/*
	 * Finaliza todos los estacionamientos vigentes en caso de que la hora del SEM sea las 20.
	 * Limpia el map estacionamientosVigentes. 
	 */
	public void finalizazHorarioVigente() {
		
	}
	
	public void setHora(int hora) {
		
		this.hora = hora;
	}
	
	public void altaDeInfraccion(String patente, int idInspector) {
		
	}

	public boolean estaEnInfraccion(String string, AppInspector app) {
		return false;
	}
}
