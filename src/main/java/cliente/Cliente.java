package cliente;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String documento;
    private String correo;
    private String telefono;

    public Cliente(int id, String nombre, String apellido, String documento, String correo, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.correo = correo;
        this.telefono = telefono;
    }

    public Cliente(String nombre, String apellido, String documento, String correo, String telefono) {
        this(0, nombre, apellido, documento, correo, telefono);
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
    public String getCorreo() { return correo; }
    public String getTelefono() { return telefono; }
}
