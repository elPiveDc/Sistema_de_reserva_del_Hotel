package factory;

import habitacion.Habitacion;
import habitacion.HabitacionSimple;
import habitacion.HabitacionconLujo;

public class habitacionfactory {

    public static Habitacion crearHabitacion(String tipo, int numero) {
        switch (tipo.toLowerCase()) {
            case "simple":
                return new HabitacionSimple(numero);
            case "lujo":
                return new HabitacionconLujo(numero);
            default:
                throw new IllegalArgumentException("Tipo de habitación no válido: " + tipo);
        }
    }
}
