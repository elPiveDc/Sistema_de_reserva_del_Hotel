package auth;

public class AuthFacade {
    private LoginService loginService;
    private Usuario usuarioActual;

    public AuthFacade(LoginService loginService) {
        this.loginService = loginService;
    }

    public boolean iniciarSesion(String usuario, String clave) {
        try {
            usuarioActual = loginService.login(usuario, clave);
            return true;
        } catch (Exception e) {
            System.out.println("Error de login: " + e.getMessage());
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
