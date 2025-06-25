package cliente;

import bd.PgConexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public void insertar(Cliente cliente) {
        String sql = "INSERT INTO clientes(nombre, apellido, documento, correo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = PgConexion.getInstancia().getConnection().prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getDocumento());
            stmt.setString(4, cliente.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> listarTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Statement stmt = PgConexion.getInstancia().getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("documento"),
                    rs.getString("correo")
                );
                lista.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Cliente buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM clientes WHERE documento = ?";
        try (PreparedStatement stmt = PgConexion.getInstancia().getConnection().prepareStatement(sql)) {
            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("documento"),
                    rs.getString("correo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
