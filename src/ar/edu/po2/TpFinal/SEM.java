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
	private List<RegistroEst> registroDeEstacionamientos;
	
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
		this.registroDeEstacionamientos = new ArrayList<RegistroEst>();
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
	public int getCantRegistroDeEst() {
		return registroDeEstacionamientos.size();
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
	protected void addRegistroDeEst(RegistroEst regEst) {
		
		this.registroDeEstacionamientos.add(regEst);
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
		Stream<RegistroEst> st = this.registroDeEstacionamientos.stream();
		Stream<ZonaEstacionamiento> st2 = this.zonasEstacionamiento.stream();
		ZonaEstacionamiento falsa = new ZonaEstacionamiento(id);
		// en caso de no tener la zona de estacionamineto de ese inpctor, se creara una falsa que indique 
		// falso en el and para hacer short circuit y no romper el sistema
		ZonaEstacionamiento z1 = st2.filter(z -> z.getIdInspector() == id).findFirst().orElse(falsa);
		// esto no falla debido al short circuit
		RegistroEst regEst = st.filter(t -> t.getPatente().equals(s)).findFirst().orElse(null);
		boolean b1 = (this.hora < 20) && (this.hora > 7);
		boolean b2 = z1.tieneEstacionadoA(s);
		return ( b1 && b2 && (regEst.getHoraFinal() < this.hora) );

	}
	
	public void altaDeInfraccion(String patente, int idInspector) {
		Stream<ZonaEstacionamiento> zonaS = this.zonasEstacionamiento.stream();
		//este mensaje nunca devulve null si es llamado por la app inspector debido a que si lo llama es porque ya se encotro
		// una zona de estacionamiento con esa id, ver mensaje estaEnInfraccion
		ZonaEstacionamiento zona = zonaS.filter(z -> z.getIdInspector() == idInspector).findFirst().orElse(null);
		Infraccion i = new Infraccion(patente,this.getFecha(), this.getHora(), idInspector, zona);
		this.addInfracccion(i);
	}
	
	public void addEstacionamientoPV(String patente, int horas) {
		RegistroEst r = new RegistroEst(patente, this.getHora(),horas);
		this.addRegistroDeEst(r);
	}

	
	public void addTicket(Ticket t) {
		this.addTicketHistorico(t);
		this.contadorIdTickets ++;
		
	}
	
	// falta avisar al usuario que se hizo
	public void registrarEstacionamientoApp(String patente,AppUsuario app) {
		int numeroTelefono = app.getNTelefono();
		Stream<Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == numeroTelefono).findFirst().orElse(this.nuevoCredito(numeroTelefono));
		if (credito.getCredito() >= 40d) {
			RegistroEst r = new RegistroEst(patente, this.getHora(),numeroTelefono,credito.getCredito());
			this.addRegistroDeEst(r);
			String notificacion = "Hora de inicio: " + this.getHora() + ", Hora de final: " + r.getHoraFinal();
			app.notificar(notificacion);
		} else {
			app.notificar("Saldo insuficiente. Estacionamiento no permitido");
		}
	}

	

	private Credito nuevoCredito(int numeroTelefono) {
		Credito c = new Credito(numeroTelefono);
		this.addCredito(c);
		return c;
	}

	
	// Resgista la carga de un telefono en el map credito, y en caso de que ya exista el telefono en el map, habra que
		// actualizar el saldo del mismo.
	public void cargarCredito(double creditoACargar, int nTelefono) {
		Stream <Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == nTelefono).findFirst().orElse(this.nuevoCredito(nTelefono));
		credito.aumentarCredito(creditoACargar);
	}
	
		
	public void removerEstacionamientoDe_(AppUsuario app) {
		// precon, el registro asociado al numero esta en los registros del dia y tiene saldo suficiente para hacer el estacionamiento
		// el numero de teledono no es nunca 0
		int numeroTelefono = app.getNTelefono();
		Stream<RegistroEst> s = this.registroDeEstacionamientos.stream();
		RegistroEst r = s.filter(rc -> rc.getNTelefono() == numeroTelefono).findFirst().orElse(null);
		this.registroDeEstacionamientos.remove(r);
		int hI = r.getHoraInicio();
		int hF = this.getHora();
		int horasT = hF-hI;
		double totalP = horasT*40d;
		Stream <Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == numeroTelefono).findFirst().orElse(null);
		credito.decrementarCredito(totalP);
		String notificacion = "Hora de Inicio: " + hI + " Hora de Fin: " + hF + " Cantidad Horas Estacionado: " + horasT + " Costo: " + totalP;
		app.notificar(notificacion);
	}
	
	/*
	 * Finaliza todos los estacionamientos vigentes en caso de que la hora del SEM sea las 20.
	 * Limpia el map estacionamientosVigentes. 
	 */
	public void finalizazHorarioVigente() {
		if (this.getHora() == 20) 
			this.registroDeEstacionamientos.clear();
	}
	
	
	// Añadir de Lista.
	
	public Double consultarSaldoDe_(int nTelefono) {
		
		//mediante un stream(listaDeCreditos) deberia devolver el saldo asignado al numero pasado
		Stream <Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == nTelefono).findFirst().orElse(this.nuevoCredito(nTelefono));
		return credito.getCredito();
	}


	

	
}
