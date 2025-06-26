package habitacion;

public abstract class Habitacion {
    protected int id;
    protected int numero;
    protected String tipo;
    protected double precioBase;
    protected String estado;

    public Habitacion(int id, int numero, String tipo, double precioBase, String estado) {
        this.id = id;
        this.numero = numero;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getNumero() { return numero; }
    public String getTipo() { return tipo; }
    public double getPrecioBase() { return precioBase; }
    public String getEstado() { return estado; }

    public abstract String getDescripcion();
    public abstract double getPrecioFinal();
}
