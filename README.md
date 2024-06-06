# TpFinalObj2

Apuntes dia 15/05 

- Consideramos que el telefono es unico por usuario
- Debido a como estructuramos el problema, exceptuamos al comerciamente y lo dejamos todo en el punto de venta

Chage log:

 - se crearon las carpetas para trabajar
 - implementada y testeada la clase auto
 - creada y sin testear la clase infraccion

 Change log:

 - Creadas las clases Estacionamiento e Inspector, solo tienen string
 - Terminado de implementar la clase infraccion y testeada

 Change log:
   ---- todo esto se encuentra en la carpeta ejemplo del branch de lautaro
   - se cambio los maps del sem por el objeto ticket para mayor uniformidad del sistema, es decir, 
	 	ahora para el registro historico se guardan items del tipo Ticket y para los estacionamientos 
			se guardan registros tambien de tipo TickeytEst, ver uml.
  	- se delega la responsabilidad decalcular la hora del final del estacionamiento al ticketEStApp mas no la
	 	exceptcion, esta debera ser hecha por el SEM
			(esta debera hacer catch al caso que el saldo sea menor a 40)
	  - se hicieron las clases inspectorApp, TicketEst,TicketEstApp, probar todo con test porque no pude debido a 
		 problemas de la computadora del edifico
	  - definido un solo mensaje del Sem, probar con mock, dejo comentado que hace cada cosa del stream para que sepas como anda
 
