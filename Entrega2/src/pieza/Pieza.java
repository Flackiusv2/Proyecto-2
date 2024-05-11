package pieza;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import usuario.Cliente;

public abstract class Pieza {
	
	private String titulo;
    private Autor autor;
    private String año;
    private String lugarCreacion;
    private String fechaDevolucion;
    private boolean disponibleVentaValorFijo;
    private boolean bloqueada;
    private int precioFijo;
    public abstract String getTipoPieza();
    public boolean vendida;
    public String fechaVenta;
    public List<Cliente> dueños;

    public Pieza(String titulo, String anioCreacion, String lugarCreacion, String fechaDevolucion, boolean disponibleVentaValorFijo, boolean bloqueada) {
        this.titulo = titulo;
        this.año = anioCreacion;
        this.lugarCreacion = lugarCreacion;
        this.fechaDevolucion = fechaDevolucion;
        this.disponibleVentaValorFijo = disponibleVentaValorFijo;
        this.bloqueada = bloqueada;
        this.precioFijo = 0;
        this.vendida = false;
        this.fechaVenta = "";
        this.dueños = new ArrayList<Cliente>();
    }
    
    public void mostrarHistorial() {
    	System.out.println("Titulo de la pieza: " + this.titulo);
    	System.out.println("La pieza esta bloqueda: " + this.bloqueada);
    	if (dueños.size() > 0) {
    		System.out.println("Los dueños de la pieza han sido: ");
    		for (Cliente cl : dueños) {
    			System.out.println(cl.getLogin());
    			}
    	}else {
    		System.out.println("Esta pieza no ha sido vendida aun!");
    	}
    	
    	
    	if (vendida) {
    		System.out.println("La pieza ha sido vendida por " + getPrecioFijo() + " en la fecha " + getFechaVenta());
    	}else {
    		
    	}
    }
    
    public void agregarDueño(Cliente cl) {
    	dueños.add(cl);
    }

    public void seVendio(String fecha) {
    	vendida = true;
    	fechaVenta = fecha;
    }
    
    public String getFechaVenta() {
    	return fechaVenta;
    }
    
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor1) {
        this.autor = autor1;
    }

    public String getAnioCreacion() {
        return año;
    }

    public void setAnioCreacion(String anioCreacion) {
        this.año = anioCreacion;
    }

    public String getLugarCreacion() {
        return lugarCreacion;
    }

    public void setLugarCreacion(String lugarCreacion) {
        this.lugarCreacion = lugarCreacion;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDisponibleVentaValorFijo() {
        return disponibleVentaValorFijo;
    }

    public void setDisponibleVentaValorFijo(boolean disponibleVentaValorFijo) {
        this.disponibleVentaValorFijo = disponibleVentaValorFijo;
    }

    public boolean isBloqueada() {
        return bloqueada;
    }

    public void setBloqueada(boolean bloqueada) {
        this.bloqueada = bloqueada;
    }


    public int getPrecioFijo() {
        return precioFijo;
    }
    public int setPrecioFijo() {
        return precioFijo;
    }
}
