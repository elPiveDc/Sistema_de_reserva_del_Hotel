package factory;

import habitacion.Habitacion;
import habitacion.HabitacionSimple;
import habitacion.HabitacionconLujo;

public class habitacionfactory {

    public static Habitacion crearHabitacion(String tipo, int id, int numero, double precio, String estado) {
        switch (tipo.toLowerCase()) {
            case "simple":
                return new HabitacionSimple(id, numero, precio, estado);
            case "lujo":
                return new HabitacionconLujo(id, numero, precio, estado);
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido: " + tipo);
        }
    }
}
