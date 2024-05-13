package consola;

import java.util.List;
import java.util.Map;

import logica.Compra;
import logica.Galeria;
import pieza.Pieza;
import usuario.Administrador;
import usuario.Comprador;
import usuario.Propietario;

public class ConsolaAdmin extends ConsolaBasica{
	
	private final String[] opcionesAdmin= new String[]{"Ver historia de un comprador", "Verificar comprador", "Aumentar límite de credito", "Confirmar venta", "Devolución de pieza", "Verificar seriedad de oferta", "Bloquear pieza", "Desbloquear pieza","Ver historial de una pieza","Ver historia de un artista", "Regresar"};
	
	private Galeria laGaleria;
	private Administrador admin;
	Map<String,Comprador> mapaCompradores;
	List<Pieza> piezasDisponibles; 
	List<Pieza> piezasPasadas; 
	
	public ConsolaAdmin ( Galeria galeria  )
    {
        this.laGaleria = galeria;
        this.admin = laGaleria.getAdministrador();
        this.mapaCompradores = laGaleria.getControladorUsuarios().getMapaCompradores();
    	this.piezasDisponibles = laGaleria.getInventario().getPiezasDisponibleVenta();
    	this.piezasPasadas = laGaleria.getInventario().getPiezasPasadas();
    }
	
