package consola;

import logica.Galeria;
import usuario.Administrador;

public class ConsolaCrearEmpleados extends ConsolaBasica {
	
	
	private final String[] opcionesCrearEmpleado = new String[]{ "Nuevo Administrador", "Nuevo Cajero", "Nuevo operador", "Guardar y regresar"};
	
	private Galeria laGaleria;

    public ConsolaCrearEmpleados( Galeria galeria )
    {
        this.laGaleria = galeria;
    }
    
    public Galeria mostrarOpciones( )
    {
       
        boolean regresar = false;

        while( !regresar )
        {
            
            int opcionSeleccionada = mostrarMenu( "Menú para crear empelados", opcionesCrearEmpleado );
            if( opcionSeleccionada == 1 )
            {
            	String nombre = pedirCadenaAlUsuario( "Ingrese su nombre de usuario" );
				String password = pedirCadenaAlUsuario( "Ingrese su contraseña" );
				String id = obtenerNuevoID();
				Administrador nuevoAdmin = new Administrador(nombre, password, "AdminGeneral", laGaleria,  id );
                
				laGaleria.setAdministradorGaleria(nuevoAdmin);
				laGaleria.getControladorUsuarios().agregarUsuario(nombre, password);
            }
            
            else if (opcionSeleccionada == 2) {
            	String nombre = pedirCadenaAlUsuario( "Ingrese su nombre de usuario" );
				String password = pedirCadenaAlUsuario( "Ingrese su contraseña" );
				String id = obtenerNuevoID();
				Administrador nuevoCajero = new Administrador(nombre, password, "Cajero", laGaleria,  id );
                
				laGaleria.getControladorUsuarios().agregarEmpleado(nuevoCajero);
				laGaleria.getControladorUsuarios().agregarEmpleadoByName(nombre, nuevoCajero);
				laGaleria.getControladorUsuarios().agregarUsuario(nombre, password);
            }
            
            else if( opcionSeleccionada == 3 )
            {
            	String nombre = pedirCadenaAlUsuario( "Ingrese su nombre de usuario" );
				String password = pedirCadenaAlUsuario( "Ingrese su contraseña" );
				String id = obtenerNuevoID();
				Administrador nuevoOperador = new Administrador(nombre, password, "Operador", laGaleria,  id );
                
				laGaleria.getControladorUsuarios().agregarEmpleado(nuevoOperador);
				laGaleria.getControladorUsuarios().agregarEmpleadoByName(nombre, nuevoOperador);
				laGaleria.getControladorUsuarios().agregarUsuario(nombre, password);
            }
            else if( opcionSeleccionada == 4 )
            {
                regresar = true;
            }
        }
        return laGaleria;

    }
	
	
}
