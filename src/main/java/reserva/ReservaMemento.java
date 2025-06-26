package reserva;

public class ReservaMemento {
    private final Reserva estadoGuardado;

    public ReservaMemento(Reserva reserva) {
        this.estadoGuardado = new Reserva(
            reserva.getId(),
            reserva.getCliente(),
            reserva.getHabitacion(),
            reserva.getFechaIngreso(),
            reserva.getFechaSalida(),
            reserva.getEstado(),
            reserva.getTotalCalculado()
        );
    }

    public Reserva getEstadoGuardado() {
        return estadoGuardado;
    }
}
