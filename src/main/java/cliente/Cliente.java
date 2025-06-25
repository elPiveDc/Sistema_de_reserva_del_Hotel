package cliente;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String documento;
    private String correo;

    // Constructor
    public Cliente(int id, String nombre, String apellido, String documento, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.correo = correo;
    }

    // Constructor sin ID (para insertar)
    public Cliente(String nombre, String apellido, String documento, String correo) {
        this(0, nombre, apellido, documento, correo);
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getDocumento() { return documento; }
    public String getCorreo() { return correo; }
}
