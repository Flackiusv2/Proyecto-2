package logica;



import java.util.LinkedList;
import java.util.List;

import pieza.Pieza;

public class Subasta {
    private String id;
    private boolean vendida;
    private int valorMinimo;
    public int valorInicial;
    private List<Oferta> ofertas;
    private Pieza pieza;

    
    public Subasta(String id,int valorMinimo,int valorInicial, Pieza pieza) {
        this.id = id;
        this.valorInicial=valorInicial;
        this.valorMinimo=valorMinimo;
        this.pieza=pieza;
        ofertas=new LinkedList<Oferta>( );
    }

    public String getId() {
        return id;
    }

    public boolean esVendida() {
        return vendida;
    }
    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public Pieza getPieza() {
        return pieza;
    }
    
    public int getValorMinimo() {
    	return valorMinimo;
    }
    public void agregarOferta(Oferta oferta) {
        ofertas.add(oferta);
    }
    public Boolean evaluarOferta(Oferta oferta){
       int valor=oferta.getValorOferta();
       if (valor>valorInicial){
        return true;
       }
       else {
        return false;
       }
    }
    public String recibirRegistrarOferta(Oferta oferta){
        if (evaluarOferta(oferta)){
            agregarOferta(oferta);
            return "La oferta se agrego correctamente a la subasta";

        }
        else{
            return "La oferta no supero el valor inicial y no pudo ser registrada";
        }
    }
    public String terminarSubasta(){
        Oferta ofertaMax=ofertas.get(0);
        for (Oferta oferta : ofertas) {
            if (oferta.getValorOferta() > ofertaMax.getValorOferta()){
                ofertaMax=oferta;
            }
            
        }
        if (ofertaMax.getValorOferta()>valorMinimo){
            Compra compra= new Compra(id, ofertaMax.getValorOferta(), id,pieza );
            vendida=true;
            ofertaMax.getComprador().agregarCompra(compra);
             return "Se termino la subasta con id:"+id;
        }

        else {
            return "La subasta no se termino dado que no habián ofertas que cumplieran con el valor mínimo";
        }
    }
}
