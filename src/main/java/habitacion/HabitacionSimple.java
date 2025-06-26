package habitacion;

public class HabitacionSimple extends Habitacion {
    public HabitacionSimple(int id, int numero, double precioBase, String estado) {
        super(id, numero, "Simple", precioBase, estado);
    }

    @Override
    public String getDescripcion() {
        return "Habitación Simple #" + numero;
    }

    @Override
    public double getPrecioFinal() {
        return precioBase;
    }
}
