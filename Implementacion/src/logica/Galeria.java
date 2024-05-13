package logica;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import pieza.Autor;
import pieza.Escultura;
import pieza.Fotografia;
import pieza.Impresion;
import pieza.Pieza;
import pieza.Pintura;
import pieza.Video;
import usuario.Administrador;
import usuario.Cajero;
import usuario.Cliente;
import usuario.Comprador;
import usuario.ControladorUsuarios;
import usuario.Propietario;
import usuario.Empleado;
import usuario.Operador;

public class Galeria {
    private Inventario inventario;
    private ControladorUsuarios controladorUsuarios;
    private Administrador administradorGaleria;
    private Map<String, Subasta> subastas;
    private Map<String, Compra> compras;
    
    public Galeria(Inventario inventario, ControladorUsuarios controladorUsuarios) {
        this.inventario = inventario;
        this.controladorUsuarios = controladorUsuarios;
        this.subastas = new HashMap<String, Subasta>( );
        this.compras = new HashMap<String, Compra>( );
    }


    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public ControladorUsuarios getControladorUsuarios() {
        return controladorUsuarios;
    }

    public Administrador getAdministrador(){
        return administradorGaleria;
    }
    public void setControladorUsuarios(ControladorUsuarios controladorUsuarios) {
        this.controladorUsuarios = controladorUsuarios;
    }

    public void setAdministradorGaleria(Administrador administradorGaleria) {
        this.administradorGaleria = administradorGaleria;
    }
    public Map<String, Subasta> getSubastas() {
        return subastas;
    }

    public void setSubastas(Map<String, Subasta> subastas) {
        this.subastas = subastas;
    }

    public Map<String, Compra> getCompras() {
        return compras;
    }

    public void setCompras(Map<String, Compra> compras) {
        this.compras = compras;
    }

    public void agregarSubasta(Subasta subasta){
        this.subastas.put(subasta.getId(), subasta);
    }

    public void agregarCompra(Compra compra, Comprador compa){
    	compra.getPieza().agregarDueño(compa);
    	compra.getPieza().seVendio("2024/05/21");
        this.compras.put(compra.getId(), compra);
        
    }

    public Subasta encontrarSubasta(String id) {
        return subastas.get(id);

    }
    
    
    public void guardarEstado( File archivo ) throws IOException
    {
        PrintWriter writer = new PrintWriter( archivo );
        
        //Guardamos todas las piezas
        for (Pieza pz : inventario.getPiezasDisponibleVenta()) {
        	
        	String titulo =  pz.getTitulo();
        	String anio = pz.getAnioCreacion();
        	String lugar = pz.getLugarCreacion();
        	String fecha = pz.getFechaDevolucion();
        	boolean disponible = pz.isDisponibleVentaValorFijo();
        	boolean bloqueada = pz.isBloqueada();
        	String autor = pz.getAutor().getNombre();
        	boolean anonimo = pz.getAutor().isEsAnonimo();
        	int precio = pz.getPrecioFijo();
        	 
        	
        	if( pz.getTipoPieza().equals( "Escultura" ) )
            {	
                Escultura es = (Escultura) pz;
                int alto = es.getAlto();
                int ancho = es.getAncho();
                int profundidad = es.getProfundidad();
                int peso = es.getPeso();
                String materiales = es.getMaterialesConstruccion();
                boolean electricidad = es.isNecesitaElectricidad();
                
                writer.println(pz.getTipoPieza() + ":" +  titulo + ":" +  anio + ":" + lugar + ":" + fecha + ":"+  disponible  
            			+ ":"+  bloqueada +":"+  alto + ":" + ancho + ":" + profundidad + ":" + peso + ":"+ materiales + ":" + electricidad
            			+ ":" + autor +":"+  anonimo + ":" + precio );  
                
            }
            else if( pz.getTipoPieza().equals( "Fotografia" ) )
            {
            	Fotografia ft = (Fotografia) pz;
            	String resolucion = ft.getResolucion();
            	String tamanio = ft.getTamanio();            	
            	writer.println(pz.getTipoPieza() + ":" +  titulo + ":" +  anio + ":" + lugar + ":" + fecha + ":"+  disponible  
            			+ ":"+  bloqueada +":"+ resolucion +":"+  tamanio +":"+  autor +":"+  anonimo+ ":" + precio ); 
                
                
            }
            else if( pz.getTipoPieza().equals( "Impresion" ) )
            {
            	Impresion imp = (Impresion) pz;
            	String tamaño = imp.getTamaño();
                String resolucion = imp.getResolucion();
            	String tipoDePapel = imp.getTipoDePapel();
            	String acabado = imp.getAcabado();
            	
            	writer.println(pz.getTipoPieza() + ":" +  titulo + ":" +  anio + ":" + lugar + ":" + fecha + ":"+  disponible  
            			+ ":"+  bloqueada +":"+ tamaño +":"+ resolucion +":"+ tipoDePapel +":"+ acabado +":"+ autor +":"+  anonimo+ ":" + precio ); 
                
            }
            else if( pz.getTipoPieza().equals( "Pintura" ) )
            {
            	Pintura pt =  (Pintura) pz;
            	int alto = pt.getAlto();
            	int ancho = pt.getAncho();
            	String tecnica = pt.getTecnica();
            	
            	writer.println(pz.getTipoPieza() + ":" +  titulo + ":" +  anio + ":" + lugar + ":" + fecha + ":"+  disponible  
            			+ ":"+  bloqueada +":"+ alto +":"+ ancho +":"+ tecnica +":"+ autor +":"+  anonimo+ ":" + precio ); 
                
            }
            else if( pz.getTipoPieza().equals( "Video" ) )
            {
            	Video vd = (Video) pz;
            	String duracion =  vd.getDuracion();
            	String tamanio = vd.getTamanio();
            	
            	writer.println(pz.getTipoPieza() + ":" +  titulo + ":" +  anio + ":" + lugar + ":" + fecha + ":"+  disponible  
            			+ ":"+  bloqueada +":"+ duracion +":"+ tamanio +":"+ autor +":"+  anonimo+ ":" + precio ); 
                
            }
        	
        	
        	
        	
        }
        
        //Guardamos todos los compradores porque actuan como propietarios tambien
        for( Comprador cli : controladorUsuarios.getMapaCompradores().values( ) )
        {
            writer.println("comprador:" + cli.getLogin() +":"+ cli.getPassword()+":"+ cli.getNombre()+":"+ cli.getTelefono()
            +":"+ cli.getLimiteCompras() +":"+ cli.getId());
            
            
        }
        
        writer.close( );
    }
        
