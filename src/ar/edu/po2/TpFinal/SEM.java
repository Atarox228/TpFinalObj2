package ar.edu.po2.TpFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

public class SEM extends Observable{

	//Variables de Instancia
	
	// Representa la hora actual cuando recibe algun mensaje par finalizar estacionamiento.
	private int hora; 
	
	// Precio equivale a 40$
	private double precioEstacionamiento;
	
	private List<Ticket> registroDeMovimientos;
	
	// tiene registrados los creditos en la app de cada cliente
	private List<Credito> listaDeCreditos;
	
	private List<Infraccion> listaDeInfracciones;
	
	// Son todos los estacionamientos registrados(tanto compra puntual como app).
	private List<TicketEst> ticketsDeEstacionamientos;
	
	private List<ZonaEstacionamiento> zonasEstacionamiento;
	
	private int contadorIdTickets;
	
	
	//Constructores
	
	public SEM() {
		
		this.hora = 7;
		this.precioEstacionamiento = 40d;
		this.contadorIdTickets = 0;
		this.registroDeMovimientos = new ArrayList<Ticket>();
		this.listaDeCreditos = new ArrayList<Credito>();
		this.listaDeInfracciones = new ArrayList<Infraccion>();
		this.ticketsDeEstacionamientos = new ArrayList<TicketEst>();
		this.zonasEstacionamiento = new ArrayList<ZonaEstacionamiento>();
	}
	
	public void setHora(int hora) {
		
		this.hora = hora;
	}
	
	//Metodos
	/* 
	 * Crea un ticket y lo guarda en la registroDeMovimientos, y registra ademas en la lista estacionamientoVigentes.
	 * Este metodo lo llama un puntoVenta con el metodo acreditarEst.
	 */
	public void registrarCompraPuntual(String patente, int cantidadHoras) {
		
	}
	
	// Eliminia la patente del map estacionamientoVigentes.
	public void removerEstacionamientoDe_(String patente) {
		
	}
	
	// Eliminia del map estacionadosAPP, y ademas elimina la patente de este del map estacionadosVigentes.
	public void removerEstacionamientoDe_(int numeroTelefono) {
		
	}
	//Registra en el map estacionamientoVigentes y estacionadosAPP.
	public void registrarEstacionamientoApp(String patente,int numeroTelefono) {
		
	}
	
	// Resgista la carga de un telefono en el map credito, y en caso de que ya exista el telefono en el map, habra que
	// actualizar el saldo del mismo.
	public void cargarCredito(double credito, int nTelefono) {
		
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
	
	
	public void altaDeInfraccion(String patente, int idInspector) {
		
	}

	public boolean estaEnInfraccion(String string, AppInspector app) {
		return false;
	}
	
	// Añadir de Lista.
	private void agregarTicketHistorico(Ticket ticket) {
		
		this.registroDeMovimientos.add(ticket);
	}
	private void agregarCredito(Credito credito) {
		
		this.listaDeCreditos.add(credito);
	}
	private void agregarInfracccion(Infraccion infraccion) {
		
		this.listaDeInfracciones.add(infraccion);
	}
	private void agregarTicketDeEstacionamiento(TicketEst ticket) {
		
		this.ticketsDeEstacionamientos.add(ticket);
	}
	private void agregarZonaEstacionamiento(ZonaEstacionamiento zona) {
		
		this.zonasEstacionamiento.add(zona);
	}
}
