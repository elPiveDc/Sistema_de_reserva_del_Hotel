package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgConexion implements iconexionbd{

    // 1. Instancia única (Singleton)
    private static PgConexion instancia;

    // 2. Conexión privada
    private Connection conexion;

    // 3. Constructor privado
    private PgConexion() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conexion = DriverManager.getConnection(
                    DBConfig.get("postgres.url"),
                    DBConfig.getUser(),
                    DBConfig.getPasword()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 4. Método estático para obtener la instancia única
    public static PgConexion getInstancia() {
        if (instancia == null) {
            instancia = new PgConexion(); // se crea una sola vez
        }
        return instancia;
    }

    // 5. Método para obtener la conexión
    @Override
    public Connection getConnection() {
        return conexion;
    }

    // 6. Método opcional para cerrar la conexión si es necesario
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
