public class PuntoVenta {

  // Intancia de Variables
  private ZonaEstacionamiento zona;
  private SEM sistemaSEM;

  // Constructores
  public PuntoVEnta(ZonaEstacionamiento zona) {

    this.zona = zona;
  }

  // Metodos

  /*
   * Recibe una patente y una hora, y llama al SEM con el metodo
   * registrarCompraPuntual.
   * Ademas crear un tikcet.
   */
  public void acreditarEst(String patente, int horas) {
    sistemaSEM.registrarCompraPuntual(patente, horas);
    zona.estacionar(patente);
  }

  /*
   * Recibe un monto y un telefono, y llama al Sem con el metodo cargarCredito.
   * Ademas crear un tikcet.
   */
  public void cargarSaldo(Double monto, Telefono telefono) {
    sistemaSEM.cargarCredito(monto, telefono);

  }
}