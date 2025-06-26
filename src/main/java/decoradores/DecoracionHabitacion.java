package decoradores;

public class DecoracionHabitacion {
    private int id;
    private int idHabitacion;
    private String servicioExtra;
    private double costoAdicional;

    public DecoracionHabitacion(int id, int idHabitacion, String servicioExtra, double costoAdicional) {
        this.id = id;
        this.idHabitacion = idHabitacion;
        this.servicioExtra = servicioExtra;
        this.costoAdicional = costoAdicional;
    }

    public DecoracionHabitacion(int idHabitacion, String servicioExtra, double costoAdicional) {
        this(0, idHabitacion, servicioExtra, costoAdicional);
    }

    public int getId() { return id; }
    public int getIdHabitacion() { return idHabitacion; }
    public String getServicioExtra() { return servicioExtra; }
    public double getCostoAdicional() { return costoAdicional; }
}
