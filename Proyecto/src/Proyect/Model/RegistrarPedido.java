package Proyect.Model;

import java.util.ArrayList;

/**
 *
 * @author juan
 */

public class RegistrarPedido {
    
    private String  producto;
    private int cantidadVendida;
    private String NombreCliente;
    private String fecha;
    static ArrayList<RegistrarPedido> listaDePedidos = new ArrayList<>();
    
    public RegistrarPedido(String producto, int cantidadVendida, String cliente, String fecha) {
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.NombreCliente = cliente;
        this.fecha = fecha;
    }

    public String getProducto() {
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
    
    public static void pedidoRegistrado(String nombreProducto, int cantidadVendida, String nombreCliente, String fecha){
        
        RegistrarPedido pedido = new RegistrarPedido(nombreProducto,cantidadVendida, nombreCliente, fecha);
        listaDePedidos.add(pedido);
        System.out.println(listaDePedidos);
    }
    
    public static void pedidoNoRegistradoPorStock(Producto producto, int cantidadVendida, String nombreCliente, String fecha){
    }
    
    public static void pedidoNoRegistradoPorFaltaDeDatos(Producto producto, int cantidadVendida, String nombreCliente, String fecha){
    }
    
    
    
}
