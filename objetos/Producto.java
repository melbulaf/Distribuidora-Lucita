/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Paquete_de_fuentes;

/**
 *
 * @author Samuel
 */
public class Producto {
    String codigo;
    String nombre;
    double precio;
    int cantidad;
    String categoria;
    public Producto(String codigo, String nombre, String categoria, double precio, int cantidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }
}
