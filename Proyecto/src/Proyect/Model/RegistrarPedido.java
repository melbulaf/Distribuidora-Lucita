package Proyect.Model;
/**
 *
 * @author juan
 */
public class RegistrarPedido {
    private Producto producto;
    private int cantidadVendida;
    private String fecha;
    private Cliente cliente;

    public RegistrarPedido(Producto producto, int cantidadVendida, String fecha) {
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
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
