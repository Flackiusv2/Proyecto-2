package consola;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import pieza.Pieza;



public abstract class ConsolaBasica {
	
	/**
     * Le pide al usuario que ingrese una cadena de caracteres
     * @param mensaje El mensaje con el que se solicita la información
     * @return La cadena introducida por el usuario
     */
    protected String pedirCadenaAlUsuario( String mensaje )
    {
        try
        {
            System.out.print( mensaje + ": " );
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
            String input = reader.readLine( );
            return input;
        }
        catch( IOException e )
        {
            System.out.println( "Error leyendo de la consola" );
        }
        return "error";
    }
    
    /**
     * Le pide confirmación al usuario, indicándole que debe responder 'si' o 'no'.
     * @param mensaje El mensaje con el que se solicita la información
     * @return Retorna true únicamente si el usuario responde 'sí', 'si' o 'si', independientemente de las minúsculas y las mayúsculas. Retorna false en cualquier otro caso.
     */
    protected boolean pedirConfirmacionAlUsuario( String mensaje )
    {
        try
        {
            System.out.print( mensaje + " (Responda 'si' o 'no' ) " );
            BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
            String input = reader.readLine( ).toLowerCase( );
            boolean respuesta = false;
            if( input.equals( "si" ) || input.equals( "sí" ) || input.equals( "s" ) )
                respuesta = true;

            return respuesta;
        }
        catch( IOException e )
        {
            System.out.println( "Error leyendo de la consola" );
        }
        return false;
    }
    
    /**
     * Le pide al usuario que ingrese un número que no puede tener cifras decimales
     * @param mensaje El mensaje con el que se solicita la información
     * @return El valor introducido por el usuario
     */
    protected int pedirEnteroAlUsuario( String mensaje )
    {
        int valorResultado = Integer.MIN_VALUE;
        while( valorResultado == Integer.MIN_VALUE )
        {
            try
            {
                System.out.print( mensaje + ": " );
                BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
                String input = reader.readLine( );
                int numero = Integer.parseInt( input );
                valorResultado = numero;
            }
            catch( NumberFormatException nfe )
            {
                System.out.println( "El valor digitado no es un entero" );
            }
            catch( IOException e )
            {
                System.out.println( "Error leyendo de la consola" );
            }
        }
        return valorResultado;
    }

	protected int mostrarMenu( String nombreMenu, String[] opciones )
    {
        System.out.println( "\n---------------------" );
        System.out.println( nombreMenu );
        System.out.println( "---------------------" );

        for( int i = 1; i <= opciones.length; i++ )
        {
            System.out.println( " " + i + ". " + opciones[ i - 1 ] );
        }
        String opcion = pedirCadenaAlUsuario( "Escoja la opción deseada" );
        try
        {
            int opcionSeleccionada = Integer.parseInt( opcion );
            if( opcionSeleccionada > 0 && opcionSeleccionada <= opciones.length )
                return opcionSeleccionada;
            else
            {
                System.out.println( "Esa no es una opción válida. Digite solamente números entre 1 y " + opciones.length );
                return mostrarMenu( nombreMenu, opciones );
            }
        }
        catch( NumberFormatException nfe )
        {
            System.out.println( "Esa no es una opción válida. Digite solamente números." );
            return mostrarMenu( nombreMenu, opciones );
        }
    }
	
	protected void mostrarEstadoActual( int cantidadPiezas, Collection<Pieza> piezasInventario, String[] nombresClientes )
    {
        mostrarInformacionBasica( cantidadPiezas, piezasInventario );
        System.out.println( "Los clientes son: " + Arrays.toString( nombresClientes ) );
        System.out.println( "******************\n" );
    }
	
	private void mostrarInformacionBasica( int cantidadPiezas, Collection<Pieza> PizasGaleria )
    {	
		int max = 0;
		String nomMax = "";
		int min = 0;
		String nomMin = "";
        System.out.println( "\n******************" );
        System.out.println( "ESTADO ACTUAL" );
        System.out.println( "La galeria tiene actualmente " + cantidadPiezas + " piezas");
        System.out.println( "Las pieza mas cara y barata son:" );
        for( Pieza pz : PizasGaleria )
        {
            if (pz.getPrecioFijo() > max) {
            	max  = pz.getPrecioFijo();
            	nomMax = pz.getTitulo();
            }else if (min == 0) {
            	min = pz.getPrecioFijo();
            	nomMin = pz.getTitulo();
            }else if (pz.getPrecioFijo() < min) {
            	min = pz.getPrecioFijo();
            	nomMin = pz.getTitulo();
            }     
        }
        System.out.println("La pieza mas cara es: " +  nomMax + ", su precio es de " + Integer.toString(max));
        System.out.println("La pieza mas barata es: " +  nomMin + ", su precio es de " + Integer.toString(min));
        
        
    }
	protected  String obtenerNuevoID() {
        StringBuilder idGenerado = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int digito = random.nextInt(10); // Dígitos de 0 a 9
            idGenerado.append(digito);
        }

        return idGenerado.toString();
    }
	
}


