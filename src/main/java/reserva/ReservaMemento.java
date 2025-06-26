package reserva;

public class ReservaMemento {
    private final Reserva estadoGuardado;

    public ReservaMemento(Reserva reserva) {
        // Clonamos para evitar aliasing (simple copia aquí para ejemplo)
        this.estadoGuardado = new Reserva(
            reserva.getId(),
            reserva.getCliente(),
            reserva.getHabitacion(),
            reserva.getFechaInicio(),
            reserva.getFechaFin(),
            reserva.getPrecioTotal()
        );
    }

    public Reserva getEstadoGuardado() {
        return estadoGuardado;
    }
}
