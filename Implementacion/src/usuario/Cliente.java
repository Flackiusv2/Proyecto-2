package usuario;


public class Cliente extends Usuario {
    private String nombre;
    private String telefono;
    private String id;

    public Cliente(String login, String password, String nombre, String telefono, String id) {
        super(login, password);
        this.nombre = nombre;
        this.telefono = telefono;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getId() {
        return id;
    }
    
}

