package usuario;

import java.util.Map;
import java.util.Random;

import logica.Galeria;

import java.util.HashMap;

public class ControladorUsuarios {
    
    private  Map<String, Empleado> mapaEmpleados;
    private  Map<String, Empleado> mapaEmpleadosByName;
    private  Map<String, Comprador> mapaCompradores ;
    private  Map<String, Propietario> mapaPropietarios;
    private Map<String, String> baseDeDatos;
    private Galeria galeria;
    
    public ControladorUsuarios(){
        
        mapaEmpleados = new HashMap<String, Empleado>();
        mapaCompradores = new HashMap<String, Comprador>();
        mapaPropietarios = new HashMap<String, Propietario>();
        baseDeDatos = new HashMap<String, String>();
        mapaEmpleadosByName = new HashMap<String, Empleado>();
    }
    
    public void agregarEmpleadoByName(String nombre, Empleado empleado) {
    	mapaEmpleadosByName.put(nombre, empleado);
    }
    
    public Empleado getEmpleadoByName(String nombre) {
    	return mapaEmpleadosByName.get(nombre);
    }

    public void setGaleria(Galeria galeria){
        this.galeria=galeria;
    }
    public Operador crearOperador(String login, String password, String rol){
        String Nuevoid = Usuario.obtenerNuevoID();
        Operador operador = new Operador(login, password, rol, this.galeria,Nuevoid);
        mapaEmpleados.put(Nuevoid, operador);
        return operador;
    }
     
    public Cajero crearCajero(String login, String password, String rol){
        String Nuevoid = Usuario.obtenerNuevoID();
        Cajero cajero = new Cajero(login, password, rol, this.galeria, Nuevoid);
        mapaEmpleados.put(Nuevoid, cajero);
        return cajero;
    }

    public Administrador crearAdmin(String login, String password, String rol){
        String Nuevoid = Usuario.obtenerNuevoID();
        Administrador administrador = new Administrador(login, password, rol, this.galeria, Nuevoid);
        mapaEmpleados.put(Nuevoid, administrador);
        return administrador;
    }
    
    public void agregarUsuario(String nombre, String password) {
    	baseDeDatos.put(nombre, password);
    }
    
    public Map<String, String> getBaseDeDatos() {
    	return baseDeDatos;
    }

    public Comprador crearComprador(String login, String password, String nombre, String telefono, int limiteCompras){
        String Nuevoid = Usuario.obtenerNuevoID();
        Comprador comprador = new Comprador(login, password, nombre, telefono, limiteCompras, Nuevoid);
        mapaCompradores.put(Nuevoid, comprador);
        return comprador;
    }

    public Propietario crearPropietario(String login, String password, String nombre, String telefono){
        String Nuevoid = Usuario.obtenerNuevoID();
        Propietario propietario = new Propietario(login, password, nombre, telefono,Nuevoid);
        mapaPropietarios.put(Nuevoid, propietario);
        return propietario;
    }
    

    public Empleado obtenerEmpleadoById(String id){
        return mapaEmpleados.get(id);
    }

    public Comprador obtenerComprador(String id){
        return mapaCompradores.get(id);
    }

    public Propietario obtenerPropietario(String id){
        return mapaPropietarios.get(id);
    }

    public void agregarComprador (Comprador comprador){

        mapaCompradores.put(comprador.getLogin(), comprador);
    }
    public void agregarPropietario (Propietario propietario){

        mapaPropietarios.put(propietario.getLogin(), propietario);
    }
    public void agregarEmpleado (Empleado empleado){

        mapaEmpleados.put(empleado.getLogin(), empleado);
    }

    public Map<String, Empleado> getMapaEmpleados(){
        return mapaEmpleados;
    }

    public Map<String, Comprador> getMapaCompradores(){
        return mapaCompradores;
    }

    public Map<String, Propietario> getMapaPropietarios(){
        return mapaPropietarios;
    }

    public void setMapaEmpleados(Map<String, Empleado> mapaEmpleados){
        this.mapaEmpleados = mapaEmpleados;
    }

    public void setMapaCompradores(Map<String, Comprador> mapaCompradores){
        this.mapaCompradores = mapaCompradores;
    }

    public void setMapaPropietarios(Map<String, Propietario> mapaPropietarios){
        this.mapaPropietarios = mapaPropietarios;
    }
    

}
