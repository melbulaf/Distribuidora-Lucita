/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;

/**
 *
 * @author MELANIE BULA FUENTES
 */


import Proyect.Model.Ruta;
import Proyect.Model.Producto;
import Proyect.Model.Pedido;
import Proyect.Model.Cliente;
import java.util.ArrayList;
import java.util.Arrays;

public class MisDatosDeEjemplo {

    public static void crearProductos() {
        Producto p1 = new Producto("Galleta Punto", "Galleta", 6500, 5525);
        Producto p2 = new Producto("Galleta Cuca", "Galleta", 6500, 5525);
        Producto p3 = new Producto("Bombombun", "Golosina", 10000, 8500);
        Producto p4 = new Producto("Rollo", "Pastel", 7000, 5950);
        Producto p5 = new Producto("Cigarrillos", "Miscelanea", 20000, 17000);
    }

    public static ArrayList<Ruta> crearRutas() {
        ArrayList<Ruta> rutas = new ArrayList<>();

        // --- RUTA LUNES ---
        ArrayList<Cliente> clientesLunes = new ArrayList<>();
        clientesLunes.add(new Cliente("Carlos Pérez", Arrays.asList(
            new Pedido("Pedido 1", "Bodega Norte", "09:00", false, Arrays.asList(
                new Producto(1001, "Galletas", "General", 5000, 4000, 10),
                new Producto(1002, "Jugos", "General", 3000, 2500, 5)
            )),
            new Pedido("Pedido 2", "Almacén Central", "10:30", true, Arrays.asList(
                new Producto(1003, "Café", "General", 8000, 7000, 3),
                new Producto(1004, "Azúcar", "General", 4500, 3500, 2)
            ))
        )));
        clientesLunes.add(new Cliente("Empresa XYZ", Arrays.asList(
            new Pedido("Pedido 3", "Piso 2", "12:00", false, Arrays.asList(
                new Producto(1005, "Leche", "General", 4000, 3500, 20)
            ))
        )));
        rutas.add(new Ruta("Lunes", "Ruta Norte", clientesLunes));

        // --- RUTA MARTES ---
        ArrayList<Cliente> clientesMartes = new ArrayList<>();
        clientesMartes.add(new Cliente("Tienda Los Andes", Arrays.asList(
            new Pedido("Pedido 1", "Depósito", "08:00", false, Arrays.asList(
                new Producto(1006, "Refrescos", "General", 2500, 2000, 15),
                new Producto(1007, "Pan", "General", 1500, 1000, 12)
            ))
        )));
        clientesMartes.add(new Cliente("Ferretería Sur", Arrays.asList(
            new Pedido("Pedido 2", "Principal", "11:00", true, Arrays.asList(
                new Producto(1008, "Clavos", "Ferretería", 500, 300, 50)
            ))
        )));
        rutas.add(new Ruta("Martes", "Ruta Sur", clientesMartes));

        // --- RUTA JUEVES ---
        ArrayList<Cliente> clientesJueves = new ArrayList<>();
        clientesJueves.add(new Cliente("Panadería Dulce Hogar", Arrays.asList(
            new Pedido("Pedido 1", "Mostrador", "10:00", false, Arrays.asList(
                new Producto(1009, "Harina", "Panadería", 1200, 1000, 8),
                new Producto(1010, "Huevos", "Panadería", 500, 400, 24)
            ))
        )));
        rutas.add(new Ruta("Jueves", "Ruta Centro", clientesJueves));

        // --- RUTA VIERNES ---
        ArrayList<Cliente> clientesViernes = new ArrayList<>();
        clientesViernes.add(new Cliente("Heladería Fresca", Arrays.asList(
            new Pedido("Pedido 1", "Almacén", "09:30", false, Arrays.asList(
                new Producto(1011, "Helados", "Heladería", 3500, 3000, 30),
                new Producto(1012, "Conos", "Heladería", 500, 400, 60)
            ))
        )));
        clientesViernes.add(new Cliente("Supermercado La Oferta", Arrays.asList(
            new Pedido("Pedido 2", "Recepción", "13:00", true, Arrays.asList(
                new Producto(1013, "Arroz", "Supermercado", 2000, 1500, 10),
                new Producto(1014, "Frijoles", "Supermercado", 1800, 1400, 7),
                new Producto(1015, "Aceite", "Supermercado", 8000, 7000, 4)
            ))
        )));
        rutas.add(new Ruta("Viernes", "Ruta Oeste", clientesViernes));

        return rutas;
    }
}
