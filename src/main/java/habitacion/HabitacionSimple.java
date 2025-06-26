package habitacion;

public class HabitacionSimple extends Habitacion {

    public HabitacionSimple(int numero) {
        super(numero, 50.0); // ejemplo de precio base
    }

    @Override
    public String getDescripcion() {
        return "Habitación Simple";
    }
}
