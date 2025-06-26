package habitacion;

import bd.Conexion;
import java.sql.*;

public class HabitacionDAO {
    
    public void guardar(Habitacion h) {
        String sql = "INSERT INTO habitacion (numero, tipo, precio_base, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexion.getInstancia().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, h.getNumero());
            stmt.setString(2, h.getTipo()); // "Simple" o "Lujo"
            stmt.setDouble(3, h.getPrecioBase());
            stmt.setString(4, h.getEstado());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Habitacion obtenerPorNumero(int numero) {
        String sql = "SELECT * FROM habitacion WHERE numero = ?";

        try (Connection conn = Conexion.getInstancia().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numero);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id_habitacion");
                String tipo = rs.getString("tipo");
                double precio = rs.getDouble("precio_base");
                String estado = rs.getString("estado");

                return switch (tipo.toLowerCase()) {
                    case "simple" -> new HabitacionSimple(id, numero, precio, estado);
                    case "lujo" -> new HabitacionconLujo(id, numero, precio, estado);
                    default -> null;
                };
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
