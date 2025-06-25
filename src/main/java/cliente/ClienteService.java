package cliente;

import java.util.List;

public class ClienteService {

    private ClienteDAO dao = new ClienteDAO();

    public void registrarCliente(Cliente cliente) {
        // Validación básica
        if (cliente.getNombre().isBlank() || cliente.getDocumento().isBlank()) {
            throw new IllegalArgumentException("Nombre y documento son obligatorios.");
        }

        // Verificar duplicados
        Cliente existente = dao.buscarPorDocumento(cliente.getDocumento());
        if (existente != null) {
            throw new IllegalArgumentException("El documento ya está registrado.");
        }

        dao.insertar(cliente);
    }

    public List<Cliente> listarClientes() {
        return dao.listarTodos();
    }

    public Cliente buscarPorDocumento(String documento) {
        return dao.buscarPorDocumento(documento);
    }
}
