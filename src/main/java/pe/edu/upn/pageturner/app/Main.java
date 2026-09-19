package pe.edu.upn.pageturner.app;

import pe.edu.upn.pageturner.datos.DatosPrueba;
import pe.edu.upn.pageturner.modelo.Cliente;
import pe.edu.upn.pageturner.modelo.Libro;
import pe.edu.upn.pageturner.modelo.Reserva;
import pe.edu.upn.pageturner.modelo.Venta;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Libro> libros = DatosPrueba.crearLibros();
        List<Cliente> clientes = DatosPrueba.crearClientes();
        List<Venta> ventas = DatosPrueba.crearVentas(libros, clientes);
        List<Reserva> reservas = DatosPrueba.crearReservas(libros, clientes);

        imprimirLibros(libros);
        imprimirClientes(clientes);
        imprimirVentas(ventas);
        imprimirReservas(reservas);
    }

    private static void imprimirLibros(List<Libro> libros) {
        System.out.println("=== LIBROS ===");
        for (Libro libro : libros) {
            System.out.printf("%s | Autor: %s | ISBN: %s | Precio: S/ %.2f | Stock: %d%n",
                    libro.getTitulo(), libro.getAutor(), libro.getIsbn(), libro.getPrecio(), libro.getStock());
        }
    }

    private static void imprimirClientes(List<Cliente> clientes) {
        System.out.println("\n=== CLIENTES ===");
        for (Cliente cliente : clientes) {
            System.out.printf("%s | DNI: %s | Correo: %s%n",
                    cliente.getNombre(), cliente.getDni(), cliente.getCorreo());
        }
    }

    private static void imprimirVentas(List<Venta> ventas) {
        System.out.println("\n=== VENTAS ===");
        for (Venta venta : ventas) {
            System.out.printf("%s | Cliente: %s | Libro: %s | Cantidad: %d%n",
                    venta.getFecha(), venta.getCliente().getNombre(), venta.getLibro().getTitulo(), venta.getCantidad());
        }
    }

    private static void imprimirReservas(List<Reserva> reservas) {
        System.out.println("\n=== RESERVAS ===");
        for (Reserva reserva : reservas) {
            System.out.printf("%s | Cliente: %s | Libro: %s%n",
                    reserva.getFecha(), reserva.getCliente().getNombre(), reserva.getLibro().getTitulo());
        }
    }
}
