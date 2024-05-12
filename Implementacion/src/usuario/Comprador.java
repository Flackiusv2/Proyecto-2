package usuario;

import java.util.ArrayList;
import java.util.LinkedList;
import logica.Compra;
import java.util.List;
import pieza.Pieza;


public class Comprador extends Cliente {
    private int limiteCompras;
    private List<Compra> misCompras;
    

    public Comprador(String login, String password, String nombre, String telefono, int limiteCompras, String id){
        super(login, password, nombre, telefono, id);
        this.limiteCompras = limiteCompras;
        
        misCompras = new ArrayList<Compra>( );
    }
    public int getLimiteCompras() {
        return limiteCompras;
    }
    public void setLimiteCompras(int limiteCompras) {
        this.limiteCompras = limiteCompras;
    }
    public List<Compra> getmisCompras() {
        return misCompras;
    }
   

    public void agregarCompra(Compra compra) {
         misCompras.add(compra);
    }
    public Compra realizarCompraFija(Pieza pieza){
        // Realiza una compra fija
        if (pieza.isDisponibleVentaValorFijo() && !pieza.isBloqueada() && this.limiteCompras >= pieza.getPrecioFijo()){
            Compra nuevaCompra = new Compra(Usuario.obtenerNuevoID(), pieza.getPrecioFijo(), "Efectivo", pieza);
            this.misCompras.add(nuevaCompra);
            return  nuevaCompra;
        }else {
        	return null;
        }
    }
       
    public void verHistorialCompras(){
        for (Compra compra : misCompras) {
            System.out.println(compra);
        }
    }
    

}
