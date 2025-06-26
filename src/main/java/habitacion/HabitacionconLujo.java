package habitacion;

public class HabitacionconLujo extends Habitacion {

    public HabitacionconLujo(int numero) {
        super(numero, 150.0);
    }

    @Override
    public String getDescripcion() {
        return "Habitación de Lujo";
    }
}
