package reserva;

import bd.Conexion;
import cliente.Cliente;
import factory.habitacionfactory;
import habitacion.Habitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private final Connection conn;

    public ReservaDAO() {
        this.conn = Conexion.getInstancia().getConnection();
    }

    public void guardar(Reserva reserva) {
        String sql = "INSERT INTO reservas (id, cliente_id, habitacion_numero, fecha_inicio, fecha_fin, precio_total) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getId());
            stmt.setInt(2, reserva.getCliente().getId());
            stmt.setInt(3, reserva.getHabitacion().getNumero());
            stmt.setDate(4, Date.valueOf(reserva.getFechaInicio()));
            stmt.setDate(5, Date.valueOf(reserva.getFechaFin()));
            stmt.setDouble(6, reserva.getPrecioTotal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM reservas WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No existe reserva con ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> listar() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.id, r.fecha_inicio, r.fecha_fin, r.precio_total, " +
                "c.id AS cliente_id, c.nombre AS cliente_nombre, c.apellido AS cliente_apellido, " +
                "c.documento AS cliente_documento, c.correo AS cliente_correo, " +
                "h.numero AS hab_numero, h.tipo AS hab_tipo " +
                "FROM reservas r " +
                "JOIN clientes c ON r.cliente_id = c.id " +
                "JOIN habitaciones h ON r.habitacion_numero = h.numero";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("cliente_id"),
                        rs.getString("cliente_nombre"),
                        rs.getString("cliente_apellido"),
                        rs.getString("cliente_documento"),
                        rs.getString("cliente_correo")
                );

                String tipoHab = rs.getString("hab_tipo");
                int numeroHab = rs.getInt("hab_numero");
                Habitacion habitacion = habitacionfactory.crearHabitacion(tipoHab, numeroHab);

                Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        cliente,
                        habitacion,
                        rs.getDate("fecha_inicio").toLocalDate(),
                        rs.getDate("fecha_fin").toLocalDate(),
                        rs.getDouble("precio_total")
                );

                lista.add(reserva);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Reserva obtener(int id) {
        String sql = "SELECT r.id, r.fecha_inicio, r.fecha_fin, r.precio_total, " +
                "c.id AS cliente_id, c.nombre AS cliente_nombre, c.apellido AS cliente_apellido, " +
                "c.documento AS cliente_documento, c.correo AS cliente_correo, " +
                "h.numero AS hab_numero, h.tipo AS hab_tipo " +
                "FROM reservas r " +
                "JOIN clientes c ON r.cliente_id = c.id " +
                "JOIN habitaciones h ON r.habitacion_numero = h.numero " +
                "WHERE r.id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getInt("cliente_id"),
                            rs.getString("cliente_nombre"),
                            rs.getString("cliente_apellido"),
                            rs.getString("cliente_documento"),
                            rs.getString("cliente_correo")
                    );

                    String tipoHab = rs.getString("hab_tipo");
                    int numeroHab = rs.getInt("hab_numero");
                    Habitacion habitacion = habitacionfactory.crearHabitacion(tipoHab, numeroHab);

                    return new Reserva(
                            rs.getInt("id"),
                            cliente,
                            habitacion,
                            rs.getDate("fecha_inicio").toLocalDate(),
                            rs.getDate("fecha_fin").toLocalDate(),
                            rs.getDouble("precio_total")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