	public void mostrarMenu( )
    {
        boolean regresar = false;

        while( !regresar )
        {
            int opcionSeleccionada = mostrarMenu( "Menu de la Galeria", opcionesAdmin );
            if( opcionSeleccionada == 1 )
            {	
            	System.out.println("Los compradores acutales son: \n");
            	for (Comprador cp : mapaCompradores.values()) {
            	    System.out.println(cp.getLogin());
            	}
            	String compradorName  = pedirCadenaAlUsuario("Ingrese el nombre del comprador deseado");
            	Comprador compa = mapaCompradores.get(compradorName);
            	List<Compra> misCompras = compa.getmisCompras();
            	if (misCompras.size() == 0) {
            		System.out.println(compradorName + " no ha realizado ninguna compra!");
            	}
            	else {
            	System.out.println(compradorName + " ha comprado las siguientes piezas: ");
            	int valorColeccion = 0;
            	for (Compra unaCompra: misCompras) {
            		
            		Pieza pz = unaCompra.getPieza();
            		System.out.println( pz.getTitulo() + " en el " + pz.getFechaVenta());
            		valorColeccion += pz.getPrecioFijo();
            	}
            	System.out.println("El valor total de la coleecion es de: " + valorColeccion);
            	}
            }
            else if( opcionSeleccionada == 2 )
            {	
            	System.out.println("Los compradores acutales son: \n");
            	for (Comprador cp : mapaCompradores.values()) {
            	    
            	    System.out.println(cp.getLogin());
            	}
            	String compradorName  = pedirCadenaAlUsuario("Ingrese el nombre del comprador a registrar");
            	Comprador compa = mapaCompradores.get(compradorName);
            	String id = compa.getLogin();
                verificarComprador(admin, id );
            }
            else if( opcionSeleccionada == 3 )
            {	
            	System.out.println("Los compradores acutales son: \n");
            	for (Comprador cp : mapaCompradores.values()) {
            	    
            	    System.out.println(cp.getLogin() + ", su limite acutal es: " + cp.getLimiteCompras());
            	}
            	String compradorName  = pedirCadenaAlUsuario("Ingrese el nombre del comprador al que le va a subir el limite");
            	int newLimit = pedirEnteroAlUsuario("Ingrese cual va a ser el nuevo limite del comprador");
            	Comprador compa = mapaCompradores.get(compradorName);
            	String id = compa.getLogin();
                aumentarLimite(admin, id, compa, newLimit );
            }
            else if( opcionSeleccionada == 4 )
            {	
            	System.out.println("Las piezas disponibles para confirmar venta son: \n");
            	for (Pieza pz : piezasDisponibles) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + "con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza a ingresar");
            	Pieza piezaVenta = laGaleria.getInventario().buscarPieza(piezaName);
            	String newID = obtenerNuevoID();
            	Compra nuevaCompra =  new Compra(newID,piezaVenta.getPrecioFijo(), "tarjeta", piezaVenta);
            	confirmarVenta(nuevaCompra, piezaVenta, "flackius", admin);
            }
            else if( opcionSeleccionada == 5 )
            {
            	System.out.println("Los propietarios acutales son: \n");
            	Map<String,Propietario> mapaPropietarios = laGaleria.getControladorUsuarios().getMapaPropietarios();
            	for (Propietario pp : mapaPropietarios.values()) {
            	    
            	    System.out.println(pp.getLogin());
            	}
            	String compradorName  = pedirCadenaAlUsuario("Ingrese el nombre del propietario al que se le va a regresar la pieza");
            	System.out.println("Las piezas disponibles para hacer devolucion son: \n");
            	for (Pieza pz : piezasDisponibles) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + "con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza a devolver");
            	Pieza piezaReturn = laGaleria.getInventario().buscarPieza(piezaName);
            	Propietario prop = laGaleria.getControladorUsuarios().getMapaPropietarios().get(compradorName);
            	String id = prop.getId();
            	devolucionPieza(prop, piezaReturn, id, admin );
            }
            else if( opcionSeleccionada == 6 )
            {	
            	System.out.println("Los compradores acutales son: \n");
            	for (Comprador cp : mapaCompradores.values()) {
            	    
            	    System.out.println(cp.getLogin());
            	}
            	String compradorName  = pedirCadenaAlUsuario("Ingrese el nombre del comprador");
            	Comprador compa = mapaCompradores.get(compradorName);
            	verificarSeriedadOferta(admin, compa);
            }
            else if( opcionSeleccionada == 7 )
            {	
            	System.out.println("Las piezas disponibles son: \n");
            	for (Pieza pz : piezasDisponibles) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + " y su estado de bloqueo es: " + pz.isBloqueada());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza a bloquear");
            	Pieza piezaReturn = laGaleria.getInventario().buscarPieza(piezaName);
            	bloquearPieza(admin, piezaName, piezaReturn);
            }
            else if( opcionSeleccionada == 8 )
            {	
            	System.out.println("Las piezas disponibles son: \n");
            	for (Pieza pz : piezasDisponibles) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + " y su estado de bloqueo es: " + pz.isBloqueada());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza a desbloquear");
            	Pieza piezaReturn = laGaleria.getInventario().buscarPieza(piezaName);
            	desbloquearPieza(admin, piezaName, piezaReturn);
            }
            else if( opcionSeleccionada == 9 )
            {	
            	System.out.println("Las piezas disponibles son: \n");
            	for (Pieza pz : piezasPasadas) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + " con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza de interes");
            	Pieza pz = laGaleria.getInventario().buscarPieza(piezaName);
            	pz.mostrarHistorial();
            }
            else if( opcionSeleccionada == 10 )
            {	
            	System.out.println("Las piezas disponibles son: \n");
            	for (Pieza pz : piezasPasadas) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + " y su autor es " + pz.getAutor().getNombre());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza de interes");
            	Pieza pz = laGaleria.getInventario().buscarPieza(piezaName);
            	List<Pieza> piezasCreadas = pz.getAutor().getPiezas();
            	for (Pieza creacion : piezasCreadas) {
            		System.out.println("La pieza " + creacion.getTitulo() + " ha sido creada en el " + creacion.getAnioCreacion() + " y fue vendida por" +
            				creacion.getPrecioFijo() + " en el " + creacion.getFechaVenta());
            	}
       	
            }
            else if( opcionSeleccionada == 11 )
            {
                regresar = true;
            }
        }
    	
    }
	
	private void registrarIngresoPieza(Administrador admin, Pieza piezaIngreso) {
		
		
		admin.registrarIngresoPieza(piezaIngreso);
        System.out.println("Se agrego la pieza con la siguiente info a la bodega de la galeria:");
        System.out.println("Título: "+piezaIngreso.getTitulo());
        System.out.println("Año Creación: "+piezaIngreso.getAnioCreacion());
        System.out.println("Lugar Creación: "+piezaIngreso.getLugarCreacion());
        
    }
	private  void verificarComprador(Administrador admin, String id) {
        
        
        System.out.println("El resultado de la verificación de la existencia del comprador con id "+id+ " fue: ");
        System.out.println(admin.verificarComprador(id));
        
    }
    private void aumentarLimite(Administrador admin, String id, Comprador comprador, int newLimite) {
        
        System.out.println("El límite anterior del comprador " + id + " es de: " +comprador.getLimiteCompras());
        admin.aumentarLimite(id, newLimite);
        System.out.println("El nuevo límite del comprador " +id+ " es de: " + comprador.getLimiteCompras());
        
    }

    private void confirmarVenta(Compra compra1, Pieza pz, String id, Administrador admin) {
        
        admin.confirmarVenta(compra1,pz,id);
        System.out.println("Se confirmo la venta de la pieza "+pz.getTitulo()+" por un precio de " + compra1.getValorPagado());
    }

    private  void devolucionPieza(Propietario propietario, Pieza pz, String id, Administrador admin) {
        
        System.out.println("El propietario tenia "+ propietario.getMisPiezasActuales().size()+" piezas.");
        admin.devolucionPieza(pz,id);
        List<Pieza> piezasActuales=propietario.getMisPiezasActuales();
        System.out.println("Se elimino la pieza: "+pz.getTitulo()+ " y al propietario le quedaron: "+ piezasActuales.size()+" piezas.");
        
            
        }

    private void verificarSeriedadOferta(Administrador admin, Comprador comprador) {
        String id = comprador.getLogin();
        System.out.println("El limite de compras del comprador " +id+ "es "+comprador.getLimiteCompras());
        System.out.println("La oferta es de 20000");
        System.out.println(admin.verificarSeriedadOferta(id, 20000));
    }

    private void bloquearPieza(Administrador admin, String piezaName, Pieza pz) {
        admin.bloquearPieza(piezaName);
        System.out.println("Se bloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+ pz.getTitulo());
        System.out.println("Esta bloqueada: " + pz.isBloqueada());
        
    }

    private  void desbloquearPieza(Administrador admin, String piezaName, Pieza pz) {
        admin.desbloquearPieza(piezaName);
        System.out.println("Se desbloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+ pz.getTitulo());
        System.out.println("Esta bloqueada: "+pz.isBloqueada());
    }
	
}
