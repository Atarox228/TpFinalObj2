package ar.edu.po2.TpFinal;

public class Ticket {

	//Instancia de Variables
	private int idTicket; // El numero de ticket es unico dentro del sistema.
	private PuntoVenta puntoDeVenta;
	private LocalDate fecha;
	private int hora;
	private int cantidadHoras; // Horas compradas
	private int numeroCelular; // el numero del cual se cargo
	private double montoCargado; // el monto total cargado por el celular

	//Constructores
	/*
	 * Constructor para cuando se carga un ticket por pago de horas. Osea por compra puntual. 
	 */
	public Ticket(int id, PuntoVenta puntoDeVenta, LocalDate fecha, int hora, int cantidadHoras) {
		
		this.idTicket = id;
		this.puntoDeVenta = puntoDeVenta;
		this.fecha = fecha;
		this.hora = hora; 
		this.cantidadHoras = cantidadHoras;
		this.numeroCelular = 0;
		this.montoCargado = 0;
	}
	/*
	 * Constructor para cuando se carga un ticket por recargar de saldo. Osea por via app.
	 */
	public Ticket(int id, PuntoVenta puntoDeVenta, LocalDate fecha, int hora, int numeroCelular,
			double montoCargado) {
		
		this.idTicket = id;
		this.puntoDeVenta = puntoDeVenta;
		this.fecha = fecha;
		this.hora = hora;
		this.cantidadHoras = 0;
		this.numeroCelular = numeroCelular;
		this.montoCargado = montoCargado;
	}
	
	//Metodos
	
	/*
	 * Retorna el identificar del ticket.
	 */
	public int getId() {
		
		return this.idTicket;
	}
	
	/*
	 * Retorna la hora en la cual se genero el ticket.
	 * Observacion : esto no quiere decir que la persona estaciono exactamente a la misma hora que se genero el ticket. 
	 * Aunque en el caso de la compra puntal si se podria asumir, via app no.
	 */
	public int getHora() {
		
		return this.hora;
	}
	/*
	 * Retorna el numero de celular por el cual fue registrado el ticket. 
	 * En caso de que este haya sido por compra puntal retorna 0.
	 */
	public int getNumeroCelular() {
		
		return this.numeroCelular;
	}
}
