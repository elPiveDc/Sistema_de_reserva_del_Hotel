package reserva;

import bd.Conexion;
import cliente.Cliente;
import cliente.ClienteDAO;
import habitacion.Habitacion;
import habitacion.HabitacionDAO;
import habitacion.HabitacionSimple;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private final HabitacionDAO habitacionDAO = new HabitacionDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public void guardar(Reserva reserva) {
        String sql = "INSERT INTO reserva(id_cliente, id_habitacion, fecha_ingreso, fecha_salida, estado, total_calculado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = Conexion.getInstancia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, reserva.getCliente().getId());
            stmt.setInt(2, reserva.getHabitacion().getId());
            stmt.setDate(3, Date.valueOf(reserva.getFechaIngreso()));
            stmt.setDate(4, Date.valueOf(reserva.getFechaSalida()));
            stmt.setString(5, reserva.getEstado());
            stmt.setDouble(6, reserva.getTotalCalculado());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> listar() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (Connection conn = Conexion.getInstancia().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            // Pre-cargar los datos de todas las reservas antes de consultar otros DAOs
            List<Object[]> datosTemporales = new ArrayList<>();

            while (rs.next()) {
                Object[] fila = new Object[7];
                fila[0] = rs.getInt("id_reserva");
                fila[1] = rs.getInt("id_cliente");
                fila[2] = rs.getInt("id_habitacion");
                fila[3] = rs.getDate("fecha_ingreso").toLocalDate();
                fila[4] = rs.getDate("fecha_salida").toLocalDate();
                fila[5] = rs.getString("estado");
                fila[6] = rs.getDouble("total_calculado");
                datosTemporales.add(fila);
            }

            // Ahora que ya no usamos rs, podemos hacer las consultas con seguridad
            for (Object[] fila : datosTemporales) {
                int idReserva = (int) fila[0];
                int idCliente = (int) fila[1];
                int idHabitacion = (int) fila[2];
                LocalDate ingreso = (LocalDate) fila[3];
                LocalDate salida = (LocalDate) fila[4];
                String estado = (String) fila[5];
                double total = (double) fila[6];

                Cliente cliente = clienteDAO.obtenerPorId(idCliente);
                if (cliente == null) {
                    cliente = new Cliente(idCliente, "Desconocido", "", "", "", "");
                }

                Habitacion habitacion = habitacionDAO.obtenerPorNumero(idHabitacion);
                if (habitacion == null) {
                    habitacion = new HabitacionSimple(idHabitacion, -1, 0.0, "Eliminada");
                }

                Reserva r = new Reserva(idReserva, cliente, habitacion, ingreso, salida, estado, total);
                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }



    public void eliminar(int id) {
        String sql = "DELETE FROM reserva WHERE id_reserva = ?";
        try (PreparedStatement stmt = Conexion.getInstancia().getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Reserva obtener(int id) {
        String sql = "SELECT * FROM reserva WHERE id_reserva = ?";
        try (Connection conn = Conexion.getInstancia().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int idCliente = rs.getInt("id_cliente");
                int idHabitacion = rs.getInt("id_habitacion");

                Cliente cliente = clienteDAO.obtenerPorId(idCliente);
                if (cliente == null) {
                    cliente = new Cliente(idCliente, "Desconocido", "", "", "", "");
                }

                Habitacion habitacion = habitacionDAO.obtenerPorNumero(idHabitacion); // mismo método que usas en listar
                if (habitacion == null) {
                    habitacion = new HabitacionSimple(idHabitacion, -1, 0.0, "Eliminada");
                }

                return new Reserva(
                    rs.getInt("id_reserva"),
                    cliente,
                    habitacion,
                    rs.getDate("fecha_ingreso").toLocalDate(),
                    rs.getDate("fecha_salida").toLocalDate(),
                    rs.getString("estado"),
                    rs.getDouble("total_calculado")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
