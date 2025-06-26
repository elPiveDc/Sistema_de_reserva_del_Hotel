
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion implements iconexionbd {

    private static Conexion instancia;
    private Connection conexion;

    private Conexion() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Usar las claves del archivo db.properties
            this.conexion = DriverManager.getConnection(
                DBConfig.get("db.url"),
                DBConfig.getUser(),
                DBConfig.getPassword()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    @Override
    public Connection getConnection() {
        return conexion;
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
