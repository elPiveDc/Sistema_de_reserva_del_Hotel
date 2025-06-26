package habitacion;

public class Mantenimiento implements estadoHabitacion{
    @Override
    public String getEstado() {
        return "En Mantenimiento";
    }
}
