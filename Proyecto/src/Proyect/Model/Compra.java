/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import Proyect.Model.Producto;

/**
 *
 * @author Rifter
 */
public class Compra {
    public Producto producto;
    public int cantidad;
    public double total;
    
    public Compra(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = (producto.precio * cantidad);
    }
    
}
