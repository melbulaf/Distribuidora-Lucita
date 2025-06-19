package Proyect.Model;
/**
 *
 * @author juan
 */
public class RegistrarPedido {
    
    private Producto producto;
    private int cantidadVendida;
    private Cliente cliente;
    private String fecha;
    
    public RegistrarPedido(Producto producto, int cantidadVendida, Cliente cliente, String fecha) {
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.cliente = cliente;
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

    public Cliente getcliente() {
        return cliente;
    }
    
    
}
