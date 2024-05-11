package consola;

import logica.Galeria;


public class ConsolaUsarGaleria extends ConsolaBasica{
	
	
	private final String[] opcionesUsarGaleria = new String[]{ "Iniciar sesion", "Crear nuevo usuario", "Regresar" };
	
	
	private Galeria laGaleria;

	
    public ConsolaUsarGaleria( Galeria galeria  )
    {
        this.laGaleria = galeria;
    }
    
    
    public void mostrarMenu()
    {
        boolean regresar = false;

        while( !regresar )
        {
            int opcionSeleccionada = mostrarMenu( "Menu de la Galeria", opcionesUsarGaleria );
            if( opcionSeleccionada == 1 )
            {	
            	ConsolaInicioSesion consolaSesion = new ConsolaInicioSesion( laGaleria);
            	consolaSesion.mostrarMenu();
                
            }
            else if( opcionSeleccionada == 2 )
            {
                registrarse();
            }
            else if( opcionSeleccionada == 3 )
            {
                regresar = true;
            }
        }
    	
    }   
    
 
    public void registrarse() {
    	
    }
       
}
    
    
    
    
