package auth;

public class AuthFacade {

    private LoginService loginService = new LoginService();
    private Usuario usuarioActual;

    public boolean iniciarSesion(String usuario, String clave) {
        try {
            usuarioActual = loginService.login(usuario, clave);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getRolActual() {
        return usuarioActual != null ? usuarioActual.getRol() : null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
