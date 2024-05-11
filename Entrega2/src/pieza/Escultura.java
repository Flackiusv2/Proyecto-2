package pieza;

import java.util.List;

public class Escultura extends Pieza {

	private int alto;
    private int ancho;
    private int profundidad;
    private int peso;
    private String materialesConstruccion;
    private boolean necesitaElectricidad;

    public Escultura(String titulo, String anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada, int alto, int ancho, int profundidad, int peso, String materialesConstruccion, boolean necesitaElectricidad) {
        super(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada);
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad = profundidad;
        this.peso = peso;
        this.materialesConstruccion = materialesConstruccion;
        this.necesitaElectricidad = necesitaElectricidad;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getMaterialesConstruccion() {
        return materialesConstruccion;
    }

    public void setMaterialesConstruccion(String materialesConstruccion) {
        this.materialesConstruccion = materialesConstruccion;
    }

    public boolean isNecesitaElectricidad() {
        return necesitaElectricidad;
    }

    public void setNecesitaElectricidad(boolean necesitaElectricidad) {
        this.necesitaElectricidad = necesitaElectricidad;
    }


    public String getTipoPieza() {
        return "Escultura";
    }

}

