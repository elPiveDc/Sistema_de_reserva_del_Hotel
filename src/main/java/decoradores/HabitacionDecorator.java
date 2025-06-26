package decoradores;

import habitacion.Habitacion;

public abstract class HabitacionDecorator extends Habitacion {

    protected Habitacion habitacion;

    public HabitacionDecorator(Habitacion habitacion) {
        super(habitacion.getNumero(), habitacion.getPrecio());
        this.habitacion = habitacion;
    }

    @Override
    public abstract String getDescripcion();

    @Override
    public double getPrecio() {
        return habitacion.getPrecio();
    }
}
