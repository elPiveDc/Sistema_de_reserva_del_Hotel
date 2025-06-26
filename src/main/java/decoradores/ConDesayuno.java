package decoradores;

import habitacion.Habitacion;

public class ConDesayuno extends HabitacionDecorator {

    public ConDesayuno(Habitacion habitacion) {
        super(habitacion);
    }

    @Override
    public String getDescripcion() {
        return habitacion.getDescripcion() + " + Desayuno";
    }

    @Override
    public double getPrecioFinal() {
        return habitacion.getPrecioFinal() + 10.0;
    }
}
