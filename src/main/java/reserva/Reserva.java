package reserva;

import cliente.Cliente;
import habitacion.Habitacion;

import java.time.LocalDate;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String estado;
    private double totalCalculado;

    public Reserva(int id, Cliente cliente, Habitacion habitacion,
                   LocalDate fechaIngreso, LocalDate fechaSalida,
                   String estado, double totalCalculado) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.estado = estado;
        this.totalCalculado = totalCalculado;
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public Habitacion getHabitacion() { return habitacion; }
    public LocalDate getFechaIngreso() { return fechaIngreso; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public String getEstado() { return estado; }
    public double getTotalCalculado() { return totalCalculado; }
}
