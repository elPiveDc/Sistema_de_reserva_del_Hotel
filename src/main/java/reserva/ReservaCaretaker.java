package reserva;

import java.util.Stack;

public class ReservaCaretaker {
    private Stack<ReservaMemento> historial = new Stack<>();

    public void guardarEstado(Reserva reserva) {
        historial.push(new ReservaMemento(reserva));
    }

    public Reserva deshacer() {
        if (!historial.isEmpty()) {
            return historial.pop().getEstadoGuardado();
        }
        return null;
    }
}
