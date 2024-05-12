package logica;

import java.util.ArrayList;
import java.util.List;

import pieza.Pieza;

public class Inventario {
	
	private List<Pieza> piezasEnExhibicion;
    private List<Pieza> piezasEnBodega;
    private List<Pieza> piezasPasadas;
    private List<Pieza> piezasDisponibleVenta;

    public Inventario() {
        this.piezasEnExhibicion = new ArrayList<Pieza>( );
        this.piezasEnBodega = new ArrayList<Pieza>( );
        this.piezasPasadas = new ArrayList<Pieza>( );
        this.piezasDisponibleVenta = new ArrayList<Pieza>( );
    }

    public List<Pieza> getPiezasEnExhibicion() {
        return piezasEnExhibicion;
    }

    public List<Pieza> getPiezasEnBodega() {
        return piezasEnBodega;
    }

    public List<Pieza> getPiezasPasadas() {
        return piezasPasadas;
    }

    public List<Pieza> getPiezasDisponibleVenta() {
        return piezasDisponibleVenta;
    }

    public void guardarEnBodega(Pieza pieza){
        this.piezasEnBodega.add(pieza);
    }
    public void ponerEnDisponibles(Pieza pieza){
        this.piezasDisponibleVenta.add(pieza);
    }
    public void pasarAPasadas(Pieza pieza){
        this.piezasPasadas.add(pieza);
    }
    
    public void realizarCompra(Pieza pieza) {
    	int i = 0;
    	for (Pieza pz: piezasDisponibleVenta) {
    		if (pz.equals(pieza)) {
    			break;
    		}else {
    			i +=1;
    		}
    	
    	}
    	piezasDisponibleVenta.remove(i);
    	
    }
    
    
    
    public void pasarAExhibicion(Pieza pieza){
        this.piezasEnExhibicion.add(pieza);
    }

    public void eliminarDeBodega(Pieza pieza){
        this.piezasEnBodega.remove(pieza);
    }

    public Pieza buscarPieza(String titulo){
        for (Pieza pieza : piezasEnExhibicion) {
            if(pieza.getTitulo().equals(titulo)){
                return pieza;
            }
        }
        for (Pieza pieza : piezasEnBodega) {
            if(pieza.getTitulo().equals(titulo)){
                return pieza;
            }
        }
        for (Pieza pieza : piezasPasadas) {
            if(pieza.getTitulo().equals(titulo)){
                return pieza;
            }
        }
        return null;
        
    }

    public void bloquearPieza(String titulo){
        Pieza pieza = buscarPieza(titulo);
        if(pieza != null){
            pieza.setBloqueada(true);
        }
    }

    public void desbloquearPieza(String titulo){
        Pieza pieza = buscarPieza(titulo);
        if(pieza != null){
            pieza.setBloqueada(false);
        }
    }

    
}
