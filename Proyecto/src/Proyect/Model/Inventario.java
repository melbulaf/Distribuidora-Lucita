/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Proyect.Model;
import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Inventario {
    
    public static ArrayList<Producto> productos;
    public static Inventario instancia;
    private ArrayList<RegistrarPedido> registrarPedidos = new ArrayList<>();
    
    public Inventario() {
        productos = new ArrayList<>();
    }
    
    public static Inventario getInstancia() {
        if (instancia == null) {
            instancia = new Inventario();
        }
        return instancia;
    }
    
    public ArrayList<Producto> obtenerProductos() {
        return productos;
    }
    
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
}        
