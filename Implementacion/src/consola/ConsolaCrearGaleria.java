package consola;



import logica.Galeria;
import logica.Inventario;
import usuario.ControladorUsuarios;

public class ConsolaCrearGaleria extends ConsolaBasica {
	
	
	private final String[] opcionesCrearGaleria = new String[]{ "Crear inventario",  "Crear clientes", "Crear galeria(PASO FINAL)", "Regresar sin crear una galeria" };
		
    
    private Inventario elInventario;
    
    private ControladorUsuarios elControlador;
	
	public Galeria mostrarOpciones( )
    {
        Galeria nuevaGaleria = null;
        boolean regresar = false;

        while( nuevaGaleria == null && !regresar )
        {
            
            int opcionSeleccionada = mostrarMenu( "Menú de creación de la galeria", opcionesCrearGaleria );
            if( opcionSeleccionada == 1 )
            {
                ConsolaCrearInventario consolaInventario =  new ConsolaCrearInventario();
                elInventario = consolaInventario.mostrarOpciones();
                
            }
            
            else if (opcionSeleccionada == 2) {
            	ConsolaCrearClientes consolaUsuarios =  new ConsolaCrearClientes();
                elControlador = consolaUsuarios.mostrarOpciones();
            	
            }
            
            else if( opcionSeleccionada == 3 )
            {
                boolean todoOk = true;
                if( this.elControlador.getMapaCompradores().size( ) == 0 )
                {
                    System.out.println( "No se puede crear una galeria sin clientes" );
                    todoOk = false;
                }
                if( this.elInventario.getPiezasDisponibleVenta().size( ) == 0 )
                {
                    System.out.println( "No se puede crear una galeria sin piezas" );
                    todoOk = false;
                }

                if( todoOk )
                    nuevaGaleria = new Galeria( elInventario, elControlador);
            }
            else if( opcionSeleccionada == 4 )
            {
                regresar = true;
            }
        }

        return nuevaGaleria;
    }
	
	

	
	
	
	
	
}
