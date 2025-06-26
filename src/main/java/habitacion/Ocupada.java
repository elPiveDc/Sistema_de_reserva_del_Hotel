package habitacion;

public class Ocupada implements estadoHabitacion{
    @Override
    public String getEstado() {
        return "Ocupada";
    }
}
