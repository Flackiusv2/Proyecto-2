package usuario;

public class Empleado extends Usuario {
    private String rol;
    private String id;

    
    public Empleado(String usuario, String contraseña, String rol, String id) {
        super(usuario, contraseña);
        this.rol = rol;
        this.id = id;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }
    public String getId() {
        return id;
    }
}
