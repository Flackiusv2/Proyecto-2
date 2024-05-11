package pieza;

import java.util.ArrayList;
import java.util.List;

public class Autor {
	
	private String nombre;
    private boolean esAnonimo;
    private List<Pieza> piezasCreadas;

    public Autor(String nombre, boolean esAnonimo) {
        this.nombre = nombre;
        this.esAnonimo = esAnonimo;
        this.piezasCreadas = new ArrayList<Pieza>();
    }
    public List<Pieza> getPiezas() {
    	return piezasCreadas;
    }
    
    public void agregarPieza(Pieza pz) {
    	piezasCreadas.add(pz);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEsAnonimo() {
        return esAnonimo;
    }

    public void setEsAnonimo(boolean esAnonimo) {
        this.esAnonimo = esAnonimo;
    }

    
}
