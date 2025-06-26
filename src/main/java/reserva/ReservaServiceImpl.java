package reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaServiceImpl implements IReservaService {

    private ReservaDAO reservaDAO;

    public ReservaServiceImpl(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    public ReservaServiceImpl() {
        this.reservaDAO = new ReservaDAO();
    }

    @Override
    public void crearReserva(Reserva reserva) {
        reservaDAO.guardar(reserva);
        System.out.println("Reserva creada con ID: " + reserva.getId());
    }

    @Override
    public void cancelarReserva(int id) {
        reservaDAO.eliminar(id);
        System.out.println("Reserva cancelada ID: " + id);
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    @Override
    public Reserva obtenerReserva(int id) {
        return reservaDAO.obtener(id);
    }
}
