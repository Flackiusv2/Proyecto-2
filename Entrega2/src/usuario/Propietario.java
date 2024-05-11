package usuario;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pieza.Pieza;


public class Propietario extends Cliente{
    private List<Pieza> misPiezasActuales; 
    private List<Pieza> misPiezasPasadas;

    public Propietario(String usuario, String contraseña, String nombre, String telefono, String id){
        super(usuario, contraseña, nombre, telefono,id);
        misPiezasActuales = new ArrayList<Pieza>( );
        misPiezasPasadas = new ArrayList<Pieza>( );

    }

    public List<Pieza> getMisPiezasActuales(){
        return misPiezasActuales;
    }

    public List<Pieza> getMisPiezasPasadas(){
        return misPiezasPasadas;
    }
    public void agregarPieza(Pieza pieza){
        misPiezasActuales.add(pieza);
    }

    public void pasarAPasadas(Pieza pieza){
        misPiezasActuales.remove(pieza);
        misPiezasPasadas.add(pieza);
    }

    public void agregarAPasadas(Pieza pieza){
        misPiezasPasadas.add(pieza);
    }



}