package consola;
import java.util.List;

import java.util.Scanner;

import logica.Galeria;
import logica.Compra;
import logica.Oferta;
import logica.Subasta;
import pieza.Autor;
import pieza.Escultura;
import pieza.Fotografia;
import pieza.Pieza;
import pieza.Pintura;
import pieza.Video;
import usuario.ControladorUsuarios;
import usuario.Administrador;
import usuario.Cajero;
import usuario.Comprador;
import usuario.Operador;
import usuario.Propietario;
import logica.Inventario;


public class consolaUsuario {

	public static Autor autor1= new Autor("dabibo", false);
    public static Pintura pinturaAgregar=new Pintura("mariachis", "1996", "Francia","25-20-2018", true, false, 60, 80, "Oleo");
    public static Pintura pintura1=new Pintura("koliflor", "1964", "Italia","05-12-2016", true, true, 70, 40, "Oleo");
    public static Video video1= new Video("muajau", "1999", "Korea","20-03-2024", true, false, "45", "360");
    public static Fotografia foto1= new Fotografia("el canil", "2003", "Colombia","28-11-2014", true, false, "12", "400");
    public static Fotografia foto2= new Fotografia("juriol", "2009", "Mexico","14-05-2015", false, false, "30", "190");
    public static Inventario inventario1= new Inventario();
    public static ControladorUsuarios controlador= new ControladorUsuarios();
    public static Galeria galeriaConsola = new Galeria(inventario1,controlador);
    
