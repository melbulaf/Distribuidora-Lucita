/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;

/**
 *
 * @author MELANIE BULA FUENTES
 */

import java.util.List;

public class Pedido {
    public String nombre;
    public String lugar;
    public String hora;
    public boolean urgente;
    public List<Producto> productos;

    public Pedido(String nombre, String lugar, String hora, boolean urgente, List<Producto> productos) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.hora = hora;
        this.urgente = urgente;
        this.productos = productos;
    }
}