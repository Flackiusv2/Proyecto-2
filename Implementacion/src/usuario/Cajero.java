package usuario;


import logica.Compra;

import pieza.Pieza;
import logica.Galeria;

public class Cajero extends Empleado{
    private Galeria galeria;
    public Cajero(String login, String password, String rol, Galeria galeria,String id){
        super(login, password, rol,id);
        this.galeria=galeria;
    }
    public String registrarPago(Compra compra, Pieza pieza, String idComprador){
        this.galeria.getAdministrador().confirmarVenta(compra, pieza, idComprador);
        return "Pago registrado";
    }
    public String entregarPieza(Pieza pieza, String idComprador){
        // Entrega una pieza
        if (this.galeria.getAdministrador().verificarComprador(idComprador)){
            this.galeria.getInventario().getPiezasEnBodega().remove(pieza);
            this.galeria.getInventario().getPiezasEnExhibicion().add(pieza);
            String nombre = pieza.getTitulo();
            this.galeria.getAdministrador().desbloquearPieza(nombre);

            //agregar a misPiezasActuales del propietario antes comprador
            if (this.galeria.getControladorUsuarios().obtenerPropietario(idComprador) != null){
                this.galeria.getControladorUsuarios().obtenerPropietario(idComprador).getMisPiezasActuales().add(pieza);
                return idComprador;
            }
            else{
                Comprador comprador = this.galeria.getControladorUsuarios().obtenerComprador(idComprador);
                Propietario propietario=this.galeria.getControladorUsuarios().crearPropietario(comprador.getLogin(),comprador.getPassword(),comprador.getNombre(),comprador.getTelefono());
                galeria.getControladorUsuarios().agregarPropietario(propietario);
                propietario.agregarPieza(pieza);
                return propietario.getId();
            }
        }
        return idComprador;
       
    }
    
}
