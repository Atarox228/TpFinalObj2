package ar.edu.po2.TpFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.stream.Stream;

public class SEM extends Observable{

	/*
	 * Modificar TicketApp, no queda en el historico, solo para estacionamiento vigente, y no deberia aumentar el contador
	 * 
	 */
	
	
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
	
	private String fecha;
	
	
	//Constructores
	
	public SEM(String fecha) {
		
		this.hora = 7;
		this.precioEstacionamiento = 40d;
		this.contadorIdTickets = 0;
		this.registroDeMovimientos = new ArrayList<Ticket>();
		this.listaDeCreditos = new ArrayList<Credito>();
		this.listaDeInfracciones = new ArrayList<Infraccion>();
		this.ticketsDeEstacionamientos = new ArrayList<TicketEst>();
		this.zonasEstacionamiento = new ArrayList<ZonaEstacionamiento>();
		this.fecha = fecha;
	}
	
	public double getPrecioEstacionamiento() {
		return precioEstacionamiento;
	}
	public int getContadorIdTickets() {
		return contadorIdTickets;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		// se asume que la hora es correcta (0-23)
		this.hora = hora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCantTickets() {
		return registroDeMovimientos.size();
	}
	public int getCantTicketsEst() {
		return ticketsDeEstacionamientos.size();
	}
	public int getCantCreditos() {
		return listaDeCreditos.size();
	}
	public int getCantZonasEst() {
		return zonasEstacionamiento.size();
	}
	public int getCantInfracciones() {
		return listaDeInfracciones.size();
	}
	
	
	protected void addTicketHistorico(Ticket ticket) {
		
		this.registroDeMovimientos.add(ticket);
	}
	protected void addCredito(Credito credito) {
		
		this.listaDeCreditos.add(credito);
	}
	protected void addInfracccion(Infraccion infraccion) {
		
		this.listaDeInfracciones.add(infraccion);
	}
	protected void addTicketDeEstacionamiento(TicketEst ticket) {
		
		this.ticketsDeEstacionamientos.add(ticket);
	}
	protected void addZonaEstacionamiento(ZonaEstacionamiento zona) {
		
		this.zonasEstacionamiento.add(zona);
	}

	//Metodos
	/* 
	 * Crea un ticket y lo guarda en la registroDeMovimientos, y registra ademas en la lista estacionamientoVigentes.
	 * Este metodo lo llama un puntoVenta con el metodo acreditarEst.
	 */
	
	public boolean estaEnInfraccion(String s, int id) {
		Stream<TicketEst> st = this.ticketsDeEstacionamientos.stream();
		Stream<ZonaEstacionamiento> st2 = this.zonasEstacionamiento.stream();
		ZonaEstacionamiento falsa = new ZonaEstacionamiento(id);
		// en caso de no tener la zona de estacionamineto de ese inpctor, se creara una falsa que indique 
		// falso en el and para hacer short circuit y no romper el sistema
		ZonaEstacionamiento z1 = st2.filter(z -> z.getIdInspector() == id).findFirst().orElse(falsa);
		// esto no falla debido al short circuit
		TicketEst ticket = st.filter(t -> t.getPatente().equals(s)).findFirst().orElse(null);
		return (z1.tieneEstacionadoA(s) && (ticket.getHoraMaxima() < this.hora));

	}
	
	public void altaDeInfraccion(String patente, int idInspector) {
		Stream<ZonaEstacionamiento> zonaS = this.zonasEstacionamiento.stream();
		//este mensaje nunca devulve null si es llamado por la app inspector debido a que si lo llama es porque ya se encotro
		// una zona de estacionamiento con esa id, ver mensaje estaEnInfraccion
		ZonaEstacionamiento zona = zonaS.filter(z -> z.getIdInspector() == idInspector).findFirst().orElse(null);
		Infraccion i = new Infraccion(patente,this.getFecha(), this.getHora(), idInspector, zona);
		this.addInfracccion(i);
	}
	
	
	public void registrarCompraPuntual(String patente, int cantidadHoras, PuntoVenta pv) {
		TicketEst ticket = new TicketEstPV(this.contadorIdTickets,patente,cantidadHoras,this.getHora(),this.getFecha(),pv);;
		this.addTicket(ticket);
		this.addTicketDeEstacionamiento(ticket);
	}
	
	private void addTicket(Ticket t) {
		this.addTicketHistorico(t);
		this.contadorIdTickets ++;
		
	}
	
	// falta avisar al usuario que se hizo
	public void registrarEstacionamientoApp(String patente,AppUsuario app) {
		int numeroTelefono = app.getNTelefono();
		Stream<Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == numeroTelefono).findFirst().orElse(this.nuevoCredito(numeroTelefono));
		if (credito.getCredito() >= 40d) {
			TicketEst ticket = new TicketEstApp(this.getContadorIdTickets(),patente,numeroTelefono,this.getHora(),this.getFecha(),credito.getCredito());
			this.addTicket(ticket);
			this.addTicketDeEstacionamiento(ticket);
		} else {
			this.notifySaldo(app);
		}
		

	}

	private void notifySaldo(AppUsuario app) {
		// esto le dice a la app que no tiene saldo suficiente 
		
	}

	private Credito nuevoCredito(int numeroTelefono) {
		Credito c = new Credito(numeroTelefono);
		this.addCredito(c);
		return c;
	}

	// Eliminia la patente del map estacionamientoVigentes.
	public void removerEstacionamientoDe_(String patente) {
		
	}
	
	// Eliminia del map estacionadosAPP, y ademas elimina la patente de este del map estacionadosVigentes.
	public void removerEstacionamientoDe_(int numeroTelefono) {
		
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
	public void enviarMensajeDeInicioEstacionamiento(Celular telefono) {
		
	}
	
	/*
	 * Finaliza todos los estacionamientos vigentes en caso de que la hora del SEM sea las 20.
	 * Limpia el map estacionamientosVigentes. 
	 */
	public void finalizazHorarioVigente() {
		
	}
	
	
	// Añadir de Lista.
	
	public void consultarSaldoDe_(int numero) {
		
		//mediante un stream(listaDeCreditos) deberia devolver el saldo asignado al numero pasado
	}

	
}
