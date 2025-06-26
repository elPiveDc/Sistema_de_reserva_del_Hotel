package observer;

public interface iobservable {

    void agregarObservador(iobservador observador);

    void eliminarObservador(iobservador observador);

    void notificarObservadores(String mensaje);
}
