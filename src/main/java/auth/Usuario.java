package auth;

public class Usuario {
    private int id;
    private String username;
    private String password;
    private String rol; // ej: admin, recepcionista

    public Usuario(int id, String username, String password, String rol) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String username, String password) {
        this(0, username, password, null);
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }
}
