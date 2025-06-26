package main;

import auth.*;
import cliente.*;
import decoradores.ConDesayuno;
import decoradores.ConLimpiezaExtra;
import habitacion.*;
import reserva.*;

import java.time.LocalDate;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ClienteService clienteService;
    static ReservaServiceImpl reservaService;
    static HabitacionDAO habitacionDAO;

    public static void main(String[] args) {
        System.out.println("=== Hotel Luna de Media Noche Simulado ===");

        clienteService = new ClienteService(new ClienteDAO());
        reservaService = new ReservaServiceImpl(new ReservaDAO());
        habitacionDAO = new HabitacionDAO();

        // Login
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        LoginService loginService = new LoginService(usuarioDAO);
        AuthFacade auth = new AuthFacade(loginService);

        System.out.print("Usuario (correo): ");
        String u = sc.nextLine();
        System.out.print("Contraseña: ");
        String p = sc.nextLine();

        if (!auth.iniciarSesion(u, p)) {
            System.out.println("Login fallido");
            return;
        }

        System.out.println("Bienvenido, " + auth.getUsuarioActual().getNombre());
        menuPrincipal();
    }

    static void menuPrincipal() {
        while (true) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar Cliente");
            System.out.println("2. Crear Habitación");
            System.out.println("3. Crear Reserva");
            System.out.println("4. Ver Reservas");
            System.out.println("5. Salir");

            String op = sc.nextLine();
            switch (op) {
                case "1" -> registrarCliente();
                case "2" -> crearHabitacion();
                case "3" -> crearReserva();
                case "4" -> verReservas();
                case "5" -> {
                    System.out.println("Hasta luego!");
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    static void registrarCliente() {
        System.out.println("\n--- Registro de Cliente ---");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Documento: ");
        String documento = sc.nextLine();
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Teléfono: ");
        String telefono = sc.nextLine();

        Cliente c = new Cliente(nombre, apellido, documento, correo, telefono);
        try {
            clienteService.registrarCliente(c);
            System.out.println("Cliente registrado en la base de datos");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void crearHabitacion() {
        System.out.println("\n--- Crear Habitación ---");
        System.out.print("Número de habitación: ");
        int numero = Integer.parseInt(sc.nextLine());
        System.out.print("Tipo (simple/lujo): ");
        String tipo = sc.nextLine();
        System.out.print("Precio base: ");
        double precio = Double.parseDouble(sc.nextLine());
        System.out.print("Estado (Disponible/Ocupada/Mantenimiento): ");
        String estado = sc.nextLine();

        int id = new Random().nextInt(1000); // simulación de ID

        Habitacion h = null;

        switch (tipo.toLowerCase()) {
            case "simple" -> h = new HabitacionSimple(id, numero, precio, estado);
            case "lujo" -> h = new HabitacionconLujo(id, numero, precio, estado);
            default -> {
                System.out.println("Tipo no válido");
                return; // ahora sí es válido
            }
        }

        System.out.print("¿Agregar desayuno? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            h = new ConDesayuno(h);
        }
        System.out.print("¿Agregar limpieza extra? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            h = new ConLimpiezaExtra(h);
        }

        System.out.println("Habitación creada: " + h.getDescripcion());
        System.out.println("Precio final: $" + h.getPrecioFinal());

        habitacionDAO.guardar(h);
        System.out.println("Habitación registrada en la base de datos.");
    }

    static void crearReserva() {
        System.out.println("\n--- Crear Reserva ---");

        System.out.print("Documento del cliente: ");
        String doc = sc.nextLine();
        Cliente c = clienteService.buscarPorDocumento(doc);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.print("Número de habitación: ");
        int numero = Integer.parseInt(sc.nextLine());
        Habitacion h = habitacionDAO.obtenerPorNumero(numero);
        if (h == null) {
            System.out.println("Habitación no encontrada.");
            return;
        }

        System.out.print("¿Agregar desayuno? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            h = new ConDesayuno(h);
        }
        System.out.print("¿Agregar limpieza extra? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            h = new ConLimpiezaExtra(h);
        }

        System.out.print("Fecha inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(sc.nextLine());
        System.out.print("Fecha fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(sc.nextLine());

        double total = h.getPrecioFinal();
        Reserva r = new Reserva(new Random().nextInt(1000), c, h, inicio, fin, "Activa", total);
        reservaService.crearReserva(r);

        System.out.println("✅ Reserva creada correctamente.");
    }

    static void verReservas() {
        System.out.println("\n--- Reservas Registradas ---");
        List<Reserva> lista = reservaService.listarReservas();
        for (Reserva r : lista) {
            System.out.printf("ID: %d | Cliente: %s | Habitación: %s | Desde: %s hasta %s | Estado: %s | Total: $%.2f\n",
                    r.getId(),
                    r.getCliente().getNombre(),
                    r.getHabitacion().getDescripcion(),
                    r.getFechaIngreso(),
                    r.getFechaSalida(),
                    r.getEstado(),
                    r.getTotalCalculado());
        }
    }
}
