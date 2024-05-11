package pieza;

public class Impresion extends Pieza {
	
	
	private String tamaño;
	private String resolucion;
	private String tipoDePapel;
	private String acabado;
	
	
	
	public Impresion(String titulo, String anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada,
			String tamaño, String resolucion, String tipoDePapel, String acabado) {
		
		super(titulo, anioCreacion, lugarCreacion, fechaDevolucion, disponibleVentaValorFijo, bloqueada);
		this.tamaño = tamaño;
		this.resolucion = resolucion;
		this.tipoDePapel = tipoDePapel;
		this.acabado = acabado;
	}
	public String getTamaño() {
		return tamaño;
	}
	public String getResolucion() {
		return resolucion;
	}
	public String getTipoDePapel() {
		return tipoDePapel;
	}
	public String getAcabado() {
		return acabado;
	}
	public String getTipoPieza(){
        return "Impresion";
    }
	
	
}
