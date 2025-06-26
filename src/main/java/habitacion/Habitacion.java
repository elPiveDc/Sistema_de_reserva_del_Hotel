package habitacion;

public abstract class Habitacion {

    protected int numero;
    protected double precioBase;
    protected estadoHabitacion estado;

    public Habitacion(int numero, double precioBase) {
        this.numero = numero;
        this.precioBase = precioBase;
        this.estado = new Disponible(); // por defecto
    }

    public abstract String getDescripcion();

    public double getPrecio() {
        return precioBase;
    }

    public estadoHabitacion getEstado() {
        return estado;
    }

    public void setEstado(estadoHabitacion estado) {
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

}
