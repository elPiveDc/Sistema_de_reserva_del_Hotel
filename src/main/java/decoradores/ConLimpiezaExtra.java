package decoradores;

import habitacion.Habitacion;

public class ConLimpiezaExtra extends HabitacionDecorator {

    public ConLimpiezaExtra(Habitacion habitacion) {
        super(habitacion);
    }

    @Override
    public String getDescripcion() {
        return habitacion.getDescripcion() + " + Limpieza Extra";
    }

    @Override
    public double getPrecioFinal() {
        return habitacion.getPrecioFinal() + 5.0;
    }
}
