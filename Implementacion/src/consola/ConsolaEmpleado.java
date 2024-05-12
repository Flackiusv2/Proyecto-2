package consola;

import java.util.List;

import logica.Galeria;
import pieza.Pieza;
import usuario.Cajero;
import usuario.Empleado;


public class ConsolaEmpleado extends ConsolaBasica {
	
	
	private final String[] opcionesCajero = new String[]{"Registrar pago", "Entregar Pieza","Ver historial de una pieza","Ver historia de un artista", "Regresar"};
	private final String[] opcionesOperador = new String[]{"Recibir y registrar oferta","Ver historial de una pieza","Ver historia de un artista", "Regresar"};

	private String rol;
	private Empleado mySelf;
	private Galeria laGaleria;
	List<Pieza> piezasDisponibles;
	
	public ConsolaEmpleado ( Galeria galeria, String rol1, Empleado empleado )
    {
        this.laGaleria = galeria;
        this.rol = rol1;
        this.mySelf = empleado;
        this.piezasDisponibles = laGaleria.getInventario().getPiezasDisponibleVenta();
    }
	
	
	
	public void mostrarMenu( )
    {	
		
		if (rol == "Cajero") {
			mostrarMenuCajero();
		}
		else {
			mostrarMenuOperador();
		}

     }
    	
    
	
	public void mostrarMenuCajero() {
		boolean regresar = false;
		while( !regresar )
        {
            int opcionSeleccionada = mostrarMenu( "Menu de la Galeria", opcionesCajero);
            if( opcionSeleccionada == 1 )
            {	
            	System.out.println("El estado de registro del pago de la compra con id 311589 por el comprador con el id 547293 es: Exitosa!");
                
            }
            else if( opcionSeleccionada == 2 )
            {	
            	String pzDev = pedirCadenaAlUsuario("Ingrese la pieza a devolver");
            	String nombreC = pedirCadenaAlUsuario("Ingrese el nombre del comprador");
            	Pieza pz = laGaleria.getInventario().buscarPieza(pzDev);
            	Cajero yCajero = (Cajero) mySelf; 
            	String id = yCajero.entregarPieza(pz, nombreC);
            	System.out.println("La pieza con título fue entregada exitosamente!");
            }
            else if( opcionSeleccionada == 3 )
            {
            	for (Pieza pz : piezasDisponibles) {
            		System.out.println("Las piezas disponibles son: \n");
            		System.out.println("La pieza " + pz.getTitulo() + " con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza de interes");
            	Pieza pz = laGaleria.getInventario().buscarPieza(piezaName);
            	pz.mostrarHistorial();
            }
            else if( opcionSeleccionada == 4 )
            {
            	for (Pieza pz : piezasDisponibles) {
            		System.out.println("Las piezas disponibles son: \n");
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
            
            else if( opcionSeleccionada == 4 )
            {
                regresar = true;
            }
        }
	}
	
	public void mostrarMenuOperador() {
		boolean regresar = false;
		while( !regresar )
	    {
	        int opcionSeleccionada = mostrarMenu( "Menu de la Galeria", opcionesOperador );
	        if( opcionSeleccionada == 1 )
	        {	
	        	
	            System.out.println("Se recibio la oferta con un valor de 100.000 a la subasta con id 5569871 y el resultado de su registro es: Exitoso!");
	            
	        }
	        else if( opcionSeleccionada == 2 )
            {
            	for (Pieza pz : piezasDisponibles) {
            		System.out.println("Las piezas disponibles son: \n");
            		System.out.println("La pieza " + pz.getTitulo() + "con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza de interes");
            	Pieza pz = laGaleria.getInventario().buscarPieza(piezaName);
            	pz.mostrarHistorial();
            }
            else if( opcionSeleccionada == 3 )
            {
            	for (Pieza pz : piezasDisponibles) {
            		System.out.println("Las piezas disponibles son: \n");
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
	       
	        else if( opcionSeleccionada == 4 )
	        {
	            regresar = true;
	        }
	    }
	}
	
}
