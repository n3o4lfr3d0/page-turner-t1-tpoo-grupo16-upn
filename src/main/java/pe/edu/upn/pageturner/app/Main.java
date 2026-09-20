package pe.edu.upn.pageturner.app;

import pe.edu.upn.pageturner.datos.DatosPrueba;
import pe.edu.upn.pageturner.modelo.Cliente;
import pe.edu.upn.pageturner.modelo.Libro;
import pe.edu.upn.pageturner.modelo.Reserva;
import pe.edu.upn.pageturner.modelo.Venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Libro> libros = new ArrayList<>(DatosPrueba.crearLibros());
        List<Cliente> clientes = new ArrayList<>(DatosPrueba.crearClientes());
        List<Venta> ventas = new ArrayList<>(DatosPrueba.crearVentas(libros, clientes));
        List<Reserva> reservas = new ArrayList<>(DatosPrueba.crearReservas(libros, clientes));

        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1:
                    registrarCliente(clientes);
                    break;
                case 2:
                    registrarLibro(libros);
                    break;
                case 3:
                    registrarVenta(ventas, libros, clientes);
                    break;
                case 4:
                    registrarReserva(reservas, libros, clientes);
                    break;
                case 5:
                    imprimirClientes(clientes);
                    break;
                case 6:
                    imprimirLibros(libros);
                    break;
                case 7:
                    imprimirVentas(ventas);
                    break;
                case 8:
                    imprimirReservas(reservas);
                    break;
                case 0:
                    System.out.println("\nPrograma finalizado.");
                    break;
                default:
                    System.out.println("\nOpción no válida. Seleccione una opción del 0 al 8.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    //Es el menu del sistema que el usuario va interactuar para realizar las distintas operaciones
    private static void mostrarMenu() {

        System.out.println("\n=================================");
        System.out.println("Librería Académica 'PageTurner' ");
        System.out.println("=================================");
        System.out.println("1. Registrar cliente");
        System.out.println("2. Registrar libro");
        System.out.println("3. Registrar venta");
        System.out.println("4. Registrar reserva");
        System.out.println("5. Mostrar clientes");
        System.out.println("6. Mostrar libros");
        System.out.println("7. Mostrar ventas");
        System.out.println("8. Mostrar reservas");
        System.out.println("0. Salir");
        System.out.println("=================================");
    }

    public static void imprimirLibros(List<Libro> libros) {
        System.out.println("=== LIBROS ===");
        int l = 1;
        for (Libro libro : libros) {
            System.out.printf("%s | N°: %s | Autor: %s | ISBN: %s | Precio: S/ %.2f | Stock: %d%n",
                    l, libro.getTitulo(), libro.getAutor(), libro.getIsbn(), libro.getPrecio(), libro.getStock());
            l++;
        }
    }

    public static void imprimirClientes(List<Cliente> clientes) {
        System.out.println("\n=== CLIENTES ===");
        int c = 1;
        for (Cliente cliente : clientes) {
            System.out.printf("%s | N°: %s | DNI: %s | Correo: %s%n",
                    c, cliente.getNombre(), cliente.getDni(), cliente.getCorreo());
            c++;
        }
    }

    public static void imprimirVentas(List<Venta> ventas) {
        System.out.println("\n=== VENTAS ===");
        int v = 1;
        for (Venta venta : ventas) {
            System.out.printf("%s | N°: %s | Cliente: %s | Libro: %s | Cantidad: %d%n",
                    v, venta.getFecha(), venta.getCliente().getNombre(), venta.getLibro().getTitulo(), venta.getCantidad());
            v++;
        }
    }

    public static void imprimirReservas(List<Reserva> reservas) {
        System.out.println("\n=== RESERVAS ===");
        int r = 1;
        for (Reserva reserva : reservas) {
            System.out.printf("%s | N°: %s  | Cliente: %s | Libro: %s%n",
                    r, reserva.getFecha(), reserva.getCliente().getNombre(), reserva.getLibro().getTitulo());
            r++;
        }
    }

    public static void registrarCliente( List<Cliente> clientes) {
        System.out.println("\n=== REGISTRAR CLIENTE ===");
        while (true) {
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese DNI: ");
            String dni = scanner.nextLine();
            System.out.print("Ingrese correo: ");
            String correo = scanner.nextLine();
            Cliente cliente = new Cliente(nombre, dni, correo);
            String error = validarCliente(cliente);
            if (error != null) {
                System.out.println(error);
                System.out.println("Por favor, ingrese nuevamente los datos.\n");
                continue;
            }
            clientes.add(cliente);
            System.out.println("\nCliente registrado correctamente.");
            break;
        }
    }

    public static void registrarLibro(List<Libro> libros) {
        System.out.println("\n=== REGISTRAR LIBRO ===");
        while (true) {
            System.out.print("Ingrese título: ");
            String titulo = scanner.nextLine();
            System.out.print("Ingrese autor: ");
            String autor = scanner.nextLine();
            System.out.print("Ingrese ISBN: ");
            String isbn = scanner.nextLine();
            double precio = leerDouble("Ingrese precio: S/ " );

            int stock = leerEntero("Ingrese stock: ");
            Libro libro = new Libro( titulo, autor, isbn, precio, stock);
            String error = validarLibro(libro);
            if (error != null) {
                System.out.println(error);
                continue;
            }

            libros.add(libro);
            System.out.println("\nLibro registrado correctamente.");
            break;
        }
    }

    public static void registrarVenta(List<Venta> ventas, List<Libro> libros, List<Cliente> clientes) {
        System.out.println("\n=== REGISTRAR VENTA ===");
        imprimirClientes(clientes);
        int clienteIndex = leerEntero("Digita el número de cliente: ");

        if (clienteIndex < 1 || clienteIndex > clientes.size()) {
            System.out.println("Cliente no válido.");
            return;
        }

        imprimirLibros(libros);
        int libroIndex = leerEntero("Digita el número de libro: ");

        if (libroIndex < 1 || libroIndex > libros.size()) {
            System.out.println("libro no válido.");
            return;
        }

        Libro libro = libros.get(libroIndex - 1);
        int cantidad = leerEntero("Ingrese cantidad de libros: ");
        String error = validarVenta( libro, cantidad);

        if (error != null) {
            System.out.println(error);
            return;
        }

        Cliente cliente = clientes.get(clienteIndex - 1);
        Venta venta = new Venta( LocalDate.now(), cantidad, cliente, libro); // LocalDate.now() es para obtener la fecha del sistema
        ventas.add(venta);
        libro.setStock(libro.getStock() - cantidad);
        System.out.println("\nVenta registrada correctamente.");
        System.out.println("Stock restante: " + libro.getStock());
    }

    public static void registrarReserva(List<Reserva> reservas, List<Libro> libros, List<Cliente> clientes) {
        System.out.println("\n=== REGISTRAR RESERVA ===");
        imprimirClientes(clientes);
        int clienteIndex = leerEntero("Digita el número de cliente: ");
        if (clienteIndex < 1 || clienteIndex > clientes.size()) {
            System.out.println("Cliente no válido.");
            return;
        }
        imprimirLibros(libros);
        int libroIndex = leerEntero("Digita el número de libro: ");

        if (libroIndex < 1 || libroIndex > libros.size()) {
            System.out.println("Libro no válido.");
            return;
        }

        Libro libro = libros.get(libroIndex - 1);
        String error = validarReserva(libro);
        if (error != null) {
            System.out.println(error);
            return;
        }
        Cliente cliente = clientes.get(clienteIndex - 1);
        Reserva reserva = new Reserva(LocalDate.now(), cliente, libro); //LocalDate.now() es para tener la fecha del sistema
        reservas.add(reserva);
        System.out.println("\nReserva registrada correctamente." );
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número entero.");
            }
        }
    }

    public static String validarCliente(Cliente cliente) {
        if (cliente == null) {
            return "El cliente no puede ser nulo.";
        }

        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            return "Debe ingresar el nombre del cliente.";
        }

        if (!esTexto(cliente.getNombre())) {
            return "El nombre solo debe contener letras y espacios.";
        }

        if (cliente.getDni() == null || cliente.getDni().trim().isEmpty()) {
            return "Debe ingresar el DNI.";
        }

        if (cliente.getDni().length() != 8) {
            return "El DNI debe tener exactamente 8 dígitos.";
        }

        if (!esNumero(cliente.getDni())) {
            return "El DNI solo debe contener números.";
        }

        if (cliente.getCorreo() == null || cliente.getCorreo().trim().isEmpty()) {
            return "Debe ingresar el correo.";
        }

        if (!correoValido(cliente.getCorreo())) {
            return "Debe ingresar un correo válido. Ejemplo: usuario@correo.com";
        }

        return null;
    }

    public static String validarLibro(Libro libro) {

        if (libro == null) {
            return "El libro no puede ser nulo.";
        }

        if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            return "debe ingresar el título del libro.";
        }

        if (libro.getAutor() == null || libro.getAutor().trim().isEmpty()) {
            return "debe ingresar el autor.";
        }

        if (!esTexto(libro.getAutor())) {
            return "El autor solo debe contener letras y espacios.";
        }

        if (libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()) {
            return "debe ingresar el ISBN.";
        }

        if (libro.getIsbn().length() != 13) {
            return "el ISBN debe tener 13 caracteres.";
        }

        if (!esNumero(libro.getIsbn())) {
            return "el ISBN solo debe contener números.";
        }

        if (libro.getPrecio() <= 0) {
            return "el precio debe ser mayor que 0.";
        }

        if (libro.getStock() < 0) {
            return "el stock no puede ser negativo.";
        }

        return null;
    }

    public static String validarVenta(Libro libro, int cantidad) {
        if (libro == null) {
            return "no se encontró el libro.";
        }

        if (cantidad <= 0) {
            return "la cantidad debe ser mayor que 0.";
        }

        if (libro.getStock() < cantidad) {
            return "No disponemos de stock de libro. ";
        }

        return null;
    }

    public static String validarReserva(Libro libro) {
        if (libro == null) {
            return "No se encontró el libro.";
        }
        if (libro.getStock() <= 0) {
            return "Aviso: no hay stock disponible del libro \""
                    + libro.getTitulo()
                    + "\". No se puede realizar la reserva.";
        }
        return null;
    }

    public static boolean esNumero(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (!Character.isDigit(caracter)) {
                return false;
            }
        }
        return true;
    }

    public static boolean correoValido(String correo) {
        int posicionArroba = correo.indexOf("@");
        int posicionPunto = correo.lastIndexOf(".");
        if (posicionArroba <= 0) {
            return false;
        }
        if (posicionPunto <= posicionArroba + 1) {
            return false;
        }
        if (posicionPunto == correo.length() - 1) {
            return false;
        }
        return true;
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido.");
            }
        }
    }

    public static boolean esTexto(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char caracter = texto.charAt(i);
            if (!Character.isLetter(caracter) && caracter != ' ') {
                return false;
            }
        }
        return true;
    }
}
