package consola;

import java.util.Map;

import logica.Galeria;
import usuario.Comprador;
import usuario.Empleado;

public class ConsolaInicioSesion extends ConsolaBasica{
	
	private final String[] opcionesInicioSesion = new String[]{ "Iniciar sesion como Admin", "Iniciar sesion como empleado","Iniciar sesion como comprador", "Regresar" };

	Map<String,String> MapaUsuarios;
	
	private Galeria laGaleria;
	
	public ConsolaInicioSesion ( Galeria galeria  )
    {
        this.laGaleria = galeria;
        this.MapaUsuarios = laGaleria.getControladorUsuarios().getBaseDeDatos();
    }
	
	

	
    
	public void mostrarMenu( )
    {
        boolean regresar = false;

        while( !regresar )
        {
        	int opcionSeleccionada = mostrarMenu( "Menu de inicio de sesion", opcionesInicioSesion );
            if( opcionSeleccionada == 1 )
            {
            	String nombre = pedirCadenaAlUsuario( "Ingrese su nombre de usuario" );
        		String password = pedirCadenaAlUsuario( "Ingrese su contraseña" );
        		String password_database = MapaUsuarios.get(nombre);
        		String password_as_admin = laGaleria.getAdministrador().getPassword();
        		
        		
        		if (password.equals(password_database)) {
        			if (password_as_admin.equals(password)) {
        				System.out.println("Se ha iniciado sesion correctamente!");
        				ConsolaAdmin consolaAdmin = new ConsolaAdmin( laGaleria );
        				consolaAdmin.mostrarMenu();
        				
        			}else {
        				System.out.println("No eres administrador de la galeria!");
        			}
        			
        		}else {
        			System.out.println("Contraseña incorrecta, intente nuevamente!");
        		}
        		
            }
            else if( opcionSeleccionada == 2 )
            {
            	String nombre = pedirCadenaAlUsuario( "Ingrese su nombre de usuario" );
        		String password = pedirCadenaAlUsuario( "Ingrese su contraseña" );
        		String rol = pedirCadenaAlUsuario("Cual es su rol de empleado(Operador/Cajero)");
        		String password_verificacion = MapaUsuarios.get(nombre);
   
        		if (password.equals(password_verificacion)) {
        			Empleado empl_verficacion = laGaleria.getControladorUsuarios().getEmpleadoByName(nombre);
        			String rol_check = empl_verficacion.getRol();
        			String password_empl_check = empl_verficacion.getPassword();
        			if (rol_check.equals(rol)) {
	        			if (password_empl_check.equals(password)) {
		        			System.out.println("Incio de sesion exitoso!");
		        			Empleado nuevoEmpleado = laGaleria.getControladorUsuarios().getEmpleadoByName(nombre);
		        			ConsolaEmpleado consolaEmpl = new ConsolaEmpleado( laGaleria, rol, nuevoEmpleado );
		        			consolaEmpl.mostrarMenu();
		        		}else {
		        			System.out.println("No eres un empleado de la galeria!");
		        		}
        			}else {
        				System.out.println("Su rol no coincide con el registrado en el sistema");
        			}
        		}else {
        			System.out.println("Contraseña incorrecta, intente nuevamente!");
        		}
            }
            
            else if( opcionSeleccionada == 3 )
            {
            	String nombre = pedirCadenaAlUsuario( "Ingrese su nombre de usuario" );
        		String password = pedirCadenaAlUsuario( "Ingrese su contraseña" );
        		String password_verificacion = MapaUsuarios.get(nombre);
	
        		if (password.equals(password_verificacion)) { 		
        			Comprador mySelf = laGaleria.getControladorUsuarios().getMapaCompradores().get(nombre);
        			ConsolaComprador consolaComprador = new ConsolaComprador( laGaleria, mySelf);
        			consolaComprador.mostrarMenu();
        			
        		}else {
        			System.out.println("Contraseña incorrecta, intente nuevamente!");
        		}
            }
            
            else if( opcionSeleccionada == 4 )
            {
                regresar = true;
            }
            
            
        }
    	
    } 
}
