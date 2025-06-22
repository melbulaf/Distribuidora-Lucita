package Proyect.Model;

import java.util.ArrayList;
import Proyect.Model.Producto;
import Proyect.Model.Inventario;
import javax.swing.JOptionPane;

/**
 *
 * @author juan
 */

public class RegistrarPedido {
    
    private Producto  producto;
    private int cantidadVendida;
    private String NombreCliente;
    private String fecha;
    static ArrayList<RegistrarPedido> listaDePedidos = new ArrayList<>();
    
    public RegistrarPedido(Producto producto, int cantidadVendida, String cliente, String fecha) {
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.NombreCliente = cliente;
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public String getFecha() {
        return fecha;
    }

    public String getcliente() {
        return NombreCliente;
    }
    
    public static boolean pedidoRegistrado(String nombreProducto, int cantidadVendida, String nombreCliente, String fecha) {
    ArrayList<Producto> productos = Inventario.instancia.obtenerProductos();

    for (Producto producto : productos) {
        if (producto.getNombre().equalsIgnoreCase(nombreProducto)) {
            if (producto.getCantidad() > cantidadVendida) {
                // Crear pedido
                RegistrarPedido nuevoPedido = new RegistrarPedido(producto, cantidadVendida, nombreCliente, fecha);
                listaDePedidos.add(nuevoPedido);

                // Actualizar cantidad disponible en inventario
                producto.setCantidad(producto.getCantidad() - cantidadVendida);

                System.out.println("Pedido registrado exitosamente:");
                return true;
            } else {
                System.out.println("Error: No hay suficiente stock para el producto: " + producto.getNombre());
                JOptionPane.showMessageDialog(null, "cantidad insuficiente");
                    return false;
            }
        }
    }

    System.out.println("Error: Producto no encontrado: " + nombreProducto);
    JOptionPane.showMessageDialog(null, "Producto no encontrado");
        return false;
    
    
}
}
