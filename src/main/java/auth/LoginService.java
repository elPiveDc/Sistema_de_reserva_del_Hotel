package auth;

public class LoginService {

    private UsuarioDAO usuarioDAO;

    public LoginService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public LoginService(UsuarioDAO dao) {
        this.usuarioDAO = dao;
    }

    public Usuario login(String correo, String contrasena) throws Exception {
        Usuario u = usuarioDAO.buscarPorCorreo(correo);

        if (u == null) {
            throw new Exception("Usuario no encontrado.");
        }

        if (!u.getContrasena().equals(contrasena)) {
            throw new Exception("Contraseña incorrecta.");
        }

        return u;
    }
}
