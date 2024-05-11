package consola;

import java.util.Scanner;

import logica.Galeria;

public class shitHolder {
	/**
     test para prpoar los commits
	Scanner scanner = new Scanner(System.in);
    int opcionMenuPrincipal;

    System.out.println("Bienvenido a la Galería y Casa de Subastas");

    do {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Cargar Galería");
        System.out.println("2. Salvar Galería");
        System.out.println("3. Ingresar como Usuario");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        opcionMenuPrincipal = scanner.nextInt();

        switch (opcionMenuPrincipal) {
            case 1:
                Galeria galeria = PersistenciaGaleria.cargarGaleria();
                System.out.println("Se ha cargado la galeria: "+galeria.equals(galeria));
            case 2:
                consolaUsuario.salvar();
                break;
            case 3:
                ingresarComoUsuario(scanner);
                opcionMenuPrincipal = 0;
                break;
            case 0:
                System.out.println("Gracias por utilizar la Galería y Casa de Subastas. ¡Hasta luego!");
                scanner.close();
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    } while (opcionMenuPrincipal != 0 );

    scanner.close();
}

   private static void ingresarComoUsuario(Scanner scanner) {
        int opcionIngresarUsuario;
    
        System.out.println("\n--- ¿Cómo que tipo de usuario desea ingresar?---");
        System.out.println("1. Comprador");
        System.out.println("2. Propietario");
        System.out.println("3. Administrador de Galería");
        System.out.println("4. Operador de Subastas");
        System.out.println("5. Cajero");
        System.out.println("0. Volver al Menú Principal");
        System.out.print("Seleccione cómo desea ingresar: ");
        opcionIngresarUsuario = scanner.nextInt();
    
        switch (opcionIngresarUsuario) {
            case 1:
                consolaUsuario.menuComprador();
                break;
            case 2:
            	consolaUsuario.menuPropietario();
                break;
            case 3:
            	consolaUsuario.menuAdministradorGaleria();
                break;
            case 4:
            	consolaUsuario.menuOperadorSubastas();
                break;
            case 5:
            	consolaUsuario.menuCajero();
                break;
            case 0:
                System.out.println("Volviendo al Menú Principal...");
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
        System.out.println("Ha salido del sistema con exito!");
    }
	
     */	
	
}
