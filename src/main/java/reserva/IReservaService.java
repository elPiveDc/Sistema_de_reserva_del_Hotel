package reserva;

import java.util.List;

public interface IReservaService {

    void crearReserva(Reserva reserva);

    void cancelarReserva(int id);

    List<Reserva> listarReservas();

    Reserva obtenerReserva(int id);
}
