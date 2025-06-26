package habitacion;

public class HabitacionconLujo extends Habitacion {
    public HabitacionconLujo(int id, int numero, double precioBase, String estado) {
        super(id, numero, "Lujo", precioBase, estado);
    }

    @Override
    public String getDescripcion() {
        return "Habitación de Lujo #" + numero;
    }

    @Override
    public double getPrecioFinal() {
        return precioBase * 1.2; // Ejemplo: recargo por lujo
    }
}
