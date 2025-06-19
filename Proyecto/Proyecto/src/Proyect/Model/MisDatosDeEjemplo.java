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
import java.util.List;

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
                new Producto("Galletas", 10),
                new Producto("Jugos", 5)
            )),
            new Pedido("Pedido 2", "Almacén Central", "10:30", true, Arrays.asList(
                new Producto("Café", 3),
                new Producto("Azúcar", 2)
            ))
        )));
        clientesLunes.add(new Cliente("Empresa XYZ", Arrays.asList(
            new Pedido("Pedido 3", "Piso 2", "12:00", false, Arrays.asList(
                new Producto("Leche", 20)
            ))
        )));
        rutas.add(new Ruta("Lunes", "Ruta Norte", clientesLunes));

        // --- RUTA MARTES ---
        ArrayList<Cliente> clientesMartes = new ArrayList<>();
        clientesMartes.add(new Cliente("Tienda Los Andes", Arrays.asList(
            new Pedido("Pedido 1", "Depósito", "08:00", false, Arrays.asList(
                new Producto("Refrescos", 15),
                new Producto("Pan", 12)
            ))
        )));
        clientesMartes.add(new Cliente("Ferretería Sur", Arrays.asList(
            new Pedido("Pedido 2", "Principal", "11:00", true, Arrays.asList(
                new Producto("Clavos", 50)
            ))
        )));
        rutas.add(new Ruta("Martes", "Ruta Sur", clientesMartes));

        // --- RUTA JUEVES ---
        ArrayList<Cliente> clientesJueves = new ArrayList<>();
        clientesJueves.add(new Cliente("Panadería Dulce Hogar", Arrays.asList(
            new Pedido("Pedido 1", "Mostrador", "10:00", false, Arrays.asList(
                new Producto("Harina", 8),
                new Producto("Huevos", 24)
            ))
        )));
        rutas.add(new Ruta("Jueves", "Ruta Centro", clientesJueves));

        // --- RUTA VIERNES ---
        ArrayList<Cliente> clientesViernes = new ArrayList<>();
        clientesViernes.add(new Cliente("Heladería Fresca", Arrays.asList(
            new Pedido("Pedido 1", "Almacén", "09:30", false, Arrays.asList(
                new Producto("Helados", 30),
                new Producto("Conos", 60)
            ))
        )));
        clientesViernes.add(new Cliente("Supermercado La Oferta", Arrays.asList(
            new Pedido("Pedido 2", "Recepción", "13:00", true, Arrays.asList(
                new Producto("Arroz", 10),
                new Producto("Frijoles", 7),
                new Producto("Aceite", 4)
            ))
        )));
        rutas.add(new Ruta("Viernes", "Ruta Oeste", clientesViernes));

        return rutas;
    }

}