    public void guardarEmpleados( File archivo ) throws IOException
    {
        PrintWriter writer = new PrintWriter( archivo );
      //Guardamos a todos los empleados
        for (Empleado empl : controladorUsuarios.getMapaEmpleados().values()) {
        	if (empl.getRol().equals("Cajero")) {
        		
        		writer.println("cajero:" +empl.getLogin() +":"+ empl.getPassword() +":"+ empl.getRol() +
        				":" + empl.getId() );
        	}else {
        		
        		writer.println("operador:" +empl.getLogin() +":"+ empl.getPassword() +":"+ empl.getRol() +
        				":" + empl.getId() );
        	}
        }
        //Guardamos al admin de la galeria
        writer.println("admin:" + administradorGaleria.getLogin() +":"+ administradorGaleria.getPassword() +":"+ administradorGaleria.getRol() + ":" + administradorGaleria.getId());
        
        writer.close( );
    }

    public static Galeria cargarEstado( File archivo ) throws FileNotFoundException, IOException, NumberFormatException
    {	
    	Inventario cInventario = new Inventario();
    	ControladorUsuarios control = new ControladorUsuarios();
        

        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            if( partes[ 0 ].equals( "Escultura" ) )
            {	
                boolean disponible;
                boolean bloqueada;
                boolean anonimo;
            	String titulo = partes[1];
                String anio = partes[2];
                String lugar = partes [3];
                String fecha = partes [4];
                String Sdisponible = partes[5];
                String Sbloqueada = partes[6];
                int alto = Integer.parseInt(partes[7]);
                int  ancho = Integer.parseInt(partes[8]);
                int  profundidad = Integer.parseInt(partes[9]);
                int  peso = Integer.parseInt(partes[10]);
                String material = partes[11];
                String Selectricidad = partes[12];
                boolean electricidad = Boolean.parseBoolean(Selectricidad);
                String nombreAutor = partes[13];
                String Sanonimo = partes[14];
                int precio = Integer.parseInt(partes[15]);
                
                if (Sdisponible == "true") {
                	disponible = true;
                }else {
                		disponible = false;
                }
                if (Sbloqueada == "true") {
                	bloqueada = true;
                }else {
                		bloqueada = false;
                }
                if (Sanonimo == "true") {
                	anonimo = true;
                }else {
                		anonimo = false;
                }
                
                Autor nuevoAutor = new Autor(nombreAutor, anonimo);
                
                
                Escultura nuevaEscultura = new Escultura(titulo,anio,lugar,fecha,disponible,bloqueada,
                		alto,ancho,profundidad,peso,material,electricidad);
                
                nuevaEscultura.setPrecioFijo(precio);
                nuevaEscultura.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaEscultura);
                cInventario.pasarAExhibicion(nuevaEscultura);
                cInventario.pasarAPasadas(nuevaEscultura);
            }
            else if( partes[ 0 ].equals( "Fotografia" ) )
            {
            	boolean disponible;
                boolean bloqueada;
                boolean anonimo;
            	String titulo = partes[ 1 ];
                String anio = partes[2];
                String lugar = partes [3];
                String fecha = partes [4];
                String Sdisponible = partes[5];
                String Sbloqueada = partes[6];
                String resolucion = partes[7];
                String tamanio = partes[8];
                String nombreAutor = partes[9];
                String Sanonimo = partes[10];
                int precio = Integer.parseInt(partes[11]);
                
                if (Sdisponible == "true") {
                	disponible = true;
                }else {
                		disponible = false;
                }
                if (Sbloqueada == "true") {
                	bloqueada = true;
                }else {
                		bloqueada = false;
                }
                if (Sanonimo == "true") {
                	anonimo = true;
                }else {
                		anonimo = false;
                }
                
                Autor nuevoAutor = new Autor(nombreAutor, anonimo);
                
                
                Fotografia nuevaFoto  = new Fotografia(titulo,anio,lugar,fecha,disponible,bloqueada,
                		resolucion,tamanio);
                
                nuevaFoto.setPrecioFijo(precio);
                nuevaFoto.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaFoto);
                cInventario.pasarAExhibicion(nuevaFoto);
                cInventario.pasarAPasadas(nuevaFoto);
                
            }
            else if( partes[ 0 ].equals( "Impresion" ) )
            {
            	boolean disponible;
                boolean bloqueada;
                boolean anonimo;
            	String titulo = partes[ 1 ];
                String anio = partes[2];
                String lugar = partes [3];
                String fecha = partes [4];
                String Sdisponible = partes[5];
                String Sbloqueada = partes[6];
                String tamanio = partes[7];
                String resolucion = partes[8];
                String tipoPapel = partes[9];
                String acabado = partes[10];
                String nombreAutor = partes[11];
                String Sanonimo = partes[12];
                int precio = Integer.parseInt(partes[13]);
                
                if (Sdisponible == "true") {
                	disponible = true;
                }else {
                		disponible = false;
                }
                if (Sbloqueada == "true") {
                	bloqueada = true;
                }else {
                		bloqueada = false;
                }
                if (Sanonimo == "true") {
                	anonimo = true;
                }else {
                		anonimo = false;
                }
                
                Autor nuevoAutor = new Autor(nombreAutor, anonimo);
                
                
                Impresion nuevaImpresion  = new Impresion(titulo,anio,lugar,fecha,disponible,bloqueada,
                		tamanio,resolucion,tipoPapel,acabado);
                
                nuevaImpresion.setPrecioFijo(precio);
                nuevaImpresion.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaImpresion);
                cInventario.pasarAExhibicion(nuevaImpresion);
                cInventario.pasarAPasadas(nuevaImpresion);
                
            }
            else if( partes[ 0 ].equals( "Pintura" ) )
            {
            	boolean disponible;
                boolean bloqueada;
                boolean anonimo;
            	String titulo = partes[ 1 ];
                String anio = partes[2];
                String lugar = partes [3];
                String fecha = partes [4];
                String Sdisponible = partes[5];
                String Sbloqueada = partes[6];
                int alto = Integer.parseInt(partes[7]);
                int ancho = Integer.parseInt(partes[8]);
                String tecnica = partes[9];
                String nombreAutor = partes[10];
                String Sanonimo = partes[11];
                int precio = Integer.parseInt(partes[12]);
                
                if (Sdisponible.equals("true")) {
                	disponible = true;
                }else {
                		disponible = false;
                }
                if (Sbloqueada.equals("true")) {
                	bloqueada = true;
                }else {
                		bloqueada = false;
                }
                if (Sanonimo.equals("true")) {
                	anonimo = true;
                }else {
                		anonimo = false;
                }
                
                Autor nuevoAutor = new Autor(nombreAutor, anonimo);
                
                
                Pintura nuevaPintura   = new Pintura(titulo,anio,lugar,fecha,disponible,bloqueada,
                		alto,ancho,tecnica);
                
                nuevaPintura.setPrecioFijo(precio);
                nuevaPintura.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaPintura);
                cInventario.pasarAExhibicion(nuevaPintura);
                cInventario.pasarAPasadas(nuevaPintura);
                
            }
            else if( partes[ 0 ].equals( "Video" ) )
            {
            	boolean disponible;
                boolean bloqueada;
                boolean anonimo;
            	String titulo = partes[ 1 ];
                String anio = partes[2];
                String lugar = partes [3];
                String fecha = partes [4];
                String Sdisponible = partes[5];
                String Sbloqueada = partes[6];
                String nombreAutor = partes[7];
                String duracion = partes[8];
                String tamanio = partes[9];
                String Sanonimo = partes[10];
                int precio = Integer.parseInt(partes[11]);
                
                if (Sdisponible == "true") {
                	disponible = true;
                }else {
                		disponible = false;
                }
                if (Sbloqueada == "true") {
                	bloqueada = true;
                }else {
                		bloqueada = false;
                }
                if (Sanonimo == "true") {
                	anonimo = true;
                }else {
                		anonimo = false;
                }
                
                Autor nuevoAutor = new Autor(nombreAutor, anonimo);
                
                
                Video nuevoVideo = new Video(titulo,anio,lugar,fecha,disponible,bloqueada,
                		duracion,tamanio);
                
                nuevoVideo.setPrecioFijo(precio);
                nuevoVideo.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevoVideo);
                cInventario.pasarAExhibicion(nuevoVideo);
                cInventario.pasarAPasadas(nuevoVideo);
                
            }
            else if( partes[ 0 ].equals("comprador" ) )
            {
            	String nombre = partes[ 1 ];
                String password = partes[2];
                String nickname = partes [3];
                String telefono = partes [4];
                String Slimite = partes[5];
                String id =  partes[6];
               
        		int limite = Integer.parseInt(Slimite);
        		Comprador nuevoComprador =  new Comprador(nombre,password,nickname,telefono,limite,id);
        		control.agregarComprador(nuevoComprador);
        		control.agregarUsuario(nombre, password);
            }
            
            
            
            
            line = br.readLine( );
        }
        br.close( );

        Galeria nuevaGaleria = new Galeria( cInventario, control );
        return nuevaGaleria;
    
    }
    
    public static Galeria cargarEmpleados( File archivo, Galeria laGaleria ) throws FileNotFoundException, IOException, NumberFormatException
    {	
    	
        BufferedReader br = new BufferedReader( new FileReader( archivo ) );
        String line = br.readLine( );
        while( line != null )
        {
            String[] partes = line.split( ":" );
            if( partes[ 0 ].equals("cajero" ) )
            {
            	String nombre = partes[ 1 ];
                String password = partes[2];
                String rol = partes [3];
                String id =  partes[4];
                
                Cajero nuevoCajero  =  new Cajero(nombre,password,rol,laGaleria,id);
                laGaleria.getControladorUsuarios().agregarEmpleado(nuevoCajero);
				laGaleria.getControladorUsuarios().agregarEmpleadoByName(nombre, nuevoCajero);
				laGaleria.getControladorUsuarios().agregarUsuario(nombre, password);
            }
            else if( partes[ 0 ].equals("operador" ) )
            {
            	String nombre = partes[ 1 ];
                String password = partes[2];
                String rol = partes [3];
                String id =  partes[4];
                
                Operador nuevoOperador  =  new Operador(nombre,password,rol,laGaleria,id);
                laGaleria.getControladorUsuarios().agregarEmpleado(nuevoOperador);
				laGaleria.getControladorUsuarios().agregarEmpleadoByName(nombre, nuevoOperador);
				laGaleria.getControladorUsuarios().agregarUsuario(nombre, password);
            }
            else if( partes[ 0 ].equals("admin" ) )
            {
            	String nombre = partes[ 1 ];
                String password = partes[2];
                String rol = partes [3];
                String id =  partes[4];
                
                Administrador nuevoAdmin =  new Administrador(nombre,password,rol,laGaleria,id);
                laGaleria.setAdministradorGaleria(nuevoAdmin);
                laGaleria.getControladorUsuarios().agregarUsuario(nombre, password);
        		
            }
            line = br.readLine( );
        }
        br.close( );

        return laGaleria;
    }
}
    

