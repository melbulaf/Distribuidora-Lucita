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
    private final int codigo;
    private static int contador = 1000;
    public String nombre;
    public double precio;
    public int cantidad;
    public String categoria;
    public Producto(String nombre, String categoria, double precio, int cantidad){
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.categoria = categoria;
    }
    public Producto(String nombre, int cantidad) {
    this.nombre = nombre;
    this.cantidad = cantidad;
    this.codigo = contador++;
    this.precio = 0.0;
    this.categoria = "";
}
    public Producto(String nombre, String categoria, double precio){
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 0;
        this.categoria = categoria;
    }
    
    public int getCodigo(){
        return this.codigo;
    }
}