    public static Comprador comprador= new Comprador("amir", "12345", "amir","5975484526", 1000000, "547293");
    public static Propietario propietario= new Propietario("guikol", "15326", "guicol", "1589863153","153628");
    public static Administrador admin= new Administrador("fabian", "5896714", "Admin",galeriaConsola, "758964");
    public static Cajero cajero= new  Cajero("ozuna", "548963", "Cajero",galeriaConsola, "996354");
    public static Operador operador= new Operador("juanin", "156532", "Operador",galeriaConsola, "476597");
    public static Oferta oferta1= new Oferta(30000, comprador);
    public static Oferta oferta2= new Oferta(45000, comprador);
    public static Oferta ofertaRecibir= new Oferta(15000, comprador);
    public static Subasta subasta1= new Subasta("5569871",40500,12000,video1);
    public static Compra compra1= new Compra("311589", 20000, "tarjeta", foto1);
    public static Compra compra2= new Compra("85631", 70000, "tarjeta", pinturaAgregar);
    
    
    public static void setUp(){
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
        
        comprador.agregarCompra(compra1);
        comprador.agregarCompra(compra2);
        galeriaConsola.setAdministradorGaleria(admin);
    }
    
    
    public static void menuComprador() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Comprador ---");
            System.out.println("1. Realizar compra fija");
            System.out.println("2. Ver historial de compras");
            System.out.println("3. Salir");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	realizarCompraF();
                    break;
                case 2:
                    HistorialCompras();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                 
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }

    public static void realizarCompraF() {
    System.out.println("El resultado de la compra de la pieza "+ foto1.getTitulo()+ " fue: "); 
    System.out.println(comprador.realizarCompraFija(foto1)); 
    System.out.println("El resultado de la compra de la pieza "+ foto2.getTitulo()+ " fue: "); 
    System.out.println(comprador.realizarCompraFija(foto2));
    }

    private static void HistorialCompras() {
        
        List<Compra> Compras=comprador.getmisCompras();
        System.out.println("Tus compras son:");
        System.out.println(Compras.size());
        for (Compra compra : Compras) {
            
            System.out.println("Pieza:"+ compra.getPieza().getTitulo()+ " Valor Pagado: "+ compra.getValorPagado()); 
        }
            
    }
    
    public static void menuAdministradorGaleria() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Administrador de Galería ---");
            System.out.println("1. Registrar ingreso de pieza");
            System.out.println("2. Verificar comprador");
            System.out.println("3. Aumentar límite de crédito");
            System.out.println("4. Confirmar venta");
            System.out.println("5. Devolución de pieza");
            System.out.println("6. Verificar seriedad de oferta");
            System.out.println("7. Bloquear pieza");
            System.out.println("8. Desbloquear pieza");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarIngresoPieza();
                    break;
                case 2:
                    verificarComprador();
                    break;
                case 3:
                    aumentarLimite();
                    break;
                case 4:
                	confirmarVenta();
                    break;
                case 5:
                	devolucionPieza();
                    break;
                case 6:
                    verificarSeriedadOferta();
                    break;
                case 7:
                    bloquearPieza();
                    break;
                case 8:
                    desbloquearPieza();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void registrarIngresoPieza() {
        admin.registrarIngresoPieza(pinturaAgregar);
        System.out.println("Se agrego la pieza con la siguiente info a la bodega de la galeria:");
        System.out.println("Título: "+pinturaAgregar.getTitulo());
        System.out.println("Año Creación: "+pinturaAgregar.getAnioCreacion());
        System.out.println("Lugar Creación: "+pinturaAgregar.getLugarCreacion());
        
    }
    

    private static void verificarComprador() {
        
        
        System.out.println("El resultado de la verificación de la existencia del comprador con id 547293 fue: ");
        System.out.println(admin.verificarComprador("547293"));
        
    }
    private static void aumentarLimite() {
        
        System.out.println("El límite anterior del comprador 547293 es: "+comprador.getLimiteCompras());
        admin.aumentarLimite("547293", 200000);
        System.out.println("El nuevo límite del comprador 547293 es: "+comprador.getLimiteCompras());
        
    }

    private static void confirmarVenta() {
        
        admin.confirmarVenta(compra1,foto1,"547293");
        System.out.println("Se confirmo la venta de la pieza "+foto1.getTitulo()+" por un precio de "+compra1.getValorPagado());
    }

    private static void devolucionPieza() {
        
        System.out.println("El propietario tenia "+ propietario.getMisPiezasActuales().size()+" piezas.");
        admin.devolucionPieza(video1,"153628");
        List<Pieza> piezasActuales=propietario.getMisPiezasActuales();
        System.out.println("Se elimino la pieza: "+video1.getTitulo()+ " y al propietario le quedaron: "+ piezasActuales.size()+" piezas.");
        
            
        }

    private static void verificarSeriedadOferta() {
        
        Comprador comprador=galeriaConsola.getControladorUsuarios().obtenerComprador("547293");
        System.out.println("El limite de compras del comprador 547293 es "+comprador.getLimiteCompras());
        System.out.println("La oferta es de 20000");
        System.out.println(admin.verificarSeriedadOferta("547293", 20000));
    }

    private static void bloquearPieza() {
        admin.bloquearPieza("el canil");
        System.out.println("Se bloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+foto1.getTitulo());
        System.out.println("Esta bloqueada: "+foto1.isBloqueada());
        
    }

    private static void desbloquearPieza() {
        admin.desbloquearPieza("koliflor");
        System.out.println("Se desbloqueo la pieza con el siguiente título:");
        System.out.println("Título: "+pintura1.getTitulo());
        System.out.println("Esta bloqueada: "+pintura1.isBloqueada());
    }
    
    
    public static void menuPropietario() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Propietario ---");
            System.out.println("1. Ver mis piezas pasadas");
            System.out.println("2. Ver mis piezas actuales");
            System.out.println("3. Salir");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                	verMisPiezasPasadas();
                    break;
                case 2:
                    verMisPiezasActuales();
                    break;
                case 3:
                	System.out.println("Saliendo...");
                	break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 3);

        scanner.close();
    }



    private static void verMisPiezasPasadas()  {
        
        List<Pieza> piezasPasadas=propietario.getMisPiezasPasadas();
        System.out.println("Tus piezas pasadas son:");
        for (Pieza pieza : piezasPasadas) {
            System.out.println(pieza.getTitulo()); 
        }
            
        }
    private static void verMisPiezasActuales() {
        
        List<Pieza> piezasActuales=propietario.getMisPiezasActuales();
        System.out.println("Tus piezas actuales son:");
        for (Pieza pieza : piezasActuales) {
            System.out.println(pieza.getTitulo()); 
        }
            
        }
    
    public static void menuOperadorSubastas() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Operador de Subastas ---");
            System.out.println("1. Terminar subasta");
            System.out.println("2. Evaluar oferta");
            System.out.println("3. Recibir y registrar oferta");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    terminarSubasta();
                    break;
                case 2:
                	evaluarOferta();
                    break;
                case 3:
                	recibirRegistrarOferta();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }

    private static void terminarSubasta() {
        
        String respuesta=operador.terminarSubasta("5569871");
        System.out.println("El resultado de terminar la subasta 5569871 fue:");
        System.out.println(respuesta);
        
    }
    
    private static void evaluarOferta() {
        
        if (operador.evaluarOferta(ofertaRecibir,"5569871")==true){
            System.out.println("La oferta es correcta pues supera el valor inicial de la subasta");
        }
        else{
            System.out.println("La oferta no es correcta pues no supera el valor inicial de la subasta");
        }
    }

    private static void recibirRegistrarOferta() {
        
        
        String respuesta=operador.recibirRegistrarOferta(ofertaRecibir,"5569871");
        System.out.println("Se recibio la oferta con un valor de "+ofertaRecibir.getValorOferta()+" a la subasta con id 5569871 y el resultado de su registro es:");
        System.out.println(respuesta);
    }

    public static void menuCajero() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        setUp();
        do {
            System.out.println("\n--- Menú Cajero ---");
            System.out.println("1. Registrar pago");
            System.out.println("2. Entregar pieza");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una acción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    registrarPago();
                    break;
                case 2:
                    entregarPieza();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }
    private static void registrarPago() {
        
        System.out.println("El estado de registro del pago de la compra con id 311589 por el comprador con el id 547293 es: ");
        System.out.println(cajero.registrarPago(compra1, foto1,"547293"));
    }

    private static void entregarPieza() {
        
        String id=cajero.entregarPieza(foto2, "547293");
        System.out.println("La pieza con título "+ foto2.getTitulo()+ " fue entregada al comprador con id 547293");
        System.out.println("Ahora las piezas del comprador son: ");
        for (Pieza pieza :galeriaConsola.getControladorUsuarios().obtenerPropietario(id).getMisPiezasActuales() ) {
            System.out.println(pieza.getTitulo());
            
        }
    }
    
    
   
    
    

}
