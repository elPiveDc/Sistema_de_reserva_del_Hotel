package auth;

public class LoginService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

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
