package decoradores;

import habitacion.Habitacion;

public abstract class HabitacionDecorator extends Habitacion {

    protected Habitacion habitacion;

    public HabitacionDecorator(Habitacion habitacion) {
        super(habitacion.getId(), habitacion.getNumero(), habitacion.getTipo(), habitacion.getPrecioBase(), habitacion.getEstado());
        this.habitacion = habitacion;
    }

    @Override
    public abstract String getDescripcion();

    @Override
    public abstract double getPrecioFinal();
}
