package decoradores;

import bd.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DecoracionHabitacionDAO {

    public void insertar(DecoracionHabitacion decoracion) {
        String sql = "INSERT INTO decoracion_habitacion(id_habitacion, servicio_extra, costo_adicional) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getInstancia().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, decoracion.getIdHabitacion());
            stmt.setString(2, decoracion.getServicioExtra());
            stmt.setDouble(3, decoracion.getCostoAdicional());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DecoracionHabitacion> listarPorHabitacion(int idHabitacion) {
        List<DecoracionHabitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM decoracion_habitacion WHERE id_habitacion = ?";
        try (Connection conn = Conexion.getInstancia().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idHabitacion);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DecoracionHabitacion decoracion = new DecoracionHabitacion(
                    rs.getInt("id_decoracion"),
                    rs.getInt("id_habitacion"),
                    rs.getString("servicio_extra"),
                    rs.getDouble("costo_adicional")
                );
                lista.add(decoracion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
