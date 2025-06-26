package auth;

public class LoginService {
    private UsuarioDAO usuarioDAO;

    public LoginService() {
        this.usuarioDAO = new UsuarioDAO(); // por defecto
    }

    // Constructor para inyectar DAO personalizado (mock o real)
    public LoginService(UsuarioDAO dao) {
        this.usuarioDAO = dao;
    }

    public Usuario login(String username, String password) throws Exception {
        Usuario u = usuarioDAO.buscarPorUsername(username);

        if (u == null) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!u.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta.");
        }

        return u;
    }
}