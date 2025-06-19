/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import static Proyect.Model.Inventario.productos;
import Proyect.Model.Producto;
import java.util.ArrayList;

/**
 *
 * @author Rifter
 */
public class Compra {
    public Producto producto;
    public int cantidad;
    public double total;
    public static ArrayList<Compra> compras = new ArrayList<>();
    
    public Compra(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
        this.total = (producto.precioC * cantidad);
        compras.add(this);
    }
    
}
