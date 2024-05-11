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
import usuario.Cliente;
import usuario.Comprador;
import usuario.ControladorUsuarios;
import usuario.Propietario;
import usuario.Empleado;

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

    public void agregarCompra(Compra compra){
        this.compras.put(compra.getId(), compra);
    }

    public Subasta encontrarSubasta(String id) {
        return subastas.get(id);

    }
    
    
    public void guardarEstado( File archivo ) throws IOException
    {
        PrintWriter writer = new PrintWriter( archivo );
        
        
        for (Pieza pz : inventario.getPiezasDisponibleVenta()) {
        	
        	writer.println(pz.getTipoPieza() + ":" +  pz.getTitulo() + ":" +  pz.getAnioCreacion() +
        			":" + pz.getLugarCreacion() + ":" + pz.getFechaDevolucion() + ":"+  pz.isDisponibleVentaValorFijo() 
        			+ ":"+  pz.isBloqueada()+":"+ pz.getAutor().getNombre()+":"+ pz.getAutor().isEsAnonimo());  
        	
        }
        
        
        for( Comprador cli : controladorUsuarios.getMapaCompradores().values( ) )
        {
            writer.println("comprador:" + cli.getLogin() +":"+ cli.getPassword()+":"+ cli.getNombre()+":"+ cli.getTelefono()
            +":"+ cli.getLimiteCompras() +":"+ cli.getId());
        }
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
            	String titulo = partes[ 1 ];
                String anio = partes[2];
                String lugar = partes [3];
                String fecha = partes [4];
                String Sdisponible = partes[5];
                String Sbloqueada = partes[6];
                String nombreAutor = partes[7];
                String Sanonimo = partes[8];
                
                
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
                		10,10,10,100,"Cemento",false);
                
                nuevaEscultura.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaEscultura);
                cInventario.pasarAExhibicion(nuevaEscultura);
                
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
                String nombreAutor = partes[7];
                String Sanonimo = partes[8];
                
                
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
                		"1920x1080","100");
                
                nuevaFoto.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaFoto);
                cInventario.pasarAExhibicion(nuevaFoto);
                
                
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
                String nombreAutor = partes[7];
                String Sanonimo = partes[8];
                
                
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
                		"1920x1080","100","glossy","troquelo");
                
                nuevaImpresion.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaImpresion);
                cInventario.pasarAExhibicion(nuevaImpresion);
                
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
                String nombreAutor = partes[7];
                String Sanonimo = partes[8];
                
                
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
                
                
                Pintura nuevaPintura   = new Pintura(titulo,anio,lugar,fecha,disponible,bloqueada,
                		10,10,"oleo");
                
                nuevaPintura.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevaPintura);
                cInventario.pasarAExhibicion(nuevaPintura);
                
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
                String Sanonimo = partes[8];
                
                
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
                		"60","100");
                
                nuevoVideo.setAutor(nuevoAutor);
                cInventario.ponerEnDisponibles(nuevoVideo);
                cInventario.pasarAExhibicion(nuevoVideo);
                
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
}
    

