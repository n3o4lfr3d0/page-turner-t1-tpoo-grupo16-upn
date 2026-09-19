package pe.edu.upn.pageturner.datos;

import pe.edu.upn.pageturner.modelo.Cliente;
import pe.edu.upn.pageturner.modelo.Libro;
import pe.edu.upn.pageturner.modelo.Reserva;
import pe.edu.upn.pageturner.modelo.Venta;

import java.time.LocalDate;
import java.util.List;

public final class DatosPrueba {
    private DatosPrueba() {
    }

    public static List<Libro> crearLibros() {
        return List.of(
                new Libro("Técnicas de programación orientada a objetos", "Victor Alfredo Muguerza Capristán", "9788448198448", 89.90, 7),
                new Libro("Análisis de algoritmos y estrategias de programación", "Robinson Manuel Yañez Romero", "9780132847377", 125.50, 3),
                new Libro("Sistemas operativos", "Jesús Alberto Lluen Gamarra", "9780078022159", 149.90, 0)
        );
    }

    public static List<Cliente> crearClientes() {
        return List.of(
                new Cliente("Alfredo Soto Nolazco", "43594317", "n00539560@upn.pe"),
                new Cliente("Marco Antonio Zenon Aycachi Gálvez", "70831452", "n00531991@upn.pe")
        );
    }

    public static List<Venta> crearVentas(List<Libro> libros, List<Cliente> clientes) {
        return List.of(
                new Venta(LocalDate.of(2026, 9, 10), 1, clientes.get(0), libros.get(0)),
                new Venta(LocalDate.of(2026, 9, 12), 2, clientes.get(1), libros.get(1))
        );
    }

    public static List<Reserva> crearReservas(List<Libro> libros, List<Cliente> clientes) {
        return List.of(
                new Reserva(LocalDate.of(2026, 9, 15), clientes.get(0), libros.get(2)),
                new Reserva(LocalDate.of(2026, 9, 16), clientes.get(1), libros.get(2))
        );
    }
}
