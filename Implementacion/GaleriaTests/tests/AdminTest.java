package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Compra;
import logica.Galeria;
import logica.Inventario;
import logica.Oferta;
import logica.Subasta;
import pieza.Autor;
import pieza.Fotografia;
import pieza.Pieza;
import pieza.Pintura;
import pieza.Video;
import usuario.Administrador;
import usuario.Cajero;
import usuario.Comprador;
import usuario.ControladorUsuarios;
import usuario.Operador;
import usuario.Propietario;

public class AdminTest {
	
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
	Autor autor1= new Autor("dabibo", false);
    Pintura pinturaAgregar=new Pintura("mariachis", "1996", "Francia","25-20-2018", true, false, 60, 80, "Oleo");
    Pintura pintura1=new Pintura("koliflor", "1964", "Italia","05-12-2016", true, true, 70, 40, "Oleo");
    Video video1= new Video("muajau", "1999", "Korea","20-03-2024", true, false, "45", "360");
    Fotografia foto1= new Fotografia("el canil", "2003", "Colombia","28-11-2014", true, false, "12", "400");
    Fotografia foto2= new Fotografia("juriol", "2009", "Mexico","14-05-2015", false, false, "30", "190");
    Inventario inventario1= new Inventario();
    ControladorUsuarios controlador= new ControladorUsuarios();
    Galeria galeriaConsola = new Galeria(inventario1,controlador);
    
    Comprador comprador= new Comprador("amir", "12345", "amir","5975484526", 1000000, "547293");
    Propietario propietario= new Propietario("guikol", "15326", "guicol", "1589863153","153628");
    Administrador admin= new Administrador("fabian", "5896714", "Admin",galeriaConsola, "758964");
    Cajero cajero= new  Cajero("ozuna", "548963", "Cajero",galeriaConsola, "996354");
    Operador operador= new Operador("juanin", "156532", "Operador",galeriaConsola, "476597");
    Oferta oferta1= new Oferta(30000, comprador);
    Oferta oferta2= new Oferta(45000, comprador);
    Oferta ofertaRecibir= new Oferta(15000, comprador);
    Subasta subasta1= new Subasta("5569871",40500,12000,video1);
    Compra compra1= new Compra("311589", 20000, "tarjeta", foto1);
    Compra compra2= new Compra("85631", 70000, "tarjeta", pinturaAgregar);
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
        galeriaConsola.agregarCompra(compra1);
        galeriaConsola.agregarCompra(compra2);
        comprador.agregarCompra(compra1);
        comprador.agregarCompra(compra2);
        galeriaConsola.setAdministradorGaleria(admin);
    }

	@Test
	public void testIngreso() {
		List<Pieza> lista_a = galeriaConsola.getInventario().getPiezasEnBodega();
		admin.registrarIngresoPieza(pinturaAgregar);
		List<Pieza> lista_b = galeriaConsola.getInventario().getPiezasEnBodega();
		lista_a.add(pinturaAgregar);
		assertEquals(lista_a, lista_b);
	}
	public void testVenta() {
		String respuesta = admin.confirmarVenta(compra1,foto1,"547293");
		assertEquals("Venta confirmada",respuesta);
		
	}
	public void testdevolucion() {
		List<Pieza> lista_a = admin.galeria.getInventario().getPiezasEnBodega();
		lista_a.add(video1);
		List<Pieza> mis_a = propietario.getMisPiezasActuales();
		admin.devolucionPieza(video1,"153628");
		List<Pieza> lista_b = admin.galeria.getInventario().getPiezasEnBodega();
		List<Pieza> mis_b = propietario.getMisPiezasActuales();
		assertEquals(lista_a, lista_b);
		assertNotEquals(mis_a,mis_b);
	}
	public void testAugLim() {
		int lim_1 = comprador.getLimiteCompras();
		admin.aumentarLimite("547293", 200000);
		int lim_2 = comprador.getLimiteCompras();
		assertNotEquals(lim_1,lim_2);
	}
	public void testVerificar() {
		assertEquals(admin.verificarComprador("547293"), true);
		
	}
	public void testSeriedad() {
		String respuesta = admin.verificarSeriedadOferta("547293", 20000);
		assertNotEquals(respuesta, "El comprador no está registrado");
	}
	
	public void testBloquear() {
		
		Pieza pieza = inventario1.buscarPieza("koliflor");
		boolean estado_1 = pieza.isBloqueada();
		admin.bloquearPieza("koliflor");
		boolean estado_2 = pieza.isBloqueada();
		assertNotEquals(estado_1,estado_2);
	}
	public void testDesBloq() {
		Pieza pieza = inventario1.buscarPieza("koliflor");
		boolean estado_1 = pieza.isBloqueada();
		admin.desbloquearPieza("koliflor");
		boolean estado_2 = pieza.isBloqueada();
		assertNotEquals(estado_1,estado_2);
	}
	
	
}
