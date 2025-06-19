/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import Proyect.Model.Inventario;

/**
 *
 * @author Samuel
 */
public class Producto {
    private final int codigo;
    private static int contador = 1000;
    public String nombre;
    public double precio;
    public double precioC;
    public int cantidad;
    public String categoria;
    public Producto(String nombre, String categoria, double precio,double precioC, int cantidad){
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = cantidad;
        this.categoria = categoria;
        Inventario.getInstancia().agregarProducto(this);
    }
    public Producto(String nombre, int cantidad) {
    this.nombre = nombre;
    this.cantidad = cantidad;
    this.codigo = contador++;
    this.precio = 0.0;
    this.precioC = 0.0;
    this.categoria = "";
    Inventario.getInstancia().agregarProducto(this);
}
    public Producto(String nombre, String categoria, double precio, double precioC){
        this.codigo = contador++;
        this.nombre = nombre;
        this.precio = precio;
        this.precioC = precioC;
        this.cantidad = 0;
        this.categoria = categoria;
        Inventario.getInstancia().agregarProducto(this);
    }
    
    public int getCodigo(){
        return this.codigo;
    }
    
    public String getNombre() {
        return nombre;
}

    public int getCantidad() {
        return cantidad;
}

    public void reducirCantidad(int cantidadVendida) {
        if (cantidadVendida <= cantidad) {
            cantidad -= cantidadVendida;
    }
}
    
    
    
}