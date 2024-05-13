package consola;

import java.util.List;
import java.util.Map;

import logica.Compra;
import logica.Galeria;
import pieza.Pieza;
import usuario.Administrador;
import usuario.Comprador;

public class ConsolaComprador extends ConsolaBasica {
	
	
	private final String[] opcionesComprador = new String[]{"Realizar compra fija", "Ver historial de compras", "Ver historial de una pieza","Ver historia de un artista", "Salir"};

	
	private Galeria laGaleria;
	Comprador myself;
	List<Pieza> piezasDisponibles; 
	List<Pieza> piezasPasadas; 
	
	public ConsolaComprador ( Galeria galeria, Comprador yo  )
    {
        this.laGaleria = galeria;
    	this.piezasDisponibles = laGaleria.getInventario().getPiezasDisponibleVenta();
    	this.myself = yo;
    	this.piezasPasadas = laGaleria.getInventario().getPiezasPasadas();
    }
	
	public void mostrarMenu( )
    {
        boolean regresar = false;

        while( !regresar )
        {
            int opcionSeleccionada = mostrarMenu( "Menu de la Galeria", opcionesComprador );
            if( opcionSeleccionada == 1 )
            {	
            	System.out.println("Las piezas disponibles son: \n");
            	for (Pieza pz : piezasDisponibles) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + " con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza que desea comprar");
            	Pieza piezaCompra = laGaleria.getInventario().buscarPieza(piezaName);
            	realizarCompraF(piezaCompra, myself);
            }
            else if( opcionSeleccionada == 2 )
            {
            	List<Compra> misCompras = myself.getmisCompras();
            	System.out.println("Tienes un total de " + misCompras.size() + " compras");
            	
            	for (Compra miCompra : misCompras) {
            		System.out.println("Pieza:"+ miCompra.getPieza().getTitulo()+ ", valor Pagado: "+ miCompra.getValorPagado());
            	}
            	
            }
            
            else if( opcionSeleccionada == 3 )
            {	
            	System.out.println("Las piezas disponibles son: \n");
            	for (Pieza pz : piezasPasadas) {
            		
            		System.out.println("La pieza " + pz.getTitulo() + " con un valor de " + pz.getPrecioFijo());  
            		
            	}
            	String piezaName  = pedirCadenaAlUsuario("Ingrese el nombre de la pieza de interes");
            	Pieza pz = laGaleria.getInventario().buscarPieza(piezaName);
            	pz.mostrarHistorial();
            }
            else if( opcionSeleccionada == 4 )
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
            else if( opcionSeleccionada == 5 )
            {
                regresar = true;
            }
        }
    	
    } 
	
	public  void realizarCompraF(Pieza pz, Comprador yo) {
		
	    System.out.println("El resultado de la compra de la pieza "+ pz.getTitulo()+ " fue: "); 
	    Compra nuevaCompra = yo.realizarCompraFija(pz); 
	    if (nuevaCompra == null){
	    	System.out.println("Compra no exitosa, probablemente no tengas suficiente dinero!");
	    }else {
	    
	    laGaleria.agregarCompra(nuevaCompra,  yo);
	    laGaleria.getInventario().realizarCompra(pz);
	    System.out.println("Compra existosa!");
	    }
	}
	
	
	
	
}
