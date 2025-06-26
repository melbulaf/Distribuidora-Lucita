package Proyect.Model;

import static Proyect.Model.Compra.compras;
import java.util.ArrayList;
import Proyect.Model.Producto;
import Proyect.Model.Inventario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
    static{
        cargarCR();
    }
    

    
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
        if (producto.nombre.equalsIgnoreCase(nombreProducto)) {
            if (producto.cantidad >= cantidadVendida) {
                // Crear pedido
                RegistrarPedido nuevoPedido = new RegistrarPedido(producto, cantidadVendida, nombreCliente, fecha);
                listaDePedidos.add(nuevoPedido);

                // Actualizar cantidad disponible en inventario
                producto.setCantidad(producto.cantidad - cantidadVendida);

                System.out.println("Pedido registrado exitosamente:");
                guardarCR();
                return true;
            } else {
                System.out.println("Error: No hay suficiente stock para el producto: " + producto.nombre);
                JOptionPane.showMessageDialog(null, "cantidad insuficiente");
                    return false;
            }
        }
    }

    System.out.println("Error: Producto no encontrado: " + nombreProducto);
    JOptionPane.showMessageDialog(null, "Producto no encontrado");
        return false;
    
    }
    public static void guardarCR() {
    File archivoCompras = new File("src\\Proyect\\Controler\\BD\\RegistrarPedido.txt");
    try {
        PrintWriter salida = new PrintWriter(archivoCompras);
        for (int i = listaDePedidos.size() - 1; i >= 0; i--) {
            RegistrarPedido p = listaDePedidos.get(i);
            salida.println(
                p.getProducto().nombre + "," +
                p.getCantidadVendida() + "," +
                p.getcliente() + "," +
                p.getFecha()
            );
        }
        salida.close();
    } catch (FileNotFoundException ex) {
        ex.printStackTrace(System.out);
    }
}

    
    public static void cargarCR() {
    File archivoCompras = new File("src\\Proyecto\\Controler\\BD\\RegistrarPedido.txt");
    try {
        BufferedReader leer = new BufferedReader(new FileReader(archivoCompras));
        listaDePedidos.clear();  // Limpiamos la lista antes de cargar nuevos datos
        ArrayList<String> lineas = new ArrayList<>();
        String lectura;

        // Leer todas las líneas del archivo
        while ((lectura = leer.readLine()) != null) {
            lineas.add(lectura);
        }

        // Procesar las líneas al revés (última compra primero)
        for (int i = lineas.size() - 1; i >= 0; i--) {
            String[] partes = lineas.get(i).split(",");

            String nombreProducto = partes[0];                   // ahora es un nombre, no un ID
            int cantidadVendida = Integer.parseInt(partes[1]);   // cantidad
            String nombreCliente = partes[2];                    // cliente
            String fecha = partes[3];                            // fecha

            Producto productoEncontrado = null;

            for (Producto p : Inventario.productos) {
                if (p.nombre.equalsIgnoreCase(nombreProducto)) {
                    productoEncontrado = p;
                    break;
                }
            }

            if (productoEncontrado != null) {
                RegistrarPedido nuevoPedido = new RegistrarPedido(productoEncontrado, cantidadVendida, nombreCliente, fecha);
                listaDePedidos.add(nuevoPedido);
            } else {
                System.out.println("Producto con nombre \"" + nombreProducto + "\" no encontrado.");
            }
        }

        leer.close();
    } catch (FileNotFoundException ex) {
        ex.printStackTrace(System.out);
    } catch (IOException ex) {
        ex.printStackTrace(System.out);
    }
}

}
