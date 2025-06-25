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

    public static ArrayList<Ruta> crearRutas() throws ClassNotFoundException {
        Class.forName("Proyect.Model.Producto"); //Objeto de ejemplo para inicializar la clase producto
                                                   //Y que se carguen los objetos.
        ArrayList<Ruta> rutas = new ArrayList<>();

        // --- RUTA LUNES ---
        ArrayList<Cliente> clientesLunes = new ArrayList<>();
        clientesLunes.add(new Cliente("Carlos Pérez", Arrays.asList(
            new Pedido("Pedido 1", "Bodega Norte", "09:00", false, Arrays.asList(
                Inventario.productos.get(4),
                Inventario.productos.get(5)
            )),
            new Pedido("Pedido 2", "Almacén Central", "10:30", true, Arrays.asList(
                Inventario.productos.get(6),
                Inventario.productos.get(7)
            ))
        )));
        clientesLunes.add(new Cliente("Empresa XYZ", Arrays.asList(
            new Pedido("Pedido 3", "Piso 2", "12:00", false, Arrays.asList(
                Inventario.productos.get(8)
            ))
        )));
        rutas.add(new Ruta("Lunes", "Norte", clientesLunes));

        // --- RUTA MARTES ---
        ArrayList<Cliente> clientesMartes = new ArrayList<>();
        clientesMartes.add(new Cliente("Tienda Los Andes", Arrays.asList(
            new Pedido("Pedido 1", "Depósito", "08:00", false, Arrays.asList(
                Inventario.productos.get(9),
                Inventario.productos.get(10)
            ))
        )));
        clientesMartes.add(new Cliente("Ferretería Sur", Arrays.asList(
            new Pedido("Pedido 2", "Principal", "11:00", true, Arrays.asList(
                Inventario.productos.get(11)
            ))
        )));
        rutas.add(new Ruta("Martes", "Sur", clientesMartes));

        // --- RUTA JUEVES ---
        ArrayList<Cliente> clientesJueves = new ArrayList<>();
        clientesJueves.add(new Cliente("Panadería Dulce Hogar", Arrays.asList(
            new Pedido("Pedido 1", "Mostrador", "10:00", false, Arrays.asList(
                Inventario.productos.get(12),
                Inventario.productos.get(13)
            ))
        )));
        rutas.add(new Ruta("Jueves", "Centro", clientesJueves));

        // --- RUTA VIERNES ---
        ArrayList<Cliente> clientesViernes = new ArrayList<>();
        clientesViernes.add(new Cliente("Heladería Fresca", Arrays.asList(
            new Pedido("Pedido 1", "Almacén", "09:30", false, Arrays.asList(
                Inventario.productos.get(14),
                Inventario.productos.get(15)
            ))
        )));
        clientesViernes.add(new Cliente("Supermercado La Oferta", Arrays.asList(
            new Pedido("Pedido 2", "Recepción", "13:00", true, Arrays.asList(
                Inventario.productos.get(16),
                Inventario.productos.get(17),
                Inventario.productos.get(18)
            ))
        )));
        rutas.add(new Ruta("Viernes", "Oeste", clientesViernes));

        return rutas;
    }
}
