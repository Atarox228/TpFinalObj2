package ar.edu.po2.TpFinal;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;
import java.util.stream.Stream;

public class SEM extends Observable{
	
	//Variables de Instancia
	
	// Representa la hora actual cuando recibe algun mensaje par finalizar estacionamiento.
	private int hora; 
	
	// Precio equivale a 40$
	private double precioEstacionamiento;
	
	// Funciona como el registro historico. 
	private List<Ticket> registroDeMovimientos;
	
	// tiene registrados los creditos en la app de cada cliente
	private List<Credito> listaDeCreditos;
	
	private List<Infraccion> listaDeInfracciones;
	
	// Son todos los estacionamientos registrados(tanto compra puntual como app).
	private List<RegistroEst> registroDeEstacionamientos;
	
	private List<ZonaEstacionamiento> zonasEstacionamiento;
	
	// Contador para generar id's unicos.
	private int contadorIdTickets;
	
	// Fecha del dia actual.
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
	
	// Getter y Setters
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
	
	// Metodos de agregar en listas.
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
		this.allNotify();
	}

	protected void addZonaEstacionamiento(ZonaEstacionamiento zona) {
		
		this.zonasEstacionamiento.add(zona);
	}

	//Metodos
	/* 
	 * Indica si la patente ingresada por parametro tiene un estacionamiento vigente. 
	 * Chequeando de que existe en la zona de estacionamiento y si existe un registro de esta patente.  
	 */
	
	public boolean estaEnInfraccion(String patente, int id) {
		Stream<RegistroEst> st = this.registroDeEstacionamientos.stream();
		Stream<ZonaEstacionamiento> st2 = this.zonasEstacionamiento.stream();
		ZonaEstacionamiento falsa = new ZonaEstacionamiento(id);
		// en caso de no tener la zona de estacionamineto de ese inpctor, se creara una falsa que indique 
		// falso en el and para hacer short circuit y no romper el sistema
		ZonaEstacionamiento z1 = st2.filter(z -> z.getIdInspector() == id).findFirst().orElse(falsa);
		// esto no falla debido al short circuit
		RegistroEst regEst = st.filter(t -> t.getPatente().equals(patente)).findFirst().orElse(null);
		boolean b1 = (this.hora < 20) && (this.hora > 7);
		boolean b2 = z1.tieneEstacionadoA(patente);
		return ( b1 && b2 && (regEst.getHoraFinal() < this.hora) );

	}
	
	/*
	 * Genera una infraccion a la patente ingresada por parametro.
	 */
	public void altaDeInfraccion(String patente, int idInspector) {
		Stream<ZonaEstacionamiento> zonaS = this.zonasEstacionamiento.stream();
		//este mensaje nunca devulve null si es llamado por la app inspector debido a que si lo llama es porque ya se encotro
		// una zona de estacionamiento con esa id, ver mensaje estaEnInfraccion
		ZonaEstacionamiento zona = zonaS.filter(z -> z.getIdInspector() == idInspector).findFirst().orElse(null);
		Infraccion i = new Infraccion(patente,this.getFecha(), this.getHora(), idInspector, zona);
		this.addInfracccion(i);
	}
	
	/*
	 * Guarda un registro de estacionamiento por punto de venta.
	 */
	public void addEstacionamientoPV(String patente, int horas) {
		RegistroEst r = new RegistroEstPV(patente, this.getHora(),horas);
		this.addRegistroDeEst(r);
	}
	
	/*
	 * Agrega el ticket pasado por parametro con el metodo 'addTicketHistorico' y aumenta el contador.
	 */
	
	public void addTicket(Ticket t) {
		this.addTicketHistorico(t);
		this.contadorIdTickets ++;
		
	}
	
	/*
	 * Genera un registro, no importa si la patente haya registrado o no una carga de credito.
	 * En caso de que exista un carga se genera el registro y se guarda, ademas de avisar a la app.
	 * Sino posee el saldo suficiente avisa a la app sobre el saldo insuficiente.
	 */
	public void registrarEstacionamientoApp(String patente,AppUsuario app) {
		
		int numeroTelefono = app.getNTelefono();
		ZonaEstacionamiento zona = app.getZona();
		Stream<Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == numeroTelefono).findFirst().orElse(this.nuevoCredito(numeroTelefono));
		if (credito.getCredito() >= 40d) {
			RegistroEst r = new RegistroEstApp(patente, this.getHora(),numeroTelefono,credito.getCredito());
			this.addRegistroDeEst(r);
			String notificacion = "Hora de inicio: " + this.getHora() + ", Hora de final: " + r.getHoraFinal();
			app.notificar(notificacion);
			zona.estacionar(patente);
		} else {
			app.notificar("Saldo insuficiente. Estacionamiento no permitido");
		}
	}
	
	

	private Credito nuevoCredito(int numeroTelefono) {
		Credito c = new Credito(numeroTelefono);
		this.addCredito(c);
		return c;
	}

	
	/*
	 * En este metodo si el nTelefono no existe genera un credito y le carga su credito correspondiente con el monto
	 * pasado por parametro. Si este numero ya tiene un credito unicamente recarga el monto padado por parametro.
	 */
	public void cargarCredito(double creditoACargar, int nTelefono) {
		Stream <Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == nTelefono).findFirst().orElse(this.nuevoCredito(nTelefono));
		credito.aumentarCredito(creditoACargar);
	}
	
	/*
	 * Remueve el estacionamiento de una app pasada por parametro. Ya que esta app conoce el numero para buscarlo en la lista
	 * de creditos y de registro, ademas se necesita la zona para poder sacar el auto con dicha patente.
	 * Por ultimo notifica a la app misma con los detalles requiridos por el enunciado. 
	 */
	public void removerEstacionamientoDe_(AppUsuario app) {
		// precon, el registro asociado al numero esta en los registros del dia y tiene saldo suficiente para hacer el estacionamiento
		// el numero de teledono no es nunca 0
		int numeroTelefono = app.getNTelefono();
		ZonaEstacionamiento zona = app.getZona();
		Stream<RegistroEst> s = this.registroDeEstacionamientos.stream();
		RegistroEst r = s.filter(rc -> rc.getNTelefono() == numeroTelefono).findFirst().orElse(null);
		zona.desEstacionar(r.getPatente());
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
	 * Limpia la lista de registrosDeEstacionamientos estacionamientosVigentes. 
	 */
	public void finalizazHorarioVigente() {
		if (this.getHora() == 20) 
			this.registroDeEstacionamientos.clear();
	}
	

	public Double consultarSaldoDe_(int nTelefono) {
		
		//mediante un stream(listaDeCreditos) deberia devolver el saldo asignado al numero pasado
		Stream <Credito> sc = this.listaDeCreditos.stream();
		Credito credito = sc.filter(c -> c.getNTelefono() == nTelefono).findFirst().orElse(this.nuevoCredito(nTelefono));
		return credito.getCredito();
	}
	
	/*
	 * Retorna una zona de estacionamiento random que posea el SEM.
	 * En caso de que este vacio retorna un null.
	 */
	public ZonaEstacionamiento obtenerZonaCercana() {
		//precondicion: zonasEstacionamiento no esta vacia
		Random random = new Random();
		int indexRandom = random.nextInt(this.getCantZonasEst());
		return this.zonasEstacionamiento.get(indexRandom);
	}
	
	public void allNotify() {
		this.setChanged();
		this.notifyObservers();	
	}
	


	

	
}
