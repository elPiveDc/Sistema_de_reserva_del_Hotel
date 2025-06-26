package observer;

public class notificadoremail implements iobservador{

    private String email;

    public notificadoremail(String email) {
        this.email = email;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Enviando email a " + email + ": " + mensaje);
        // Aquí podría ir integración real con sistema de email
    }
}
