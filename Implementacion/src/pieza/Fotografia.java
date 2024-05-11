package pieza;

public class Fotografia extends Pieza{
	
	private String resolucion;
    private String tamanio;

    public Fotografia(String titulo, String anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada, String resolucion, String tamanio) {
        super(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada);
        this.resolucion = resolucion;
        this.tamanio = tamanio;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getTipoPieza(){
        return "Fotografia";
    }


}
