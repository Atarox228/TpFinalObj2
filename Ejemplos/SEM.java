package tpfinal.copy;

import java.util.List;

public class SEM {
	
	//Instancia de Variables
	
		private List<Ticket> compras;
		
		// tiene registrados los creditos en la app de cada cliente
		private List<RegistroCredito> creditos;
		// Representa la hora actual cuando recibe algun mensaje par finalizar estacionamiento.
		private int hora; 
		// Precio equivale a 40$
		private double precioEstacionamiento;
		
		private List<Infraccion> infracciones;
		
		// Son todos los estacionamientos registrados(tanto compra puntual como app).
		private List<TicketEst> estacionamientos;
		
		private List<ZonaEstacionamiento> zonasEstacionamiento;
		
		public estaEnInfraccion(String s, Int id) {
			// crea el stream de los estacionaminetos
			Stream<TicketEst> s = this.estacionamientos.stream();
			// crea el stream de las zonas
			Stream<ZonaEstacionamiento> s2 = this.zonasEstacionamiento.stream();
			// obtiene el ticket asociado a la patente del parametro
			TicketEst t = s.filter(t -> t.getPatente().equals(s)).get();
			// obtiene la zona a la que esta asignado el id del inspector
			ZonaEstacionamiento z1 = s2.filter(z -> z.getInspector().getId().equals(id)).get();
			// prueba si esta o no en infraccion, solo esta en infraccion si el auto ya esta estacionado y si su ticket esta fuera de hora
			return (z1.tieneEstacionadoA(s) && t.horaFinalizacion() > this.hora);
			// tieneEstaacionado corrobora si se encuentra en la lista de patentes de la zona, hora finalizacion se tiene que crear, pero en TicketEstPV
			// devuelve una suma y en TicketEstApp devuelvo una de las variables de instacia
		}
		
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
}
