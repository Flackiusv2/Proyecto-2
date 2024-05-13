package tests;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import logica.Compra;
import logica.Galeria;
import logica.Inventario;
import logica.Oferta;
import logica.Subasta;
import pieza.Autor;
import pieza.Fotografia;
import pieza.Pintura;
import pieza.Video;
import usuario.Administrador;
import usuario.Cajero;
import usuario.Comprador;
import usuario.ControladorUsuarios;
import usuario.Operador;
import usuario.Propietario;

public class CompradorTest {
	Autor autor1;
	Pintura pinturaAgregar;
	Pintura pintura1;
	Video video1;
	Fotografia foto1;
	Fotografia foto2;
	Inventario inventario1;
	ControladorUsuarios controlador;
	Galeria galeriaConsola;
	Comprador comprador;
	Propietario propietario;
	Administrador admin;
	Cajero cajero;
	Operador operador;
	Oferta oferta1;
	Oferta oferta2;
	Oferta ofertaRecibir;
	Subasta subasta1;
	Compra compra1;
	Compra compra2;
	
	@BeforeEach
	void setUp() throws Exception {
	 autor1= new Autor("dabibo", false);
     pinturaAgregar=new Pintura("mariachis", "1996", "Francia","25-20-2018", true, false, 60, 80, "Oleo");
     pintura1=new Pintura("koliflor", "1964", "Italia","05-12-2016", true, true, 70, 40, "Oleo");
     video1= new Video("muajau", "1999", "Korea","20-03-2024", true, false, "45", "360");
     foto1= new Fotografia("el canil", "2003", "Colombia","28-11-2014", true, false, "12", "400");
     foto2= new Fotografia("juriol", "2009", "Mexico","14-05-2015", false, false, "30", "190");
     inventario1= new Inventario();
     controlador= new ControladorUsuarios();
     galeriaConsola = new Galeria(inventario1,controlador);
    
     comprador= new Comprador("amir", "12345", "amir","5975484526", 1000000, "547293");
     propietario= new Propietario("guikol", "15326", "guicol", "1589863153","153628");
     admin= new Administrador("fabian", "5896714", "Admin",galeriaConsola, "758964");
     cajero= new  Cajero("ozuna", "548963", "Cajero",galeriaConsola, "996354");
     operador= new Operador("juanin", "156532", "Operador",galeriaConsola, "476597");
     oferta1= new Oferta(30000, comprador);
     oferta2= new Oferta(45000, comprador);
     ofertaRecibir= new Oferta(15000, comprador);
     subasta1= new Subasta("5569871",40500,12000,video1);
     compra1= new Compra("311589", 20000, "tarjeta", foto1);
     compra2= new Compra("85631", 70000, "tarjeta", pinturaAgregar);
        galeriaConsola.getInventario().guardarEnBodega(foto1);
        galeriaConsola.getInventario().guardarEnBodega(video1);
        galeriaConsola.getInventario().guardarEnBodega(pintura1);
        galeriaConsola.getInventario().ponerEnDisponibles(foto1);
        galeriaConsola.getInventario().ponerEnDisponibles(video1);
        galeriaConsola.getInventario().ponerEnDisponibles(pintura1);
        galeriaConsola.getControladorUsuarios().agregarComprador(comprador);
        galeriaConsola.getControladorUsuarios().agregarPropietario(propietario);
        galeriaConsola.getControladorUsuarios().agregarEmpleado(admin);
        galeriaConsola.getControladorUsuarios().agregarEmpleado(cajero);
        galeriaConsola.getControladorUsuarios().agregarEmpleado(operador);
        subasta1.agregarOferta(oferta1);
        subasta1.agregarOferta(oferta2);
        galeriaConsola.agregarSubasta(subasta1);
        propietario.agregarPieza(foto1);
        propietario.agregarPieza(video1);
        propietario.agregarPieza(pintura1);
        propietario.pasarAPasadas(pintura1);
        galeriaConsola.agregarCompra(compra1, comprador);
        galeriaConsola.agregarCompra(compra2, comprador);
        comprador.agregarCompra(compra1);
        comprador.agregarCompra(compra2);
        galeriaConsola.setAdministradorGaleria(admin);
    }
	
	public void testcomprar() {
		List<Compra> l1= comprador.getmisCompras();
		comprador.realizarCompraFija(foto1);
		List<Compra> l2= comprador.getmisCompras();
		assertNotEquals(l1,l2);
		
	}
}
