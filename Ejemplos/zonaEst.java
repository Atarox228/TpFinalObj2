public class ZonaEstacionamiento{
  //
  private List<String> patentes;
  private InspectoApp inspector;
  private List<PuntoVenta> puntosDeVenta;

  public void estacionar(String patente){
    patentes.add(patente);
  }

  public void desEstacionar(String patente){
    patentes.remove(patente);
  }

}