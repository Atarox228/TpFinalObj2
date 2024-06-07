public class AppUsuario{
  //Variables
  private Modo modo;
  private SEM sistSem;
  private String patentePred; //patente predeterminada por usuario
  private int numeroTel;
  //Metodos

  /*
   * Constructor de la clase AppUsuario
  */


  /*
   * Recibe una patente y un telefo, llama al SEM con el metodo registrarEstacionamientoApp() y este le devulve un ticketApp
   * 2nda opcion. Recibe una patente y un telefo, llama al SEM con el metodo registrarEstacionamientoApp() y con otro metodo encargado de hacer el ticket y la excepcion
  */
  public ticketApp inicioDeEstacionamiento(String patente){

    return(sistSem.registrarEstacionamientoApp(patente, numeroTel));
    
  }

  /*
  * llama al SEM con el metodo removerEstacionamientoDe_(numero) que le devulve un ticketApp
  *
  */
  
  public ticketApp finDeEstacionamiento(){
    
  }
  /*
  * le manda un mensaje al modo actual para hacerlo cambiar el modo
  */
  public void cambiarModo(){
    
  }

  /*
  * llama al SEM con el metodo consultarSaldoDe(numero)
  */
  public void consultarSaldo(){

  }

  
}