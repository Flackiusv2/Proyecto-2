package usuario;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Usuario {

	private String usuario;
	private String contraseña;
	public static Set<String> idsExistentes = new HashSet<>();
	
	public Usuario(String usuario, String contraseña) {
		this.usuario = usuario;
		this.contraseña = contraseña;
	}
	
	public boolean login(String Lusuario, String Lcontraseña) {
		if (Lusuario == usuario){
			if (Lcontraseña == contraseña){
				return true;
			}
		}
		return false;
		
	}

	public String getUsuario() {
		return usuario;
	} 
	public String getLogin() {
        return usuario;
    }

    public String getPassword() {
        return contraseña;
    }

    public void setLogin(String lusuario) {
        this.usuario = lusuario;
    }

    public void setPassword(String password) {
        this.contraseña = password;
    }

//Método para obtener un nuevo ID para el usuario
public static String obtenerNuevoID() {
        StringBuilder idGenerado = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int digito = random.nextInt(10); // Dígitos de 0 a 9
            idGenerado.append(digito);
        }

        return idGenerado.toString();
    }

    public static String generarIDUnico(Set<String> idsExistentes) {
        String id;
        do {
            id = obtenerNuevoID();
        } while (idsExistentes.contains(id));
        return id;
}
}
