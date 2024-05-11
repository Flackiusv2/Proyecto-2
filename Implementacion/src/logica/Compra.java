package logica;

import pieza.Pieza;

public class Compra {
    private String id;
    private int valorPagado;
    private String tipoPago;
    private Pieza pieza;

    public Compra(String id, int valorPagado, String tipoPago, Pieza pieza) {
        this.id = id;
        this.valorPagado = valorPagado;
        this.tipoPago = tipoPago;
        this.pieza = pieza;
    }

    public String getId() {
        return id;
    }

    public int getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(int valorPagado) {
        this.valorPagado = valorPagado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public boolean verificarVentaValorFijo(Pieza pieza, int valorPagado){
        if( valorPagado >= pieza.getPrecioFijo() && pieza.isDisponibleVentaValorFijo() && !pieza.isBloqueada()){
            return true;
        }
        return false;
        
        
    }

}