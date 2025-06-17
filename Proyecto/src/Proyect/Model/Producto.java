/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;

/**
 *
 * @author Samuel
 */
public class Producto {
    public String codigo;
    public String nombre;
    public double precio;
    public int cantidad;
    public String categoria;
    public Producto(String codigo, String nombre, String categoria, double precio, int cantidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }
    public Producto(String nombre, int cantidad) {
    this.nombre = nombre;
    this.cantidad = cantidad;
    this.codigo = "";
    this.precio = 0.0;
    this.categoria = "";
}
}
